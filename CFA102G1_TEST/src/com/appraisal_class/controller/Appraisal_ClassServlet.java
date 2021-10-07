package com.appraisal_class.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appraisal_case.model.Appraisal_CaseVO;
import com.appraisal_class.model.Appraisal_ClassService;
import com.appraisal_class.model.Appraisal_ClassVO;

public class Appraisal_ClassServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer acl_no = new Integer(req.getParameter("acl_no"));

				/*************************** 2.開始查詢資料 *****************************************/
				Appraisal_ClassService appraisalClassSvc = new Appraisal_ClassService();
				Appraisal_ClassVO appraisalClassVO = appraisalClassSvc.getOneA_Class(acl_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("appraisalClassVO", appraisalClassVO);
				String url = "/back_end/appraisal_class/listOneA_Class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Class.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_class/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer acl_no = new Integer(req.getParameter("acl_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Appraisal_ClassService appraisalClassSvc = new Appraisal_ClassService();
				Appraisal_ClassVO appraisalClassVO = appraisalClassSvc.getOneA_Class(acl_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("appraisalClassVO", appraisalClassVO);
				String url = "/back_end/appraisal_class/update_a_class_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Class.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/appraisal_class/listAllA_Class.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer acl_no = new Integer(req.getParameter("acl_no").trim());

				String acl_id = req.getParameter("acl_id");
				if (acl_id == null || acl_id.trim().length() == 0) {
					errorMsgs.add("估價類別名稱：請勿空白");
				}
				Appraisal_ClassVO appraisalClassVO = new Appraisal_ClassVO();
				appraisalClassVO.setAcl_no(acl_no);
				appraisalClassVO.setAcl_id(acl_id);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appraisalClassVO", appraisalClassVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/appraisal_class/update_a_class_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				Appraisal_ClassService appraisalClassSvc = new Appraisal_ClassService();
				appraisalClassVO = appraisalClassSvc.updateA_Class(acl_no, acl_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("appraisalClassVO", appraisalClassVO);
				String url = "/back_end/appraisal_class/listAllA_Class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Class.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/appraisal_class/update_a_class_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String acl_id = req.getParameter("acl_id");

				if (acl_id == null || acl_id.trim().length() == 0) {
					errorMsgs.add("估價類別名稱：請勿空白");
				}

				Appraisal_ClassVO appraisalClassVO = new Appraisal_ClassVO();
				appraisalClassVO.setAcl_id(acl_id);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appraisalClassVO", appraisalClassVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/appraisal_class/addA_Class.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始新增資料 ***************************************/
				Appraisal_ClassService appraisalClassSvc = new Appraisal_ClassService();
				appraisalClassVO = appraisalClassSvc.addA_Class(acl_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/appraisal_class/listAllA_Class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Class.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_class/addA_Class.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer acl_no = new Integer(req.getParameter("acl_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				Appraisal_ClassService appraisalClassSvc = new Appraisal_ClassService();
				appraisalClassSvc.deleteA_Class(acl_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/appraisal_class/listAllA_Class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Class.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/appraisal_class/listAllA_Class.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listCaseByClass_A".equals(action) || "listCase_ByClass_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer acl_no = new Integer(req.getParameter("acl_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Appraisal_ClassService appraisalClassSvc = new Appraisal_ClassService();
				Set<Appraisal_CaseVO> set = appraisalClassSvc.getA_CaseByA_Class(acl_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listCase_ByClass", set);
				
				String url = null;
				if ("listCaseByClass_A".equals(action)) {
					url = "/back_end/appraisal_class/listCase_ByClass.jsp";
				}else if ("listCase_ByClass_B".equals(action)) {
					url = "/back_end/appraisal_class/listAllA_Class.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 ***********************************/
			}catch (Exception e) {
				throw new ServletException(e);
			}

		}
	}
}
