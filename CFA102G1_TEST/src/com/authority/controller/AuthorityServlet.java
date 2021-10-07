package com.authority.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.authority.model.AuthorityService;
import com.authority.model.AuthorityVO;
import com.staff.model.StaffService;
import com.staff.model.StaffVO;

public class AuthorityServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
	}
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
    	
    	req.setCharacterEncoding("UTF-8");
    	String action = req.getParameter("action");
    	
    	if("getOne_For_Display".equals(action)) {//select_page.jsp的請求
    	List<String>errorMsgs = new LinkedList<String>();
    	req.setAttribute("errorMsgs", errorMsgs);
    	try {/***1.接收請求參數-輸入格是的錯誤處理***/
    		String str = req.getParameter("authority_no");
    		if(str == null||(str.trim()).length()==0) {
    			errorMsgs.add("請輸入功能編號");
    		}
    		if(!errorMsgs.isEmpty()) {
    			RequestDispatcher failureView = req
    					.getRequestDispatcher("path");
    			failureView.forward(req, res);
    			return;
    					
    		}
    		Integer authority_no = null;
    		
    		try {
    			authority_no = new Integer(str);
    		}catch(Exception e) {
    			errorMsgs.add("功能編碼格式不正確");
    		}
    		if(!errorMsgs.isEmpty()) {
    			RequestDispatcher failureView = req
    					.getRequestDispatcher("path");
    			failureView.forward(req, res);
    			return;
    		}
    		/**********2.開始查詢資料***********/
            AuthorityService authoritySvc = new AuthorityService();
            AuthorityVO authorityVO = authoritySvc.getOneAuthority(authority_no);
            if(authorityVO == null) {
            	errorMsgs.add("查無資料");
            }
            if(!errorMsgs.isEmpty()) {
            	RequestDispatcher failureView = req
            			.getRequestDispatcher("path");
            	failureView.forward(req, res);
            	return;
            }
            /************3.查尋完成，準備轉交**************/
            req.setAttribute("authorityVO", authorityVO);//資料庫取出的memberVO物件,存入req
            String url = "/path/jsp";
            RequestDispatcher successView = req
            		.getRequestDispatcher(url);
            successView.forward(req, res);
	    }catch(Exception e) {
	    	errorMsgs.add("無法取得資料:"+ e.getMessage());// 成功轉交 listOneMember.jsp
	    	RequestDispatcher failureView = req
	    			.getRequestDispatcher("/path/jsp");
	    	failureView.forward(req, res);
	    }
    	
    	}
    	if("getOne_For_Update".equals(action)) {// 來自listAllMember.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************1.接收參數*************/
				Integer authority_no = new Integer(req.getParameter("authority_no"));
				/*************2.開始查詢資料*******************/
				AuthorityService authoritySvc = new AuthorityService();
				AuthorityVO authorityVO = authoritySvc.getOneAuthority(authority_no);
				/*************3.查尋完成，準備轉交*********************/
				req.setAttribute("authorityVO", authorityVO);//資料庫取出的memberVO物件,存入req
				String url = "path/jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);
				
				/*************可能的其他錯誤*********/
				
				}catch(Exception e) {
					errorMsgs.add("無法取得要修改的資料:"+ e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("path");
					failureView.forward(req, res);
				}
		}
		if("update".equals(action)) {// 來自update_member_input.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************接收請求參數-輸入可是錯誤處理*******/
				Integer authority_no = new Integer(req.getParameter("authority_no").trim());
				String authority_name = req.getParameter("authority_name");
				String authority_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(authority_name == null||authority_name.trim().length()==0) {
					errorMsgs.add("功能名稱:請勿空白");
				}else if(!authority_name.trim().matches(authority_nameReg)) {
					errorMsgs.add("功能名稱:只能是中英文、數字，長度必須在2到10之間");
				}	
					
				
				AuthorityVO authorityVO = new AuthorityVO();
				authorityVO.setAuthority_no(authority_no);
				authorityVO.setAuthority_name(authority_name);
				
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("authorityVO", authorityVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("path");
					failureView.forward(req, res);
					return;
				}
				
				/*************2.開始修改資料***************/
				AuthorityService authoritySvc = new AuthorityService();
				authorityVO = authoritySvc.updateAuthority(authority_no, authority_name);
				/*************3.修改完成,準備轉交*****************/
				req.setAttribute("authorityVO", authorityVO);//資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/path/";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************其他可能的錯誤******************/
				}catch(Exception e) {
					errorMsgs.add("修改資料失敗:"+ e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("path");
					failureView.forward(req, res);
					
				}
			   
		}
		if("insert".equals(action)) {//來自addMember.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
            
			try {
				/**************1.接收請求參數-輸入格式錯誤處理******************/
				String authority_name = req.getParameter("authority_name");
				String authority_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (authority_name == null || authority_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!authority_name.trim().matches(authority_nameReg)) { 
					errorMsgs.add("會員姓名: 只能是中英文字母、數字和且長度必需在2到10之間");
	            }
				
				AuthorityVO authorityVO = new AuthorityVO();
				authorityVO.setAuthority_name(authority_name);
					
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("authorityVO", authorityVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("path");
					failureView.forward(req, res);
					return;
				}
				
				 /*************2.開始新增資料*************/
				AuthorityService authoritySvc = new AuthorityService();
				authorityVO = authoritySvc.addAuthority(authority_name);
				/**************3.新增完成,準備轉交************/
				String url = "path";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);
				successView.forward(req, res);
				/**************其他可能的錯誤處理****************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("path");
				failureView.forward(req, res);
				
			}
			
		}
	}
}









