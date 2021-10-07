package com.sec_product_class.model;

import java.io.Serializable;

//Table Name : SEC_PRODUCT_CLASS(二手商品類別)

public class ProductClassVO implements Serializable{
	private Integer spc_no;
	private String spc_name;
	
	public Integer getSpc_no() {
		return spc_no;
	}
	public void setSpc_no(Integer spc_no) {
		this.spc_no = spc_no;
	}
	public String getSpc_name() {
		return spc_name;
	}
	public void setSpc_name(String spc_name) {
		this.spc_name = spc_name;
	}
	
}
