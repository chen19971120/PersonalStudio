package com.rentalOrder.model;

import java.io.Serializable;
import java.sql.Date;

//Table Name :  RENTAL_ORDER(租賃商品訂單)

public class RentalOrderVO implements Serializable,Comparable<RentalOrderVO>{
	private Integer	ro_no;
	private Integer	mem_no;
	private Integer	rpl_no;
	private String ro_status;
	private String	ro_pay_status;
	private String	ro_pay_method;
	private String	ro_ship_method;
	private String	ro_ship_status;
	private String	ro_ship_addrs;
	private Date ro_starttime;
	private Date ro_endtime;
	private Date ro_oncerentendtime;
	private Date ro_return_date;
	private Integer	ro_day;
	private Integer	ro_price;
	private Integer	ro_totalprice;
	private Integer	ro_deposit;
	private String	ro_deposit_status;
	private String	ro_return_status;
	private String	ro_return_method;
	private String	ro_product_status;
	private Integer	ro_repaircost;
	private Integer	ro_delay_days;
	private Integer	ro_return_deposit;

	public RentalOrderVO(Integer ro_no, Integer mem_no, Integer rpl_no, String ro_status, String ro_pay_status,
			String ro_pay_method, String ro_ship_method, String ro_ship_status, String ro_ship_addrs,
			Date ro_starttime, Date ro_endtime, Date ro_oncerentendtime, Date ro_return_date,
			Integer ro_day, Integer ro_price, Integer ro_totalprice, Integer ro_deposit, String ro_deposit_status,
			String ro_return_status, String ro_return_method, String ro_product_status, Integer ro_repaircost,
			Integer ro_delay_days, Integer ro_return_deposit) {
		super();
		this.ro_no = ro_no;
		this.mem_no = mem_no;
		this.rpl_no = rpl_no;
		this.ro_status = ro_status;
		this.ro_pay_status = ro_pay_status;
		this.ro_pay_method = ro_pay_method;
		this.ro_ship_method = ro_ship_method;
		this.ro_ship_status = ro_ship_status;
		this.ro_ship_addrs = ro_ship_addrs;
		this.ro_starttime = ro_starttime;
		this.ro_endtime = ro_endtime;
		this.ro_oncerentendtime = ro_oncerentendtime;
		this.ro_return_date = ro_return_date;
		this.ro_day = ro_day;
		this.ro_price = ro_price;
		this.ro_totalprice = ro_totalprice;
		this.ro_deposit = ro_deposit;
		this.ro_deposit_status = ro_deposit_status;
		this.ro_return_status = ro_return_status;
		this.ro_return_method = ro_return_method;
		this.ro_product_status = ro_product_status;
		this.ro_repaircost = ro_repaircost;
		this.ro_delay_days = ro_delay_days;
		this.ro_return_deposit = ro_return_deposit;
	}

	public RentalOrderVO() {
		
	}

	public Integer getRo_no() {
		return ro_no;
	}

	public void setRo_no(Integer ro_no) {
		this.ro_no = ro_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public Integer getRpl_no() {
		return rpl_no;
	}

	public void setRpl_no(Integer rpl_no) {
		this.rpl_no = rpl_no;
	}

	public String getRo_status() {
		return ro_status;
	}

	public void setRo_status(String ro_status) {
		this.ro_status = ro_status;
	}

	public String getRo_pay_status() {
		return ro_pay_status;
	}

	public void setRo_pay_status(String ro_pay_status) {
		this.ro_pay_status = ro_pay_status;
	}

	public String getRo_pay_method() {
		return ro_pay_method;
	}

	public void setRo_pay_method(String ro_pay_method) {
		this.ro_pay_method = ro_pay_method;
	}

	public String getRo_ship_method() {
		return ro_ship_method;
	}

	public void setRo_ship_method(String ro_ship_method) {
		this.ro_ship_method = ro_ship_method;
	}

	public String getRo_ship_status() {
		return ro_ship_status;
	}

	public void setRo_ship_status(String ro_ship_status) {
		this.ro_ship_status = ro_ship_status;
	}

	public String getRo_ship_addrs() {
		return ro_ship_addrs;
	}

	public void setRo_ship_addrs(String ro_ship_addrs) {
		this.ro_ship_addrs = ro_ship_addrs;
	}

	public Date getRo_starttime() {
		return ro_starttime;
	}

	public void setRo_starttime(Date ro_starttime) {
		this.ro_starttime = ro_starttime;
	}

	public Date getRo_endtime() {
		return ro_endtime;
	}

	public void setRo_endtime(Date ro_endtime) {
		this.ro_endtime = ro_endtime;
	}

	public Date getRo_oncerentendtime() {
		return ro_oncerentendtime;
	}

	public void setRo_oncerentendtime(Date ro_oncerentendtime) {
		this.ro_oncerentendtime = ro_oncerentendtime;
	}

	public Date getRo_return_date() {
		return ro_return_date;
	}

	public void setRo_return_date(Date ro_return_date) {
		this.ro_return_date = ro_return_date;
	}

	public Integer getRo_day() {
		return ro_day;
	}

	public void setRo_day(Integer ro_day) {
		this.ro_day = ro_day;
	}

	public Integer getRo_price() {
		return ro_price;
	}

	public void setRo_price(Integer ro_price) {
		this.ro_price = ro_price;
	}

	public Integer getRo_totalprice() {
		return ro_totalprice;
	}

	public void setRo_totalprice(Integer ro_totalprice) {
		this.ro_totalprice = ro_totalprice;
	}

	public Integer getRo_deposit() {
		return ro_deposit;
	}

	public void setRo_deposit(Integer ro_deposit) {
		this.ro_deposit = ro_deposit;
	}

	public String getRo_deposit_status() {
		return ro_deposit_status;
	}

	public void setRo_deposit_status(String ro_deposit_status) {
		this.ro_deposit_status = ro_deposit_status;
	}

	public String getRo_return_status() {
		return ro_return_status;
	}

	public void setRo_return_status(String ro_return_status) {
		this.ro_return_status = ro_return_status;
	}

	public String getRo_return_method() {
		return ro_return_method;
	}

	public void setRo_return_method(String ro_return_method) {
		this.ro_return_method = ro_return_method;
	}

	public String getRo_product_status() {
		return ro_product_status;
	}

	public void setRo_product_status(String ro_product_status) {
		this.ro_product_status = ro_product_status;
	}

	public Integer getRo_repaircost() {
		return ro_repaircost;
	}

	public void setRo_repaircost(Integer ro_repaircost) {
		this.ro_repaircost = ro_repaircost;
	}

	public Integer getRo_delay_days() {
		return ro_delay_days;
	}

	public void setRo_delay_days(Integer ro_delay_days) {
		this.ro_delay_days = ro_delay_days;
	}

	public Integer getRo_return_deposit() {
		return ro_return_deposit;
	}

	public void setRo_return_deposit(Integer ro_return_deposit) {
		this.ro_return_deposit = ro_return_deposit;
	}

	@Override
	public int compareTo(RentalOrderVO roVO) {
		if(this.getRo_no()>roVO.getRo_no())
			return 1;
		else if (this.getRo_no()==roVO.getRo_no())
			return 0;
		else 
			return -1;
	}
}
