package com.appraisal_case.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.appraisal_case.model.Appraisal_CaseService;
import com.appraisal_case.model.Appraisal_CaseVO;
import com.appraisal_case_images.model.Appraisal_Case_ImagesService;
import com.appraisal_case_images.model.Appraisal_Case_ImagesVO;
import com.appraisal_class.model.Appraisal_ClassService;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@javax.servlet.annotation.MultipartConfig
public class Appraisal_CaseServlet extends HttpServlet {

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
				String str = req.getParameter("aca_no");
				if (str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請輸入估價案件編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/appraisal_case/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer aca_no = null;
				try {
					aca_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("估價案件編號格是不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/appraisal_case/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				Appraisal_CaseVO appraisalCaseVO = appraisalCaseSvc.getOneA_Case(aca_no);
				
				Integer aca_no1 = new Integer(req.getParameter("aca_no"));
				Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
				List<Appraisal_Case_ImagesVO> appraisalCaseImagesVO = appraisalCaseImagesSvc.getAll().stream().filter(i -> i.getAca_no().intValue() == aca_no1.intValue()).collect(Collectors.toList());
				
				if (appraisalCaseVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_case/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("appraisalCaseVO", appraisalCaseVO);
				req.setAttribute("appraisalCaseImagesVO", appraisalCaseImagesVO);
				String url = "/back_end/appraisal_case/listOneA_Case.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Case.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_case/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer aca_no = new Integer(req.getParameter("aca_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				Appraisal_CaseVO appraisalCaseVO = appraisalCaseSvc.getOneA_Case(aca_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("appraisalCaseVO", appraisalCaseVO);
				String url = "/back_end/appraisal_case/update_a_case_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Case.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer aca_no = new Integer(req.getParameter("aca_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				// 估價商品名稱
				String aca_itm_id = req.getParameter("aca_itm_id");
				if (aca_itm_id == null || aca_itm_id.trim().length() == 0) {
					errorMsgs.add("估價商品名稱請勿空白");
				}
				// 商品類別編號
				Integer acl_no = new Integer(req.getParameter("acl_no").trim());

				// 估價商品內容(規格)
				String aca_itm_spec = req.getParameter("aca_itm_spec");
				// 案件日期
				Timestamp aca_date = null;
				try {
					aca_date = Timestamp.valueOf(req.getParameter("aca_date").trim());
				} catch (IllegalArgumentException e) {
					aca_date = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("案件日期：請輸入日期");
				}

				// 案件狀態
				String aca_itm_mode = req.getParameter("aca_itm_mode");

				// 報價
				Integer aca_first_p = null;
				try {
					aca_first_p = new Integer(req.getParameter("aca_first_p").trim());
				} catch (NumberFormatException e) {
					aca_first_p = 0;
				}

				// 門市收貨日期
				Timestamp aca_recpt_date = null;
				try {
					aca_recpt_date = Timestamp.valueOf(req.getParameter("aca_recpt_date").trim());
				} catch (IllegalArgumentException e) {
					if("已收取商品".equals(aca_itm_mode)) {
						errorMsgs.add("門市收貨日期：請輸入日期");
					}else {
						aca_recpt_date = null;
					}
				}
				// 成交價，可先不給值
				Integer aca_final_p = null;
				try {
					aca_final_p = new Integer(req.getParameter("aca_final_p").trim());
				} catch (NumberFormatException e) {
					aca_final_p = 0;
				}

				// 出貨日期
				Timestamp aca_shipment_date = null;
				try {
					aca_shipment_date = Timestamp.valueOf(req.getParameter("aca_shipment_date").trim());
				} catch (IllegalArgumentException e) {
					if("商品退回".equals(aca_itm_mode)) {
						errorMsgs.add("出貨日期：請輸入日期");
					}else {
						aca_shipment_date = null;
					}
				}

				// 取貨日期，可先不給值
				Timestamp aca_pickup_date = null;
				try {
					aca_pickup_date = Timestamp.valueOf(req.getParameter("aca_pickup_date").trim());
				} catch (IllegalArgumentException e) {
						aca_pickup_date = null;
				}

				// 付款方式
				String aca_pay = req.getParameter("aca_pay");

				// 完成日期，可先不給值
				Timestamp aca_comp_date = null;
				try {
					aca_comp_date = Timestamp.valueOf(req.getParameter("aca_comp_date").trim());
				} catch (IllegalArgumentException e) {
						aca_comp_date = null;
				}

				// 運送方式
				String aca_cod = req.getParameter("aca_cod");

				// 配送地址
				String aca_adrs = req.getParameter("aca_adrs").trim();
				if (aca_adrs == null || aca_adrs.trim().length() == 0) {
					errorMsgs.add("配送地址：請輸入配送地址");
				}

				Appraisal_CaseVO appraisalCaseVO = new Appraisal_CaseVO();
				appraisalCaseVO.setAca_no(aca_no);
				appraisalCaseVO.setMem_no(mem_no);
				appraisalCaseVO.setAca_itm_id(aca_itm_id);
				appraisalCaseVO.setAcl_no(acl_no);
				appraisalCaseVO.setAca_itm_spec(aca_itm_spec);
				appraisalCaseVO.setAca_date(aca_date);
				appraisalCaseVO.setAca_itm_mode(aca_itm_mode);
				appraisalCaseVO.setAca_first_p(aca_first_p);
				appraisalCaseVO.setAca_recpt_date(aca_recpt_date);
				appraisalCaseVO.setAca_final_p(aca_final_p);
				appraisalCaseVO.setAca_shipment_date(aca_shipment_date);
				appraisalCaseVO.setAca_pickup_date(aca_pickup_date);
				appraisalCaseVO.setAca_pay(aca_pay);
				appraisalCaseVO.setAca_comp_date(aca_comp_date);
				appraisalCaseVO.setAca_cod(aca_cod);
				appraisalCaseVO.setAca_adrs(aca_adrs);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appraisalCaseVO", appraisalCaseVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_case/update_a_case_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				appraisalCaseVO = appraisalCaseSvc.updateA_Case(aca_no, mem_no, aca_itm_id, acl_no, aca_itm_spec,
						aca_date, aca_itm_mode, aca_first_p, aca_recpt_date, aca_final_p, aca_shipment_date,
						aca_pickup_date, aca_pay, aca_comp_date, aca_cod, aca_adrs);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				Appraisal_ClassService appraisalClassSvc = new Appraisal_ClassService();
				if (requestURL.equals("/back_end/appraisal_class/listCase_ByClass.jsp")|| requestURL.equals("/back_end/appraisal_class/listAllA_Class.jsp")) {
					req.setAttribute("listCase_ByClass", appraisalClassSvc.getA_CaseByA_Class(acl_no));
				}
				else if(requestURL.equals("/back_end/appraisal_case/listOneA_Case.jsp")||requestURL.equals("/back_end/appraisal_case/listAllA_Case.jsp")) {
					req.setAttribute("appraisalCaseVO", appraisalCaseVO);
				}
				else if(requestURL.equals("/back_end/appraisal_case/listA_Case_ByCompositeQuery.jsp")) {
					List<Appraisal_CaseVO> list = appraisalCaseSvc.getAll().stream().filter(i -> i.getAca_no().intValue() ==aca_no.intValue()).collect(Collectors.toList());
					req.setAttribute("listA_Case_ByCompositeQuery", list);
				}
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Case.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_case/update_a_case_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 查詢有無此會員 若無會員無法新增估價單
				Integer mem_no = null;
				try {
					mem_no = new Integer(req.getParameter("mem_no"));
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入會員編號(格式為數字)");
				}
				// 估價商品名稱
				String aca_itm_id = req.getParameter("aca_itm_id");
				if (aca_itm_id == null || aca_itm_id.trim().length() == 0) {
					errorMsgs.add("估價商品名稱請勿空白");
				}
				// 商品類別編號
				Integer acl_no = new Integer(req.getParameter("acl_no").trim());

				// 估價商品內容(規格)
				String aca_itm_spec = req.getParameter("aca_itm_spec");

				// 案件日期
				Timestamp aca_date = null;
				try {
					aca_date = Timestamp.valueOf(req.getParameter("aca_date").trim());
				} catch (IllegalArgumentException e) {
					aca_date = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("案件日期：請輸入日期");
				}

				// 案件狀態
				String aca_itm_mode = req.getParameter("aca_itm_mode");

				// 報價
				Integer aca_first_p = null;
				try {
					aca_first_p = new Integer(req.getParameter("aca_first_p").trim());
				} catch (NumberFormatException e) {
					aca_first_p = 0;
				}

				// 門市收貨日期
				Timestamp aca_recpt_date = null;
				try {
					aca_recpt_date = Timestamp.valueOf(req.getParameter("aca_recpt_date").trim());
				} catch (IllegalArgumentException e) {
					aca_recpt_date = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("門市收貨日期：請輸入日期");
				}
				// 成交價，可先不給值
				Integer aca_final_p = null;
				try {
					aca_final_p = new Integer(req.getParameter("aca_final_p").trim());
				} catch (NumberFormatException e) {
					aca_final_p = 0;
				}

				// 出貨日期，可先不給值
				Timestamp aca_shipment_date = null;

				// 取貨日期，可先不給值
				Timestamp aca_pickup_date = null;

				// 付款方式
				String aca_pay = req.getParameter("aca_pay");

				// 完成日期，可先不給值
				Timestamp aca_comp_date = null;

				// 運送方式
				String aca_cod = req.getParameter("aca_cod");

				// 配送地址
				String aca_adrs = req.getParameter("aca_adrs");
				if (aca_adrs == null || aca_adrs.trim().length() == 0) {
					errorMsgs.add("請輸入配送地址");
				}

				Appraisal_CaseVO appraisalCaseVO = new Appraisal_CaseVO();
				appraisalCaseVO.setMem_no(mem_no);
				appraisalCaseVO.setAca_itm_id(aca_itm_id);
				appraisalCaseVO.setAcl_no(acl_no);
				appraisalCaseVO.setAca_itm_spec(aca_itm_spec);
				appraisalCaseVO.setAca_date(aca_date);
				appraisalCaseVO.setAca_itm_mode(aca_itm_mode);
				appraisalCaseVO.setAca_first_p(aca_first_p);
				appraisalCaseVO.setAca_recpt_date(aca_recpt_date);
				appraisalCaseVO.setAca_final_p(aca_final_p);
				appraisalCaseVO.setAca_shipment_date(aca_shipment_date);
				appraisalCaseVO.setAca_pickup_date(aca_pickup_date);
				appraisalCaseVO.setAca_pay(aca_pay);
				appraisalCaseVO.setAca_comp_date(aca_comp_date);
				appraisalCaseVO.setAca_cod(aca_cod);
				appraisalCaseVO.setAca_adrs(aca_adrs);

				/*************************** 2.開始新增資料 ***************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(mem_no);
				if (memVO == null) {
					errorMsgs.add("查無此會員，請輸入現有會員");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appraisalCaseVO", appraisalCaseVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_case/addA_Case.jsp");
					failureView.forward(req, res);
					return;
				}

				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				appraisalCaseVO = appraisalCaseSvc.addA_Case(mem_no, aca_itm_id, acl_no, aca_itm_spec, aca_date,
						aca_itm_mode, aca_first_p, aca_recpt_date, aca_final_p, aca_shipment_date, aca_pickup_date,
						aca_pay, aca_comp_date, aca_cod, aca_adrs);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/appraisal_case/listAllA_Case.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Case.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_case/addA_Case.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listA_Case_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/*************************** 1.將輸入資料轉為Map **********************************/
				Map<String ,String[]>map = req.getParameterMap();

				/*************************** 2.開始複合查詢 ***************************************/
				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				List<Appraisal_CaseVO> list = appraisalCaseSvc.getAll(map);
				req.setAttribute("listA_Case_ByCompositeQuery", list);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back_end/appraisal_case/listA_Case_ByCompositeQuery.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_case/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("information".equals(action)) {
			try {
				Integer aca_no = new Integer(req.getParameter("aca_no"));
				
				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				Appraisal_CaseVO appraisalCaseVO = appraisalCaseSvc.getOneA_Case(aca_no);
				req.setAttribute("appraisalCaseVO", appraisalCaseVO);
				
				String url = "/back_end/appraisal_case/A_CaseInformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Case.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer aca_no = new Integer(req.getParameter("aca_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				appraisalCaseSvc.deleteA_Case(aca_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/appraisal_case/listAllA_Case.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Case.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_case/listAllA_Case.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
