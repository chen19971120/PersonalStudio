package com.store_information.controller;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import com.consultation.model.ConsultDAO;
import com.consultation.model.ConsultVO;
import com.sec_product_images.model.SecProductImagesService;
import com.sec_product_images.model.SecProductImagesVO;
import com.sec_product_inform.model.ProductInformService;
import com.sec_product_inform.model.ProductInformVO;
import com.store_information.model.*;

public class SiServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String numReg = "[0-9]*";
		String emailReg = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("si_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/store_information/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer si_no = null;
				try {
					si_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/store_information/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SiService siSvc = new SiService();
				SiVO siVO = siSvc.getOneSi(si_no);
				if (siVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/store_information/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("siVO", siVO); // 資料庫取出的siVO物件,存入req
				String url = "/back_end/store_information/listOneSi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneSi.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/store_information/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action))

		{ // 來自listAllSi.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer si_no = new Integer(req.getParameter("si_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				SiService siSvc = new SiService();
				SiVO siVO = siSvc.getOneSi(si_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("siVO", siVO); // 資料庫取出的siVO物件,存入req
				String url = "/back_end/store_information/update_si_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_si_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_si_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer si_no = new Integer(req.getParameter("si_no").trim());

				String si_address = req.getParameter("si_address");
				String si_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
				if (si_address == null || si_address.trim().length() == 0) {
					errorMsgs.add("門市地址: 請勿空白");
				} else if (!si_address.trim().matches(si_addressReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("門市地址: 只能是中、英文字母、數字和_ , 且長度必需在2到60之間");
				}

				String si_open = req.getParameter("si_open").trim();
				if (si_open == null || si_open.trim().length() == 0) {
					errorMsgs.add("營業時間: 請勿空白");
				}

//				String si_phone = req.getParameter("si_phone");
//				if ((si_phone != null || si_phone.trim().length() != 0) && !si_phone.trim().matches(numReg)) {
//					errorMsgs.add("電話號碼只能為數字");
//				}
				
				String si_phone = req.getParameter("si_phone");
				if (si_phone == null || si_phone.trim().length() == 0) {
					errorMsgs.add("門市電話: 請勿空白");
				} else if(!si_phone.trim().matches(numReg)){
					errorMsgs.add("門市電話 格式錯誤");
				}
				
				String si_email = req.getParameter("si_email");
				if (si_email == null || si_email.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				} else if (!si_email.trim().matches(emailReg)) {
					errorMsgs.add("Email 格式須為 example@example.com");
				}

				String si_line = req.getParameter("si_line").trim();
				if (si_line == null || si_line.trim().length() == 0) {
					errorMsgs.add("LINE資訊: 請勿空白");
				}

				SiVO siVO = new SiVO();
				siVO.setSi_no(si_no);
				siVO.setSi_address(si_address);
				siVO.setSi_open(si_open);
				siVO.setSi_phone(si_phone);
				siVO.setSi_email(si_email);
				siVO.setSi_line(si_line);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("siVO", siVO); // 含有輸入格式錯誤的siVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/store_information/update_si_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				SiService siSvc = new SiService();
				siVO = siSvc.updateSi(si_no, si_address, si_open, si_phone, si_email, si_line);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("siVO", siVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/store_information/listAllSi.jsp";///back_end/store_information/listAllSi.jsp
 				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
 				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/store_information/update_si_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addSi.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer si_no = null;
				try {
					si_no = new Integer(req.getParameter("si_no").trim());
					System.out.println(si_no);
				} catch (NumberFormatException e) {
					si_no = 0;
					errorMsgs.add("商家編號: 請勿空白");
				}

				String si_address = req.getParameter("si_address");
				String si_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
				if (si_address == null || si_address.trim().length() == 0) {
					errorMsgs.add("門市地址: 請勿空白");
				} else if (!si_address.trim().matches(si_addressReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("門市地址: 只能是中、英文字母、數字和_ , 且長度必需在2到60之間");
				}
				String si_open = req.getParameter("si_open").trim();
				if (si_open == null || si_open.trim().length() == 0) {
					errorMsgs.add("營業時間: 請勿空白");
				}

				String si_phone = req.getParameter("si_phone");
				if (si_phone == null || si_phone.trim().length() == 0) {
					errorMsgs.add("門市電話: 請勿空白");
				} else if(!si_phone.trim().matches(numReg)){
					errorMsgs.add("門市電話 格式錯誤");
				}

				String si_email = req.getParameter("si_email");
				if (si_email == null || si_email.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				} else if (!si_email.trim().matches(emailReg)) {
					errorMsgs.add("Email 格式須為 example@example.com");
				}

				String si_line = req.getParameter("si_line").trim();
				if (si_line == null || si_line.trim().length() == 0) {
					errorMsgs.add("LINE資訊: 請勿空白");
				}
				SiVO siVO = new SiVO();
				siVO.setSi_no(si_no);
				siVO.setSi_address(si_address);
				siVO.setSi_open(si_open);
				siVO.setSi_phone(si_phone);
				siVO.setSi_email(si_email);
				siVO.setSi_line(si_line);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("siVO", siVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/store_information/addSi.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				SiService siSvc = new SiService();
				siVO = siSvc.addSi(si_no, si_address, si_open, si_phone, si_email, si_line);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/store_information/listAllSi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllSi.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/store_information/addSi.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllSi.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer si_no = new Integer(req.getParameter("si_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				SiService siSvc = new SiService();
				siSvc.deleteSi(si_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/store_information/listAllSi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/store_information/listAllSi.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_From06".equals(action)) {

			try {
				// Retrieve form parameters.
				Integer si_no = new Integer(req.getParameter("si_no"));

				SiDAO dao = new SiDAO();
				SiVO siVO = dao.findByPrimaryKey(si_no);

				req.setAttribute("siVO", siVO); // 資料庫取出的siVO物件,存入req

				// Bootstrap_modal
				boolean openModal = true;
				req.setAttribute("openModal", openModal);

				// 取出的empVO送給listOneSi.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/store_information/listAllSi.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
