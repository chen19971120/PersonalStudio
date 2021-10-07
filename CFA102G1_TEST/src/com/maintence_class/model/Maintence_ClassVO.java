package com.maintence_class.model;

import java.io.Serializable;

//Table Name : MAINTENANCE_CLASS(維修案件類別)

public class Maintence_ClassVO implements Serializable {
	
	private Integer mcl_no;
	private String mcl_id;
	
	public Integer getMcl_no() {
		return mcl_no;
	}
	public void setMcl_no(Integer mcl_no) {
		this.mcl_no = mcl_no;
	}
	public String getMcl_id() {
		return mcl_id;
	}
	public void setMcl_id(String mcl_id) {
		this.mcl_id = mcl_id;
	}
	
	

}
