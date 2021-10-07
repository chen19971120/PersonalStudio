package com.appraisal_case.model;

import java.io.Serializable;
import java.sql.Timestamp;

//Table Name：ARRRAISAL_CASE(估價案件)

public class Appraisal_CaseVO implements Serializable {
	private Integer aca_no;
	private Integer mem_no;
	private String aca_itm_id;
	private Integer acl_no;
	private String aca_itm_spec;
	private Timestamp aca_date;
	private String aca_itm_mode;
	private Integer aca_first_p;
	private Timestamp aca_recpt_date;
	private Integer aca_final_p;
	private Timestamp aca_shipment_date;
	private Timestamp aca_pickup_date;
	private String aca_pay;
	private Timestamp aca_comp_date;
	private String aca_cod;
	private String aca_adrs;

	public Appraisal_CaseVO() {
	}

	public Appraisal_CaseVO(Integer aca_no, Integer mem_no, String aca_itm_id, Integer acl_no, String aca_itm_spec,
			Timestamp aca_date, String aca_itm_mode, Integer aca_first_p, Timestamp aca_recpt_date, Integer aca_final_p,
			Timestamp aca_shipment_date, Timestamp aca_pickup_date, String aca_pay, Timestamp aca_comp_date,
			String aca_cod, String aca_adrs) {
		this.aca_no = aca_no;
		this.mem_no = mem_no;
		this.aca_itm_id = aca_itm_id;
		this.acl_no = acl_no;
		this.aca_itm_spec = aca_itm_spec;
		this.aca_date = aca_date;
		this.aca_itm_mode = aca_itm_mode;
		this.aca_first_p = aca_first_p;
		this.aca_recpt_date = aca_recpt_date;
		this.aca_final_p = aca_final_p;
		this.aca_shipment_date = aca_shipment_date;
		this.aca_pickup_date = aca_pickup_date;
		this.aca_pay = aca_pay;
		this.aca_comp_date = aca_comp_date;
		this.aca_cod = aca_cod;
		this.aca_adrs = aca_adrs;
	}

	public Integer getAca_no() {
		return aca_no;
	}

	public void setAca_no(Integer aca_no) {
		this.aca_no = aca_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public String getAca_itm_id() {
		return aca_itm_id;
	}

	public void setAca_itm_id(String aca_itm_id) {
		this.aca_itm_id = aca_itm_id;
	}

	public Integer getAcl_no() {
		return acl_no;
	}

	public void setAcl_no(Integer acl_no) {
		this.acl_no = acl_no;
	}

	public String getAca_itm_spec() {
		return aca_itm_spec;
	}

	public void setAca_itm_spec(String aca_itm_spec) {
		this.aca_itm_spec = aca_itm_spec;
	}

	public Timestamp getAca_date() {
		return aca_date;
	}

	public void setAca_date(Timestamp aca_date) {
		this.aca_date = aca_date;
	}

	public String getAca_itm_mode() {
		return aca_itm_mode;
	}

	public void setAca_itm_mode(String aca_itm_mode) {
		this.aca_itm_mode = aca_itm_mode;
	}

	public Integer getAca_first_p() {
		return aca_first_p;
	}

	public void setAca_first_p(Integer aca_first_p) {
		this.aca_first_p = aca_first_p;
	}

	public Timestamp getAca_recpt_date() {
		return aca_recpt_date;
	}

	public void setAca_recpt_date(Timestamp aca_recpt_date) {
		this.aca_recpt_date = aca_recpt_date;
	}

	public Integer getAca_final_p() {
		return aca_final_p;
	}

	public void setAca_final_p(Integer aca_final_p) {
		this.aca_final_p = aca_final_p;
	}

	public Timestamp getAca_shipment_date() {
		return aca_shipment_date;
	}

	public void setAca_shipment_date(Timestamp aca_shipment_date) {
		this.aca_shipment_date = aca_shipment_date;
	}

	public Timestamp getAca_pickup_date() {
		return aca_pickup_date;
	}

	public void setAca_pickup_date(Timestamp aca_pickup_date) {
		this.aca_pickup_date = aca_pickup_date;
	}

	public String getAca_pay() {
		return aca_pay;
	}

	public void setAca_pay(String aca_pay) {
		this.aca_pay = aca_pay;
	}

	public Timestamp getAca_comp_date() {
		return aca_comp_date;
	}

	public void setAca_comp_date(Timestamp aca_comp_date) {
		this.aca_comp_date = aca_comp_date;
	}

	public String getAca_cod() {
		return aca_cod;
	}

	public void setAca_cod(String aca_cod) {
		this.aca_cod = aca_cod;
	}

	public String getAca_adrs() {
		return aca_adrs;
	}

	public void setAca_adrs(String aca_adrs) {
		this.aca_adrs = aca_adrs;
	}
}