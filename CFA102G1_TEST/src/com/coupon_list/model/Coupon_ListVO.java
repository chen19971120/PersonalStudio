package com.coupon_list.model;

import java.io.Serializable;

//Table name：COUPON_LIST(優惠券明細)

public class Coupon_ListVO implements Serializable {
	private Integer ci_no;
	private Integer mem_no;
	private String cl_status;

	public Coupon_ListVO() {
	}

	public Coupon_ListVO(Integer ci_no, Integer mem_no, String cl_status) {
		this.ci_no = ci_no;
		this.mem_no = mem_no;
		this.cl_status = cl_status;
	}

	public Integer getCi_no() {
		return ci_no;
	}

	public void setCi_no(Integer ci_no) {
		this.ci_no = ci_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public String getCl_status() {
		return cl_status;
	}

	public void setCl_status(String cl_status) {
		this.cl_status = cl_status;
	}

}