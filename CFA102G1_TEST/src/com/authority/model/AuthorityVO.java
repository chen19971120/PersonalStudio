package com.authority.model;

//Table Name : AUTHORITY(功能)

public class AuthorityVO implements java.io.Serializable{
	private Integer authority_no;
	private String authority_name;
	public AuthorityVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthorityVO(Integer authority_no, String authority_name) {
		super();
		this.authority_no = authority_no;
		this.authority_name = authority_name;
	}
	public Integer getAuthority_no() {
		return authority_no;
	}
	public void setAuthority_no(Integer authority_no) {
		this.authority_no = authority_no;
	}
	public String getAuthority_name() {
		return authority_name;
	}
	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}
	
}