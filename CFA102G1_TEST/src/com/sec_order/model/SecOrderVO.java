package com.sec_order.model;

import java.io.Serializable;
import java.sql.Timestamp;

//Table Name : SEC_ORDER(二手商品訂單)

public class SecOrderVO implements Serializable{
	private Integer so_no;
	private Timestamp so_purtime;
	private Integer mem_no;
	private String so_sta;
	private String so_pay_sta;
	private String so_ship_sta;
	private Integer ci_no;
	private Integer so_totalpri;
	private String so_prodel;
	private String so_deladrs;
	private String so_paymthd;
	private Timestamp so_shipdate;
	private Integer so_delcost;
	private Integer so_discount_price;
	
	public Integer getSo_no() {
		return so_no;
	}
	public void setSo_no(Integer so_no) {
		this.so_no = so_no;
	}
	public Timestamp getSo_purtime() {
		return so_purtime;
	}
	public void setSo_purtime(Timestamp so_purtime) {
		this.so_purtime = so_purtime;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getSo_sta() {
		return so_sta;
	}
	public void setSo_sta(String so_sta) {
		this.so_sta = so_sta;
	}
	public String getSo_pay_sta() {
		return so_pay_sta;
	}
	public void setSo_pay_sta(String so_pay_sta) {
		this.so_pay_sta = so_pay_sta;
	}
	public String getSo_ship_sta() {
		return so_ship_sta;
	}
	public void setSo_ship_sta(String so_ship_sta) {
		this.so_ship_sta = so_ship_sta;
	}
	public Integer getCi_no() {
		return ci_no;
	}
	public void setCi_no(Integer ci_no) {
		this.ci_no = ci_no;
	}
	public Integer getSo_totalpri() {
		return so_totalpri;
	}
	public void setSo_totalpri(Integer so_totalpri) {
		this.so_totalpri = so_totalpri;
	}
	public String getSo_prodel() {
		return so_prodel;
	}
	public void setSo_prodel(String so_prodel) {
		this.so_prodel = so_prodel;
	}
	public String getSo_deladrs() {
		return so_deladrs;
	}
	public void setSo_deladrs(String so_deladrs) {
		this.so_deladrs = so_deladrs;
	}
	public String getSo_paymthd() {
		return so_paymthd;
	}
	public void setSo_paymthd(String so_paymthd) {
		this.so_paymthd = so_paymthd;
	}
	public Timestamp getSo_shipdate() {
		return so_shipdate;
	}
	public void setSo_shipdate(Timestamp so_shipdate) {
		this.so_shipdate = so_shipdate;
	}
	public Integer getSo_delcost() {
		return so_delcost;
	}
	public void setSo_delcost(Integer so_delcost) {
		this.so_delcost = so_delcost;
	}
	public Integer getSo_discount_price() {
		return so_discount_price;
	}
	public void setSo_discount_price(Integer so_discount_price) {
		this.so_discount_price = so_discount_price;
	}
	
}
