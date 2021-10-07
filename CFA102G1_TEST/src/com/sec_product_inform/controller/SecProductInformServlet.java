package com.sec_product_inform.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sec_product_images.model.SecProductImagesService;
import com.sec_product_images.model.SecProductImagesVO;
import com.sec_product_inform.model.*;

import oracle.net.aso.i;
import oracle.net.aso.k;
import oracle.net.aso.l;

import com.sec_product_class.model.*;
import com.sec_product_images.*;

@javax.servlet.annotation.MultipartConfig
public class SecProductInformServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("Action : " + action);
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.檢查輸入變數******/
			try {
				String name = req.getParameter("spi_name");
				if(name == null || name.trim().length()==0) {
					errorMsgs.add("請輸入商品名稱!!");
				}
				
				//此欄位無須檢查
				Integer productClass = new Integer(req.getParameter("spc_no").trim());
			
				String productContent = req.getParameter("spi_content");
				if(productContent == null || productContent.trim().length()==0) {
					errorMsgs.add("請輸入商品內容!!");
				}
				
				
				Integer productPrice=null;
				try {
					productPrice = new Integer(req.getParameter("spi_pri").trim());
				} catch (NumberFormatException e) {
					productPrice = 0;
					errorMsgs.add("商品價格請輸入數字!!");
				}
				
				Integer productStock=null;
				try {
					productStock = new Integer(req.getParameter("spi_stock").trim());
				} catch (NumberFormatException e) {
					productStock = 0;
					errorMsgs.add("商品庫存請輸入數字!!");
				}

				//此欄位無須檢查
				String productSta = req.getParameter("spi_sta");
				
				ProductInformVO productInformVO = new ProductInformVO();
				productInformVO.setSpi_name(name);
				productInformVO.setSpc_no(productClass);
				productInformVO.setSpi_content(productContent);
				productInformVO.setSpi_pri(productPrice);
				productInformVO.setSpi_stock(productStock);
				productInformVO.setSpi_sta(productSta);
				
				req.setAttribute("productInformVO", productInformVO);
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back_end/secProductInfo/addSecProductInfo.jsp");
					failView.forward(req, res);
					return;
				}
				
				/****2.開始新增商品******/
				ProductInformService productInformSvc = new ProductInformService();
				ProductInformVO priInformVO = 
							productInformSvc.insertProductInform(name, productClass, productContent, productPrice, productStock, productSta);
				System.out.println("新增商品成功!!!");
				

				/****3.新增商品照片*/
				Collection<Part> parts = req.getParts();
				System.out.println("total parts : " + parts.size());

				for(Part part : parts) {
					InputStream in = part.getInputStream();
					if(in.available()!=0 && part.getContentType()!=null) {
						SecProductImagesService secProductImagesSvc = new SecProductImagesService();
						byte[] img = new byte[in.available()];
						in.read(img);
						in.close();
						secProductImagesSvc.insertSecProductImages(priInformVO.getSpi_no(), img);
					}
				}

				/****4.新增完成，頁面轉向***/
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/secProductInfo/listAllSecProductInfo.jsp");
				successView.forward(req, res);
				return;
				
			} catch (Exception e) {
				/****5.新增失敗****/
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back_end/secProductInfo/addSecProductInfo.jsp");
					failView.forward(req, res);
					return;
				}
			}
			
		}
		
		if("getOneForDiplay".equals(action)){
			System.out.println("Enter getOneForDiplay!!!");
			/****1.取值****/
			Integer spi_no = new Integer(req.getParameter("spi_no"));

			/****2.從資料庫取出****/
			ProductInformService productInformSvc = new ProductInformService();
			ProductInformVO productInformVO = productInformSvc.getOneProductInform(spi_no);
			
			/***取出圖片***/
			SecProductImagesService secProductImagesSvc = new SecProductImagesService();
			List<SecProductImagesVO> imagesList = secProductImagesSvc.getAll();
			
			List<SecProductImagesVO> filterImagesList= imagesList.stream()
																 .filter(i -> i.getSpi_no().intValue() == spi_no.intValue())
																 .collect(Collectors.toList());
			
			System.out.println("filterImagesList:" + filterImagesList.isEmpty());
			
			req.setAttribute("filterImagesList", filterImagesList);
			req.setAttribute("productInformVO", productInformVO);
			
			/****3.設定燈箱顯示****/
//			boolean openLightBox = true;
//			req.setAttribute("openLightBox", openLightBox);
//			
//			System.out.println("openLightBox : " + openLightBox);

			/****4.頁面轉向 ****/
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/secProductInfo/listOneProductInfo.jsp");
			successView.forward(req, res);
			return;
			
		}
		
		if("getOneForUpdate".equals(action)) {
			
			System.out.println("enter getOneForUpdate!!");
			/****1.接收請求參數****/
			Integer spi_no = new Integer(req.getParameter("spi_no"));
			/****2.取得資料****/
			ProductInformService productInformSvc = new ProductInformService();
			ProductInformVO productInformVO = productInformSvc.getOneProductInform(spi_no);
			req.setAttribute("productInformVO", productInformVO);

			/****3.轉向至update頁面****/
			System.out.println("頁面轉向!!!");
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/secProductInfo/updateProductInfo.jsp");
			successView.forward(req, res);
			return;
			
		}
		
		if("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.檢查輸入變數******/
			try {
				String name = req.getParameter("spi_name");
				if(name == null || name.trim().length()==0) {
					errorMsgs.add("請輸入商品名稱!!");
				}
				
				Integer spi_no = new Integer(req.getParameter("spi_no"));
				
				//此欄位無須檢查
				Integer productClass = new Integer(req.getParameter("spc_no").trim());
			
				String productContent = req.getParameter("spi_content");
				if(productContent == null || productContent.trim().length()==0) {
					errorMsgs.add("請輸入商品內容!!");
				}
				
				
				Integer productPrice =null;
				try {
					productPrice = new Integer(req.getParameter("spi_pri").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品價格請輸入數字!!");
				}
				
				Integer productStock =null;
				try {
					productStock = new Integer(req.getParameter("spi_stock").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品庫存請輸入數字!!");
				}

				//此欄位無須檢查
				String productSta = req.getParameter("spi_sta");
				
				ProductInformVO productInformVO = new ProductInformVO();
				productInformVO.setSpi_no(spi_no);
				productInformVO.setSpi_name(name);
				productInformVO.setSpc_no(productClass);
				productInformVO.setSpi_content(productContent);
				productInformVO.setSpi_pri(productPrice);
				productInformVO.setSpi_stock(productStock);
				productInformVO.setSpi_sta(productSta);
				
				req.setAttribute("productInformVO", productInformVO);
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back_end/secProductInfo/updateProductInfo.jsp");
					failView.forward(req, res);
					return;
				}
				
				/****2.開始新增商品****/
				ProductInformService productInformSvc = new ProductInformService();
				productInformSvc.updateProductInform(spi_no, name, productClass, productContent, productPrice, productStock, productSta);
				System.out.println("修改商品成功!!!");
												
				/****3.新增完成，頁面轉向***/
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/secProductInfo/listAllSecProductInfo.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("更新失敗!!!");
				/****4.更新失敗****/
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back_end/secProductInfo/updateProductInfo.jsp");
					failView.forward(req, res);
					return;
				}
			}
		}
		
		if("FindByPK".equals(action)) {			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);			
			try {
				
			/****1.檢查輸入參數****/
			Integer spi_no = new Integer(req.getParameter("spi_no"));
			
			
			/****2.查詢資料****/
			ProductInformService productInformSvc = new ProductInformService();
			ProductInformVO productInformVO = productInformSvc.getOneProductInform(spi_no);
			
			if(productInformVO==null) {
				errorMsgs.add("查無商品");	
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			//配合前台頁面EL取值，將VO存入List中
			List<ProductInformVO> productInfoVOs = new LinkedList<ProductInformVO>();
			productInfoVOs.add(productInformVO);
			req.setAttribute("productInfoVOs", productInfoVOs);			
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
								req.getRequestDispatcher("/back_end/secProductInfo/afterSeach.jsp");
			successView.forward(req, res);
			return;
			
			}catch (NumberFormatException e) {
				errorMsgs.add("請輸入數字!!");
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
				failView.forward(req, res);
				return;
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
				failView.forward(req, res);
				return;
			}	
		}
		
		if("ExportImages".equals(action)) {
			res.setContentType("image/jpeg");
			Integer spim_no= new Integer(req.getParameter("spim_no"));
			System.out.println("Integer >> spim_no : " + spim_no);
			SecProductImagesService secProductImagesSvc = new SecProductImagesService();
			SecProductImagesVO secProductImagesVO = secProductImagesSvc.getOneSecProductImages(spim_no);
			byte[] imgArry = secProductImagesVO.getSpim_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(imgArry);
			out.close();	
			return;
		}
		
		if("showAllProduct".equals(action)) {
			HttpSession session = req.getSession();
			Integer global_spc_no = (Integer) session.getAttribute("global_spc_no");
						
			/****1.取得變數****/
			try {
				Integer spc_no = new Integer( req.getParameter("spc_no") );
				global_spc_no = spc_no;	
			} 
			//沒有取得spc_no
			catch (Exception e) {
				if(global_spc_no == null)
					//預設為顯示全部商品
					global_spc_no = 0;
			}
			session.setAttribute("global_spc_no", global_spc_no);

			/****2.透過searchProductList()取得該類別的商品列表****/
			List<ProductInformVO> afterFiterProduct = searchProductList(global_spc_no);
					
			req.setAttribute("afterFiterProduct", afterFiterProduct);
						
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/front_end/secProductInfo/productShopPage.jsp");
			successView.forward(req, res);
			return;
		}
		
		if("showProductDetail".equals(action)) {			
			/****1.取得參數****/
			Integer spi_no = new Integer(req.getParameter("spi_no"));
			
			/****2.開始查詢商品****/
			ProductInformService productInformSvc = new ProductInformService();
			ProductInformVO productInformVO = productInformSvc.getOneProductInform(spi_no);
			
			req.setAttribute("productInformVO", productInformVO);
			
			/****3.開始查詢商品圖片****/
			SecProductImagesService productImagesSvc = new SecProductImagesService();
			List<SecProductImagesVO> secProductImagesVOs = productImagesSvc.getAll();
			
			List<SecProductImagesVO> afterFilterImages = null;
			afterFilterImages = secProductImagesVOs.stream()
							 					   .filter(i -> i.getSpi_no().equals(spi_no))
							 					   .collect(Collectors.toList());
			req.setAttribute("afterFilterImages", afterFilterImages);
			
			/****4.頁面轉向****/
			RequestDispatcher successView = 
					 req.getRequestDispatcher("/front_end/secProductInfo/productDetail.jsp");
			successView.forward(req, res);
			return;
		}
		
		
		
		/*購物車>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>購物車*/
		if("cart".equals(action)) {

			HttpSession session = req.getSession();
			Vector<ProductInformVO> productInformList = (Vector<ProductInformVO>) session.getAttribute("shoppingCart_sec");
			String cart_action = req.getParameter("cart_action");
			/*使用HashMap儲存商品數量 (key:spi_no商品編號, value: quantity購買數量)*/
			Map<Integer, Integer> Quamap = (Map<Integer, Integer>) session.getAttribute("Quamap");
			String forwardStr = null;
			
			if(!cart_action.equals("checkout")) {
				
				//刪除購物車商品
				if(cart_action.equals("delete")) {
					String delete = req.getParameter("index");
					int del = Integer.parseInt(delete);
					productInformList.removeElementAt(del);
					//刪除完商品回到購物車頁面
					forwardStr = "/front_end/secProductInfo/cart.jsp";
				}
				//刪除購物車中所有商品
				else if(cart_action.equals("deleteAll")) {
					Quamap.clear();
					productInformList.removeAllElements();
					//刪除完商品回到購物車頁面
					forwardStr = "/front_end/secProductInfo/cart.jsp";
				}
				
				//新增商品到購物車中
				else if(cart_action.equals("add")) {
					Integer spi_no = new Integer(req.getParameter("spi_no"));
					Integer quantity = new Integer(req.getParameter("quantity"));
					boolean match = false;
					
					/*從新將商品查詢出來並存入req，forward回原本商品頁面才有資料 >>>>*/
					/****開始查詢商品****/
					ProductInformService productInformSvc = new ProductInformService();
					ProductInformVO productInformVO = productInformSvc.getOneProductInform(spi_no);
					req.setAttribute("productInformVO", productInformVO);
										
					/****開始查詢商品圖片****/
					SecProductImagesService productImagesSvc = new SecProductImagesService();
					List<SecProductImagesVO> secProductImagesVOs = productImagesSvc.getAll();
					
					List<SecProductImagesVO> afterFilterImages = null;
					afterFilterImages = secProductImagesVOs.stream()
									 					   .filter(i -> i.getSpi_no().equals(spi_no))
									 					   .collect(Collectors.toList());
					req.setAttribute("afterFilterImages", afterFilterImages);
					/*<<<<從新將商品查詢出來並存入req，forward回原本商品頁面才有資料 */
					
					
					//新增第一個商品到購物車
					if(productInformList==null) {
						productInformList = new Vector<ProductInformVO>();
						productInformList.add(productInformVO);
						Quamap = new HashMap<Integer, Integer>();
						Quamap.put(spi_no, quantity);
					}else {
						//檢查是否為重複的商品
						for(int i=0 ; i<productInformList.size() ; i++ ) {
							ProductInformVO productInformVO2 = productInformList.get(i);
							if(productInformVO2.getSpi_no().equals(productInformVO.getSpi_no())) {
								/*直接將原本的數量覆蓋掉*/
								Quamap.put(spi_no, quantity);
								match = true;
							}
						}
						
						//如果不是重複商品則新增
						if(!match) {
							productInformList.add(productInformVO);
							Quamap.put(spi_no, quantity);
						}
					}
					//新增完商品回到商品頁面
					forwardStr = "/front_end/secProductInfo/productDetail.jsp";
				}
				
				/*計算購物車內商品價格*/
				int subTotal = 0;
				for (ProductInformVO product : productInformList) {
					subTotal += product.getSpi_pri() * Quamap.get(product.getSpi_no());
				}
				Quamap.put(999, subTotal);
				
				/*計算購物車內商品數量*/
				Integer amount = (int) productInformList.size();
				Quamap.put(998, amount);

				session.setAttribute("Quamap", Quamap);
				session.setAttribute("shoppingCart_sec", productInformList);
				RequestDispatcher successVisw = 
						req.getRequestDispatcher(forwardStr);
				successVisw.forward(req, res);
				return;
			}
		}
		/*購物車<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<購物車*/
		
//		//依類別編號查詢
//		if("findBySpcNo".equals(action)) {
//			
//			/****1.取得參數****/
//			Integer spc_no = new Integer(req.getParameter("spc_no"));
//			
//			/****2.查詢商品****/
//			ProductInformService productInformSvc = new ProductInformService();
//			List<ProductInformVO> productInformVOs = productInformSvc.getAll()
//																	 .stream()
//																	 .filter(p -> p.getSpc_no().equals(spc_no))
//																	 .collect(Collectors.toList());
//			req.setAttribute("productInfoVOs", productInformVOs);
//			
//			/****3.頁面轉向****/
//			RequestDispatcher successView = 
//					req.getRequestDispatcher("/back_end/secProductInfo/afterSeach.jsp");
//			successView.forward(req, res);
//			return;
//			
//		}
//		
//		//依商品名稱查詢
//		if("findBySpiName".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/****1.取得參數****/
//			String spi_name = req.getParameter("spi_name");
//
//			if(spi_name.trim().length()==0) {
//				errorMsgs.add("請輸入商品名稱!!");
//				
//				RequestDispatcher failView = 
//						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
//				failView.forward(req, res);
//				return;
//			}
//			
//			/****2.查詢商品****/
//			ProductInformService productInformSvc = new ProductInformService();
//			List<ProductInformVO> productInformVOs = productInformSvc.getAll()
//																	 .stream()
//																	 .filter(p -> p.getSpi_name()   /*不區分大小寫*/
//																			 	   .toLowerCase()
//																			 	   .contains(spi_name.toLowerCase()))
//																	 .collect(Collectors.toList());
//			if(productInformVOs.size()==0) {
//				errorMsgs.add("查無商品");
//				
//				RequestDispatcher failView = 
//						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
//				failView.forward(req, res);
//				return;
//			}
//			
//			req.setAttribute("productInfoVOs", productInformVOs);
//			
//			/****3.頁面轉向****/
//			RequestDispatcher successView = 
//					req.getRequestDispatcher("/back_end/secProductInfo/afterSeach.jsp");
//			successView.forward(req, res);
//			return;
//		}
//		
//		//依商品狀態查詢
//		if("findBySpiSta".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/****1.取得參數****/
//			String spi_sta = req.getParameter("spi_sta");
//						
//			/****2.查詢商品****/
//			ProductInformService productInformSvc = new ProductInformService();
//			List<ProductInformVO> productInformVOs = productInformSvc.getAll()
//																	 .stream()
//																	 .filter(p -> p.getSpi_sta().equals(spi_sta))
//																	 .collect(Collectors.toList());
//			if(productInformVOs.size()==0) {
//				errorMsgs.add("查無商品");
//				
//				RequestDispatcher failView = 
//						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
//				failView.forward(req, res);
//				return;
//			}
//			
//			req.setAttribute("productInfoVOs", productInformVOs);
//			
//			/****3.頁面轉向****/
//			RequestDispatcher successView = 
//					req.getRequestDispatcher("/back_end/secProductInfo/afterSeach.jsp");
//			successView.forward(req, res);
//			return;
//		}
		
		//複合查詢
		if("compoundQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);		
			
			/****1.取得參數****/
			String spi_no_str = req.getParameter("spi_no");
			Integer spi_no = null;
			if(spi_no_str.trim().length() != 0) {
				try {
					spi_no = new Integer(req.getParameter("spi_no"));
				} catch (NumberFormatException e) {
					errorMsgs.add("商品編號請輸入數字");
					
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
					failView.forward(req, res);
					return;
				}
			}

			String spi_name = req.getParameter("spi_name");
			String spc_no = req.getParameter("spc_no");
			String spi_sta = req.getParameter("spi_sta");
			
			Map<String, String> condititon = new HashMap<String, String>();
			condititon.put("spi_no_str", spi_no_str);
			condititon.put("spi_name", spi_name);
			condititon.put("spc_no", spc_no);
			condititon.put("spi_sta", spi_sta);
			
			/****2.開始查詢****/
			ProductInformService productInformSvc = new ProductInformService();
			List<ProductInformVO> productInformVOs = productInformSvc.getAll();
			Set<String> key = condititon.keySet();
			
			key.stream()
			   .forEach(k -> compoundQuery(productInformVOs, k, condititon.get(k)));
			
			if(productInformVOs.isEmpty()) {
				errorMsgs.add("查無商品!!");
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			req.setAttribute("productInfoVOs", productInformVOs);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secProductInfo/afterSeach.jsp");
			successView.forward(req, res);
			return;
		}
	}
	
	/*此方法根據輸入的商品類別編號搜尋並過濾，回傳過濾後的商品列表*/
	private List<ProductInformVO> searchProductList(Integer spc_no) {
		List<ProductInformVO> afterFiterProduct = null;
				
		/****1.開始查詢****/
		ProductInformService productInformSvc = new ProductInformService();
		List<ProductInformVO> productInformVOs = productInformSvc.getAll();
				
		/****2.過濾商品****/
		if(spc_no == 0) {
			/****spc_no=0 表示要查詢所有的商品，並去除狀態為"下架"或庫存為0的商品****/
			afterFiterProduct = productInformVOs.stream()
												.filter(i -> i.getSpi_stock().intValue() > 0)
												.filter(i -> i.getSpi_sta().equals("上架"))
												.collect(Collectors.toList());
		}else {
			/****依據spc_no，開始過濾商品，並去除狀態為"下架"或庫存為0的商品****/
			afterFiterProduct = productInformVOs.stream()
												.filter(i -> i.getSpc_no().equals(spc_no))
												.filter(i -> i.getSpi_stock().intValue() > 0)
												.filter(i -> i.getSpi_sta().equals("上架"))
												.collect(Collectors.toList());	
		}
		return afterFiterProduct;
	}
	
	//此方法根據輸入的key和value過濾productInformVOs內的VO
	private void compoundQuery (List<ProductInformVO> productInformVOs, String key ,String value) {
		List<ProductInformVO> productInformVO_after = null;
		
		if(value.trim().length()!=0) {
			switch (key) {
			case "spi_no_str":
		
				productInformVO_after = productInformVOs.stream()
														.filter(i -> i.getSpi_no().equals(new Integer(value)))
														.collect(Collectors.toList());
				productInformVOs.clear();				
				productInformVOs.addAll(productInformVO_after);
				break;
			case "spi_name":
				productInformVO_after = productInformVOs.stream()
														.filter(i -> i.getSpi_name()	/*不區分大小寫*/
																	  .toLowerCase()
																	  .contains(value.toLowerCase()))
														.collect(Collectors.toList());
				productInformVOs.clear();
				productInformVOs.addAll(productInformVO_after);
				break;
			case "spc_no":
				productInformVO_after = productInformVOs.stream()
														.filter(i -> i.getSpc_no().equals(new Integer(value)))
														.collect(Collectors.toList());
				productInformVOs.clear();
				productInformVOs.addAll(productInformVO_after);
				break;
			case "spi_sta":
				productInformVO_after = productInformVOs.stream()
														.filter(i -> i.getSpi_sta().equals(value))
														.collect(Collectors.toList());
				productInformVOs.clear();
				productInformVOs.addAll(productInformVO_after);
				break;
			default:
				break;
			}
		}
	}
}
