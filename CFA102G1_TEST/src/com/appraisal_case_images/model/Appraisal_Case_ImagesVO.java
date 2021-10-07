package com.appraisal_case_images.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

//Table Name：APPRAISAL_CASE_IMAGES(估價案件圖片)

public class Appraisal_Case_ImagesVO implements Serializable {

	private Integer aci_no;
	private Integer aca_no;
	private byte[] aci_img;

	public Appraisal_Case_ImagesVO() {
	}

	public Appraisal_Case_ImagesVO(Integer aci_no, Integer aca_no, byte[] aci_img) {
		this.aci_no = aci_no;
		this.aca_no = aca_no;
		this.aci_img = aci_img;
	}

	public Integer getAci_no() {
		return aci_no;
	}

	public void setAci_no(Integer aci_no) {
		this.aci_no = aci_no;
	}

	public Integer getAca_no() {
		return aca_no;
	}

	public void setAca_no(Integer aca_no) {
		this.aca_no = aca_no;
	}

	public byte[] getAci_img() {
		return aci_img;
	}

	public void setAci_img(byte[] aci_img) {
		this.aci_img = aci_img;
	}
}