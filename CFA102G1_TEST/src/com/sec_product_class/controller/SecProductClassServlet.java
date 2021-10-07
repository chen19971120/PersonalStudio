package com.sec_product_class.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sec_product_class.model.ProductClassService;
import com.sec_product_class.model.ProductClassVO;
import com.sec_product_images.model.SecProductImagesService;

public class SecProductClassServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action : " + action);
		
		if("getOneForUpdate".equals(action)) {
			System.out.println("Enter getOneForUpdate!!");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****1.取值****/
				Integer spc_no = new Integer(req.getParameter("spc_no"));
				
				/****2.取得資料****/
				ProductClassService productClassSvc = new ProductClassService();
				ProductClassVO productClassVO = productClassSvc.getOneProductClass(spc_no);
				req.setAttribute("productClassVO", productClassVO);

				/****3.頁面轉向****/
				RequestDispatcher successView = 
									req.getRequestDispatcher("/back_end/secProductClass/updateProductClass.jsp");
				successView.forward(req, res);
				return;
				
			}catch (NumberFormatException e) {
				errorMsgs.add("輸入錯誤!!");
				
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductClass/listAllProductCLass.jsp");
				failView.forward(req, res);
				return;
			} 
			
			catch (Exception e) {
				errorMsgs.add("查無資料!!");
				
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductClass/listAllProductCLass.jsp");
				failView.forward(req, res);
				return;
			}

		}
		
		if("update".equals(action)) {
			System.out.println("Enter update!!");
			String str = req.getParameter("spc_no");
			System.out.println("spc_no " + str);
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****1.取得參數****/
				Integer spc_no = new Integer(req.getParameter("spc_no"));
				
				String spc_name = req.getParameter("spc_name");
				
				
				if(spc_name==null || spc_name.trim().length()==0) {
					errorMsgs.add("請輸入類別名稱!!");
				}
				
				ProductClassVO productClassVO = new ProductClassVO();
				productClassVO.setSpc_no(spc_no);
				productClassVO.setSpc_name(spc_name);
				req.setAttribute("productClassVO", productClassVO);
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductClass/updateProductClass.jsp");
					failView.forward(req, res);
					return;
				}
				
				/****2.更新資料****/
				ProductClassService productClassSvc = new ProductClassService();
				productClassSvc.updateProductClass(spc_no, spc_name);
				
				/****3.頁面轉向****/
				RequestDispatcher successView = 
						req.getRequestDispatcher("/back_end/secProductClass/listAllProductClass.jsp");
				successView.forward(req, res);
				
				
			}catch (Exception e) {
				errorMsgs.add("更新失敗!!!");
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductClass/updateProductClass.jsp");
				failView.forward(req, res);
				return;
			}
		
		}
		
		if("FindByPK".equals(action)) {
			System.out.println("Enter FindByPK!!");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs_class", errorMsgs);
			
			/****1.取得參數****/
			String str = req.getParameter("spc_no");
			if(str==null || str.trim().length()==0) {
				errorMsgs.add("請輸入類別編號!!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			
			Integer spc_no = null;
			try {
				spc_no = new Integer(str);
			} catch (NumberFormatException e) {
				errorMsgs.add("請輸入數字!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			/****2.開始查詢****/
			ProductClassService productClassSvc = new ProductClassService();
			ProductClassVO productClassVO = productClassSvc.getOneProductClass(spc_no);
			
			if(productClassVO == null) {
				errorMsgs.add("查無資料!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductInfo/select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			req.setAttribute("productClassVO", productClassVO);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secProductClass/listOneProductClass.jsp");
			successView.forward(req, res);
			return;
		}
		
		if("insert".equals(action)) {
			System.out.println("Enter insert!!");
			String spc_name = req.getParameter("spc_name");
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			if(spc_name==null || spc_name.trim().length()==0) {
				errorMsgs.add("請輸入類別名稱!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back_end/secProductClass/addSecProductClass.jsp");
				failView.forward(req, res);
				return;
			}
			
			/****2.新增資料****/
			ProductClassService productClassSvc = new ProductClassService();
			productClassSvc.addProductClass(spc_name);
			
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back_end/secProductClass/listAllProductClass.jsp");
			successView.forward(req, res);
			return;
		
		}
	}
}
