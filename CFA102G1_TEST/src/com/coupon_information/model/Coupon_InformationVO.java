package com.coupon_information.model;

import java.io.Serializable;
import java.sql.Timestamp;

//Table name：COUPON_INFORMATION(優惠券資訊)

public class Coupon_InformationVO implements Serializable {
	private Integer ci_no;
	private String ci_name;
	private String ci_code;
	private Timestamp ci_start_time;
	private Timestamp ci_end_time;
	private Integer discount;
	private String ci_content;

	public Coupon_InformationVO() {
	}

	public Coupon_InformationVO(Integer ci_no, String ci_name, String ci_code, Timestamp ci_start_time,
			Timestamp ci_end_time, Integer discount, String ci_content) {
		this.ci_no = ci_no;
		this.ci_name = ci_name;
		this.ci_code = ci_code;
		this.ci_start_time = ci_start_time;
		this.ci_end_time = ci_end_time;
		this.discount = discount;
		this.ci_content = ci_content;
	}

	public Integer getCi_no() {
		return ci_no;
	}

	public void setCi_no(Integer ci_no) {
		this.ci_no = ci_no;
	}

	public String getCi_name() {
		return ci_name;
	}

	public void setCi_name(String ci_name) {
		this.ci_name = ci_name;
	}

	public String getCi_code() {
		return ci_code;
	}

	public void setCi_code(String ci_code) {
		
		this.ci_code = ci_code;
	}

	public Timestamp getCi_start_time() {
		
		return ci_start_time;
	}

	public void setCi_start_time(Timestamp ci_start_time) {
		this.ci_start_time = ci_start_time;
	}

	public Timestamp getCi_end_time() {
		return ci_end_time;
	}

	public void setCi_end_time(Timestamp ci_end_time) {
		this.ci_end_time = ci_end_time;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getCi_content() {
		return ci_content;
	}

	public void setCi_content(String ci_content) {
		this.ci_content = ci_content;
	}
}