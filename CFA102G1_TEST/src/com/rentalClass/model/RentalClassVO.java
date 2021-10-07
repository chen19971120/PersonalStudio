package com.rentalClass.model;

import java.io.Serializable;

//Table Name : RENTAL_CLASS(租賃商品類別)

public class RentalClassVO implements Serializable,Comparable<RentalClassVO>{
	private Integer rc_no;
	private String rc_name;
	private String rc_item;	
	private String rc_detail;	
	private Integer rc_deposit;	
	private Integer rc_price;
	private Integer rc_total_count;
	private Integer rc_total_score;	
	private Integer rc_storage;	
	private String rc_status;
	
	public RentalClassVO() {
	}

	public RentalClassVO(String rc_name, String rc_item, String rc_detail, Integer rc_deposit, Integer rc_price) {
		super();
		this.rc_name = rc_name;
		this.rc_item = rc_item;
		this.rc_detail = rc_detail;
		this.rc_deposit = rc_deposit;
		this.rc_price = rc_price;
	}



	public Integer getRc_no() {
		return rc_no;
	}

	public void setRc_no(Integer rc_no) {
		this.rc_no = rc_no;
	}

	public String getRc_name() {
		return rc_name;
	}

	public void setRc_name(String rc_name) {
		this.rc_name = rc_name;
	}

	public String getRc_item() {
		return rc_item;
	}

	public void setRc_item(String rc_item) {
		this.rc_item = rc_item;
	}

	public String getRc_detail() {
		return rc_detail;
	}

	public void setRc_detail(String rc_detail) {
		this.rc_detail = rc_detail;
	}

	public Integer getRc_deposit() {
		return rc_deposit;
	}

	public void setRc_deposit(Integer rc_deposit) {
		this.rc_deposit = rc_deposit;
	}

	public Integer getRc_price() {
		return rc_price;
	}

	public void setRc_price(Integer rc_price) {
		this.rc_price = rc_price;
	}

	public Integer getRc_total_count() {
		return rc_total_count;
	}

	public void setRc_total_count(Integer rc_total_count) {
		this.rc_total_count = rc_total_count;
	}

	public Integer getRc_total_score() {
		return rc_total_score;
	}

	public void setRc_total_score(Integer rc_total_score) {
		this.rc_total_score = rc_total_score;
	}

	public Integer getRc_storage() {
		return rc_storage;
	}

	public void setRc_storage(Integer rc_storage) {
		this.rc_storage = rc_storage;
	}

	public String getRc_status() {
		return rc_status;
	}

	public void setRc_status(String rc_status) {
		this.rc_status = rc_status;
	}

	@Override
	public int compareTo(RentalClassVO addRcVO) {
		if(this.rc_total_count>addRcVO.rc_total_count)
			return 1;
		else if (this.rc_total_count == addRcVO.rc_total_count)
				return 0;
		else
			return -1;
	}


}
