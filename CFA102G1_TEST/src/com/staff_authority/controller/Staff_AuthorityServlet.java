package com.staff_authority.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.staff_authority.model.Staff_AuthorityService;
import com.staff_authority.model.Staff_AuthorityVO;

public class Staff_AuthorityServlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {// 來自select_page.jsp的請求
      List<String>errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			try {/***1.接收請求參數-輸入格是的錯誤處理***/
				String str = req.getParameter("staff_no");
				if(str == null||(str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("path");
					failureView.forward(req, res);
					return;
				}
				Integer staff_no = null;
				
				try {
					staff_no = new Integer(str);
				}catch(Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("path");
					failureView.forward(req, res);
					return;
				}
				/**********2.開始查詢資料***********/
				Staff_AuthorityService staff_authoritySvc = new Staff_AuthorityService();
				Staff_AuthorityVO staff_authorityVO = staff_authoritySvc.getOneStaff_Authority(staff_no);
				if(staff_authorityVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("path");
					failureView.forward(req, res);
					return;
				}
				/************3.查尋完成，準備轉交**************/
	            req.setAttribute("staff_authorityVO", staff_authorityVO);	//資料庫取出的staffVO物件,存入req	
			    String url = "/path/jsp";
			    RequestDispatcher successView = req.getRequestDispatcher(url);
			    successView.forward(req, res);
			    }catch(Exception e) {
			    	errorMsgs.add("無法取得資料:"+ e.getMessage());// 成功轉交 listOneMember.jsp
			    	RequestDispatcher failureView = req
			    			.getRequestDispatcher("/path/jsp");
			    	failureView.forward(req, res);
			    }
       }
		
		
	}
}
