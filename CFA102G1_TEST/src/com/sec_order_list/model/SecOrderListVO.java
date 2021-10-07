package com.sec_order_list.model;

import java.io.Serializable;

//Table Name : SEC_ORDER_LIST(訂單明細)

public class SecOrderListVO implements Serializable{
	private Integer sol_no;
	private Integer so_no;
	private Integer spi_no;
	private Integer sol_proamot;
	private Integer sol_pri;
	public Integer getSol_no() {
		return sol_no;
	}
	public void setSol_no(Integer sol_no) {
		this.sol_no = sol_no;
	}
	public Integer getSo_no() {
		return so_no;
	}
	public void setSo_no(Integer so_no) {
		this.so_no = so_no;
	}
	public Integer getSpi_no() {
		return spi_no;
	}
	public void setSpi_no(Integer spi_no) {
		this.spi_no = spi_no;
	}
	public Integer getSol_proamot() {
		return sol_proamot;
	}
	public void setSol_proamot(Integer sol_proamot) {
		this.sol_proamot = sol_proamot;
	}
	public Integer getSol_pri() {
		return sol_pri;
	}
	public void setSol_pri(Integer sol_pri) {
		this.sol_pri = sol_pri;
	}
	
}
