package com.staff.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.staff.model.StaffService;
import com.staff.model.StaffVO;



public class StaffServlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		String staff_emailReg = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
		String staff_phoneReg = "[0-9]*";
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
							.getRequestDispatcher("/back_end/login/select_page.jsp");
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
							.getRequestDispatcher("/back_end/login/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/**********2.開始查詢資料***********/
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getOneStaff(staff_no);
				if(staffVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/login/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/************3.查尋完成，準備轉交**************/
	            req.setAttribute("staffVO", staffVO);	//資料庫取出的staffVO物件,存入req	
			    String url = "/back_end/staff/listOneStaff.jsp";
			    RequestDispatcher successView = req.getRequestDispatcher(url);
			    successView.forward(req, res);
			    }catch(Exception e) {
			    	errorMsgs.add("無法取得資料:"+ e.getMessage());// 成功轉交 listOneMember.jsp
			    	RequestDispatcher failureView = req
			    			.getRequestDispatcher("/back_end/login/select_page.jsp");
			    	failureView.forward(req, res);
			    	return;
			    }
       }
		if("getOne_For_Update".equals(action)) {// 來自listAllMember.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************1.接收參數*************/
				Integer staff_no = new Integer(req.getParameter("staff_no"));
				/*************2.開始查詢資料*******************/
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getOneStaff(staff_no);
				/*************3.查尋完成，準備轉交*********************/
				req.setAttribute("staffVO", staffVO);//資料庫取出的staffVO物件,存入req
				String url = "/back_end/staff/update_staff_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);
				return;
				/*************可能的其他錯誤*********/
				
				}catch(Exception e) {
					errorMsgs.add("無法取得要修改的資料:"+ e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/staff/listAllStaff.jsp");
					failureView.forward(req, res);
					return;
				}
		}
		if("update".equals(action)) {// 來自update_staff_input.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************接收請求參數-輸入可是錯誤處理*******/
				Integer staff_no = new Integer(req.getParameter("staff_no").trim());
				String staff_name = req.getParameter("staff_name");
				String staff_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(staff_name == null||staff_name.trim().length()==0) {
					errorMsgs.add("員工姓名:請勿空白");
				}else if(!staff_name.trim().matches(staff_nameReg)) {
					errorMsgs.add("會員姓名:只能是中英文、數字，長度必須在2到10之間");
				}	
					
				String staff_gender = req.getParameter("staff_gender").trim();
				if(staff_gender==null||staff_gender.trim().length()==0) {
					errorMsgs.add("性別:請勿空白");
				 }
				String staff_phone = req.getParameter("staff_phone").trim();
				if((staff_phone == null||staff_phone.trim().length()==0)&&!staff_phone.trim().matches(staff_phoneReg)){
					errorMsgs.add("手機號碼只能是數字");
				}
				String staff_email = req.getParameter("staff_email").trim();
				if(staff_email == null||staff_email.trim().length()==0) {
					errorMsgs.add("信箱不能為空");
				}else if(!staff_email.trim().matches(staff_emailReg)) {
					errorMsgs.add("Email 格式須為 test@test.com");
				}
				String staff_address = req.getParameter("staff_address").trim();
				if(staff_address == null||staff_address.trim().length()==0){
		            errorMsgs.add("地址不能為空");	
			}
				String staff_account = req.getParameter("staff_account").trim();
				if(staff_account == null||staff_account.trim().length()==0) {
				    errorMsgs.add("帳號不能為空");	
				}
				String staff_password = req.getParameter("staff_password").trim();
				if(staff_password == null||staff_password.trim().length()==0) {
				    errorMsgs.add("密碼不能為空");		
				}
				String staff_sta = req.getParameter("staff_sta").trim();
				if(staff_sta == null||staff_sta.trim().length()==0) {
					errorMsgs.add("狀態不能為空");
				}
				
				StaffVO staffVO = new StaffVO();
				staffVO.setStaff_no(staff_no);
				staffVO.setStaff_name(staff_name);
				staffVO.setStaff_gender(staff_gender);
				staffVO.setStaff_phone(staff_phone);
				staffVO.setStaff_email(staff_email);
				staffVO.setStaff_address(staff_address);
				staffVO.setStaff_account(staff_account);
				staffVO.setStaff_password(staff_password);
				staffVO.setStaff_sta(staff_sta);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("staffVO", staffVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/staff/update_staff_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************2.開始修改資料***************/
				StaffService staffSvc = new StaffService();
				staffVO = staffSvc.updateStaff(staff_no, staff_name, staff_gender, staff_phone, staff_email, staff_address, staff_account, staff_password, staff_sta);
				/*************3.修改完成,準備轉交*****************/
				req.setAttribute("staffVO", staffVO);//資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/back_end/staff/listOneStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************其他可能的錯誤******************/
				}catch(Exception e) {
					errorMsgs.add("修改資料失敗:"+ e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/staff/update_staff_input.jsp");
					failureView.forward(req, res);
					return;
				}
			   
		}
		if("insert".equals(action)) {//來自addMember.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
            
			try {
				/**************1.接收請求參數-輸入格式錯誤處理******************/
				String staff_name = req.getParameter("staff_name");
				String staff_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (staff_name == null || staff_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!staff_name.trim().matches(staff_nameReg)) { 
					errorMsgs.add("會員姓名: 只能是中英文字母、數字和且長度必需在2到10之間");
	            }
				String staff_gender = req.getParameter("staff_gender").trim();
				if(staff_gender==null||staff_gender.trim().length()==0) {
					errorMsgs.add("性別請勿空白");
				}	    
				String staff_phone = req.getParameter("staff_phone");
				if ((staff_phone != null || staff_phone.trim().length() != 0) && !staff_phone.trim().matches(staff_phoneReg)) {
					errorMsgs.add("手機號碼只能為數字");
				}
				
			  String staff_email = req.getParameter("staff_email");
				if (staff_email == null || staff_email.trim().length() == 0) {
					errorMsgs.add("Email 不能為空");
				} else if(!staff_email.trim().matches(staff_emailReg)){
					errorMsgs.add("Email 格式須為 test@test.com");
				}
		      String staff_address = req.getParameter("staff_address").trim();
					if(staff_address==null||staff_address.trim().length()==0) {
						errorMsgs.add("地址請勿空白");
					}	    
			  String staff_account = req.getParameter("staff_account");
					if (staff_account == null || staff_account.trim().length() == 0) {
						errorMsgs.add("帳號不能為空");
					}	
			  String staff_password = req.getParameter("staff_password");
					if (staff_password == null || staff_password.trim().length() == 0) {
						errorMsgs.add("密碼不能為空");
					}
			  String staff_sta= req.getParameter("staff_sta");
					if (staff_sta == null || staff_sta.trim().length() == 0) {
						errorMsgs.add("狀態不能為空");
					}
					StaffVO staffVO = new StaffVO();
					staffVO.setStaff_name(staff_name);
					staffVO.setStaff_gender(staff_gender);
					staffVO.setStaff_phone(staff_phone);
					staffVO.setStaff_email(staff_email);
					staffVO.setStaff_address(staff_address);
					staffVO.setStaff_account(staff_account);
					staffVO.setStaff_password(staff_password);
					staffVO.setStaff_sta(staff_sta);
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("staffVO", staffVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/staff/addStaff.jsp");
					failureView.forward(req, res);
					return;
				}
				
				 /*************2.開始新增資料*************/
				StaffService staffSvc = new StaffService();
				try {
					staffVO = staffSvc.addStaff(staff_name, staff_gender, staff_phone, staff_email, staff_address, staff_account, staff_password, staff_sta);
				}catch (Exception e) {
					errorMsgs.add("此帳號或個人信箱已註冊過，請重新輸入!!");
					req.setAttribute("staffVO", staffVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/staff/addStaff.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/**************3.新增完成,準備轉交************/
				String url = "/back_end/staff/listAllStaff.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);
				successView.forward(req, res);
				/**************其他可能的錯誤處理****************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/staff/addStaff.jsp");
				failureView.forward(req, res);
				return;
			}
			
		}
if("login".equals(action)) {//員工登入 來自login的登入請求
	System.out.println(req.getParameter(action));

	List<String> errorMsgs = new LinkedList<String>();
	// Store this set in the request scope, in case we need to
	// send the ErrorPage view.
	req.setAttribute("errorMsgs", errorMsgs);

	try {
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		String str = req.getParameter("staff_account");
    System.out.println("mem_account :" +  str);
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入帳號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back_end/login/login.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		
		
		String staff_account = null;
		try {
			staff_account = new String(str);
		} catch (Exception e) {
			errorMsgs.add("帳號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back_end/login/login.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
	
		String str1 = req.getParameter("staff_password");
		System.out.println("staff password : " + str1);
		if (str1 == null || (str1.trim()).length() == 0) {
			errorMsgs.add("請輸入密碼");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back_end/login/login.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		
		
		String staff_password = null;
		try {
			staff_password = new String(str1);
		} catch (Exception e) {
			errorMsgs.add("密碼格式不正確");
		}
		StaffService staffSvc2 = new StaffService();
		StaffVO staffVO2 = staffSvc2.getOneStaff_account(staff_account, staff_password);
		

		if(staffVO2 != null && staffVO2.getStaff_sta().equals("停權")) {
			errorMsgs.add("您已被停權");
		}else if(staffVO2 != null && staffVO2.getStaff_sta().equals("未驗證")) {
			errorMsgs.add("您尚未驗證");
		}else if(staffVO2 != null && staffVO2.getStaff_sta() == null) {
			errorMsgs.add("您尚未驗證");
		}

		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back_end/login/login.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		
		/***************************2.開始(登入)查詢資料*****************************************/
		StaffService staffSvc = new StaffService();
		StaffVO staffVO = staffSvc.getOneStaff_account(staff_account, staff_password);
		if (staffVO == null) {
			errorMsgs.add("請確認輸入的帳號密碼是否正確");
		}
		
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back_end/login/login.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		
		HttpSession session = req.getSession();
		session.setAttribute("staffVO",staffVO);
	
		String url = "/back_end/login/login_success.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 /front_end/login/indexTest.jsp
		successView.forward(req, res);
		
		

		/***************************其他可能的錯誤處理*************************************/
	} catch (Exception e) {
		
		errorMsgs.add("無法取得資料:" + e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/back_end/login/login.jsp");
		failureView.forward(req, res);
	     }
       }
if ("update_One_Staff".equals(action)) { // 來自update_One_Staff.jsp的請求
	
	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs", errorMsgs);
	
	try {
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		Integer staff_no = new Integer(req.getParameter("staff_no"));
		String staff_name = req.getParameter("staff_name");
		String staff_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (staff_name == null || staff_name.trim().length() == 0) {
			errorMsgs.add("員工姓名: 請勿空白");
		} else if(!staff_name.trim().matches(staff_nameReg)) { 
			errorMsgs.add("員工姓名: 只能是中英文字母、數字和且長度必需在2到10之間");
        }
		String staff_gender = req.getParameter("staff_gender").trim();
		if(staff_gender==null||staff_gender.trim().length()==0) {
			errorMsgs.add("性別請勿空白");
		}	    
		String staff_phone = req.getParameter("staff_phone");
		if ((staff_phone != null || staff_phone.trim().length() != 0) && !staff_phone.trim().matches(staff_phoneReg)) {
			errorMsgs.add("手機號碼只能為數字");
		}
		
	  String staff_email = req.getParameter("staff_email");
		if (staff_email == null || staff_email.trim().length() == 0) {
			errorMsgs.add("Email 不能為空");
		} else if(!staff_email.trim().matches(staff_emailReg)){
			errorMsgs.add("Email 格式須為 test@test.com");
		}
      String staff_address = req.getParameter("staff_address").trim();
			if(staff_address==null||staff_address.trim().length()==0) {
				errorMsgs.add("地址請勿空白");
			}	    
	  String staff_account = req.getParameter("staff_account");
			if (staff_account == null || staff_account.trim().length() == 0) {
				errorMsgs.add("帳號不能為空");
			}	
	  String staff_password = req.getParameter("staff_password");
			if (staff_password == null || staff_password.trim().length() == 0) {
				errorMsgs.add("密碼不能為空");
			}
			StaffVO staffVO = new StaffVO();
			staffVO.setStaff_no(staff_no);
			staffVO.setStaff_name(staff_name);
			staffVO.setStaff_gender(staff_gender);
			staffVO.setStaff_phone(staff_phone);
			staffVO.setStaff_email(staff_email);
			staffVO.setStaff_address(staff_address);
			staffVO.setStaff_account(staff_account);
			staffVO.setStaff_password(staff_password);
			
	
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			for(String error:errorMsgs) {
				System.out.println(error);
			}
			
			req.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的member_rankVO物件,也存入req
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back_end/login/select_page.jsp");
			failureView.forward(req, res);
			return; //程式中斷
		}
		
		/***************************2.開始修改資料*****************************************/
		StaffService staffSvc = new StaffService();
		staffVO = staffSvc.update_One_Staff(staff_no,staff_name, staff_gender, staff_phone, staff_email, staff_address, staff_account, staff_password);
		
		/***************************3.修改完成,準備轉交(Send the Success view)*************/
		req.setAttribute("staffVO", staffVO); // 資料庫update成功後,正確的的member_rankVO物件,存入req
		String url = "/back_end/staff/listOneStaff.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember_rank.jsp
		successView.forward(req, res);

		/***************************其他可能的錯誤處理*************************************/
	} catch (Exception e) {
		errorMsgs.add("修改資料失敗:"+e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/back_end/staff/update_One_staff.jsp");
		failureView.forward(req, res);
		return;
	}
}

if("login_out".equals(action)) {
	///***************************開始查詢資料 ***************************/
	HttpSession session = req.getSession();
	if(session != null) {
		session.removeAttribute("StaffVO");
	}

	///****************查詢完成,準備轉交(Send the Success view)***************/
	String url = req.getContextPath() + "/back_end/login_out/login_out.jsp";
	res.sendRedirect(url);
	return;
       }
	}
}




