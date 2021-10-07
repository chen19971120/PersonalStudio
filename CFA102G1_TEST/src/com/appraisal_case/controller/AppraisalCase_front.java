package com.appraisal_case.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.appraisal_case.model.Appraisal_CaseService;
import com.appraisal_case.model.Appraisal_CaseVO;
import com.appraisal_case_images.model.Appraisal_Case_ImagesService;
import com.appraisal_case_images.model.Appraisal_Case_ImagesVO;
import com.member.model.MemberVO;

@javax.servlet.annotation.MultipartConfig
public class AppraisalCase_front extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//前端新增估價案件
		if("addCase".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				HttpSession session = req.getSession();
				// 查詢有無此會員 若無會員無法新增估價單
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
				Integer mem_no = new Integer(memberVO.getMem_no());
				// 估價商品名稱
				String aca_itm_id = req.getParameter("aca_itm_id");
				if (aca_itm_id == null || aca_itm_id.trim().length() == 0) {
					errorMsgs.add("估價商品名稱請勿空白");
				}
				// 商品類別編號
				Integer acl_no = new Integer(req.getParameter("acl_no").trim());
				
				// 估價商品內容(規格)
				String aca_itm_spec = req.getParameter("aca_itm_spec");
				
				// 案件日期，前端新增時設定當前時間
				Timestamp aca_date = new Timestamp(System.currentTimeMillis());
				// 案件狀態
				String aca_itm_mode = "審核評估中";
				
				// 報價，可先不給值
				Integer aca_first_p = 0;
				
				// 門市收貨日期
				Timestamp aca_recpt_date = null;
				
				// 成交價，可先不給值
				Integer aca_final_p = 0;
				
				// 出貨日期，可先不給值
				Timestamp aca_shipment_date = null;
				
				// 取貨日期，可先不給值
				Timestamp aca_pickup_date = null;
				
				// 付款方式
				String aca_pay = req.getParameter("aca_pay");
				
				// 完成日期，可先不給值
				Timestamp aca_comp_date = null;
				
				// 運送方式
				String aca_cod = req.getParameter("aca_cod");
				
				// 配送地址
				String aca_adrs = req.getParameter("aca_adrs");
				if (aca_adrs == null || aca_adrs.trim().length() == 0) {
					errorMsgs.add("請輸入配送地址");
				}
				
				Appraisal_CaseVO appraisalCaseVO = new Appraisal_CaseVO();
				appraisalCaseVO.setMem_no(mem_no);
				appraisalCaseVO.setAca_itm_id(aca_itm_id);
				appraisalCaseVO.setAcl_no(acl_no);
				appraisalCaseVO.setAca_itm_spec(aca_itm_spec);
				appraisalCaseVO.setAca_date(aca_date);
				appraisalCaseVO.setAca_itm_mode(aca_itm_mode);
				appraisalCaseVO.setAca_first_p(aca_first_p);
				appraisalCaseVO.setAca_recpt_date(aca_recpt_date);
				appraisalCaseVO.setAca_final_p(aca_final_p);
				appraisalCaseVO.setAca_shipment_date(aca_shipment_date);
				appraisalCaseVO.setAca_pickup_date(aca_pickup_date);
				appraisalCaseVO.setAca_pay(aca_pay);
				appraisalCaseVO.setAca_comp_date(aca_comp_date);
				appraisalCaseVO.setAca_cod(aca_cod);
				appraisalCaseVO.setAca_adrs(aca_adrs);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appraisalCaseVO", appraisalCaseVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/appraisal_case/appraisalCase.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				appraisalCaseVO = appraisalCaseSvc.addA_Case(mem_no, aca_itm_id, acl_no, aca_itm_spec, aca_date,
						aca_itm_mode, aca_first_p, aca_recpt_date, aca_final_p, aca_shipment_date, aca_pickup_date,
						aca_pay, aca_comp_date, aca_cod, aca_adrs);
				
				Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
				byte[] aci_img = null;
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					InputStream is = part.getInputStream();
					if (part.getContentType() != null && part.getSize() != 0&& is.available()!=0) {
						aci_img = new byte[is.available()];
						is.read(aci_img);
						is.close();
						/*************************** 新增照片 ***************************************/
						appraisalCaseImagesSvc.addA_Case_Image(appraisalCaseVO.getAca_no(), aci_img);//純筆記放在迴圈裡才可以上傳多張圖片
					}
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/appraisal_case/addCaseSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/appraisal_case/appraisalCase.jsp");
				failureView.forward(req, res);
			}
		}
		//前端查看詳情
		if("caseInformation".equals(action)){
			try {
				Integer aca_no = new Integer(req.getParameter("aca_no"));
				
				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				Appraisal_CaseVO appraisalCaseVO = appraisalCaseSvc.getOneA_Case(aca_no);
				req.setAttribute("appraisalCaseVO", appraisalCaseVO);
				
				Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
				List<Appraisal_Case_ImagesVO> appraisalCaseImagesVO = appraisalCaseImagesSvc.getAll().stream().filter(i -> i.getAca_no().intValue() == aca_no.intValue()).collect(Collectors.toList());
				req.setAttribute("appraisalCaseImagesVO", appraisalCaseImagesVO);

				
				String url = "/front_end/appraisal_case/caseInformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneA_Case.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		if("showIMG".equals(action)) {
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();
//			圖片查詢
			Integer aci_no = new Integer(req.getParameter("aci_no"));
			Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
			byte[] imgArray = appraisalCaseImagesSvc.getOneA_Case_Image(aci_no).getAci_img();
			out.write(imgArray);
			out.close();
		}
	}
}
