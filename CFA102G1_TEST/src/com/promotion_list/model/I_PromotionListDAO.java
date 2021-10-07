package com.promotion_list.model;

import java.util.List;

public interface I_PromotionListDAO {
	public void insert(PromotionListVO promotionList);
	public void update(PromotionListVO promotionList);
	public void delete(Integer promotions_no, Integer spi_no);
	public PromotionListVO findByPK(Integer promotions_no, Integer spi_no);
	public List<PromotionListVO> getAll();
}
