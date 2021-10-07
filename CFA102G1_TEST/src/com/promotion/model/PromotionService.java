package com.promotion.model;

import java.sql.Timestamp;
import java.util.List;

public class PromotionService{
	private I_PromotionDAO dao;
	
	public PromotionService() {
		dao = new PromotionDAO();
	}
	
	public PromotionVO insertPromotion(String promotion_name, Timestamp promotion_start_date, Timestamp promotion_end_date ) {
		PromotionVO promotionVO = new PromotionVO();
		
		promotionVO.setPromotion_name(promotion_name);
		promotionVO.setPromotion_start_date(promotion_start_date);
		promotionVO.setPromotion_end_date(promotion_end_date);
		promotionVO = dao.insert(promotionVO);
		return promotionVO;
	}

	public PromotionVO updatePromotion(Integer promotion_no, String promotion_name, Timestamp promotion_start_date, Timestamp promotion_end_date) {
		PromotionVO promotionVO = new PromotionVO();
		
		promotionVO.setPromotion_no(promotion_no);
		promotionVO.setPromotion_name(promotion_name);
		promotionVO.setPromotion_start_date(promotion_start_date);
		promotionVO.setPromotion_end_date(promotion_end_date);
		dao.update(promotionVO);
		return promotionVO;
	}

	public void deletePromotion(Integer promotion_no) {
		dao.delete(promotion_no);
	}

	public PromotionVO getOnePromotion(Integer promotion_no) {
		return dao.findByPk(promotion_no);
	}

	public List<PromotionVO> getAll() {
		return dao.getAll();
	}

}
