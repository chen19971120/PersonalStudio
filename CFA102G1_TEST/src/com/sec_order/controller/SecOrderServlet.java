package com.sec_order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;

import com.coupon_information.model.Coupon_InformationDAO;
import com.coupon_information.model.Coupon_InformationService;
import com.coupon_information.model.Coupon_InformationVO;
import com.member.model.MemberVO;
import com.sec_order.model.SecOrderService;
import com.sec_order.model.SecOrderVO;
import com.sec_order_list.model.SecOrderListService;
import com.sec_order_list.model.SecOrderListVO;
import com.sec_product_inform.model.ProductInformService;
import com.sec_product_inform.model.ProductInformVO;

import oracle.net.aso.s;

public class SecOrderServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action : " + action);
		
		if("getOneForDiplay".equals(action)) {
			
			/****1.取得參數****/
			Integer so_no = new Integer(req.getParameter("so_no"));
			
			/****2.開始查詢****/
			
			/****查詢訂單****/
			SecOrderService secOrderSvc = new SecOrderService();
			SecOrderVO secOrderVO = secOrderSvc.getOneSecOrder(so_no);
			req.setAttribute("secOrderVO", secOrderVO);
			
			/****查詢訂單明細****/
			SecOrderListService secOrderListSvc = new SecOrderListService();
			List<SecOrderListVO> secOrderListVOs = secOrderListSvc.getAll()
																  .stream()
																  .filter(s -> s.getSo_no().equals(so_no)) /*篩選出此訂單的明細*/
																  .collect(Collectors.toList());
			req.setAttribute("secOrderListVOs", secOrderListVOs);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secOrder/listOneOrder.jsp");
			successView.forward(req, res);
			return;
			
		}
		
		if("getOneForUpdate".equals(action)) {
			/****1.取的參數****/
			Integer so_no = new Integer(req.getParameter("so_no"));
			
			/****2.開始查詢****/
			SecOrderService secOrderSvc = new SecOrderService();
			SecOrderVO secOrderVO = secOrderSvc.getOneSecOrder(so_no);
			req.setAttribute("secOrderVO", secOrderVO);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secOrder/updateSecOrder.jsp");
			successView.forward(req, res);
			return;
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			Integer so_no = new Integer(req.getParameter("so_no"));//訂單編號
			String so_sta = req.getParameter("so_sta"); //訂單狀態
			String so_pay_sta = req.getParameter("so_pay_sta"); //付款狀態
			String so_ship_sta = req.getParameter("so_ship_sta"); //出貨狀態
			String so_prodel = req.getParameter("so_prodel"); //運送方式
			String str = req.getParameter("so_deladrs"); //配送地址
			System.out.println("so_shipdate : " + req.getParameter("so_shipdate"));
						
			Timestamp  so_shipdate = Timestamp.valueOf(new StringBuilder(req.getParameter("so_shipdate"))
																			.append(" 00:00:00.123")
																			.toString());  //出貨日期
			
			/**********************************************************/

			Timestamp  so_purtime = Timestamp.valueOf(req.getParameter("so_purtime")); //訂購日期
			
			Integer mem_no =new Integer(req.getParameter("mem_no")); //會員編號
			Integer ci_no =new Integer(req.getParameter("ci_no")); //優惠券編號
			Integer so_totalpri =new Integer(req.getParameter("so_totalpri")); //訂單總價格 
			String so_paymthd = req.getParameter("so_paymthd"); //付款方式 
			Integer so_delcost =new Integer(req.getParameter("so_delcost")); //運費
			Integer so_discount_price =new Integer(req.getParameter("so_discount_price")); //訂單總價格 
						
			if(str.trim().length()==0) {
				errorMsgs.add("請輸入配送地址!!!");
			}
			
			SecOrderService secOrderSvc_1 = new SecOrderService();
			SecOrderVO secOrderVO = secOrderSvc_1.getOneSecOrder(so_no);
			secOrderVO.setSo_sta(so_sta);
			secOrderVO.setSo_pay_sta(so_pay_sta);
			secOrderVO.setSo_ship_sta(so_ship_sta);
			secOrderVO.setSo_prodel(so_prodel);
			secOrderVO.setSo_deladrs(str);
			req.setAttribute("secOrderVO", secOrderVO);
			
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secOrder/updateSecOrder.jsp");
				failView.forward(req, res);
				return;
			}
			
			String so_deladrs = str;
			
			/****2.更新資料****/
			SecOrderService secOrderSvc = new SecOrderService();
			secOrderSvc.updateSecOrder(so_no, so_purtime, mem_no, so_sta, so_pay_sta, so_ship_sta, ci_no, so_totalpri, 
										so_prodel, so_deladrs, so_paymthd, so_shipdate, so_delcost, so_discount_price);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secOrder/listAllSecOrder.jsp");
			successView.forward(req, res);
			return;
			
		}
		
		if("insert".equals(action)) {
			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//取得會員資訊
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			//取得購物車內的商品
			Vector<ProductInformVO> productInformList = (Vector<ProductInformVO>) session.getAttribute("shoppingCart_sec");
			//取得商品購買數量
			Map<Integer, Integer> Quamap = (Map<Integer, Integer>) session.getAttribute("Quamap");

			/****1.取得參數****/
			String so_prodel = req.getParameter("so_prodel"); //運送方式 >> 自取or宅配
			String so_deladrs = req.getParameter("so_deladrs");//配送地址 >>需要檢查此欄位!!!
			String so_paymthd = req.getParameter("so_paymthd");//付款方式 >> 匯款or信用卡
			Integer ci_no = new Integer(req.getParameter("ci_no")); // 優惠券編號 >> ci_no=1 表示沒有使用優惠券
			Integer discount = new Integer(req.getParameter("discount"));//取得折扣價
			
			if("宅配".equals(so_prodel)) {
				//檢查配送地址是否為空
				if(so_deladrs.trim().length()==0) {
					errorMsgs.add("請輸入配送地址!!");
				}
			}
			
			//檢查購物車內是否有商品
			if( !(productInformList!=null && productInformList.size()>0) ) {
				errorMsgs.add("請加入要購買的商品");
			}
			
			req.setAttribute("so_prodel", so_prodel);
			req.setAttribute("so_deladrs", so_deladrs);
			req.setAttribute("so_paymthd", so_paymthd);
			
			if(!errorMsgs.isEmpty()) {				
				RequestDispatcher failView =
						req.getRequestDispatcher("/front_end/secOrder/checkOut.jsp");
				failView.forward(req, res);
				return;
			}
										
			/****2.給予初始值****/
			Timestamp so_purtime = new Timestamp(System.currentTimeMillis());  //訂購時間 >>預設為下單當下時間
			Integer mem_no = new Integer(memberVO.getMem_no()); //會員編號
			String so_sta = "備貨中";	//訂單狀態 >> 預設為備貨中
			String so_pay_sta = "待付款"; //付款狀態 >> 先寫死!!
			String so_ship_sta = "未出貨"; //出貨狀態 >> 預設為未出貨
			Timestamp so_shipdate = null; //出貨日期 >> 預設為空
			Integer so_delcost = new Integer( so_prodel.equals("自取")?"0":"100" ); //配送費用 >> 自取運費為0，宅配100
			Integer so_totalpri = Quamap.get(999) + so_delcost; //訂單總價格 >> 商品費用+配送費用
			Integer so_discount_price = so_totalpri - discount;//訂單優惠價格 >> 訂單總價格-優惠券折扣費用
			
			/****3.將購物車內商品加入"商品明細"中****/
			List<SecOrderListVO> secOrderListVOs = new LinkedList<SecOrderListVO>();
			SecOrderListVO secOrderVO = null;
			for(ProductInformVO productInformVO :productInformList) {
				secOrderVO = new SecOrderListVO();
				Integer Spi_no = productInformVO.getSpi_no();//取得商品編號
				secOrderVO.setSpi_no(Spi_no); //加入商品編號
				secOrderVO.setSol_proamot(Quamap.get(Spi_no));//加入商品數量
				
				//計算商品明細總價(商品價格 * 購買數量)
				Integer sol_pri = productInformVO.getSpi_pri() * Quamap.get(Spi_no);
				secOrderVO.setSol_pri(sol_pri);
				secOrderListVOs.add(secOrderVO);
			}
			
			try {
				/****4.開始新增商品訂單、商品明細****/
				SecOrderService secOrderSvc = new SecOrderService();
				SecOrderVO secOrderVO2 = null;
				secOrderVO2 = secOrderSvc.insertSecOrderWithList(so_purtime, mem_no, so_sta, so_pay_sta, so_ship_sta, ci_no, 
											so_totalpri, so_prodel, so_deladrs, so_paymthd, so_shipdate, 
											so_delcost, so_discount_price, secOrderListVOs);
				
				req.setAttribute("secOrderVO", secOrderVO2);
							
			} catch (Exception e) {
				errorMsgs.add("結帳失敗");
				RequestDispatcher failView =
						req.getRequestDispatcher("/front_end/secOrder/checkOut.jsp");
				failView.forward(req, res);
				return;
			}finally {
				//結帳完成，更新商品庫存與商品狀態。
				for(ProductInformVO productInformVO : productInformList) {
					int newStock = productInformVO.getSpi_stock() - Quamap.get(productInformVO.getSpi_no());
					productInformVO.setSpi_stock(new Integer(newStock));
					
					//檢查商品庫存為0，則下架
					if(newStock == 0) {
						productInformVO.setSpi_sta("下架");
					}
					
					//更新資料庫
					ProductInformService productInformSvc = new ProductInformService();
					productInformSvc.updateProductInform(
							productInformVO.getSpi_no(), productInformVO.getSpi_name(), productInformVO.getSpc_no(), 
							productInformVO.getSpi_content(), productInformVO.getSpi_pri(), productInformVO.getSpi_stock(), 
							productInformVO.getSpi_sta());
				}
				
				//結帳完成，清空購物車內商品
				productInformList.removeAllElements();
				Quamap.clear();
				
				session.setAttribute("Quamap", Quamap);
				session.setAttribute("shoppingCart_sec", productInformList);
				
				String str = null;
				if(so_paymthd.equals("信用卡")) {
					str = "/front_end/secOrder/creditCardPay.jsp";
				}else {
					str = "/front_end/secOrder/transferPay.jsp";
				}
				//轉至付款頁面
				RequestDispatcher successView = 
						req.getRequestDispatcher(str);
				successView.forward(req, res);
			}
			return;
		}
		
		if("confirmPay".equals(action)) {
			/*預設皆為付款成功*/
			
			/****1.取得訂單編號****/
			Integer so_no = new Integer(req.getParameter("so_no"));
			
			/****2.查詢訂單****/
			SecOrderService secOrderSvc = new SecOrderService();
			SecOrderVO secOrderVO = secOrderSvc.getOneSecOrder(so_no);
			
			String str = null;
			if( !secOrderVO.getSo_sta().equals("取消訂單") ) {
				
				/****3.修改訂單狀態 (待付款更改為已付款)****/
				secOrderVO.setSo_pay_sta("已付款");
				
				/****4.更新訂單****/
				secOrderSvc.updateSecOrder( secOrderVO.getSo_no(),		secOrderVO.getSo_purtime(),	 secOrderVO.getMem_no(), 
											secOrderVO.getSo_sta(),		secOrderVO.getSo_pay_sta(),	 secOrderVO.getSo_ship_sta(), 
											secOrderVO.getCi_no(),		secOrderVO.getSo_totalpri(), secOrderVO.getSo_prodel(), 
											secOrderVO.getSo_deladrs(),	secOrderVO.getSo_paymthd(),  secOrderVO.getSo_shipdate(), 
											secOrderVO.getSo_delcost(),	secOrderVO.getSo_discount_price() );
				/****5.設置付款成功頁面路徑****/
				str = "/front_end/secOrder/paymentSuccess.jsp";
			}else {
				//訂單狀態為"取消訂單"則無法付款
				str = "/front_end/secOrder/paymentFail.jsp";
			}
			
			
			/****6.頁面轉向****/
			req.setAttribute("secOrderVO", secOrderVO);

			RequestDispatcher view = 
					req.getRequestDispatcher(str);
			view.forward(req, res);
			return;
		}
		
		//檢查購物車內商品與優惠券
		if("checkCart".equals(action)) {
			HttpSession session = req.getSession();
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****0.檢查是否登入會員****/
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			if(memberVO==null) {
				errorMsgs.add("結帳前請先登入會員");
				RequestDispatcher failView =
						req.getRequestDispatcher("/front_end/secProductInfo/cart.jsp");
				failView.forward(req, res);
				return;
			}
			
			/****1.檢查購物車****/
			//取得購物車內的商品
			Vector<ProductInformVO> productInformList = (Vector<ProductInformVO>) session.getAttribute("shoppingCart_sec");
			
			//檢查購物車內是否有商品
			if( !(productInformList!=null && productInformList.size()>0) ) {
				errorMsgs.add("請加入要購買的商品");
			}
			
			if(!errorMsgs.isEmpty()) {				
				RequestDispatcher failView =
						req.getRequestDispatcher("/front_end/secProductInfo/cart.jsp");
				failView.forward(req, res);
				return;
			}
			
			/****2.檢查優惠券****/
			//取得優惠券編碼
			String ci_code = req.getParameter("ci_code");
			
			//檢查優惠券
			if(ci_code.trim().length()!=0) {
				//確認編碼是否存在
				Coupon_InformationService coupon_InformationSvc = new Coupon_InformationService();
				List<Coupon_InformationVO> list = coupon_InformationSvc.getAll()
																		.stream()
																		.filter(c -> c.getCi_code().equals(ci_code))
																		.collect(Collectors.toList());
				if(list.size()==0) {
					errorMsgs.add("您輸入的優惠券無效!");
				}
				
				if(!errorMsgs.isEmpty()) {				
					RequestDispatcher failView =
							req.getRequestDispatcher("/front_end/secProductInfo/cart.jsp");
					failView.forward(req, res);
					return;
				}
				
				//檢查優惠券時效
				Coupon_InformationVO coupon_VO = list.get(0);
				
				Timestamp currentTime = new Timestamp(System.currentTimeMillis()); //取得當前時間
				Timestamp start_time = coupon_VO.getCi_start_time();
				Timestamp end_time =coupon_VO.getCi_end_time();
				if( !(currentTime.compareTo(start_time)>0 && currentTime.compareTo(end_time)<0) ) {
					errorMsgs.add("您輸入的優惠券無效!");
				}
											
				if(!errorMsgs.isEmpty()) {				
					RequestDispatcher failView =
							req.getRequestDispatcher("/front_end/secProductInfo/cart.jsp");
					failView.forward(req, res);
					return;
				}
				
				//取的優惠券編碼與折扣金額
				Integer ci_no = coupon_VO.getCi_no();
				Integer discount = coupon_VO.getDiscount();
				
				req.setAttribute("ci_no", ci_no);
				req.setAttribute("discount", discount);
			}
			
			/****3.轉向至結帳頁面****/
			RequestDispatcher successView =
					req.getRequestDispatcher("/front_end/secOrder/checkOut.jsp");
			successView.forward(req, res);
			return;			
		}
		
		
		if("findByPK".equals(action)) {
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String so_no_str = req.getParameter("so_no");
			if(so_no_str.trim().length()==0) {
				errorMsgs.add("請輸入訂單編號!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secOrder/secOrder_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			Integer so_no = null;
			try {
				so_no = new Integer(so_no_str);
			} catch (Exception e) {
				errorMsgs.add("請輸入數字!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secOrder/secOrder_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			
			/****2.開始查詢****/
			SecOrderService secOrderSvc = new SecOrderService();
			List<SecOrderVO> order_list = new LinkedList<SecOrderVO>();
			SecOrderVO secOrderVO = secOrderSvc.getOneSecOrder(so_no);
			
			order_list.add(secOrderVO);
		
//			session.setAttribute("order_list_seach", order_list);
			req.setAttribute("order_list_seach", order_list);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secOrder/afterSeach.jsp");
			successView.forward(req, res);
			return;
		}
		
		//依會員編號查詢
		if("findByMemNO".equals(action)) {
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String mem_no_str = req.getParameter("mem_no");
			if(mem_no_str.trim().length()==0) {
				errorMsgs.add("請輸入會員編號!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secOrder/secOrder_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			Integer mem_no = null;
			try {
				mem_no = new Integer(mem_no_str);
			} catch (Exception e) {
				errorMsgs.add("請輸入數字!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secOrder/secOrder_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			/****2.開始查詢****/
			SecOrderService secOrderSvc = new SecOrderService();
			List<SecOrderVO> order_list = secOrderSvc.getOneSecOrderByMem(mem_no);
			
//			session.setAttribute("order_list_seach", order_list);
			req.setAttribute("order_list_seach", order_list);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secOrder/afterSeach.jsp");
			successView.forward(req, res);
			return;
		}
		
		//依訂單狀態查詢
		if("findBySoSta".equals(action)) {
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String so_sta = req.getParameter("so_sta");

			/****2.開始查詢****/
			SecOrderService secOrderSvc = new SecOrderService();
			List<SecOrderVO> order_list = secOrderSvc.getAll()
													 .stream()
													 .filter(s -> s.getSo_sta().equals(so_sta))
													 .collect(Collectors.toList());
			
//			session.setAttribute("order_list_seach", order_list);
			req.setAttribute("order_list_seach", order_list);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secOrder/afterSeach.jsp");
			successView.forward(req, res);
			return;
		}
		
		//依付款狀態查詢
		if("findBySoPaySta".equals(action)) {
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String so_pay_sta = req.getParameter("so_pay_sta");

			/****2.開始查詢****/
			SecOrderService secOrderSvc = new SecOrderService();
			List<SecOrderVO> order_list = secOrderSvc.getAll()
													 .stream()
													 .filter(s -> s.getSo_pay_sta().equals(so_pay_sta))
													 .collect(Collectors.toList());
			
//			session.setAttribute("order_list_seach", order_list);
			req.setAttribute("order_list_seach", order_list);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secOrder/afterSeach.jsp");
			successView.forward(req, res);
			return;
		}
		
		//依出貨狀態查詢
		if("findBySoShipSta".equals(action)) {
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String so_ship_sta = req.getParameter("so_ship_sta");
			/****2.開始查詢****/
			SecOrderService secOrderSvc = new SecOrderService();
			List<SecOrderVO> order_list = secOrderSvc.getAll()
													 .stream()
													 .filter(s -> s.getSo_ship_sta().equals(so_ship_sta))
													 .collect(Collectors.toList());
			
//			session.setAttribute("order_list_seach", order_list);
			req.setAttribute("order_list_seach", order_list);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secOrder/afterSeach.jsp");
			successView.forward(req, res);
			return;
		}
		
		//前台會員中心，顯示訂單詳情
		if("front_getOneForDiplay".equals(action)) {
			
			/****1.取得參數****/
			Integer so_no = new Integer(req.getParameter("so_no"));
			
			/****2.開始查詢****/
			
			/****查詢訂單****/
			SecOrderService secOrderSvc = new SecOrderService();
			SecOrderVO secOrderVO = secOrderSvc.getOneSecOrder(so_no);
			req.setAttribute("secOrderVO", secOrderVO);
			
			/****查詢訂單明細****/
			SecOrderListService secOrderListSvc = new SecOrderListService();
			List<SecOrderListVO> secOrderListVOs = secOrderListSvc.getAll()
																  .stream()
																  .filter(s -> s.getSo_no().equals(so_no)) /*篩選出此訂單的明細*/
																  .collect(Collectors.toList());
			req.setAttribute("secOrderListVOs", secOrderListVOs);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/front_end/secOrder/listOneOrder.jsp");
			successView.forward(req, res);
			return;
			
		}
		
		//前台會員中心取消訂單
		if("front_cancelOrder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			Integer so_no = new Integer(req.getParameter("so_no"));
			
			/****2.開始查詢 查詢訂單****/
			SecOrderService secOrderSvc = new SecOrderService();
			SecOrderVO secOrderVO = secOrderSvc.getOneSecOrder(so_no);
			
			/****3.檢查訂單狀態****/
			if("已付款".equals(secOrderVO.getSo_pay_sta())) {
				errorMsgs.add("此訂單已付款，無法取消!!");
			}else {
				/****4.更新訂單狀態為"取消訂單"****/
				secOrderVO.setSo_sta("取消訂單");
				
				secOrderSvc.updateSecOrder(
						secOrderVO.getSo_no(), secOrderVO.getSo_purtime(), secOrderVO.getMem_no(), secOrderVO.getSo_sta(), 
						secOrderVO.getSo_pay_sta(), secOrderVO.getSo_ship_sta(), secOrderVO.getCi_no(), secOrderVO.getSo_totalpri(), 
						secOrderVO.getSo_prodel(), secOrderVO.getSo_deladrs(), secOrderVO.getSo_paymthd(), secOrderVO.getSo_shipdate(), 
						secOrderVO.getSo_delcost(), secOrderVO.getSo_discount_price());
			}
			/****5.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/front_end/secOrder/MemberCentreSecOrder.jsp");
			successView.forward(req, res);
			return;
			
			
		}
		
		//前台會員中心付款
		if("Payment".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			Integer so_no = new Integer(req.getParameter("so_no"));
			
			/****2.取得VO****/
			SecOrderService secOrderSvc = new SecOrderService();
			SecOrderVO secOrderVO =  secOrderSvc.getOneSecOrder(so_no);
			
			/****3.確認訂單狀態****/
			if(secOrderVO.getSo_sta().equals("取消訂單")) {
				errorMsgs.add("此訂單已取消， 請重新下標!!");
			}else if(secOrderVO.getSo_pay_sta().equals("已付款")) {
				errorMsgs.add("此訂單已付款!!");
			}

			req.setAttribute("secOrderVO", secOrderVO);
			
			/****3.設置轉向頁面****/
			String str = null;
			if(secOrderVO.getSo_paymthd().equals("信用卡")) {
				str = "/front_end/secOrder/creditCardPay.jsp";
			}else {
				str = "/front_end/secOrder/transferPay.jsp";
			}
			//轉至付款頁面
			RequestDispatcher successView = 
					req.getRequestDispatcher(str);
			successView.forward(req, res);
			return;
		}
		
		//複合查詢
		if("compoundQuery".equals(action)) {			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);	
						
			/****1.取得參數****/
			String so_no_str = req.getParameter("so_no");
			Integer so_no = null;
			if(so_no_str.trim().length() != 0) {
				try {
					so_no = new Integer(req.getParameter("so_no"));
				} catch (NumberFormatException e) {
					errorMsgs.add("訂單編號請輸入數字");
				}
			}
			
			String mem_no_str = req.getParameter("mem_no");
			Integer mem_no = null;
			if(mem_no_str.trim().length() != 0) {
				try {
					mem_no = new Integer(req.getParameter("mem_no"));
				} catch (NumberFormatException e) {
					errorMsgs.add("會員編號請輸入數字");
				}
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secOrder/secOrder_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			String so_sta = req.getParameter("so_sta");
			String so_pay_sta = req.getParameter("so_pay_sta");
			String so_ship_sta = req.getParameter("so_ship_sta");
			
			Map<String, String> condititon = new HashMap<String, String>();
			condititon.put("so_no_str", so_no_str);
			condititon.put("mem_no_str", mem_no_str);
			condititon.put("so_sta", so_sta);
			condititon.put("so_pay_sta", so_pay_sta);
			condititon.put("so_ship_sta", so_ship_sta);
			
			/****2.開始查詢****/
			SecOrderService secOrderSvc = new SecOrderService();
			List<SecOrderVO> secOrderVOs = secOrderSvc.getAll();
			
			Set<String> key = condititon.keySet();
			
			key.stream()
			   .forEach(k -> compoundQuery(secOrderVOs, k, condititon.get(k)));
			
			if(secOrderVOs.isEmpty()) {
				errorMsgs.add("查無訂單!!");
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secOrder/secOrder_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			req.setAttribute("order_list_seach", secOrderVOs);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secOrder/afterSeach.jsp");
			successView.forward(req, res);
			return;
		}

	}
	
	//此方法根據輸入的key和value過濾productInformVOs內的VO
	private void compoundQuery (List<SecOrderVO> secOrderVOs, String key, String value) {
		List<SecOrderVO> secOrderVO_after = null;
		
		if(value.trim().length()!=0) {
			switch (key) {
			case "so_no_str":
				secOrderVO_after = secOrderVOs.stream()
											  .filter(i -> i.getSo_no().equals(new Integer(value)))
											  .collect(Collectors.toList());
				secOrderVOs.clear();
				secOrderVOs.addAll(secOrderVO_after);
				break;
			case "mem_no_str":
				secOrderVO_after = secOrderVOs.stream()
											  .filter(i -> i.getMem_no().equals(new Integer(value)))
											  .collect(Collectors.toList());
				secOrderVOs.clear();
				secOrderVOs.addAll(secOrderVO_after);
				break;
			case "so_sta":
				secOrderVO_after = secOrderVOs.stream()
											  .filter(i -> i.getSo_sta().equals(value))
											  .collect(Collectors.toList());
				secOrderVOs.clear();				
				secOrderVOs.addAll(secOrderVO_after);
				break;
			case "so_pay_sta":
				secOrderVO_after = secOrderVOs.stream()
											  .filter(i -> i.getSo_pay_sta().equals(value))
											  .collect(Collectors.toList());
				secOrderVOs.clear();
				secOrderVOs.addAll(secOrderVO_after);
				break;	
			case "so_ship_sta":
				secOrderVO_after = secOrderVOs.stream()
											  .filter(i -> i.getSo_ship_sta().equals(value))
											  .collect(Collectors.toList());
				secOrderVOs.clear();
				secOrderVOs.addAll(secOrderVO_after);
				break;
			default:
				break;
			}
		}
	}
	
}
