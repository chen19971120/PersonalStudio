package com.productReviews.model;

import java.io.Serializable;

//Table Name : PRODUCT_REVIEWS(租賃商品評價)

public class ProductReviewsVO implements Serializable{
	private Integer	pr_no;
	private Integer	rc_no;
	private Integer	ro_no;
	private String	pr_content;
	private byte[]	pr_images;
	private Integer	pr_score;
	private String	pr_status;

	public ProductReviewsVO() {
		
	}

	
	
	public ProductReviewsVO(Integer pr_no, Integer rc_no, Integer ro_no, String pr_content, byte[] pr_images,
			Integer pr_score, String pr_status) {
		this.pr_no = pr_no;
		this.rc_no = rc_no;
		this.ro_no = ro_no;
		this.pr_content = pr_content;
		this.pr_images = pr_images;
		this.pr_score = pr_score;
		this.pr_status = pr_status;
	}



	public Integer getPr_no() {
		return pr_no;
	}

	public void setPr_no(Integer pr_no) {
		this.pr_no = pr_no;
	}

	public Integer getRc_no() {
		return rc_no;
	}

	public void setRc_no(Integer rc_no) {
		this.rc_no = rc_no;
	}

	public Integer getRo_no() {
		return ro_no;
	}

	public void setRo_no(Integer ro_no) {
		this.ro_no = ro_no;
	}

	public String getPr_content() {
		return pr_content;
	}

	public void setPr_content(String pr_content) {
		this.pr_content = pr_content;
	}

	public byte[] getPr_images() {
		return pr_images;
	}

	public void setPr_images(byte[] pr_images) {
		this.pr_images = pr_images;
	}

	public Integer getPr_score() {
		return pr_score;
	}

	public void setPr_score(Integer pr_score) {
		this.pr_score = pr_score;
	}

	public String getPr_status() {
		return pr_status;
	}

	public void setPr_status(String pr_status) {
		this.pr_status = pr_status;
	}
}
