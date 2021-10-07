package com.promotion.model;

import java.util.List;

public interface I_PromotionDAO {
	public PromotionVO insert(PromotionVO promotionVO);
	public void update(PromotionVO promotionVO);
	public void delete(Integer promotion_no);
	public PromotionVO findByPk(Integer promotion_no);
	public List<PromotionVO> getAll();
}
