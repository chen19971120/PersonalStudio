package com.promotion_list.model;

import java.io.Serializable;

//Table Name : PROMOTION_LIST(促銷明細)

public class PromotionListVO implements Serializable{
	private Integer promotions_no;
	private Integer spi_no;
	private Integer spi_price;
	
	public Integer getPromotions_no() {
		return promotions_no;
	}
	public void setPromotions_no(Integer promotions_no) {
		this.promotions_no = promotions_no;
	}
	public Integer getSpi_no() {
		return spi_no;
	}
	public void setSpi_no(Integer spi_no) {
		this.spi_no = spi_no;
	}
	public Integer getSpi_price() {
		return spi_price;
	}
	public void setSpi_price(Integer spi_price) {
		this.spi_price = spi_price;
	}
	
	

}
