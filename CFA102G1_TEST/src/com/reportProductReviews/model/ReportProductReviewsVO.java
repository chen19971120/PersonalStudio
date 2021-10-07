package com.reportProductReviews.model;

import java.io.Serializable;
import java.sql.Timestamp;

//Table Name : REPORT_PRODUCT_REVIEWS(租賃商品檢舉)

public class ReportProductReviewsVO implements Serializable{
	private Integer	rep_no;
	private Integer	pr_no;
	private Integer	mem_no;
	private String report_content;
	private String	rep_status;
	private Timestamp rep_date;
	
	public ReportProductReviewsVO() {
		
	}
	
	public ReportProductReviewsVO(Integer rep_no, Integer pr_no, Integer mem_no, String report_content,
			String rep_status, Timestamp rep_date) {
		super();
		this.rep_no = rep_no;
		this.pr_no = pr_no;
		this.mem_no = mem_no;
		this.report_content = report_content;
		this.rep_status = rep_status;
		this.rep_date = rep_date;
	}



	public Integer getRep_no() {
		return rep_no;
	}

	public void setRep_no(Integer rep_no) {
		this.rep_no = rep_no;
	}

	public Integer getPr_no() {
		return pr_no;
	}

	public void setPr_no(Integer pr_no) {
		this.pr_no = pr_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	public String getRep_status() {
		return rep_status;
	}

	public void setRep_status(String rep_status) {
		this.rep_status = rep_status;
	}

	public Timestamp getRep_date() {
		return rep_date;
	}

	public void setRep_date(Timestamp rep_date) {
		this.rep_date = rep_date;
	}
}
