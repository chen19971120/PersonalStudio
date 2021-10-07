package com.sec_product_inform.model;

import java.io.Serializable;

//Table Name : SEC_PRODUCT_INFORMATION(二手商品資訊)

public class ProductInformVO implements Serializable{
	private Integer spi_no;
	private String spi_name;
	private Integer spc_no;
	private String spi_content;
	private Integer spi_pri; 
	private Integer spi_stock; 
	private String spi_sta;
	public Integer getSpi_no() {
		return spi_no;
	}
	public void setSpi_no(Integer spi_no) {
		this.spi_no = spi_no;
	}
	public String getSpi_name() {
		return spi_name;
	}
	public void setSpi_name(String spi_name) {
		this.spi_name = spi_name;
	}
	public Integer getSpc_no() {
		return spc_no;
	}
	public void setSpc_no(Integer spc_no) {
		this.spc_no = spc_no;
	}
	public String getSpi_content() {
		return spi_content;
	}
	public void setSpi_content(String spi_content) {
		this.spi_content = spi_content;
	}
	public Integer getSpi_pri() {
		return spi_pri;
	}
	public void setSpi_pri(Integer spi_pri) {
		this.spi_pri = spi_pri;
	}
	public Integer getSpi_stock() {
		return spi_stock;
	}
	public void setSpi_stock(Integer spi_stock) {
		this.spi_stock = spi_stock;
	}
	public String getSpi_sta() {
		return spi_sta;
	}
	public void setSpi_sta(String spi_sta) {
		this.spi_sta = spi_sta;
	}
	
	
}
