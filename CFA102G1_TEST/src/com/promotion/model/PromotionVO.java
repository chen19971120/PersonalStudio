package com.promotion.model;

import java.io.Serializable;
import java.sql.Timestamp;

//Table Name : PROMOTIONS(促銷活動)

public class PromotionVO  implements Serializable{
	private Integer promotion_no;
	private String promotion_name;
	private Timestamp promotion_start_date;
	private Timestamp promotion_end_date;
	
	public Integer getPromotion_no() {
		return promotion_no;
	}
	public void setPromotion_no(Integer promotion_no) {
		this.promotion_no = promotion_no;
	}
	public String getPromotion_name() {
		return promotion_name;
	}
	public void setPromotion_name(String promotion_name) {
		this.promotion_name = promotion_name;
	}
	public Timestamp getPromotion_start_date() {
		return promotion_start_date;
	}
	public void setPromotion_start_date(Timestamp promotion_start_date) {
		this.promotion_start_date = promotion_start_date;
	}
	public Timestamp getPromotion_end_date() {
		return promotion_end_date;
	}
	public void setPromotion_end_date(Timestamp promotion_end_date) {
		this.promotion_end_date = promotion_end_date;
	}

	
}
