package com.rentalProductList.model;

import java.io.Serializable;
import java.sql.Timestamp;

//Table Name : RENTAL_PRODUCT_LIST(租賃商品資訊)

public class RentalProductListVO implements Serializable,Comparable<RentalProductListVO>{
	private Integer	rpl_no;
	private Integer	rc_no;
	private String rpl_serialnum;
	private String rpl_note;
	private String rpl_status;
	private Integer	rpl_rentcount;
	private Timestamp rpl_jointtime;

	public RentalProductListVO() {
		
	}

	public RentalProductListVO(Integer rpl_no, Integer rc_no, String rpl_serialnum, String rpl_note, String rpl_status,
			Integer rpl_rentcount, Timestamp rpl_jointtime) {
		super();
		this.rpl_no = rpl_no;
		this.rc_no = rc_no;
		this.rpl_serialnum = rpl_serialnum;
		this.rpl_note = rpl_note;
		this.rpl_status = rpl_status;
		this.rpl_rentcount = rpl_rentcount;
		this.rpl_jointtime = rpl_jointtime;
	}



	public Integer getRpl_no() {
		return rpl_no;
	}

	public void setRpl_no(Integer rpl_no) {
		this.rpl_no = rpl_no;
	}

	public Integer getRc_no() {
		return rc_no;
	}

	public void setRc_no(Integer rc_no) {
		this.rc_no = rc_no;
	}

	public String getRpl_serialnum() {
		return rpl_serialnum;
	}

	public void setRpl_serialnum(String rpl_serialnum) {
		this.rpl_serialnum = rpl_serialnum;
	}

	public String getRpl_note() {
		return rpl_note;
	}

	public void setRpl_note(String rpl_note) {
		this.rpl_note = rpl_note;
	}

	public String getRpl_status() {
		return rpl_status;
	}

	public void setRpl_status(String rpl_status) {
		this.rpl_status = rpl_status;
	}

	public Integer getRpl_rentcount() {
		return rpl_rentcount;
	}

	public void setRpl_rentcount(Integer rpl_rentcount) {
		this.rpl_rentcount = rpl_rentcount;
	}

	public Timestamp getRpl_jointtime() {
		return rpl_jointtime;
	}

	public void setRpl_jointtime(Timestamp rpl_jointtime) {
		this.rpl_jointtime = rpl_jointtime;
	}

	public int compareTo(RentalProductListVO rplVO) {
		if(this.getRpl_rentcount()>rplVO.getRpl_rentcount())
			return 1;
		else if (this.getRpl_rentcount()==rplVO.getRpl_rentcount())
			return 0;
		else 
			return -1;
	}
}
