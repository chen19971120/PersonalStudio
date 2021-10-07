package com.sec_product_images.model;

import java.io.Serializable;

//Table Name : SEC_PRODUCT_IMAGES(二手商品圖片)

public class SecProductImagesVO implements Serializable{
	private Integer spim_no;
	private Integer spi_no;
	private byte[] spim_img;
	
	public Integer getSpim_no() {
		return spim_no;
	}
	public void setSpim_no(Integer spim_no) {
		this.spim_no = spim_no;
	}
	public Integer getSpi_no() {
		return spi_no;
	}
	public void setSpi_no(Integer spi_no) {
		this.spi_no = spi_no;
	}
	public byte[] getSpim_img() {
		return spim_img;
	}
	public void setSpim_img(byte[] spim_img) {
		this.spim_img = spim_img;
	}

}
