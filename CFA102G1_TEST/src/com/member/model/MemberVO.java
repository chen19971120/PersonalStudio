package com.member.model;

import java.sql.Date;



//Table Name : MEMBER(會員)
public class MemberVO implements java.io.Serializable{
	private Integer mem_no;
	private String mem_name;
	private String mem_gender;
	private String mem_phone;
	private String mem_email;
	private String mem_address;
	private String mem_account;
	private String mem_password;
	private Date mem_birth;
	private String mem_sta;
	
	
	
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
		
	}


	public MemberVO(Integer mem_no, String mem_name, String mem_gender, String mem_phone, String mem_email,
			String mem_address, String mem_account, String mem_password, Date mem_birth, String mem_sta) {
		super();
		this.mem_no = mem_no;
		this.mem_name = mem_name;
		this.mem_gender = mem_gender;
		this.mem_phone = mem_phone;
		this.mem_email = mem_email;
		this.mem_address = mem_address;
		this.mem_account = mem_account;
		this.mem_password = mem_password;
		this.mem_birth = mem_birth;
		this.mem_sta = mem_sta;
	}

	public Integer getMem_no() {
		return mem_no;
	}




	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}




	public String getMem_name() {
		return mem_name;
	}




	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}




	public String getMem_gender() {
		return mem_gender;
	}




	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}




	public String getMem_phone() {
		return mem_phone;
	}




	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}




	public String getMem_email() {
		return mem_email;
	}




	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}




	public String getMem_address() {
		return mem_address;
	}




	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}




	public String getMem_account() {
		return mem_account;
	}




	public void setMem_account(String mem_account) {
		this.mem_account = mem_account;
	}




	public String getMem_password() {
		return mem_password;
	}




	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}




	public Date getMem_birth() {
		return mem_birth;
	}




	public void setMem_birth(Date mem_birth) {
		this.mem_birth = mem_birth;
	}




	public String getMem_sta() {
		return mem_sta;
	}




	public void setMem_sta(String mem_sta) {
		this.mem_sta = mem_sta;
	}




	
	}
	
	
	
	



