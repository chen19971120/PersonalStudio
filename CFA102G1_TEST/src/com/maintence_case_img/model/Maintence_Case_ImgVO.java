package com.maintence_case_img.model;

import java.io.Serializable;

//Table Name : MAINTENANCE_CASE_IMAGES(驗證碼)

public class Maintence_Case_ImgVO implements Serializable{
	
	private Integer mci_no;
	private Integer mca_no;
	private String mci_before_img;
	private byte[] mci_after_img;
	
	public Integer getMci_no() {
		return mci_no;
	}
	public void setMci_no(Integer mci_no) {
		this.mci_no = mci_no;
	}
	public Integer getMca_no() {
		return mca_no;
	}
	public void setMca_no(Integer mca_no) {
		this.mca_no = mca_no;
	}
	public String getMci_before_img() {
		return mci_before_img;
	}
	public void setMci_before_img(String mci_before_img) {
		this.mci_before_img = mci_before_img;
	}
	public byte[] getMci_after_img() {
		return mci_after_img;
	}
	public void setMci_after_img(byte[] mci_after_img) {
		this.mci_after_img = mci_after_img;
	}

}
