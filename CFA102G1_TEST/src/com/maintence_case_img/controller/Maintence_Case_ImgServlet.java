package com.maintence_case_img.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maintence_case_img.model.Maintence_Case_ImgService;
import com.maintence_case_img.model.Maintence_Case_ImgVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;




public class Maintence_Case_ImgServlet extends HttpServlet {

	private static final char[] Mca_no = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		{

			if("insert".equals(action)) {
				List<String>errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/**************1.接收請求參數-輸入格式錯誤處理******************/
					Integer mca_no = new Integer(req.getParameter("MCA_NO").trim());
					String mci_before_img = req.getParameter("MCI_BEFORE_IMG");
					
					/**************2.開始新增資料****************/
					Maintence_Case_ImgVO maintence_case_imgVO = new Maintence_Case_ImgVO();
					maintence_case_imgVO.setMca_no(mca_no);
					maintence_case_imgVO.setMci_before_img(mci_before_img);
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("maintence_case_imgVO", maintence_case_imgVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("");
						failureView.forward(req, res);
						return;
					}
					/**************2.開始新增資料****************/
					Maintence_Case_ImgService maintence_case_imgSvc = new Maintence_Case_ImgService();
					
						maintence_case_imgVO = maintence_case_imgSvc.insertMaintenceCaseImg(mca_no, mci_before_img);
					
					
					/**************3.新增完成,準備轉交*****************/
					String url = "";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/**************其他可能的錯誤處理****************/
			        }catch(Exception e) {
			        	errorMsgs.add(e.getMessage());
			        	RequestDispatcher failureView = req
			        			.getRequestDispatcher("");
			        	failureView.forward(req, res);
			        	return;
			        }
			     }    
			if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
				
				List<String>errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/*******1.接收請求參數 - 輸入格式的錯誤處裡*********/
					
					
					String mci_before_img = req.getParameter("mci_before_img").trim();
					System.out.println(mci_before_img);
					if(mci_before_img == null||mci_before_img.trim().length()==0) {
						errorMsgs.add("驗證碼:請勿空白");
					}
					
					/*********2.開始查尋資料**********/
					
					
					Maintence_Case_ImgService maintence_case_imgSvc = new Maintence_Case_ImgService();
					
					
					
					System.out.println(errorMsgs);

					List< Maintence_Case_ImgVO >maintence_case_imgVO = maintence_case_imgSvc.getAll()
																					 .stream()
																					 .filter(i -> i.getMci_before_img().equals(mci_before_img))
																					 .collect(Collectors.toList());
					System.out.println(maintence_case_imgSvc.getAll());
					List< Maintence_Case_ImgVO >maintence_case_imgVOs = maintence_case_imgSvc.getAll();
	
					
					Maintence_Case_ImgVO maintence_case_imgVO_2 = new Maintence_Case_ImgVO(); 
					
					
				
					

					for(Maintence_Case_ImgVO maintence_case_imgVO1 : maintence_case_imgVOs) {
						System.out.println("比對!! : " + maintence_case_imgVO1.getMci_before_img().equals(mci_before_img) );
						if(maintence_case_imgVO1.getMca_no() != null ) {
							System.out.println(maintence_case_imgVO1.getMca_no());
							maintence_case_imgVO_2.getMca_no();
						}
						
						System.out.println("**************************************");
					}
				
					
					
					
					
					
					
					if(maintence_case_imgVO==null) {
						errorMsgs.add("查無資料");
						System.out.println(errorMsgs);
					}
					
					
					
					
					
					MemberVO memberVO = new MemberVO();
					
					Maintence_Case_ImgVO maintence_case_imgVO1 = maintence_case_imgSvc.getOneMaintenceCaseImg(mci_before_img);
					MemberService memberSvc = new MemberService();
					memberVO.setMem_no(maintence_case_imgVO1.getMca_no());
					memberVO.setMem_sta("正常");
					System.out.println(maintence_case_imgVO1.getMca_no());
					
						memberVO = memberSvc.updateMemberStatus(maintence_case_imgVO1.getMca_no(),"正常" );
					
					
					

					
					
					
					
					 req.setAttribute("memberVO", memberVO);//資料庫update成功後,正確的的VO物件,存入req
					
					
					
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("maintence_case_imgVO", maintence_case_imgVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/login/register_2.jsp");
						failureView.forward(req, res);
						return;
					}
					/*********3.查尋完成,準備轉交*********/
				
					req.setAttribute("maintence_case_imgVO", maintence_case_imgVO);// 資料庫取出的VO物件,存入req
					String url = "/front_end/login/register_success.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/**********其他可能的錯誤處裡***********/
				}catch(Exception e) {
					
					errorMsgs.add("驗證碼錯誤，請重新輸入!!");// 成功轉交 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/login/register_2.jsp");
					failureView.forward(req, res);
					return;
					
				}
			}
		}
	
	if ("forgetPwd_2".equals(action)) { 
	
		List<String>errorMsgs = new LinkedList<String>();
		
		req.setAttribute("errorMsgs", errorMsgs);
		try {
//			/*******1.接收請求參數 - 輸入格式的錯誤處裡*********/
			
			
			String mci_before_img = req.getParameter("mci_before_img").trim();
			System.out.println(mci_before_img);
			if(mci_before_img == null||mci_before_img.trim().length()==0) {
				errorMsgs.add("驗證碼:請勿空白");
			}
			
			/*********2.開始查尋資料**********/
			
			
			Maintence_Case_ImgService maintence_case_imgSvc = new Maintence_Case_ImgService();
			
			
			
			System.out.println(errorMsgs);

			List< Maintence_Case_ImgVO >maintence_case_imgVO = maintence_case_imgSvc.getAll()
																			 .stream()
																			 .filter(i -> i.getMci_before_img().equals(mci_before_img))
																			 .collect(Collectors.toList());
			System.out.println(maintence_case_imgSvc.getAll());
			List< Maintence_Case_ImgVO >maintence_case_imgVOs = maintence_case_imgSvc.getAll();

			
			Maintence_Case_ImgVO maintence_case_imgVO_2 = new Maintence_Case_ImgVO(); 
			
			
		
			
	
			for(Maintence_Case_ImgVO maintence_case_imgVO1 : maintence_case_imgVOs) {
				System.out.println("比對!! : " + maintence_case_imgVO1.getMci_before_img().equals(mci_before_img) );
				if(maintence_case_imgVO1.getMca_no() != null ) {
					System.out.println(maintence_case_imgVO1.getMca_no());
					maintence_case_imgVO_2.getMca_no();
				}
				
			
			}
			

			
			
			
			if(maintence_case_imgVO==null) {
				errorMsgs.add("查無資料");
				System.out.println(errorMsgs);
			}
			
			
			
			
			
			
			
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("maintence_case_imgVO", maintence_case_imgVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/login/forgetPassword_2.jsp");
				failureView.forward(req, res);
				return;
			}
			MemberService memberSvc = new MemberService();
			Maintence_Case_ImgVO maintence_case_imgVO1 = maintence_case_imgSvc.getOneMaintenceCaseImg(mci_before_img);
			
			MemberVO memberVO = memberSvc.getOneMem(maintence_case_imgVO1.getMca_no());
			if (memberVO == null) {
				errorMsgs.add("請確認輸入的帳號密碼是否正確");
			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/login/forgetPassword_2.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			
			HttpSession session = req.getSession();
			session.setAttribute("memberVO",memberVO);
			
			
			
			String url = "/front_end/login/update_member.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 jsp
			successView.forward(req, res);
			return;
			


			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			System.out.println("10");
			errorMsgs.add("驗證碼錯誤，請重新輸入!!");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front_end/login/forgetPassword_2.jsp");
			failureView.forward(req, res);
			return;
		}
	}
}
}
