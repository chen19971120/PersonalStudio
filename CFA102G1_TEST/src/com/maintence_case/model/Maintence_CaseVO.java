package com.maintence_case.model;

import java.io.Serializable;
import java.sql.Timestamp;

//Table Name : MAINTENANCE_CASE(維修案件)

public class Maintence_CaseVO implements Serializable{
	
	private Integer mca_no;
	private Integer mem_no;
	private String mca_itm_id;
	private Integer mcl_no;
	private String mca_itm_spec;
	private Timestamp mca_date;
	private String mca_itm_mode;
	private Integer mca_first_p;
	private Timestamp mca_recpt_date;
	private Integer mca_final_p;
	private Timestamp mca_shipment_date;
	private Timestamp mca_pickup_date;
	private String mca_pay;
	private Timestamp mca_comp_date;
	private String mca_cod;
	private String mca_adrs;
	private String mca_context;
	
	public Integer getMca_no() {
		return mca_no;
	}
	public void setMca_no(Integer mca_no) {
		this.mca_no = mca_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getMca_itm_id() {
		return mca_itm_id;
	}
	public void setMca_itm_id(String mca_itm_id) {
		this.mca_itm_id = mca_itm_id;
	}
	public Integer getMcl_no() {
		return mcl_no;
	}
	public void setMcl_no(Integer mcl_no) {
		this.mcl_no = mcl_no;
	}
	public String getMca_itm_spec() {
		return mca_itm_spec;
	}
	public void setMca_itm_spec(String mca_itm_spec) {
		this.mca_itm_spec = mca_itm_spec;
	}
	public Timestamp getMca_date() {
		return mca_date;
	}
	public void setMca_date(Timestamp mca_date) {
		this.mca_date = mca_date;
	}
	public String getMca_itm_mode() {
		return mca_itm_mode;
	}
	public void setMca_itm_mode(String mca_itm_mode) {
		this.mca_itm_mode = mca_itm_mode;
	}
	public Integer getMca_first_p() {
		return mca_first_p;
	}
	public void setMca_first_p(Integer mca_first_p) {
		this.mca_first_p = mca_first_p;
	}
	public Timestamp getMca_recpt_date() {
		return mca_recpt_date;
	}
	public void setMca_recpt_date(Timestamp mca_recpt_date) {
		this.mca_recpt_date = mca_recpt_date;
	}
	public Integer getMca_final_p() {
		return mca_final_p;
	}
	public void setMca_final_p(Integer mca_final_p) {
		this.mca_final_p = mca_final_p;
	}
	public Timestamp getMca_shipment_date() {
		return mca_shipment_date;
	}
	public void setMca_shipment_date(Timestamp mca_shipment_date) {
		this.mca_shipment_date = mca_shipment_date;
	}
	public Timestamp getMca_pickup_date() {
		return mca_pickup_date;
	}
	public void setMca_pickup_date(Timestamp mca_pickup_date) {
		this.mca_pickup_date = mca_pickup_date;
	}
	public String getMca_pay() {
		return mca_pay;
	}
	public void setMca_pay(String mca_pay) {
		this.mca_pay = mca_pay;
	}
	public Timestamp getMca_comp_date() {
		return mca_comp_date;
	}
	public void setMca_comp_date(Timestamp mca_comp_date) {
		this.mca_comp_date = mca_comp_date;
	}
	public String getMca_cod() {
		return mca_cod;
	}
	public void setMca_cod(String mca_cod) {
		this.mca_cod = mca_cod;
	}
	public String getMca_adrs() {
		return mca_adrs;
	}
	public void setMca_adrs(String mca_adrs) {
		this.mca_adrs = mca_adrs;
	}
	public String getMca_context() {
		return mca_context;
	}
	public void setMca_context(String mca_context) {
		this.mca_context = mca_context;
	}
	

}
