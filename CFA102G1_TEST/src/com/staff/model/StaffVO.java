package com.staff.model;

//Table Name : STAFF(員工)

public class StaffVO implements java.io.Serializable{
	private Integer staff_no;
	private String staff_name;
	private String staff_gender;
	private String staff_phone;
	private String staff_email;
	private String staff_address;
	private String staff_account;
	private String staff_password;
	private String staff_sta;
	
	public StaffVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StaffVO(Integer staff_no, String staff_name, String staff_gender, String staff_phone, String staff_email,
			String staff_address, String staff_account, String staff_password, String staff_sta) {
		super();
		this.staff_no = staff_no;
		this.staff_name = staff_name;
		this.staff_gender = staff_gender;
		this.staff_phone = staff_phone;
		this.staff_email = staff_email;
		this.staff_address = staff_address;
		this.staff_account = staff_account;
		this.staff_password = staff_password;
		this.staff_sta = staff_sta;
	}

	public Integer getStaff_no() {
		return staff_no;
	}

	public void setStaff_no(Integer staff_no) {
		this.staff_no = staff_no;
	}

	public String getStaff_name() {
		return staff_name;
	}

	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}

	public String getStaff_gender() {
		return staff_gender;
	}

	public void setStaff_gender(String staff_gender) {
		this.staff_gender = staff_gender;
	}

	public String getStaff_phone() {
		return staff_phone;
	}

	public void setStaff_phone(String staff_phone) {
		this.staff_phone = staff_phone;
	}

	public String getStaff_email() {
		return staff_email;
	}

	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}

	public String getStaff_address() {
		return staff_address;
	}

	public void setStaff_address(String staff_address) {
		this.staff_address = staff_address;
	}

	public String getStaff_account() {
		return staff_account;
	}

	public void setStaff_account(String staff_account) {
		this.staff_account = staff_account;
	}

	public String getStaff_password() {
		return staff_password;
	}

	public void setStaff_password(String staff_password) {
		this.staff_password = staff_password;
	}

	public String getStaff_sta() {
		return staff_sta;
	}

	public void setStaff_sta(String staff_sta) {
		this.staff_sta = staff_sta;
	}
	
	
	
}


