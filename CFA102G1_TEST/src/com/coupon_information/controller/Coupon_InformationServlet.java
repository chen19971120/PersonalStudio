package com.coupon_information.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon_information.model.Coupon_InformationService;
import com.coupon_information.model.Coupon_InformationVO;

public class Coupon_InformationServlet extends HttpServlet {

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
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ci_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入優惠券資訊編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coupon_information/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer ci_no= null;
				try {
					ci_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("優惠券資訊編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coupon_information/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Coupon_InformationService couponInformationSvc = new Coupon_InformationService();
				Coupon_InformationVO couponInformationVO = couponInformationSvc.getOneC_Information(ci_no);
				if (couponInformationVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coupon_information/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("couponInformationVO", couponInformationVO); 
				String url = "/back_end/coupon_information/listOneC_Information.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coupon_information/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer ci_no = new Integer(req.getParameter("ci_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Coupon_InformationService couponInformationSvc = new Coupon_InformationService();
				Coupon_InformationVO couponInformationVO = couponInformationSvc.getOneC_Information(ci_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("couponInformationVO", couponInformationVO);
				String url = "/back_end/coupon_information/update_c_information_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneC_Information.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
				return;
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				優惠劵編號
				Integer ci_no =  new Integer(req.getParameter("ci_no").trim());
//				優惠劵名稱
				String ci_name = req.getParameter("ci_name");
				if(ci_name == null||ci_name.trim().length() == 0) {
					errorMsgs.add("優惠券名稱：請勿空白");
				}
//				編碼
				String ci_code = req.getParameter("ci_code").trim();
				String ci_codeReg = "^[a-zA-Z0-9]{6}$";
				if(ci_code == null|| ci_code.trim().length()==0) {
					errorMsgs.add("優惠券編碼：請勿空白");
				}else if(!ci_code.trim().matches(ci_codeReg)) {
					errorMsgs.add("優惠券編碼：只能是英文字母、數字 , 且長度必需為6");
				}
//				優惠劵開始時間
				Timestamp ci_start_time =null;
				try {
					ci_start_time = Timestamp.valueOf(req.getParameter("ci_start_time").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("優惠劵開始時間：請輸入日期!");
				}
//				優惠劵結束時間
				Timestamp ci_end_time =null;
				try {
					ci_end_time = Timestamp.valueOf(req.getParameter("ci_end_time").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("優惠劵結束時間：請輸入日期!");
				}
//				優惠劵促銷折扣
				Integer discount = null;
				try {
					discount = new Integer(req.getParameter("discount").trim());
				}catch(NumberFormatException e) {
					discount = 0;
					errorMsgs.add("優惠券促銷折扣：請輸入折扣金額");
				}
//				優惠券內容
				String ci_content = req.getParameter("ci_content").trim();
				if(ci_content == null||ci_content.trim().length()==0) {
					errorMsgs.add("優惠券內容：優惠券內容請勿空白");
				}
				
				Coupon_InformationVO couponInformationVO = new Coupon_InformationVO();
				couponInformationVO.setCi_no(ci_no);
				couponInformationVO.setCi_name(ci_name);
				couponInformationVO.setCi_code(ci_code);
				couponInformationVO.setCi_start_time(ci_start_time);
				couponInformationVO.setCi_end_time(ci_end_time);
				couponInformationVO.setDiscount(discount);
				couponInformationVO.setCi_content(ci_content);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coupon_information/update_c_information_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始修改資料*****************************************/
				Coupon_InformationService couponInformationSvc = new Coupon_InformationService();
				couponInformationVO= couponInformationSvc.updateC_Information(ci_no, ci_name, ci_code, ci_start_time, ci_end_time, discount, ci_content);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("couponInformationVO", couponInformationVO); 
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/coupon_information/update_c_information_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				優惠劵名稱
				String ci_name = req.getParameter("ci_name");
				if(ci_name == null||ci_name.trim().length() == 0) {
					errorMsgs.add("優惠券名稱：請勿空白");
				}
//				編碼
				String ci_code = req.getParameter("ci_code").trim();
				String ci_codeReg = "^[a-zA-Z0-9]{6}$";
				if(ci_code == null|| ci_code.trim().length()==0) {
					errorMsgs.add("優惠券編碼：請勿空白");
				}else if(!ci_code.trim().matches(ci_codeReg)) {
					errorMsgs.add("優惠券編碼：只能是英文字母、數字 , 且長度必需為6");
				}
//				優惠劵開始時間
				Timestamp ci_start_time =null;
				try {
					ci_start_time = Timestamp.valueOf(req.getParameter("ci_start_time").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("優惠劵開始時間：請輸入日期!");
				}
//				優惠劵結束時間
				Timestamp ci_end_time =null;
				try {
					ci_end_time = Timestamp.valueOf(req.getParameter("ci_end_time").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("優惠劵結束時間：請輸入日期!");
				}
//				優惠劵促銷折扣
				Integer discount = null;
				try {
					discount = new Integer(req.getParameter("discount").trim());
				}catch(NumberFormatException e) {
					discount = 0;
					errorMsgs.add("優惠券促銷折扣：請輸入折扣金額");
				}
//				優惠券內容
				String ci_content = req.getParameter("ci_content").trim();
				if(ci_content == null||ci_content.trim().length()==0) {
					errorMsgs.add("優惠券內容：優惠券內容請勿空白");
				}
				
				Coupon_InformationVO couponInformationVO = new Coupon_InformationVO();
				couponInformationVO.setCi_name(ci_name);
				couponInformationVO.setCi_code(ci_code);
				couponInformationVO.setCi_start_time(ci_start_time);
				couponInformationVO.setCi_end_time(ci_end_time);
				couponInformationVO.setDiscount(discount);
				couponInformationVO.setCi_content(ci_content);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couponInformationVO", couponInformationVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coupon_information/addC_Information.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始新增資料*****************************************/
				Coupon_InformationService couponInformationSvc = new Coupon_InformationService();
				couponInformationVO = couponInformationSvc.addC_Information(ci_name, ci_code, ci_start_time, ci_end_time, discount, ci_content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				String url = "/back_end/coupon_information/listAllC_Information.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/coupon_information/addC_Information.jsp");
				failureView.forward(req, res);
			}

		}
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer ci_no = new Integer(req.getParameter("ci_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				Coupon_InformationService couponInformationSvc = new Coupon_InformationService();
				couponInformationSvc.deleteC_Information(ci_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/coupon_information/listAllC_Information.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneC_Information.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/coupon_information/listAllC_Information.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
