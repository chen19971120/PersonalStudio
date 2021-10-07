package com.rentalProductImages.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.rentalProductImages.model.*;

import javax.servlet.*;
import java.io.*;
import java.util.*;


@WebServlet("/rentalProductImagesServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class RentalProductImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				
				Integer rpi_no = new Integer(req.getParameter("rpi_no"));
				
				/***************************2.開始刪除資料***************************************/
				RentalProductImagesService rpiSvc = new RentalProductImagesService();
				RentalProductImagesVO rpiVO = rpiSvc.getOneRentalProductImages(rpi_no);
				Integer rc_no = rpiVO.getRc_no();
				rpiSvc.deleteRentalProductImages(rpi_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
				String requestURL = req.getParameter("requestURL");
				if(requestURL.equals("/back_end/rentalProductImages/listRpibyRC.jsp"))
					req.setAttribute("list",rpiSvc.getOneRentalClassImages(rc_no));
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductImages/listRpi.jsp");
				failureView.forward(req, res);
			}
		}	
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rpi_no = new Integer(req.getParameter("rpi_no").trim());
				
				Integer rc_no = new Integer(req.getParameter("rc_no").trim());
				
				RentalProductImagesService rpiSvc = new RentalProductImagesService();
				RentalProductImagesVO rpiVO = rpiSvc.getOneRentalProductImages(rpi_no);
				
				Part partimg = req.getPart("rpi_img"); 
				
				String filename = getFileNameFromPart(partimg);

				if (filename!= null && partimg.getContentType()!=null) {	
					InputStream in = partimg.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);	
					rpiVO.setRpi_img(buf);
					in.close();	
									
				}
		
				/***************************2.開始修改資料*****************************************/
			
				rpiSvc.updateRentalProductImages(rpi_no, rc_no, rpiVO.getRpi_img());
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String requestURL = req.getParameter("requestURL");
				if(requestURL.equals("/back_end/rentalProductImages/listRpibyRC.jsp"))
					req.setAttribute("list",rpiSvc.getOneRentalClassImages(rc_no));
				if(requestURL.equals("/back_end/rentalProductImages/listOneRpi.jsp")) {
					List<RentalProductImagesVO> list = new ArrayList<RentalProductImagesVO>();
					list.add(rpiSvc.getOneRentalProductImages(rpi_no));
					req.setAttribute("list",list);
				}
					
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductImages/update_rpi_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer rpi_no = new Integer(req.getParameter("rpi_no"));
				
				/***************************2.開始查詢資料****************************************/
				RentalProductImagesService rpiSvc = new RentalProductImagesService();
				RentalProductImagesVO rpiVO = rpiSvc.getOneRentalProductImages(rpi_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rpiVO", rpiVO);         
				String url = "/back_end/rentalProductImages/update_rpi_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductImages/listRpi.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		/***********************查詢某租賃商品圖片*************************/
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("rpi_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入圖片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductImages/listRpi.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer rpi_no = null;
				try {
					rpi_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductImages/listRpi.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RentalProductImagesService rpiSvc = new RentalProductImagesService();
				RentalProductImagesVO rpiVO = rpiSvc.getOneRentalProductImages(rpi_no);
				if (rpiVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductImages/listRpi.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				List<RentalProductImagesVO> list = new ArrayList<RentalProductImagesVO>();
				list.add(rpiVO);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/back_end/rentalProductImages/listOneRpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductImages/listRpi.jsp");
				failureView.forward(req, res);
			}
		}	
		
		/***********************查詢某租賃商品類別圖片*************************/
		if ("getOneClass_For_Display".equals(action)) {

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rc_no = new Integer(req.getParameter("rc_no"));
						
				/***************************2.開始查詢資料*****************************************/
				RentalProductImagesService rpiSvc = new RentalProductImagesService();
				List<RentalProductImagesVO> list = rpiSvc.getOneRentalClassImages(rc_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list);
				req.setAttribute("selectrc_no", rc_no); 
				String url = "/back_end/rentalProductImages/listRpibyRC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductImages/listRpi.jsp");
				failureView.forward(req, res);
			}
		}	
			
		/***********************新增圖片*************************/
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
											
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer rc_no = new Integer (req.getParameter("rc_no").trim());	
				Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
				
				RentalProductImagesService rpiSvc = new RentalProductImagesService();
				List<RentalProductImagesVO> list = new ArrayList<RentalProductImagesVO>();
				RentalProductImagesVO rpiVO = new RentalProductImagesVO();
				
				int i = 0;
				for (Part part : parts) {
					String filename = getFileNameFromPart(part);
					
					if (filename== null)
						i++;
					if (filename!= null && part.getContentType()!=null) {
				/***************************2.開始新增資料***************************************/
						InputStream in = part.getInputStream();
						byte[] buf = new byte[in.available()];
						in.read(buf);
						
						rpiVO = rpiSvc.insertRentalProductImages(rc_no, buf);
						list.add(rpiVO);
						in.close();
					}
				}			
				
				/***********************如果i==3 - 代表3個file都沒有上傳圖片*************************/
				if (i==3) {
					errorMsgs.add("請選擇圖片新增");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/rentalProductImages/addRpi.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				req.setAttribute("list", list);
				String url = "/back_end/rentalProductImages/listRpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rentalProductImages/listRpi.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}		
}
