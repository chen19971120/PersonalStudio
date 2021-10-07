package com.rentalOrder.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.rentalOrder.model.*;
import com.rentalProductList.model.*;

public class RentalOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	Timer timer = new Timer();
	int timercount =0 ;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//引導到評價頁面    
		if ("getOne_For_Pr".equals(action)) {
			try {
				Integer ro_no = new Integer(req.getParameter("ro_no"));
				
				req.setAttribute("ro_no", ro_no);
				String url = "/front_end/rental/rentalProductReviewPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rental/rentalOrderList.jsp");
				failureView.forward(req, res);
			}
		}				
			
		if ("insertProlongOrder".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String fromfront = req.getParameter("front_end");
				
				Integer ro_no = new Integer(req.getParameter("ro_no"));
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				Integer rpl_no = new Integer(req.getParameter("rpl_no"));

				String ro_ship_method ="";
				String ro_ship_addrs ="";
				
				String starttime = req.getParameter("ro_starttime");
				String endtime = req.getParameter("ro_endtime");
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date ro_starttime = new Date(formatter.parse(starttime).getTime()+60*60*24*1000);

				Date ro_endtime = null;
				
				if (endtime==null || endtime.trim().length() == 0) {
					errorMsgs.add("請選擇續租日期");
				}else {
					ro_endtime = Date.valueOf(endtime);
					long startRentDaytoEndRentDay = (formatter.parse(endtime).getTime()-formatter.parse(starttime).getTime())/(1000*24*60*60);
					if((startRentDaytoEndRentDay)<1)
						errorMsgs.add("租賃時間錯誤");
				}		
				
				String url;
				if (!errorMsgs.isEmpty()) {
					
					url = "/back_end/rentalOrder/addRo.jsp";			
					if("true".equals(fromfront))
						url = "/back_end/ro/ro.do?action=getOne_For_Prolong&ro_no="+ ro_no;								
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
			
				Integer ro_day = new Integer(req.getParameter("ro_day"));	
				Integer ro_price = new Integer(req.getParameter("ro_price"));
				Integer ro_totalprice = new Integer(req.getParameter("ro_totalprice"));
				Integer ro_deposit = new Integer(req.getParameter("ro_deposit"));

				RentalOrderVO roVO = new RentalOrderVO();
				roVO.setMem_no(mem_no);
				roVO.setRpl_no(rpl_no);
				roVO.setRo_ship_method(ro_ship_method);
				roVO.setRo_ship_addrs(ro_ship_addrs);
				roVO.setRo_starttime(ro_starttime);
				roVO.setRo_endtime(ro_endtime);
				roVO.setRo_day(ro_day);
				roVO.setRo_price(ro_price);
				roVO.setRo_totalprice(ro_totalprice);
				roVO.setRo_deposit(ro_deposit);		

				/***************************2.開始新增資料***************************************/
				RentalOrderService roSvc = new RentalOrderService();				
				
				RentalOrderVO roVO_old = roSvc.getOneRentalOrder(ro_no);
				if(roVO_old.getRo_oncerentendtime().toString().equals("1970-01-01")) {
				roVO = roSvc.insertRentalOrder(mem_no, rpl_no,ro_ship_method,ro_ship_addrs, 
						ro_starttime,ro_endtime, ro_day, ro_price,ro_totalprice,ro_deposit);

				roVO.setRo_status("續租-未付款");
				roVO.setRo_deposit_status("已收取");
				roVO.setRo_return_status("");
				roVO.setRo_product_status("");
				roVO.setRo_repaircost(0);
				roVO.setRo_return_deposit(0);
				roVO.setRo_delay_days(0);
				roVO.setRo_oncerentendtime(Date.valueOf("1970-01-01"));
				roVO = roSvc.updateRentalOrder(roVO);

				roVO_old.setRo_oncerentendtime(Date.valueOf("1970-01-02"));
				roSvc.updateRentalOrder(roVO_old);
				} else {
					url = "/front_end/rental/errorPage.jsp";
					errorMsgs.add("申請失敗,已經申請續租!");
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}				
					
				
				req.setAttribute("roVO",roVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				url = "/front_end/rental/prolongOrderCreateSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				String url = "/front_end/rental/errorPage.jsp";
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}								
		}
		
		//申請會員租賃訂單引導至續租申請    
		if ("getOne_For_Prolong".equals(action)) {
			try {
				Integer ro_no = new Integer(req.getParameter("ro_no"));
				
				req.setAttribute("ro_no", ro_no);
				String url = "/front_end/rental/prolongRentalOrderCreate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rental/rentalOrderList.jsp");
				failureView.forward(req, res);
			}
		}		
		
		//從前台導向訂單付款頁面
		if ("showRoPayPage".equals(action)) {
			try {
				Integer ro_no = new Integer(req.getParameter("ro_no"));

				req.setAttribute("ro_no", ro_no);
				String url = "/front_end/rental/payPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rental/rentalOrderList.jsp");
				failureView.forward(req, res);
			}
		}
		
		//從前台導向租賃訂單產生頁面
		if ("showRoCreatePage".equals(action)) {
			try {
				
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				HttpSession session = req.getSession();
				session.setAttribute("rc_no", rc_no);
				
				String url = "/front_end/rental/rentalOrderCreate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rental/rentalOrderCreate.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneRoStatus_For_Display".equals(action)) {
			try {
				String ro_status = req.getParameter("ro_status");
				
				RentalOrderService roSvc = new RentalOrderService();
				List<RentalOrderVO> list = roSvc.getListByRoStatus(ro_status);
				
				req.setAttribute("roStatusSelect", ro_status);
				req.setAttribute("list", list);
				String url = "/back_end/rentalOrder/listRo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalOrder/listRo.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("ro_no");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalOrder/listRo.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer ro_no = null;
				
				try {
					ro_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalOrder/listRo.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}	

				RentalOrderService roSvc = new RentalOrderService();
				RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
				
				if (roVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalOrder/listRo.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
						
				List<RentalOrderVO> list = new ArrayList<RentalOrderVO>();
				list.add(roVO);
				
				req.setAttribute("list", list);
				String url = "/back_end/rentalOrder/listRo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
					
			}catch(Exception e) {
				errorMsgs.add("查詢訂單編號錯誤");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalOrder/listRo.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
		if ("cancelRo".equals(action)) {
			try {
				Integer ro_no = new Integer(req.getParameter("ro_no"));
				
				RentalOrderService roSvc = new RentalOrderService();
				RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
				roSvc.cancelOrder(ro_no);
											
				req.setAttribute("roVO", roVO);
				req.setAttribute("closewindow",true);
				
				String fromfront = req.getParameter("fromfront");
				String url = "/back_end/rentalOrder/complete_ro.jsp";			
				
				if("true".equals(fromfront))
					url = "/front_end/rental/rentalOrderList.jsp";
				
				RequestDispatcher successView = req
						.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				System.out.println("取消訂單錯誤");
			}		
		}
		
		if ("Complete_RO".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				Integer ro_no = new Integer(req.getParameter("ro_no"));
				RentalOrderService roSvc = new RentalOrderService();
				RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
				
				String ro_product_status = req.getParameter("ro_product_status");
				String return_date = req.getParameter("ro_return_date");
				if(return_date.trim().length() == 0) {
					if(!"遺失".equals(ro_product_status))
						errorMsgs.add("請輸入日期");
				}
				
				Integer ro_repaircost = new Integer(req.getParameter("ro_repaircost"));
				if(ro_repaircost==0&&"毀損".equals(ro_product_status)) {
					errorMsgs.add("請輸入毀損扣除額");
				}	
				
				roVO.setRo_product_status(ro_product_status);	//防呆,避免按太快
	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roVO", roVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalOrder/complete_ro.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				Integer mem_no = roVO.getMem_no();
				Integer rpl_no = roVO.getRpl_no();
				String ro_status =null;
				if("遺失".equals(ro_product_status))
					ro_status = "結束-商品遺失";
				else if ("毀損".equals(ro_product_status))
					ro_status = "結束-商品毀損";
				else
					ro_status = "結束";
				
				String ro_ship_method = roVO.getRo_ship_method();
				String ro_ship_addrs = roVO.getRo_ship_addrs();
				Date ro_starttime = roVO.getRo_starttime();
				Date ro_endtime = roVO.getRo_endtime();
				Date ro_oncerentendtime = Date.valueOf("1970-01-01");
				
				Date ro_return_date =null;
				if(return_date.trim().length() == 0)
					ro_return_date = Date.valueOf("1970-01-01");
				else
					ro_return_date = Date.valueOf(return_date);
				
				Integer ro_day = roVO.getRo_day();
				Integer ro_price = roVO.getRo_price();
				Integer ro_totalprice = roVO.getRo_totalprice();
				Integer ro_deposit = roVO.getRo_deposit();
				Integer ro_return_deposit = new Integer(req.getParameter("ro_return_deposit"));
				String ro_deposit_status = null;
				if (ro_return_deposit==0)
					ro_deposit_status = "沒收";
				else if (ro_return_deposit<ro_deposit)
					ro_deposit_status = "部分退回";
				else
					ro_deposit_status = "全額退回";
								
				String ro_return_status = req.getParameter("ro_return_status");
				Integer ro_delay_days = new Integer(req.getParameter("ro_delay_days"));	
				
				//更新訂單
				roVO = roSvc.updateRentalOrder(ro_no, mem_no, rpl_no, ro_status, 
						ro_ship_method, ro_ship_addrs, ro_starttime, ro_endtime,
						ro_oncerentendtime, ro_return_date, ro_day, ro_price,
						ro_totalprice, ro_deposit, ro_deposit_status, ro_return_status,
						ro_product_status, ro_repaircost, ro_delay_days, ro_return_deposit);
				
				//更新商品狀態
				RentalProductListService rplSvc = new RentalProductListService();
				RentalProductListVO rplVO = rplSvc.getOneRentalProductList(rpl_no);
				
				if("遺失".equals(ro_product_status))
					rplVO.setRpl_status("遺失");
				else
					rplVO.setRpl_status("整備");
					
				rplSvc.updateRentalProductListByVO(rplVO);
				
				req.setAttribute("roVO", roVO);
				req.setAttribute("closewindow",true);
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/back_end/rentalOrder/complete_ro.jsp");
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalOrder/complete_ro.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		if ("getOne_For_End".equals(action)) {
			
			String requestURL = req.getParameter("requestURL"); 
			req.setAttribute("requestURL", requestURL); 
			
			String whichPage = req.getParameter("whichPage"); 
			req.setAttribute("whichPage", whichPage);
			
			try {
			
				Integer ro_no = new Integer(req.getParameter("ro_no"));
				
				RentalOrderService roSvc = new RentalOrderService();
				RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
				
				req.setAttribute("roVO", roVO);
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/back_end/rentalOrder/complete_ro.jsp");
				successView.forward(req, res);
				return;
				
			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOne_For_DisplayDetail".equals(action)) {
			
			try {

				Integer ro_no = new Integer(req.getParameter("ro_no"));
				
				RentalOrderService roSvc = new RentalOrderService();
				RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
				
				req.setAttribute("roVO", roVO);
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/back_end/rentalOrder/listOneRoDetail.jsp");
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer ro_no = new Integer(req.getParameter("ro_no"));
				
				RentalOrderService roSvc = new RentalOrderService();
				RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
				
				req.setAttribute("roVO", roVO);         
				String url = "/back_end/rentalOrder/update_ro_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalOrder/listOneRoDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();						
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				Integer ro_no = new Integer(req.getParameter("ro_no"));
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				Integer rpl_no = new Integer(req.getParameter("rpl_no"));
				String ro_status = req.getParameter("ro_status");
				String ro_ship_method = req.getParameter("ro_ship_method");
				String ro_ship_addrs = req.getParameter("ro_ship_addrs");
								
				Date ro_starttime = Date.valueOf(req.getParameter("ro_starttime"));
				Date ro_endtime = Date.valueOf(req.getParameter("ro_endtime"));
				Date ro_oncerentendtime = Date.valueOf("1970-01-01");
				Date ro_return_date = null;

				String return_date = req.getParameter("ro_return_date");
				if( return_date.trim().length() == 0)
					ro_return_date = Date.valueOf("1970-01-01");
				else
					ro_return_date = Date.valueOf(return_date);
				Integer ro_totalprice = new Integer(req.getParameter("ro_totalprice"));	
				Integer ro_day = new Integer(req.getParameter("ro_day"));
	
				
				String strro_price = req.getParameter("ro_price");
				if (strro_price == null || (strro_price.trim()).length() == 0) {
					errorMsgs.add("請輸入每天租賃價格");
				}
				
				
				Integer ro_price = null;
				try {
					ro_price = new Integer(strro_price);
				} catch (Exception e) {
					errorMsgs.add("每天租賃價格格式不正確");
				}
	
				String strro_deposit = req.getParameter("ro_deposit");
				if (strro_deposit == null || (strro_deposit.trim()).length() == 0) {
					errorMsgs.add("請輸入押金");
				}
				
				Integer ro_deposit = null;
				try {
					ro_deposit = new Integer(strro_deposit);
				} catch (Exception e) {
					errorMsgs.add("押金格式不正確");
				}

				String strro_delay_days = req.getParameter("ro_delay_days");
				if (strro_delay_days == null || (strro_delay_days.trim()).length() == 0) {
					errorMsgs.add("請輸入逾期(提前)天數");
				}
				
				Integer ro_delay_days = null;
				try {
					ro_delay_days = new Integer(strro_delay_days);
				} catch (Exception e) {
					errorMsgs.add("逾期(提前)天數格式不正確");
				}

				String strro_return_deposit = req.getParameter("ro_return_deposit");
				if (strro_return_deposit == null || (strro_return_deposit.trim()).length() == 0) {
					errorMsgs.add("請輸入實際歸還押金");
				}
				
				Integer ro_return_deposit = null;
				try {
					ro_return_deposit = new Integer(strro_return_deposit);
				} catch (Exception e) {
					errorMsgs.add("實際歸還押金格式不正確");
				}
				
				String strro_repaircost = req.getParameter("ro_repaircost");
				if (strro_repaircost == null || (strro_repaircost.trim()).length() == 0) {
					errorMsgs.add("請輸入實際歸還押金");
				}
				
				Integer ro_repaircost = null;
				try {
					ro_repaircost = new Integer(strro_repaircost);
				} catch (Exception e) {
					errorMsgs.add("實際歸還押金格式不正確");
				}
				
				RentalOrderService roSvc = new RentalOrderService();
				RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
				
				req.setAttribute("roVO", roVO);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalOrder/update_ro_input.jsp");
					failureView.forward(req, res);
					return;//
				}	
				String ro_deposit_status = req.getParameter("ro_deposit_status");
				String ro_return_status = req.getParameter("ro_return_status");
				String ro_product_status = req.getParameter("ro_product_status");
				
							
				roVO = roSvc.updateRentalOrder(ro_no, mem_no, rpl_no, ro_status, 
						ro_ship_method, ro_ship_addrs, ro_starttime, ro_endtime,
						ro_oncerentendtime, ro_return_date, ro_day, ro_price,
						ro_totalprice, ro_deposit, ro_deposit_status, ro_return_status,
						ro_product_status, ro_repaircost, ro_delay_days, ro_return_deposit);
				
				req.setAttribute("closewindow",true);
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/back_end/rentalOrder/update_ro_input.jsp");
				successView.forward(req, res);
						
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalOrder/update_ro_input.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
		
		
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String fromfront = req.getParameter("front_end");
				
				String str = req.getParameter("mem_no");
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("員工編號: 請勿空白");
				}
				Integer mem_no = null;
				try {
				mem_no = new Integer(str);
				
				}catch (NumberFormatException e) {
					errorMsgs.add("編號請填數字.");
				}
				
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				String ro_ship_method = req.getParameter("ro_ship_method");
				if (ro_ship_method == null || ro_ship_method.trim().length() == 0) {
					errorMsgs.add("請選擇配送方式");
				}
				
				
				String ro_ship_addrs = null;
				if ("宅配".equals(ro_ship_method)) {
					ro_ship_addrs = req.getParameter("ro_ship_addrs");
					if (ro_ship_addrs == null || ro_ship_addrs.trim().length() == 0) {
						errorMsgs.add("請填寫配送地址");
					}
				}
				else
					ro_ship_addrs ="";
				
				String starttime = req.getParameter("ro_starttime");
				String endtime = req.getParameter("ro_endtime");

				Date ro_starttime = null;
				Date ro_endtime = null;
				
				if (starttime == null || endtime==null || starttime.trim().length() == 0 || endtime.trim().length() == 0) {
					errorMsgs.add("請選擇租賃日期");
				}else {
					ro_starttime = Date.valueOf(starttime);
					ro_endtime = Date.valueOf(endtime);
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					long todayToStartRentDay = (formatter.parse(starttime).getTime()-new java.util.Date().getTime())/(1000*24*60*60);
					long startRentDaytoEndRentDay = (formatter.parse(endtime).getTime()-formatter.parse(starttime).getTime())/(1000*24*60*60);
					if(todayToStartRentDay<2||todayToStartRentDay>6) 
						errorMsgs.add("租賃開始日期錯誤，僅提供預約未來3-7天");
					if((startRentDaytoEndRentDay+1)<7)
						errorMsgs.add("租賃時間錯誤，最短租賃天數7天");
				}		
						
				String url;
				if (!errorMsgs.isEmpty()) {
					
					url = "/back_end/rentalOrder/addRo.jsp";			
					if("true".equals(fromfront))
						url = "/front_end/rental/rentalOrderCreate.jsp";								
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
			
				Integer ro_day = new Integer(req.getParameter("ro_day"));	
				Integer ro_price = new Integer(req.getParameter("ro_price"));
				Integer ro_totalprice = new Integer(req.getParameter("ro_totalprice"));
				Integer ro_deposit = new Integer(req.getParameter("ro_deposit"));
	
				RentalOrderVO roVO = new RentalOrderVO();
				roVO.setMem_no(mem_no);
				roVO.setRo_ship_method(ro_ship_method);
				roVO.setRo_ship_addrs(ro_ship_addrs);
				roVO.setRo_starttime(ro_starttime);
				roVO.setRo_endtime(ro_endtime);
				roVO.setRo_day(ro_day);
				roVO.setRo_price(ro_price);
				roVO.setRo_totalprice(ro_totalprice);
				roVO.setRo_deposit(ro_deposit);		
					
				if (!errorMsgs.isEmpty()) {
				
					url = "/back_end/rentalOrder/addRo.jsp";
					
					if("true".equals(fromfront))
						url = "/front_end/rental/errorPage.jsp";
									
					req.setAttribute("roVO", roVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				RentalOrderService roSvc = new RentalOrderService();				
				Integer rpl_no = roSvc.findOneRplForRent(rc_no);
				
				if(rpl_no.equals(0)) {
					errorMsgs.add("此商品目前無庫存");
					
					url = "/back_end/rentalOrder/addRo.jsp";
					
					if("true".equals(fromfront))
						url = "/front_end/rental/errorPage.jsp";
									
					req.setAttribute("roVO", roVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				roVO.setRpl_no(rpl_no);
				
				roVO = roSvc.insertRentalOrder(mem_no, rpl_no,ro_ship_method,ro_ship_addrs, 
						ro_starttime,ro_endtime, ro_day, ro_price,ro_totalprice,ro_deposit);
				
				req.setAttribute("roVO",roVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				url = "/back_end/rentalOrder/listRo.jsp";
				
				if("true".equals(fromfront))
					url = "/front_end/rental/orderCreateSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				String url = "/back_end/rentalOrder/listRo.jsp";
				String fromfront = req.getParameter("front_end");
				
				if("true".equals(fromfront))
					url = "/front_end/rental/errorPage.jsp";
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}								
		}
		
		if ("payOneRO".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
					
			try {
				String str = req.getParameter("ro_no");
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("訂單編號: 請勿空白");
				}
				Integer ro_no = null;
				try {
					ro_no = new Integer(str);
				}catch (NumberFormatException e) {
					errorMsgs.add("編號請填數字.");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalOrder/listRo.jsp");
						failureView.forward(req, res);
					return;
				}
				
				RentalOrderService roSvc = new RentalOrderService();
				RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
				if(roVO.getRo_status().equals("續租-未付款")) {
					roSvc.payforProlongOrder(ro_no);	
				} else if(roVO.getRo_status().equals("未付款")) {
					roSvc.payForOrder(ro_no);
				} else {
					errorMsgs.add("付款失敗，訂單無法付款!");			
				}
				
				String fromfront = req.getParameter("fromfront");
				String url = "/back_end/rentalOrder/listRo.jsp";			
				
				if("true".equals(fromfront))
					url = "/front_end/rental/rentalOrderList.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);			
				
			} catch (Exception e) {
				errorMsgs.add("查無此訂單");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalOrder/listRo.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("deliver".equals(action)) {
			
			try {
				Integer ro_no = new Integer(req.getParameter("ro_no"));
				
				RentalOrderService roSvc = new RentalOrderService();
				roSvc.changeOneRoStatusToOnRent(ro_no);
				
				RequestDispatcher successView = 
						req.getRequestDispatcher("/back_end/rentalOrder/listRo.jsp");
				successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalOrder/listRo.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if ("startTimer".equals(action)) {

				RentalOrderService roSvc = new RentalOrderService();
				System.out.println("計時器開始");
				TimerTask task = new TimerTask() {
					public void run() {
						timercount ++;
						System.out.println("第 " + timercount + " 次執行計時器");
						roSvc.executeTimerTask();
					}					
				};
				Calendar cal = Calendar.getInstance();
				timer = new Timer();
				timer.scheduleAtFixedRate(task, cal.getTime(), 30000);
		
		}
		
		if ("stopTimer".equals(action)) {
			System.out.println("計時器關閉");
			timer.cancel();
		}
				
	}	
	
	public void destroy() {                                            
		if(timer!=null)
			timer.cancel();
	}    
	
	
}
