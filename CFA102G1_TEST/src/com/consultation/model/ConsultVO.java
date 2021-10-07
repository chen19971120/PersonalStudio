package com.consultation.model;

import java.io.Serializable;
import java.sql.*;

//Table Name : CONSULTATION(諮詢表單)

public class ConsultVO implements Serializable{
	private Integer consult_no;
	private String consultant;
	private String consult_phone;
	private String consult_email;
	private String consult_content;
	private Integer staff_no;
	private String consult_sta;
	
	public Integer getConsult_no() {
		return consult_no;
	}
	public void setConsult_no(Integer consult_no) {
		this.consult_no = consult_no;
	}
	public String getConsultant() {
		return consultant;
	}
	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}
	public String getConsult_phone() {
		return consult_phone;
	}
	public void setConsult_phone(String consult_phone) {
		this.consult_phone = consult_phone;
	}
	public String getConsult_email() {
		return consult_email;
	}
	public void setConsult_email(String consult_email) {
		this.consult_email = consult_email;
	}
	public String getConsult_content() {
		return consult_content;
	}
	public void setConsult_content(String consult_content) {
		this.consult_content = consult_content;
	}
	public Integer getStaff_no() {
		return staff_no;
	}
	public void setStaff_no(Integer staff_no) {
		this.staff_no = staff_no;
	}
	public String getConsult_sta() {
		return consult_sta;
	}
	public void setConsult_sta(String consult_sta) {
		this.consult_sta = consult_sta;
	}
	
	
}
