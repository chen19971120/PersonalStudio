package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maintence_case_img.model.Maintence_Case_ImgService;
import com.maintence_case_img.model.Maintence_Case_ImgVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.member.model.MemberDAO;

public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		String mem_passwordReg = "^[(a-zA-Z0-9_)]{2,20}$";
		String mem_accountReg = "^[(a-zA-Z0-9_)]{2,20}$";
		String mem_emailReg = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
		String mem_phoneReg = "[0-9]*";
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String>errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*******1.接收請求參數 - 輸入格式的錯誤處裡*********/
				String str = req.getParameter("mem_no");
				if(str == null||(str.trim()).length()==0) {
					errorMsgs.add("請輸入會員編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/login/select_page_member.jsp");
					failureView.forward(req, res);
					return;
				}
				Integer mem_no = null;
				
				try {
					mem_no = new Integer(str);
				}catch(Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/login/select_page_member.jsp");
					failureView.forward(req, res);
					return;
				}
				/*********2.開始查尋資料**********/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMem(mem_no);
				if(memberVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/login/select_page_member.jsp");
					failureView.forward(req, res);
					return;
				}
				/*********3.查尋完成,準備轉交*********/
				req.setAttribute("memberVO", memberVO);// 資料庫取出的memberVO物件,存入req
				String url = "/back_end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/**********其他可能的錯誤處裡***********/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料 : "+ e.getMessage());// 成功轉交 listOneMember.jsp
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/login/select_page_member.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/**********1.接收請收參數**********/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				
				/**********2.開始查尋資料********/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMem(mem_no);
				
				/**********3.查尋完成,準備轉交***********/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
				String url = "/back_end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				
				/****************可能其他的錯誤***********/
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料 : " + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/member/listAllMember.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if("update".equals(action)) {// 來自update_member_input.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/************接收請求參數-輸入格式錯誤處理*********/
	  Integer mem_no = new Integer(req.getParameter("mem_no").trim());
	  
	  String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(mem_name == null||mem_name.trim().length()==0) {
					errorMsgs.add("會員姓名:請勿空白");
				}else if(!mem_name.trim().matches(mem_nameReg)) {
					errorMsgs.add("會員姓名:只能是中英文、數字，長度必須在2到10之間");
				}
			
				
	  String mem_gender = req.getParameter("mem_gender").trim();
		if(mem_gender==null||mem_gender.trim().length()==0) {
			errorMsgs.add("性別請勿空白");
		}
		
		java.sql.Date mem_birth = null;
		if(req.getParameter("mem_birth") == null || req.getParameter("mem_birth").toString().trim().length() == 0) {
			errorMsgs.add("日期不能為空");
		} else {
			mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth"));
		}
		  
		String mem_phone = req.getParameter("mem_phone");
		if ((mem_phone != null || mem_phone.trim().length() != 0) && !mem_phone.trim().matches(mem_phoneReg)) {
			errorMsgs.add("手機號碼只能為數字");
		}
		
		
	  String mem_email = req.getParameter("mem_email");
		if (mem_email == null || mem_email.trim().length() == 0) {
			errorMsgs.add("Email 不能為空");
		} else if(!mem_email.trim().matches(mem_emailReg)){
			errorMsgs.add("Email 格式須為 test@test.com");
		}
		
      String mem_address = req.getParameter("mem_address").trim();
			if(mem_address==null||mem_address.trim().length()==0) {
				errorMsgs.add("地址請勿空白");
			}	    
			
	  String mem_account = req.getParameter("mem_account");
			if (mem_account == null || mem_account.trim().length() == 0) {
				errorMsgs.add("帳號不能為空");
			}	
			
	  String mem_password = req.getParameter("mem_password");
			if (mem_password == null || mem_password.trim().length() == 0) {
				errorMsgs.add("密碼不能為空");
			}
			
	  String mem_sta= req.getParameter("mem_sta");
			if (mem_sta == null || mem_sta.trim().length() == 0) {
				errorMsgs.add("狀態不能為空");
			}
			
			MemberVO memberVO = new MemberVO();
			memberVO.setMem_no(mem_no);
			memberVO.setMem_name(mem_name);
			memberVO.setMem_gender(mem_gender);
			memberVO.setMem_phone(mem_phone);
			memberVO.setMem_email(mem_email);
			memberVO.setMem_address(mem_address);
			memberVO.setMem_account(mem_account);
			memberVO.setMem_password(mem_password);
			memberVO.setMem_birth(mem_birth);
			memberVO.setMem_sta(mem_sta);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/login/update_member.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/**************2.開始修改資料***********/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMember(mem_no, mem_name, mem_gender, mem_phone, mem_email, mem_address, mem_account, mem_password, mem_birth, mem_sta);
			/*************3.修改完成,準備轉交***********************/
			req.setAttribute("memberVO", memberVO);//資料庫update成功後,正確的的memberVO物件,存入req
			String url = "/front_end/member/memberInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			/************其他可能的錯誤處理*********************/
	 }catch(Exception e) {
		 errorMsgs.add("修改資料失敗" + e.getMessage());
		 RequestDispatcher failureView = req
				 .getRequestDispatcher("/front_end/login/update_member.jsp");
		 failureView.forward(req, res);
		 return;
		 
	 }
		}
		if("back_update".equals(action)) {// 來自back_update_member_input.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/************接收請求參數-輸入格式錯誤處理*********/
	  Integer mem_no = new Integer(req.getParameter("mem_no").trim());
	  
	  String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(mem_name == null||mem_name.trim().length()==0) {
					errorMsgs.add("會員姓名:請勿空白");
				}else if(!mem_name.trim().matches(mem_nameReg)) {
					errorMsgs.add("會員姓名:只能是中英文、數字，長度必須在2到10之間");
				}
				
				
	  String mem_gender = req.getParameter("mem_gender").trim();
		if(mem_gender==null||mem_gender.trim().length()==0) {
			errorMsgs.add("性別請勿空白");
		}
		
		java.sql.Date mem_birth = null;
		if(req.getParameter("mem_birth") == null || req.getParameter("mem_birth").toString().trim().length() == 0) {
			errorMsgs.add("日期不能為空");
		} else {
			mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth"));
		}
		  
		String mem_phone = req.getParameter("mem_phone");
		if ((mem_phone != null || mem_phone.trim().length() != 0) && !mem_phone.trim().matches(mem_phoneReg)) {
			errorMsgs.add("手機號碼只能為數字");
		}
		
		
	  String mem_email = req.getParameter("mem_email");
		if (mem_email == null || mem_email.trim().length() == 0) {
			errorMsgs.add("Email 不能為空");
		} else if(!mem_email.trim().matches(mem_emailReg)){
			errorMsgs.add("Email 格式須為 test@test.com");
		}
		
      String mem_address = req.getParameter("mem_address").trim();
			if(mem_address==null||mem_address.trim().length()==0) {
				errorMsgs.add("地址請勿空白");
			}	    
			
	  String mem_account = req.getParameter("mem_account");
			if (mem_account == null || mem_account.trim().length() == 0) {
				errorMsgs.add("帳號不能為空");
			}	
			
	  String mem_password = req.getParameter("mem_password");
			if (mem_password == null || mem_password.trim().length() == 0) {
				errorMsgs.add("密碼不能為空");
			}
			
	 
	 
	  String mem_sta= req.getParameter("mem_sta");
			if (mem_sta == null || mem_sta.trim().length() == 0) {
				errorMsgs.add("狀態不能為空");
			}
			
			MemberVO memberVO = new MemberVO();
			memberVO.setMem_no(mem_no);
			memberVO.setMem_name(mem_name);
			memberVO.setMem_gender(mem_gender);
			memberVO.setMem_phone(mem_phone);
			memberVO.setMem_email(mem_email);
			memberVO.setMem_address(mem_address);
			memberVO.setMem_account(mem_account);
			memberVO.setMem_password(mem_password);
			memberVO.setMem_birth(mem_birth);
			memberVO.setMem_sta(mem_sta);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/member/update_member_input.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/**************2.開始修改資料***********/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMember(mem_no, mem_name, mem_gender, mem_phone, mem_email, mem_address, mem_account, mem_password, mem_birth, mem_sta);
			/*************3.修改完成,準備轉交***********************/
			req.setAttribute("memberVO", memberVO);//資料庫update成功後,正確的的memberVO物件,存入req
			String url = "/back_end/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			/************其他可能的錯誤處理*********************/
	 }catch(Exception e) {
		 errorMsgs.add("修改資料失敗" + e.getMessage());
		 RequestDispatcher failureView = req
				 .getRequestDispatcher("/back_end/member/update_member_input.jsp");
		 failureView.forward(req, res);
		 return;
		 
	 }
		}
		
		if("insert".equals(action)) {//來自addMember.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/**************1.接收請求參數-輸入格式錯誤處理******************/
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { 
					errorMsgs.add("會員姓名: 只能是中英文字母、數字和且長度必需在2到10之間");
	            }
				String mem_gender = req.getParameter("mem_gender").trim();
				if(mem_gender==null||mem_gender.trim().length()==0) {
					errorMsgs.add("性別請勿空白");
				}	    
				String mem_phone = req.getParameter("mem_phone");
				if ((mem_phone != null || mem_phone.trim().length() != 0) && !mem_phone.trim().matches(mem_phoneReg)) {
					errorMsgs.add("手機號碼只能為數字");
				}
				
			  String mem_email = req.getParameter("mem_email");
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("Email 不能為空");
				} else if(!mem_email.trim().matches(mem_emailReg)){
					errorMsgs.add("Email 格式須為 test@test.com");
				}
		      String mem_address = req.getParameter("mem_address").trim();
					if(mem_address==null||mem_address.trim().length()==0) {
						errorMsgs.add("地址請勿空白");
					}	    
			  String mem_account = req.getParameter("mem_account");
					if (mem_account == null || mem_account.trim().length() == 0) {
						errorMsgs.add("帳號不能為空");
					}	
			  String mem_password = req.getParameter("mem_password");
					if (mem_password == null || mem_password.trim().length() == 0) {
						errorMsgs.add("密碼不能為空");
					}
			  java.sql.Date mem_birth = null;
					if(req.getParameter("mem_birth") == null || req.getParameter("mem_birth").toString().trim().length() == 0) {
						errorMsgs.add("日期不能為空");
					} else {
						mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth"));
					}
					  
			  String mem_sta= req.getParameter("mem_sta");
					if (mem_sta == null || mem_sta.trim().length() == 0) {
						errorMsgs.add("狀態不能為空");
					}
					
					MemberVO memberVO = new MemberVO();
					memberVO.setMem_name(mem_name);
					memberVO.setMem_gender(mem_gender);
					memberVO.setMem_phone(mem_phone);
					memberVO.setMem_email(mem_email);
					memberVO.setMem_address(mem_address);
					memberVO.setMem_account(mem_account);
					memberVO.setMem_password(mem_password);
					memberVO.setMem_birth(mem_birth);
					memberVO.setMem_sta(mem_sta);
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/login/update_member.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**************2.開始新增資料****************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mem_name, mem_gender, mem_phone, mem_email, mem_address, mem_account, mem_password, mem_birth, mem_sta);
				/**************3.新增完成,準備轉交*****************/
				String url = "/front_end/login/update_member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/**************其他可能的錯誤處理****************/
		        }catch(Exception e) {
		        	errorMsgs.add(e.getMessage());
		        	RequestDispatcher failureView = req
		        			.getRequestDispatcher("/front_end/login/update_member.jsp");
		        	failureView.forward(req, res);
		        	return;
		        }
		     }    
		
		if("login".equals(action)) {//會員登入 來自login的登入請求
			
			

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_account");
            
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/login/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				String mem_account = null;
				try {
					mem_account = new String(str);
				} catch (Exception e) {
					errorMsgs.add("帳號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/login/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String str1 = req.getParameter("mem_password");
				
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/login/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_password = null;
				try {
					mem_password = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("密碼格式不正確");
				}
				
				MemberService memberSvc2 = new MemberService();
				MemberVO memberVO2 = memberSvc2.getOneMem_account(mem_account, mem_password);
				
				if(memberVO2 != null && memberVO2.getMem_sta().equals("停權")) {
					errorMsgs.add("您已被停權");
				}else if(memberVO2 != null && memberVO2.getMem_sta().equals("未驗證")) {
					errorMsgs.add("您尚未驗證");
				}else if(memberVO2 != null && memberVO2.getMem_sta() == null) {
					errorMsgs.add("您尚未驗證");
				}
			
				String remember = req.getParameter("passcookies");
				if (remember != null) {
					Cookie c1 = new Cookie("mem_account", mem_account);
					Cookie c2 = new Cookie("mem_password", mem_password);
					c1.setMaxAge(500000);
					c2.setMaxAge(500000);
					c1.setPath("/");
					c2.setPath("/");
					res.addCookie(c1);
					res.addCookie(c2);
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/login/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始(登入)查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMem_account(mem_account, mem_password);
				if (memberVO == null) {
					errorMsgs.add("請確認輸入的帳號密碼是否正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/login/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				HttpSession session = req.getSession();
				session.setAttribute("memberVO",memberVO);
				
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {					
						session.removeAttribute("location");
						System.out.println("LOC : " + location);
						res.sendRedirect(location);
					}else {
						res.sendRedirect(req.getContextPath() + "/front_end/index.jsp");
					}
				} catch (Exception ignored) {	
					System.out.println("Enter Exception");
					res.sendRedirect(req.getContextPath() + "/front_end/index.jsp");
				}
			
				

				
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/login/login.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		//忘記密碼
				if ("forgetPwd".equals(action)) {
					List<String>errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mem_email = req.getParameter("mem_email").trim();	
				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memberSvc = new MemberService();
				MemberMailService mms = new MemberMailService();
				Maintence_Case_ImgVO maintence_case_imgVO1 = new Maintence_Case_ImgVO();
				
				MemberVO memberVO = memberSvc.findByEmail(mem_email);
				
				
				StringBuffer code = new StringBuffer();
				String elements = "01234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				for(int i = 0 ;i < 10; i++  )
					code.append(elements.charAt((int)(Math.random()*elements.length())));
				String abc = code.toString();
				
				if (memberVO != null) {
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
					
					String to = mem_email;
					String subject = "YSM3C-驗證碼";
				
					String messageText = memberVO.getMem_name() + "您好:\n\n"
							+ "以下是您的驗證碼，認證完畢至登入後，請至會員專區重設您的密碼:\n\n"
							+ abc + "\n\n" +req.getScheme()+"://"+req.getServerName() + ":" + req.getServerPort()+ req.getContextPath() +"/front_end/login/forgetPassword_2.jsp"
							+  "\n\n" +
							"YSM3C 敬上。";
					System.out.println("req.getScheme() : "+ req.getScheme());
					System.out.println("req.getServerName() : "+ req.getServerName());
					System.out.println("req.getServerPort() : "+ req.getServerPort());
					System.out.println("req.getContextPath() : "+ req.getContextPath());
					System.out.println("req.getServletPath() : "+ req.getServletPath());
					System.out.println("req.getPathInfo() : "+ req.getPathInfo());
					System.out.println("req.getPathTranslated() : "+ req.getPathTranslated());
					System.out.println("req.getRequestURI() : "+ req.getRequestURI());
					System.out.println("req.getQueryString() : "+ req.getQueryString());
					System.out.println("req.getRequestURL() : "+ req.getRequestURL());
					mms.sendMail(to, subject, messageText);

					Maintence_Case_ImgVO maintence_case_imgVO = new Maintence_Case_ImgVO();
					
					
					maintence_case_imgVO.setMca_no(memberVO.getMem_no());
					maintence_case_imgVO.setMci_before_img(abc);
					
					Integer mca_no = maintence_case_imgVO.getMca_no();
					String mci_before_img = maintence_case_imgVO.getMci_before_img();
					
					Maintence_Case_ImgService maintence_case_imgSvc = new Maintence_Case_ImgService();
							
					maintence_case_imgVO = maintence_case_imgSvc.insertMaintenceCaseImg(mca_no,mci_before_img )	;	
					
					
					RequestDispatcher SuccessView = req.getRequestDispatcher("/front_end/login/forgetPassword_2.jsp");
					SuccessView.forward(req, res);
					return;
				
				} else {
					errorMsgs.add("信箱不存在");
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/login/forgetPassword_1.jsp");
					failureView.forward(req, res);
					return;
				}
				
				}
			 
			
		if("register".equals(action)) { // 來自register的請求
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
		    String mem_name = req.getParameter("mem_name");
		    String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
		    if(mem_name == null||mem_name.trim().length()==0) {
		    	errorMsgs.add("會員姓名: 請勿空白");
		    }
		    else if(!mem_name.trim().matches(mem_nameReg)) {
		    	errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到60之間");
		      
		    }
		    String mem_gender = new String(req.getParameter("mem_gender"));
		  
		    String mem_phone = req.getParameter("mem_phone");
		    if(mem_phone == null||mem_phone.trim().length()==0) {
		    	errorMsgs.add("請輸入電話");
		    }else if(!mem_phone.trim().matches(mem_phoneReg)) {
		    	errorMsgs.add("電話格是錯誤");
		    }
		    String mem_email = req.getParameter("mem_email");
		    if(mem_email == null||mem_email.trim().isEmpty()) {
		    	errorMsgs.add("請輸入信箱");
		    }else if(!mem_email.trim().matches(mem_emailReg)) {
		    	errorMsgs.add("信箱格式不正確");
		    	}
		    String mem_address = req.getParameter("mem_address");
		    if(mem_address == null||mem_address.trim().length()==0) {
		        errorMsgs.add("請輸入地址");
		    }
		
		   
		    String mem_account = req.getParameter("mem_account");
			
			    if(mem_account == null||mem_account.trim().length()==0) {
			    	errorMsgs.add("會員帳號 :　請勿空白");
			 
			    }
			    else if(!mem_account.trim().matches(mem_accountReg)) {
			    	errorMsgs.add("帳號格式只能是英文字母、數字，且長度必須在2到20之間");
			    }
			String mem_password = req.getParameter("mem_password");
			
			
		        if(mem_password == null||mem_password.trim().length()==0) {
		    	    errorMsgs.add("會員密碼 :　請勿空白");
		 
		           }
		        else if(!mem_password.trim().matches(mem_passwordReg)) {
		    	       errorMsgs.add("密碼格式只能是英文字母、數字，且長度必須在2到20之間");
		    } 
		        java.sql.Date mem_birth = null;
				if(req.getParameter("mem_birth") == null || req.getParameter("mem_birth").toString().trim().length() == 0) {
					errorMsgs.add("日期不能為空");
				} else {
					mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth"));
				}
			
				
			    
		    MemberVO memberVO = new MemberVO();
			memberVO.setMem_name(mem_name);
			memberVO.setMem_gender(mem_gender);
			memberVO.setMem_phone(mem_phone);
			memberVO.setMem_email(mem_email);
			memberVO.setMem_address(mem_address);
			memberVO.setMem_account(mem_account);
			memberVO.setMem_password(mem_password);
			memberVO.setMem_birth(mem_birth);
			memberVO.setMem_sta("未驗證");
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/login/register.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
/***************************2.開始新增(註冊)資料***************************************/
			
			MemberService memberSvc = new MemberService();
			try {
				memberVO = memberSvc.addMember(mem_name, mem_gender, mem_phone, mem_email, mem_address, mem_account, mem_password, mem_birth, "未驗證");
			} catch (Exception e) {
				errorMsgs.add("此帳號或個人信箱已註冊過，請重新輸入!!");
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/login/register.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
		
			//>>>>>>>>>>>>>>>>>>>>>>>>>
			StringBuffer code = new StringBuffer();
			String elements = "01234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			for(int i = 0 ;i < 10; i++  )
				code.append(elements.charAt((int)(Math.random()*elements.length())));
			String abc = code.toString();
			
			Maintence_Case_ImgVO maintence_case_imgVO = new Maintence_Case_ImgVO();
			
			
			maintence_case_imgVO.setMca_no(memberVO.getMem_no());
			maintence_case_imgVO.setMci_before_img(abc);
			
			Integer mca_no = maintence_case_imgVO.getMca_no();
			String mci_before_img = maintence_case_imgVO.getMci_before_img();
			//<<<<<<<<<<<<<<<<<<<<<<<<
			Maintence_Case_ImgService maintence_case_imgSvc = new Maintence_Case_ImgService();
					
			maintence_case_imgVO = maintence_case_imgSvc.insertMaintenceCaseImg(mca_no,mci_before_img )	;	
			
			
			
			
			
			MemberMailService mms = new MemberMailService();
//			
			String to = mem_email;
			System.out.println(mem_email);
			String subject = "YSM3C會員帳號驗證通知信";
			String messageText = mem_name + "您好 : \n\n"
					+"感謝您使用本平臺，為了確認您的資料正確性，平臺需要驗證後才能啟用您你帳號。\n\n"
					+ "您的驗證碼 : " + abc + "\n\n" + req.getScheme()+"://"+req.getServerName() + ":" + req.getServerPort()+ req.getContextPath() +"/front_end/login/register_2.jsp"
					+  "\n\n" + "YSM3C 敬上。";
			                                     
			System.out.println(to);
			System.out.println("................................");
			System.out.println(subject);
			System.out.println("................................");

			System.out.println(messageText);
			mms.sendMail(to,subject,messageText);
			
			
			/***************************3.新增(註冊)完成,準備轉交(Send the Success view)***********/
			String url = "/front_end/login/register_2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/login/register.jsp");
				failureView.forward(req, res);
				return;
			}
		 }
		if("login_out".equals(action)) {
			///***************************開始查詢資料 ***************************/
			HttpSession session = req.getSession();
			System.out.println("測試" + session != null);
			if(session != null) {
				System.out.println("1111");
				session.removeAttribute("memberVO");
				
			}
			///****************查詢完成,準備轉交(Send the Success view)***************/
			String url = req.getContextPath() + "/front_end/login_out/login_out.jsp";
			res.sendRedirect(url);
			return;
		       }
	   }
}
    






