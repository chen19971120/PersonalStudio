package com.promotion_list.model;

import java.util.List;

public class PromotionListService{
	private I_PromotionListDAO dao;
	
	public PromotionListService() {
		dao = new PromotionListDAO();
	}

	public PromotionListVO insertPromotionList(Integer promotions_no, Integer spi_no, Integer spi_price) {
		PromotionListVO promotionListVO = new PromotionListVO();
		
		promotionListVO.setPromotions_no(promotions_no);
		promotionListVO.setSpi_no(spi_no);
		promotionListVO.setSpi_price(spi_price);
		dao.insert(promotionListVO);
		return promotionListVO;
	}

	public PromotionListVO updatePromotionList(Integer promotions_no, Integer spi_no, Integer spi_price) {
		PromotionListVO promotionListVO = new PromotionListVO();
		
		promotionListVO.setPromotions_no(promotions_no);
		promotionListVO.setSpi_no(spi_no);
		promotionListVO.setSpi_price(spi_price);
		dao.update(promotionListVO);
		return promotionListVO;
	}

	public void deletePromotionList(Integer promotions_no, Integer spi_no) {	
		dao.delete(promotions_no, spi_no);
	}

	public PromotionListVO getOnePromotionList(Integer promotions_no, Integer spi_no) {
		return dao.findByPK(promotions_no, spi_no);
	}

	public List<PromotionListVO> getAll() {
		return dao.getAll();
	}

}
