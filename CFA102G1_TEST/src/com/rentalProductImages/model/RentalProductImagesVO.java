package com.rentalProductImages.model;

import java.io.Serializable;

//Table Name : RENTAL_PRODUCT_IMAGES(租賃商品圖片)

public class RentalProductImagesVO implements Serializable{
	private Integer rpi_no;
	private Integer rc_no;
	private byte[] rpi_img;

	public RentalProductImagesVO(){
		
	}
	
	public RentalProductImagesVO(Integer rpi_no, Integer rc_no, byte[] rpi_img) {
		super();
		this.rpi_no = rpi_no;
		this.rc_no = rc_no;
		this.rpi_img = rpi_img;
	}

	public Integer getRpi_no() {
		return rpi_no;
	}

	public void setRpi_no(Integer rpi_no) {
		this.rpi_no = rpi_no;
	}

	public Integer getRc_no() {
		return rc_no;
	}

	public void setRc_no(Integer rc_no) {
		this.rc_no = rc_no;
	}

	public byte[] getRpi_img() {
		return rpi_img;
	}

	public void setRpi_img(byte[] rpi_img) {
		this.rpi_img = rpi_img;
	}
}
