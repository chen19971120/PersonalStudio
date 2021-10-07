package com.staff_authority.model;


//Table Name : STAFF_AUTHORITY(員工權限)
public class Staff_AuthorityVO implements java.io.Serializable{
	   private Integer staff_no;
	   private Integer authority_no;
	public Staff_AuthorityVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Staff_AuthorityVO(Integer staff_no, Integer authority_no) {
		super();
		this.staff_no = staff_no;
		this.authority_no = authority_no;
	}
	public Integer getStaff_no() {
		return staff_no;
	}
	public void setStaff_no(Integer staff_no) {
		this.staff_no = staff_no;
	}
	public Integer getAuthority_no() {
		return authority_no;
	}
	public void setAuthority_no(Integer authority_no) {
		this.authority_no = authority_no;
	}
	
	   
	}

