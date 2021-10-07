package com.person_msg.model;

import java.sql.Timestamp;

//Table Name : PERSON_MESSAGE(訊息總覽)

public class Person_MsgVO {
	
	private Integer pm_no;
	private Integer mem_no;
	private Timestamp pm_date;
	private String pm_content;
	private String pm_status;
	
	public Integer getPm_no() {
		return pm_no;
	}
	public void setPm_no(Integer pm_no) {
		this.pm_no = pm_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Timestamp getPm_date() {
		return pm_date;
	}
	public void setPm_date(Timestamp pm_date) {
		this.pm_date = pm_date;
	}
	public String getPm_content() {
		return pm_content;
	}
	public void setPm_content(String pm_content) {
		this.pm_content = pm_content;
	}
	public String getPm_status() {
		return pm_status;
	}
	public void setPm_status(String pm_status) {
		this.pm_status = pm_status;
	}
	
	

}
