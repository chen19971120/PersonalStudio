package com.sec_product_images.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sec_product_images.model.SecProductImagesService;
import com.sec_product_images.model.SecProductImagesVO;

@javax.servlet.annotation.MultipartConfig
public class SecProductImagesServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if("showImages".equals(action)) {
			Integer spi_no = new Integer(req.getParameter("spi_no"));
			
			SecProductImagesService secProductImagesSvc = new SecProductImagesService();
			List<SecProductImagesVO> secProductImagesVO = secProductImagesSvc.getAll();
			
			List<SecProductImagesVO> Images = secProductImagesVO.stream()
																.filter(i -> i.getSpi_no().intValue() ==  spi_no.intValue())
																.collect(Collectors.toList());
			req.setAttribute("Images", Images);
			req.setAttribute("spi_no", spi_no);  //先將spi_no 存起來，避商品沒有圖片時，無法新增
			
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/secProductImg/showImages.jsp");
			successView.forward(req, res);
			return;
		}
		
		if("delete".equals(action)) {
			Integer spim_no = new Integer(req.getParameter("spim_no"));
			Integer spi_no = new Integer(req.getParameter("spi_no"));

			System.out.println("spim_no : " + spim_no);
			
			/****1.刪除圖片****/
			SecProductImagesService secProductImagesSvc = new SecProductImagesService();
			secProductImagesSvc.deleteSecProductImages(spim_no);
			
			/****2.刪除圖片後重新查詢，並將結果存入req****/
			List<SecProductImagesVO> secProductImagesVO = secProductImagesSvc.getAll();
			List<SecProductImagesVO> Images = secProductImagesVO.stream()
																.filter(i -> i.getSpi_no().intValue() ==  spi_no.intValue())
																.collect(Collectors.toList());
			req.setAttribute("Images", Images);
			req.setAttribute("spi_no", spi_no);  //先將spi_no 存起來，避商品沒有圖片時，無法新增

			RequestDispatcher successView = req.getRequestDispatcher("/back_end/secProductImg/showImages.jsp");
			successView.forward(req, res);
			return;
			
		}
		
		if("insert".equals(action)) {
			System.out.println("enter insert");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer spi_no = new Integer(req.getParameter("spi_no"));
			
			req.setAttribute("spi_no", spi_no);  //先將spi_no 存起來，避商品沒有圖片時，無法新增

			SecProductImagesService secProductImagesSvc = new SecProductImagesService();

			/****1.新增圖片****/ 
			Collection<Part> parts = req.getParts();

			String filename = null;
			for(Part part : parts) {
				InputStream in = part.getInputStream();				
				

				if(getFileNameFromPart(part)!=null && part.getContentType()!=null) {	
					filename = getFileNameFromPart(part);
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					secProductImagesSvc.insertSecProductImages(spi_no, buf);
				}
			}
			
			if(filename == null) {
				errorMsgs.add("請選擇檔案!");
			}
			
			/****2.新增圖片後重新查詢，並將結果存入req****/
			List<SecProductImagesVO> secProductImagesVO = secProductImagesSvc.getAll();
			List<SecProductImagesVO> Images = secProductImagesVO.stream()
																.filter(i -> i.getSpi_no().intValue() == spi_no.intValue())
																.collect(Collectors.toList());
			req.setAttribute("Images", Images);
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/back_end/secProductImg/showImages.jsp");
				failView.forward(req, res);
				return;
			}
			
			/****4.頁面轉向****/
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/secProductImg/showImages.jsp");
			successView.forward(req, res);
			return;
		}
		
		if("ExportImages".equals(action)) {
			res.setContentType("image/jpeg");
			Integer spim_no= new Integer(req.getParameter("spim_no"));
			SecProductImagesService secProductImagesSvc = new SecProductImagesService();
			SecProductImagesVO secProductImagesVO = secProductImagesSvc.getOneSecProductImages(spim_no);
			byte[] imgArry = secProductImagesVO.getSpim_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(imgArry);
			out.close();
			return;
		}
		
		if("showShopImage".equals(action)) {
			res.setContentType("image/jpeg");
			
			/****1.取的參數****/
			Integer spi_no =new Integer(req.getParameter("spi_no"));
			
			/****2.查詢照片****/
			SecProductImagesService secProductImagesSvc = new SecProductImagesService();
			List<SecProductImagesVO> imgaeList = secProductImagesSvc.getAll();
			
			/****3.過濾照片****/
			Optional<SecProductImagesVO> firstImages = null;
			
			firstImages = imgaeList.stream()
								   .filter(i -> i.getSpi_no().intValue() == spi_no.intValue())
								   .findFirst();
			
			/****4.依PK取得圖片****/
			SecProductImagesVO OutImages = secProductImagesSvc.getOneSecProductImages(firstImages.get().getSpim_no());
			
			/****5.輸出圖片****/
			byte[] imgArry = OutImages.getSpim_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(imgArry);
			out.close();
			return;
		}
		
	}
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
