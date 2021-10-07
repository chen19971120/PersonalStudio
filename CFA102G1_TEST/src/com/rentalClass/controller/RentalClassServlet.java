package com.rentalClass.controller;

import com.rentalClass.model.*;
import com.rentalProductImages.model.*;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.util.*;

@WebServlet("/rentalClass.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RentalClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
			
		//從前台導向租覽特定類別租賃商品
		if ("showRc_itemList".equals(action)) {
			try {
				
				String rc_item = req.getParameter("rc_item");
					
				RentalClassService rcSvc = new RentalClassService();
				List<RentalClassVO> list = rcSvc.getOneRc_item(rc_item);
					
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/front_end/rental/rentalProductList.jsp");
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rental/rentalProductList.jsp");
				failureView.forward(req, res);
			}
		}
		//從前台導向租賃商品詳情
		if ("showRcDetail".equals(action)) {
			try {
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				req.setAttribute("rc_no", rc_no);	
				
				String url = "/front_end/rental/rentalProductDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rental/rentalProductDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後台下單防呆2對庫存為0  類別下單
		if ("addRo".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				RentalClassService rcSvc = new RentalClassService();
				RentalClassVO rcVO = rcSvc.getOneRentalClass(rc_no);
			
				if (rcVO.getRc_storage() == 0) {
					errorMsgs.add("此產品目前無庫存!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rc_no", rc_no);
				String url = "/back_end/rentalOrder/addRo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		//回傳單一種類(桌機/筆電/AIO...) rcVO List 
		if ("getOneRc_item_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String rc_item = req.getParameter("rc_item");
				/***************************2.開始查詢資料*****************************************/
				
				RentalClassService rcSvc = new RentalClassService();
				List<RentalClassVO> list = rcSvc.getOneRc_item(rc_item);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/rentalClass/listRcbyItem.jsp");
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		//後台一鍵對類別上下架 
		/***************************更改狀態**************************************/
		if ("getOne_Change_Status".equals(action)) { 
								
			try {
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				RentalClassService rcSvc = new RentalClassService();
				rcSvc.changeRcRentStatus(rc_no);
				
				String rc_item = rcSvc.getOneRentalClass(rc_no).getRc_item();
				
				String requestURL = req.getParameter("requestURL");
				if(requestURL.equals("/back_end/rentalClass/listRcbyItem.jsp")) 
					req.setAttribute("list",rcSvc.getOneRc_item(rc_item));
				if(requestURL.equals("/back_end/rentalClass/listOneRc.jsp")) {
					List<RentalClassVO> list = new ArrayList<RentalClassVO>();
					list.add(rcSvc.getOneRentalClass(rc_no));
					req.setAttribute("list",list);
				}
				
				RequestDispatcher successView = 
						req.getRequestDispatcher(requestURL);// 
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				/***************************2.開始刪除資料***************************************/
				RentalClassService rcSvc = new RentalClassService();
				rcSvc.deleteRentalClass(rc_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/rentalClass/listRc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
				failureView.forward(req, res);
			}	
		}	
		
		/***********************查詢*************************/
		if ("update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/	
				Integer rc_no = new Integer(req.getParameter("rc_no").trim());
				
				/***************************防呆,避免重打**********************/
				RentalClassService rcSvc = new RentalClassService();
				RentalClassVO rcVO = rcSvc.getOneRentalClass(rc_no);
				
				String rc_name = req.getParameter("rc_name");
				if (rc_name == null || rc_name.trim().length() == 0) {
					errorMsgs.add("類別名稱: 請勿空白");
					rc_name = rcVO.getRc_name();
				} 
				
				String rc_item = req.getParameter("rc_item");
				if (rc_item == null || rc_item.trim().length() == 0) {
					errorMsgs.add("種類: 請勿空白");
					rc_item = rcVO.getRc_item();
				}
				
				String rc_detail = req.getParameter("rc_detail");
				
				Integer rc_deposit = null;
				try {
					rc_deposit = new Integer(req.getParameter("rc_deposit").trim());
				} catch (NumberFormatException e) {
					rc_deposit = rcVO.getRc_deposit();
					errorMsgs.add("押金請填數字.");
				}	
				
				Integer rc_price = null;
				try {
					rc_price = new Integer(req.getParameter("rc_price").trim());
				} catch (NumberFormatException e) {
					rc_price = rcVO.getRc_price();
					errorMsgs.add("價格請填數字.");
				}		
				
				Integer rc_total_count = new Integer(req.getParameter("rc_total_count").trim());
				Integer rc_total_score = new Integer(req.getParameter("rc_total_score").trim());
				Integer rc_storage = new Integer(req.getParameter("rc_storage").trim());
				String rc_status = req.getParameter("rc_status");
				

				rcVO.setRc_no(rc_no);
				rcVO.setRc_name(rc_name);
				rcVO.setRc_item(rc_item);
				rcVO.setRc_detail(rc_detail);
				rcVO.setRc_deposit(rc_deposit);
				rcVO.setRc_price(rc_price);
				rcVO.setRc_total_count(rc_total_count);
				rcVO.setRc_total_score(rc_total_score);
				rcVO.setRc_storage(rc_storage);
				rcVO.setRc_status(rc_status);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rcVO", rcVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/rentalClass/update_rc_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
									}
				/***************************2.開始修改資料*****************************************/	
				rcVO = rcSvc.updateRentalClass(rc_no,rc_name,rc_item,rc_detail,rc_deposit,rc_price,rc_total_count,rc_total_score,rc_storage,rc_status);
	
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String requestURL = req.getParameter("requestURL");
				if(requestURL.equals("/back_end/rentalClass/listRcbyItem.jsp"))
					req.setAttribute("list",rcSvc.getOneRc_item(rc_item));
				if(requestURL.equals("/back_end/rentalClass/listOneRc.jsp")) {
					List<RentalClassVO> list = new ArrayList<RentalClassVO>();
					list.add(rcSvc.getOneRentalClass(rc_no));
					req.setAttribute("list",list);
				}
								
				req.setAttribute("rcVO", rcVO); 
				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalClass/update_rc_input.jsp");
				failureView.forward(req, res);
			}		
		}
		
		/***********************查詢*************************/
		if ("getOne_For_Update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				/***************************2.開始查詢資料****************************************/
				RentalClassService rcSvc = new RentalClassService();
				RentalClassVO rcVO = rcSvc.getOneRentalClass(rc_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rcVO", rcVO);         
				String url = "/back_end/rentalClass/update_rc_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		/***********************查詢*************************/
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("rc_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入租賃類別編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer rc_no = null;
				try {
					rc_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RentalClassService rcSvc = new RentalClassService();
				RentalClassVO rcVO = rcSvc.getOneRentalClass(rc_no);
				if (rcVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				List<RentalClassVO> list = new ArrayList<RentalClassVO>();
				list.add(rcVO);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list);
				String url = "/back_end/rentalClass/listOneRc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalClass/listRc.jsp");
				failureView.forward(req, res);
			}
			
		}	
			
		/***********************新增*************************/
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String rc_name = req.getParameter("rc_name");
				if (rc_name == null || rc_name.trim().length() == 0) {
					errorMsgs.add("類別名稱: 請勿空白");
				} 
				
				String rc_item = req.getParameter("rc_item").trim();
				if (rc_item == null || rc_item.trim().length() == 0) {
					errorMsgs.add("種類請勿空白");
				}
				
				String rc_detail = req.getParameter("rc_detail").trim();
				
				Integer rc_deposit = null;
				try {
					rc_deposit = new Integer(req.getParameter("rc_deposit").trim());
				} catch (NumberFormatException e) {
					rc_deposit = 1000000;
					errorMsgs.add("押金請填整數.");
				}
				
				Integer rc_price = null;
				try {
					rc_price = new Integer(req.getParameter("rc_price").trim());
				} catch (NumberFormatException e) {
					rc_price = 1000000;
					errorMsgs.add("價格請填整數.");
				}

				RentalClassVO rcVO = new RentalClassVO();
				rcVO.setRc_name(rc_name);
				rcVO.setRc_item(rc_item);
				rcVO.setRc_detail(rc_detail);
				rcVO.setRc_deposit(rc_deposit);
				rcVO.setRc_price(rc_price);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rcVO", rcVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalClass/addRc.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RentalClassService rcSvc = new RentalClassService();
				rcVO = rcSvc.insertRentalClass(rc_name,rc_item,rc_detail,rc_deposit,rc_price);
				
				Collection<Part> parts = req.getParts();
				RentalProductImagesService rpiSvc = new RentalProductImagesService();
				RentalProductImagesVO rpiVO = new RentalProductImagesVO();
				
				for (Part part : parts) {
					String filename = getFileNameFromPart(part);
										
					if (filename!= null && part.getContentType()!=null) {
						InputStream in = part.getInputStream();
						byte[] buf = new byte[in.available()];
						in.read(buf);
						
						rpiVO = rpiSvc.insertRentalProductImages(rcVO.getRc_no(), buf);
						in.close();
					}
				}	
					
				List<RentalClassVO> list = new ArrayList<RentalClassVO>();
				list.add(rcVO);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("list", list);
				String url = "/back_end/rentalClass/listOneRc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalClass/addRc.jsp");
				failureView.forward(req, res);
			}		
		}
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}		
	
}
