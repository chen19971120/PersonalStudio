package com.appraisal_case_images.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.appraisal_case.model.Appraisal_CaseService;
import com.appraisal_case.model.Appraisal_CaseVO;
import com.appraisal_case_images.model.Appraisal_Case_ImagesService;
import com.appraisal_case_images.model.Appraisal_Case_ImagesVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class Appraisal_Case_ImagesServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, IllegalStateException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {// 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("aci_no");
				Integer aci_no = new Integer(str);

				/*************************** 2.開始查詢資料 *****************************************/
				Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
				Appraisal_Case_ImagesVO appraisalCaseImagesVO = appraisalCaseImagesSvc.getOneA_Case_Image(aci_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("appraisalCaseImagesVO", appraisalCaseImagesVO);// 資料庫取出的appraisalCaseImagesVO物件,存入req
				String url = "/back_end/appraisal_case_images/listOneA_Case_Images.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/appraisal_case_images/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer aci_no = new Integer(req.getParameter("aci_no"));

				/*************************** 2.開始查詢資料 *****************************************/
				Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
				Appraisal_Case_ImagesVO appraisalCaseImagesVO = appraisalCaseImagesSvc.getOneA_Case_Image(aci_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("appraisalCaseImagesVO", appraisalCaseImagesVO);// 資料庫取出的appraisalCaseImagesVO物件,存入req
				String url = "/back_end/appraisal_case_images/update_a_case_images_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
				Appraisal_Case_ImagesVO appraisalCaseImagesVO = new Appraisal_Case_ImagesVO();

				Integer aci_no = new Integer(req.getParameter("aci_no"));

				Integer aca_no = new Integer(req.getParameter("aca_no").trim());
				//修改圖片
				
				byte[] aci_img = null;
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					InputStream is = part.getInputStream();
					if (part.getContentType() != null && part.getSize() != 0&& is.available()!=0) {
						aci_img = new byte[is.available()];
						is.read(aci_img);
						is.close();
						appraisalCaseImagesSvc.addA_Case_Image(aca_no, aci_img);//純筆記放在迴圈裡才可以上傳多張圖片
					}
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/appraisal_case_images/update_a_case_images_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				appraisalCaseImagesVO = appraisalCaseImagesSvc.updateA_Case_Image(aci_no, aca_no, aci_img);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				
				req.setAttribute("appraisalCaseImagesVO", appraisalCaseImagesVO);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/appraisal_case_images/update_a_case_images_input.jsp");
				failureView.forward(req, res);
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
		
		if("imagesInformation".equals(action)) {
			try {
				Integer aca_no = new Integer(req.getParameter("aca_no").trim());

				Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
				List<Appraisal_Case_ImagesVO> appraisalCaseImagesVO = appraisalCaseImagesSvc.getAll()
						.stream().filter(i -> i.getAca_no().intValue() == aca_no.intValue()).collect(Collectors.toList());
				
				req.setAttribute("appraisalCaseImagesVO", appraisalCaseImagesVO);
				req.setAttribute("aca_no", aca_no);
				
				Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
				Appraisal_CaseVO  appraisalCaseVO = appraisalCaseSvc.getOneA_Case(aca_no);
				req.setAttribute("appraisalCaseVO",appraisalCaseVO);
				
				String url = "/back_end/appraisal_case_images/imagesInformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
				
				Integer aca_no = new Integer(req.getParameter("aca_no").trim());
				//多張圖片
				byte[] aci_img = null;
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					InputStream is = part.getInputStream();
					if (part.getContentType() != null && part.getSize() != 0&& is.available()!=0) {
						aci_img = new byte[is.available()];
						is.read(aci_img);
						is.close();
						/*************************** 2.開始新增資料 ***************************************/
						appraisalCaseImagesSvc.addA_Case_Image(aca_no, aci_img);//純筆記放在迴圈裡才可以上傳多張圖片
					}
				}
				
				/*************************** 新增完查詢資料 ***************************************/
				List<Appraisal_Case_ImagesVO> appraisalCaseImagesVO = appraisalCaseImagesSvc.getAll()
						.stream()
						.filter(i -> i.getAca_no().intValue()==aca_no.intValue())
						.collect(Collectors.toList());
				
				req.setAttribute("appraisalCaseImagesVO", appraisalCaseImagesVO);
				req.setAttribute("aca_no", aca_no);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/appraisal_case_images/addA_Case_Images.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/appraisal_case_images/listAllA_Case_Images.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer aci_no = new Integer(req.getParameter("aci_no"));
				Integer aca_no = new Integer(req.getParameter("aca_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
				appraisalCaseImagesSvc.deleteA_Case_Image(aci_no);
				
				List<Appraisal_Case_ImagesVO> appraisalCaseImagesVO = appraisalCaseImagesSvc.getAll()
						.stream()
						.filter(i ->i.getAca_no().intValue() == aca_no.intValue())
						.collect(Collectors.toList());
				
				req.setAttribute("appraisalCaseImagesVO", appraisalCaseImagesVO);
				req.setAttribute("aca_no", aca_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
