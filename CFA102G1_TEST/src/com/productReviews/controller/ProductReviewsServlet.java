package com.productReviews.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.productReviews.model.*;


@WebServlet("/productReviewsServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ProductReviewsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//顯示評價圖片
		if ("showPrImg".equals(action)) { 
			
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();
			
			try {
				Integer pr_no = new Integer(req.getParameter("pr_no"));
				ProductReviewsService prSvc = new ProductReviewsService();
				ProductReviewsVO prVO = prSvc.getOneProductReviews(pr_no);
				byte[] img = prVO.getPr_images();
				out.write(img);
				out.close();
				
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/productReviews/listPr.jsp");
				failureView.forward(req, res);
			}
		}
		
		//在前台rentalProductReviewPage輸入評價後 新增一個評價
		if ("insert".equals(action)) { 
													
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer ro_no = new Integer(req.getParameter("ro_no").trim());
				Integer rc_no = new Integer(req.getParameter("rc_no").trim());
				Integer pr_score = new Integer(req.getParameter("pr_score"));
				String pr_content = req.getParameter("pr_content");
				byte[] pr_images = null;
				Collection<Part> parts = req.getParts(); 	
				
				for (Part part : parts) {
					String filename = getFileNameFromPart(part);
					
					if (filename!= null && part.getContentType()!=null) {
				/***************************2.開始新增資料***************************************/
						InputStream in = part.getInputStream();
						byte[] buf = new byte[in.available()];
						in.read(buf);				
						pr_images = buf;				
						in.close();
					}
				}			
				
				/***********************如果i==3 - 代表3個file都沒有上傳圖片*************************/			
				ProductReviewsService prSvc = new ProductReviewsService();
				prSvc.insertProductReviews(rc_no, ro_no, pr_content, pr_images, pr_score);
				String url = "/front_end/rental/rentalOrderList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/productReviews/listPr.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後台只顯示一個評價
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("pr_no");
				if(str==null||(str.trim()).length()==0) {
					errorMsgs.add("請輸入評價編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/back_end/productReviews/listPr.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer pr_no = null;
				try {
					pr_no = new Integer(str);
				} catch(Exception e) {
					errorMsgs.add("編號不為數字");
				}
						
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/back_end/productReviews/listPr.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ProductReviewsService prSvc = new ProductReviewsService();
				ProductReviewsVO prVO = prSvc.getOneProductReviews(pr_no);
				if (prVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/back_end/productReviews/listPr.jsp");
					failureView.forward(req, res);
					return;
				}
				
				List<ProductReviewsVO> list = new ArrayList<ProductReviewsVO>();
				list.add(prVO);
				req.setAttribute("list", list);
				
				String url = "/back_end/productReviews/listPr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
					
			}catch (Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/productReviews/listPr.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後台對一個評價上下架
		if("getOne_Change_Status".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer pr_no = new Integer(req.getParameter("pr_no"));
					
				ProductReviewsService prSvc = new ProductReviewsService();
				prSvc.changePrStatus(pr_no);
					
				String url = "/back_end/productReviews/listPr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/productReviews/listPr.jsp");
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
