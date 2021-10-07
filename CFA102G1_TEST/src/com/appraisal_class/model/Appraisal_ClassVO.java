package com.appraisal_class.model;

import java.io.Serializable;

//Table Name：APPRAISAL_CLASS(估價類別)

public class Appraisal_ClassVO implements Serializable {
	private Integer acl_no;
	private String acl_id;

	public Appraisal_ClassVO() {
	}

	public Appraisal_ClassVO(Integer acl_no, String acl_id) {
		this.acl_no = acl_no;
		this.acl_id = acl_id;
	}

	public Integer getAcl_no() {
		return acl_no;
	}

	public void setAcl_no(Integer acl_no) {
		this.acl_no = acl_no;
	}

	public String getAcl_id() {
		return acl_id;
	}

	public void setAcl_id(String acl_id) {
		this.acl_id = acl_id;
	}

}