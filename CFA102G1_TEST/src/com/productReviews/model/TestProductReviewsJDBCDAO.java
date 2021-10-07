package com.productReviews.model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TestProductReviewsJDBCDAO {
	public static void main(String[] args) throws IOException {
//		I_ProductReviewsDAO dao = new ProductReviewsDAO();
//		ProductReviewsService prSvc = new ProductReviewsService();
		
		System.out.println(123);
		
		
		
//		List<ProductReviewsVO> list = dao.getAll().stream()
//				.filter(e -> e.getRc_no()==(rc_no))
//				.filter(e -> e.getPr_status().equals("上架"))
//				.collect(Collectors.toList());
//		List<ProductReviewsVO> listAll = dao.getAll();
//		List<ProductReviewsVO> list = new ArrayList<ProductReviewsVO>();
//		for(ProductReviewsVO prVO:listAll) {
//			if(prVO.getRc_no().equals(rc_no)&&prVO.getPr_status().equals("上架"))
//				list.add(prVO);
//		}
		
		
		
		
		
		
		
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
}
