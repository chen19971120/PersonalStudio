package com.rentalProductList.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rentalClass.model.RentalClassService;
import com.rentalProductList.model.*;

public class RentalProductListServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_Change_Status".equals(action)) { 
			
			try {
				Integer rpl_no = new Integer(req.getParameter("rpl_no"));
				String rpl_status = req.getParameter("rpl_status");
				String rpl_status2 = req.getParameter("rpl_status2");
				
				RentalProductListService rplSvc = new RentalProductListService();
				Integer rc_no = rplSvc.getOneRentalProductList(rpl_no).getRc_no();
				rplSvc.changeRplStatus(rpl_no);
				
				String requestURL = req.getParameter("requestURL");
				if(requestURL.equals("/back_end/rentalProductList/listRplbyRC.jsp"))
					req.setAttribute("list",rplSvc.getOneRentalClassList(rc_no));
				if(requestURL.equals("/back_end/rentalProductList/listOneRpl.jsp")) {
					List<RentalProductListVO> list = new ArrayList<RentalProductListVO>();
					list.add(rplSvc.getOneRentalProductList(rpl_no));
					req.setAttribute("list",list);
				}
									
				req.setAttribute("rcVOselect", rc_no);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer rpl_no = new Integer(req.getParameter("rpl_no"));
				
				/***************************2.開始刪除資料***************************************/
				RentalProductListService rplSvc = new RentalProductListService();
				Integer rc_no = rplSvc.getOneRentalProductList(rpl_no).getRc_no();
				rplSvc.deleteRentalProductList(rpl_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String requestURL = req.getParameter("requestURL");
				if(requestURL.equals("/back_end/rentalProductList/listRplbyRC.jsp"))
					req.setAttribute("list",rplSvc.getOneRentalClassList(rc_no));
				
				req.setAttribute("rcVOselect", rc_no);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
				failureView.forward(req, res);
			}
		}	
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/	
				Integer rpl_no = new Integer(req.getParameter("rpl_no").trim());
				
				Integer rc_no = new Integer(req.getParameter("rc_no").trim());
				
				String rpl_serialnum = req.getParameter("rpl_serialnum");
				if (rpl_serialnum == null || rpl_serialnum.trim().length() == 0) {
					errorMsgs.add("產品序號: 請勿空白");
				}
				
				String rpl_note = req.getParameter("rpl_note");
				
				Integer rpl_rentcount = new Integer(req.getParameter("rpl_rentcount").trim());
				
				String rpl_jointtime = req.getParameter("rpl_jointtime");
				Timestamp tsrpl_jointtime = Timestamp.valueOf(rpl_jointtime);
				
				String rpl_status = req.getParameter("rpl_status");
						
				RentalProductListVO rplVO = new RentalProductListVO();


				
				rplVO.setRpl_no(rpl_no);
				rplVO.setRc_no(rc_no);
				rplVO.setRpl_serialnum(rpl_serialnum);
				rplVO.setRpl_note(rpl_note);
				rplVO.setRpl_status(rpl_status);
				rplVO.setRpl_rentcount(rpl_rentcount);
				rplVO.setRpl_jointtime(tsrpl_jointtime);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rplVO", rplVO);
					RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/rentalProductList/update_rpl_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
									}
				/***************************2.開始修改資料*****************************************/
				
				RentalProductListService rplSvc = new RentalProductListService();
				rplVO = rplSvc.updateRentalProductList(rpl_no,rc_no,rpl_serialnum,rpl_note,rpl_status,
						rpl_rentcount, tsrpl_jointtime);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String requestURL = req.getParameter("requestURL");
				if(requestURL.equals("/back_end/rentalProductList/listRplbyRC.jsp"))
					req.setAttribute("list",rplSvc.getOneRentalClassList(rc_no));
				if(requestURL.equals("/back_end/rentalProductList/listOneRpl.jsp")) {
					List<RentalProductListVO> list = new ArrayList<RentalProductListVO>();
					list.add(rplSvc.getOneRentalProductList(rpl_no));
					req.setAttribute("list",list);
				}
				
				
				req.setAttribute("rcVOselect", rc_no);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductList/update_rpl_input.jsp");
				failureView.forward(req, res);
			}

		}
		
		
		if ("getOne_For_Update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer rpl_no = new Integer(req.getParameter("rpl_no"));
				
				/***************************2.開始查詢資料****************************************/
				RentalProductListService rplSvc = new RentalProductListService();
				RentalProductListVO rplVO = rplSvc.getOneRentalProductList(rpl_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rplVO", rplVO);         
				String url = "/back_end/rentalProductList/update_rpl_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductList/update_rpl_input.jsp");
				failureView.forward(req, res);
			}


		}
		
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("rpl_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer rpl_no = null;
				try {
					rpl_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RentalProductListService rplSvc = new RentalProductListService();
				RentalProductListVO rplVO = rplSvc.getOneRentalProductList(rpl_no);
				if (rplVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				List<RentalProductListVO> list = new ArrayList<RentalProductListVO>();
				list.add(rplVO);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/back_end/rentalProductList/listOneRpl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
				failureView.forward(req, res);
			}
		}	
		
		if ("getOneClass_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer rc_no = new Integer(req.getParameter("rc_no"));	
				
				/***************************2.開始查詢資料*****************************************/
				RentalProductListService rplSvc = new RentalProductListService();		
				List<RentalProductListVO> list = rplSvc.getOneRentalClassList(rc_no);
							
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); 
				req.setAttribute("rcVOselect", rc_no); 
				String url = "/back_end/rentalProductList/listRplbyRC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneRc_item_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String rc_item = req.getParameter("rc_item");
				if (rc_item == null || (rc_item.trim()).length() == 0) {
					errorMsgs.add("請輸入種類");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductList/listRpi.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
						
				/***************************2.開始查詢資料*****************************************/
				
				RentalProductListService rplSvc = new RentalProductListService();		
				List<RentalProductListVO> list = rplSvc.getOneRc_itemList(rc_item);
							
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); 
				req.setAttribute("rc_itemselect", rc_item); 
				String url = "/back_end/rentalProductList/listRpl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductList/listRpl.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("addRpl".equals(action)) {
			Integer rcVOselect = new Integer(req.getParameter("rcVOselect"));
			req.setAttribute("rcVOselect", rcVOselect);
			String url = "/back_end/rentalProductList/addRpl.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
			
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				String rpl_serialnum = req.getParameter("rpl_serialnum");
				
				if (rpl_serialnum == null || rpl_serialnum.trim().length() == 0) {
					errorMsgs.add("產品序號: 請勿空白");
				}
				
				String rpl_note = req.getParameter("rpl_note");

				RentalProductListVO rplVO = new RentalProductListVO();
				rplVO.setRc_no(rc_no);
				rplVO.setRpl_serialnum(rpl_serialnum);
				rplVO.setRpl_note(rpl_note);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rplVO", rplVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductList/addRpl.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RentalProductListService rplSvc = new RentalProductListService();
				rplVO = rplSvc.insertRentalProductList(rc_no,rpl_serialnum,rpl_note);
				
				List<RentalProductListVO> list = new ArrayList<RentalProductListVO>();
				list.add(rplVO);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("list", list);
				String url = "/back_end/rentalProductList/listOneRpl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductList/addRpl.jsp");
				failureView.forward(req, res);
			}	
		}
	}
}	
