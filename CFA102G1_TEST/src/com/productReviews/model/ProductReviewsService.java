package com.productReviews.model;

import java.util.*;
import java.util.stream.Collectors;

public class ProductReviewsService {
	private I_ProductReviewsDAO dao;
	
	public ProductReviewsService() {
		dao = new ProductReviewsDAO();
	}
	
	public ProductReviewsVO insertProductReviews(Integer rc_no, Integer ro_no, String pr_content, byte[] pr_images,
			Integer pr_score) {
		
		ProductReviewsVO productReviewsVO = new ProductReviewsVO();
		
		productReviewsVO.setRc_no(rc_no);
		productReviewsVO.setRo_no(ro_no);
		productReviewsVO.setPr_content(pr_content);
		productReviewsVO.setPr_images(pr_images);
		productReviewsVO.setPr_score(pr_score);

		productReviewsVO = dao.insert(productReviewsVO);
		
		return productReviewsVO;
	}
	
	public ProductReviewsVO updateProductReviews(Integer pr_no, Integer rc_no, Integer ro_no, String pr_content, byte[] pr_images,
			Integer pr_score, String pr_status) {

		ProductReviewsVO productReviewsVO = new ProductReviewsVO();

		productReviewsVO.setPr_no(pr_no);
		productReviewsVO.setRc_no(rc_no);
		productReviewsVO.setRo_no(ro_no);
		productReviewsVO.setPr_content(pr_content);
		productReviewsVO.setPr_images(pr_images);
		productReviewsVO.setPr_score(pr_score);
		productReviewsVO.setPr_status(pr_status);
		
		dao.update(productReviewsVO);

		return productReviewsVO;
	}
	
	public ProductReviewsVO updateProductReviews(ProductReviewsVO prVO) {
		dao.update(prVO);
		return prVO;
	}
	
	public void deleteProductReviews(Integer pr_no) {
		dao.delete(pr_no);
	}

	public ProductReviewsVO getOneProductReviews(Integer pr_no) {
		return dao.findByPK(pr_no);
	}

	public List<ProductReviewsVO> getAll() {
		return dao.getAll();
	}
	
	//評價上下架
	public void changePrStatus(Integer pr_no) {
		ProductReviewsVO prVO = dao.findByPK(pr_no);
		if(prVO.getPr_status().equals("上架")) {
			prVO.setPr_status("下架");
			dao.update(prVO);
		} else if (prVO.getPr_status().equals("下架")) {
			prVO.setPr_status("上架");
			dao.update(prVO);
		}
	}
	//用來給前台,判斷Rc有幾個'上架'評價
	public List<ProductReviewsVO> getListbyRc(Integer rc_no) {
		List<ProductReviewsVO> list = dao.getAll().stream()
				.filter(e -> e.getRc_no().equals(rc_no))
				.filter(e -> e.getPr_status().equals("上架"))
				.collect(Collectors.toList());	
		return list;
	}
	
	//用Ro_no回傳PrVO,用來判斷租賃訂單是否已經評價過
	public ProductReviewsVO getOneProductReviewsbyRo_no(Integer ro_no) {
		List<ProductReviewsVO> list = dao.getAll().stream()
				.filter(e -> e.getRo_no().equals(ro_no))
				.collect(Collectors.toList());
		if(list.size()==0)
			return null;
		else 
			return list.get(0);
	}
}
