package com.reportProductReviews.controller;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;
import com.productReviews.model.*;
import com.reportProductReviews.model.*;

public class ReportProductReviewsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//前台新增評價檢舉
		if ("insert".equals(action)) {
					
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			try {
				Integer pr_no = new Integer(req.getParameter("pr_no"));
				Integer mem_no = new Integer(req.getParameter("mem_no"));			
				Integer rep_contentnum = new Integer(req.getParameter("rep_contentnum"));
				String rep_content = null;
				
				if(rep_contentnum==1)
					rep_content = "不實評價";
				else if (rep_contentnum==2)
					rep_content = "不適當內容";
				else if (rep_contentnum==3) {
					rep_content = req.getParameter("message");
				}
				
				ReportProductReviewsService repSvc = new ReportProductReviewsService();
				repSvc.insertReportProductReviews(pr_no, mem_no , rep_content);
				
				out.println("<div style=\"text-align:center;vertical-align:middle;line-height: 150px;\" >");
				out.println("<h3>我們已收到檢舉，將會盡速處理，謝謝!</h3>");
				out.println("<button onclick=\"window.close();\">關閉視窗</button>");
				out.println("</div>");
			} catch (Exception e) {
				throw new ServletException(e);
			}		
		}
		
		
		//前台顯示評價檢舉頁面
		if ("showRepPage".equals(action)) {
			try {
				Integer pr_no = new Integer(req.getParameter("pr_no"));
				ProductReviewsService prSvc = new ProductReviewsService();
				ProductReviewsVO prVO = prSvc.getOneProductReviews(pr_no);
				
				HttpSession session = req.getSession();
				session.setAttribute("prVO", prVO);
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/front_end/rental/reportProductReviewsPage.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		//後台瀏覽評價
		if ("getOnePr_For_DisplayDetail".equals(action)) {
			
			try {

				Integer pr_no = new Integer(req.getParameter("pr_no"));
				Integer rep_no = new Integer(req.getParameter("rep_no"));
				
				ProductReviewsService prSvc = new ProductReviewsService();
				ProductReviewsVO prVO = prSvc.getOneProductReviews(pr_no);
				ReportProductReviewsService repSvc = new ReportProductReviewsService();
				ReportProductReviewsVO repVO = repSvc.getOneReportProductReviews(rep_no);
				
				req.setAttribute("prVO", prVO);
				req.setAttribute("repVO", repVO);
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/back_end/reportProductReviews/listOnePrDetail.jsp");
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		//後台依篩選訂單狀態顯示
		if("getOneRepStatus_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String rep_status = req.getParameter("rep_status");
				ReportProductReviewsService repSvc = new ReportProductReviewsService();
				List<ReportProductReviewsVO> list = repSvc.getListbyRepStatus(rep_status);
				req.setAttribute("list", list);
				
				String url = "/back_end/reportProductReviews/listRprbyStatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				
			} catch (Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/reportProductReviews/listRpr.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
		//評價檢舉通過,下架評價
		if("passReport".equals(action)) {
					
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
				
			try {
				Integer rep_no = new Integer(req.getParameter("rep_no"));
					
				ReportProductReviewsService repSvc = new ReportProductReviewsService();
				repSvc.reportPass(rep_no);
						
				String url = "/back_end/reportProductReviews/listRpr.jsp";
				String requestURL = req.getParameter("requestURL");
				
				if(requestURL.equals("/back_end/reportProductReviews/listRprbyStatus.jsp")){
					url = "/back_end/reportProductReviews/listRprbyStatus.jsp";							
					req.setAttribute("list", repSvc.getListbyRepStatus("未處理"));
				}
				
				String fromListOnePrDetail = req.getParameter("fromListOnePrDetail");
				if("true".equals(fromListOnePrDetail)) {
					url = "/back_end/reportProductReviews/listOnePrDetail.jsp";
					ProductReviewsService prSvc = new ProductReviewsService();
					ProductReviewsVO prVO = prSvc.getOneProductReviews(repSvc.getOneReportProductReviews(rep_no).getPr_no());
					req.setAttribute("prVO", prVO);
					req.setAttribute("closewindow", true);
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
						
			} catch (Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/reportProductReviews/listRpr.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//評價檢舉不通過
		if("notPassReport".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer rep_no = new Integer(req.getParameter("rep_no"));
				
				ReportProductReviewsService repSvc = new ReportProductReviewsService();
				repSvc.reportNotPass(rep_no);
				
				String url = "/back_end/reportProductReviews/listRpr.jsp";
				String requestURL = req.getParameter("requestURL");
				
				if(requestURL.equals("/back_end/reportProductReviews/listRprbyStatus.jsp")) {
					url = "/back_end/reportProductReviews/listRprbyStatus.jsp";						
					req.setAttribute("list", repSvc.getListbyRepStatus("未處理"));
				}
						
				String fromListOnePrDetail = req.getParameter("fromListOnePrDetail");
				if("true".equals(fromListOnePrDetail)) {
					url = "/back_end/reportProductReviews/listOnePrDetail.jsp";
					ProductReviewsService prSvc = new ProductReviewsService();
					ProductReviewsVO prVO = prSvc.getOneProductReviews(repSvc.getOneReportProductReviews(rep_no).getPr_no());
					req.setAttribute("prVO", prVO);
					req.setAttribute("closewindow", true);
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch (Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/reportProductReviews/listRpr.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後台顯示一個評價檢舉
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("rep_no");
				if(str==null||(str.trim()).length()==0) {
					errorMsgs.add("請輸入評價編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/back_end/reportProductReviews/listRpr.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer rep_no = null;
				try {
					rep_no = new Integer(str);
				} catch(Exception e) {
					errorMsgs.add("編號不為數字");
				}
						
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/back_end/reportProductReviews/listRpr.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ReportProductReviewsService repSvc = new ReportProductReviewsService();
				ReportProductReviewsVO repVO = repSvc.getOneReportProductReviews(rep_no);
				if (repVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/back_end/reportProductReviews/listRpr.jsp");
					failureView.forward(req, res);
					return;
				}
				
				List<ReportProductReviewsVO> list = new ArrayList<ReportProductReviewsVO>();
				list.add(repVO);
				req.setAttribute("list", list);
				
				String url = "/back_end/reportProductReviews/listRpr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
					
			}catch (Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/reportProductReviews/listRpr.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
}
