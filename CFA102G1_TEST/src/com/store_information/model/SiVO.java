package com.store_information.model;

import java.io.Serializable;
import java.sql.*;

//Table Name : STORE_INFORMATION(商家資訊)

public class SiVO implements Serializable{
	private Integer si_no;	
	private String si_address;
	private String si_open;
	private String si_phone;
	private String si_email;
	private String si_line;
	
	public Integer getSi_no() {
		return si_no;
	}
	public void setSi_no(Integer si_no) {
		this.si_no = si_no;
	}
	public String getSi_address() {
		return si_address;
	}
	public void setSi_address(String si_address) {
		this.si_address = si_address;
	}
	public String getSi_open() {
		return si_open;
	}
	public void setSi_open(String si_open) {
		this.si_open = si_open;
	}
	public String getSi_phone() {
		return si_phone;
	}
	public void setSi_phone(String si_phone) {
		this.si_phone = si_phone;
	}
	public String getSi_email() {
		return si_email;
	}
	public void setSi_email(String si_email) {
		this.si_email = si_email;
	}
	public String getSi_line() {
		return si_line;
	}
	public void setSi_line(String si_line) {
		this.si_line = si_line;
	}
	
	
}
