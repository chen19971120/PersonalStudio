package com.consultation.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.consultation.model.*;

public class ConsultServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String emailReg = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
		String numReg = "[0-9]*";
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("consult_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入諮詢單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/consultation/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer consult_no = null;
				try {
					consult_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/consultation/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ConsultService consultSvc = new ConsultService();
				ConsultVO consultVO = consultSvc.getOneConsult(consult_no);
				if (consultVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/consultation/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("consultVO", consultVO); // 資料庫取出的consultVO物件,存入req
				String url = "/back_end/consultation/listOneConsult.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneConsult.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/consultation/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllConsult.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer consult_no = new Integer(req.getParameter("consult_no"));
				
				/***************************2.開始查詢資料****************************************/
				ConsultService consultSvc = new ConsultService();
				ConsultVO consultVO = consultSvc.getOneConsult(consult_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("consultVO", consultVO);         // 資料庫取出的consultVO物件,存入req
				String url = "/back_end/consultation/update_consult_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_consult_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_consult_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer consult_no = new Integer(req.getParameter("consult_no").trim());
				
				String consultant = req.getParameter("consultant");
				String consultantReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (consultant == null || consultant.trim().length() == 0) {
					errorMsgs.add("諮詢人姓名: 請勿空白");
				} else if(!consultant.trim().matches(consultantReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("諮詢人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
//				String consult_phone = req.getParameter("consult_phone");
//				if ((consult_phone != null || consult_phone.trim().length() != 0) && !consult_phone.trim().matches(numReg)) {
//					errorMsgs.add("手機號碼只能為數字");
//				}
				
				String consult_phone = req.getParameter("consult_phone");
				if (consult_phone == null || consult_phone.trim().length() == 0) {
					errorMsgs.add("諮詢人手機 請勿空白");
				} else if(!consult_phone.trim().matches(numReg)){
					errorMsgs.add("諮詢人手機 格式錯誤");
				}
				
				
				String consult_email = req.getParameter("consult_email");
				if (consult_email == null || consult_email.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				} else if(!consult_email.trim().matches(emailReg)){
					errorMsgs.add("Email 格式須為 example@example.com");
				}
				
				String consult_content = req.getParameter("consult_content").trim();
				if (consult_content == null || consult_content.trim().length() == 0) {
					errorMsgs.add("諮詢內容: 請勿空白");
				}

				Integer staff_no = null;
				try {
					staff_no = new Integer(req.getParameter("staff_no").trim());
				} catch (NumberFormatException e) {
					staff_no = 0;
					errorMsgs.add("員工編號: 請勿空白");
				}
				String consult_sta = req.getParameter("consult_sta").trim();
				if (consult_sta == null || consult_sta.trim().length() == 0) {
					errorMsgs.add("回覆狀態: 請勿空白");
				}
				
				ConsultVO consultVO = new ConsultVO();
				consultVO.setConsult_no(consult_no);
				consultVO.setConsultant(consultant);
				consultVO.setConsult_phone(consult_phone);
				consultVO.setConsult_email(consult_email);
				consultVO.setConsult_content(consult_content);
				consultVO.setStaff_no(staff_no);
				consultVO.setConsult_sta(consult_sta);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("consultVO", consultVO); // 含有輸入格式錯誤的consultVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/consultation/update_consult_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ConsultService consultSvc = new ConsultService();
				consultVO = consultSvc.updateConsult(consult_no,consultant,consult_phone,consult_email,consult_content,
						staff_no,consult_sta);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("consultVO", consultVO); // 資料庫update成功後,正確的的consultVO物件,存入req
				String url = "/back_end/consultation/listAllConsult.jsp";
				 				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				 				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/consultation/update_consult_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addConsult.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String consultant = req.getParameter("consultant");
				String consultantReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (consultant == null || consultant.trim().length() == 0) {
					errorMsgs.add("諮詢人姓名: 請勿空白");
				} else if(!consultant.trim().matches(consultantReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("諮詢人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String consult_phone = req.getParameter("consult_phone");
				if (consult_phone == null || consult_phone.trim().length() == 0) {
					errorMsgs.add("諮詢人手機 請勿空白");
				} else if(!consult_phone.trim().matches(numReg)){
					errorMsgs.add("諮詢人手機 格式錯誤");
				}
				
				String consult_email = req.getParameter("consult_email");
				if (consult_email == null || consult_email.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				} else if(!consult_email.trim().matches(emailReg)){
					errorMsgs.add("Email 格式須為 example@example.com");
				}
				
				String consult_content = req.getParameter("consult_content").trim();
				if (consult_content == null || consult_content.trim().length() == 0) {
					errorMsgs.add("諮詢內容: 請勿空白");
				}

				Integer staff_no = null;
				try {
					staff_no = new Integer(req.getParameter("staff_no").trim());
				} catch (NumberFormatException e) {
					staff_no = 0;
					errorMsgs.add("員工編號: 請勿空白");
				}
				
				String consult_sta = req.getParameter("consult_sta").trim();
				if (consult_sta == null || consult_sta.trim().length() == 0) {
					errorMsgs.add("回覆狀態: 請勿空白");
				}

				ConsultVO consultVO = new ConsultVO();
				consultVO.setConsultant(consultant);
				consultVO.setConsult_phone(consult_phone);
				consultVO.setConsult_email(consult_email);
				consultVO.setConsult_content(consult_content);
				consultVO.setStaff_no(staff_no);
				consultVO.setConsult_sta(consult_sta);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("consultVO", consultVO); // 含有輸入格式錯誤的consultVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/consultation/consult.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ConsultService consultSvc = new ConsultService();
				consultVO = consultSvc.addConsult(consultant,consult_phone,consult_email,consult_content,
						staff_no,consult_sta);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/consultation/consultsuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllConsult.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/consultation/consult.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllConsult.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer consult_no = new Integer(req.getParameter("consult_no"));
				
				/***************************2.開始刪除資料***************************************/
				ConsultService consultSvc = new ConsultService();
				consultSvc.deleteConsult(consult_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/consultation/listAllConsult.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/consultation/listAllConsult.jsp");
				failureView.forward(req, res);
			}
		}
	

	if ("getOne_From06".equals(action)) {

		try {
			// Retrieve form parameters.
			Integer consult_no = new Integer(req.getParameter("consult_no"));

			ConsultDAO dao = new ConsultDAO();
			ConsultVO consultVO = dao.findByPrimaryKey(consult_no);

			req.setAttribute("consultVO", consultVO); // 資料庫取出的consultVO物件,存入req
			
			//Bootstrap_modal
			boolean openModal=true;
			req.setAttribute("openModal",openModal );
			
			// 取出的empVO送給listOneConsult.jsp
			RequestDispatcher successView = req
					.getRequestDispatcher("/back_end/consultation/listAllConsult.jsp");
			successView.forward(req, res);
			return;

			// Handle any unusual exceptions
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
}
