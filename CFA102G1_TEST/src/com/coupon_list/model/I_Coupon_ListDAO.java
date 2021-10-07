package com.coupon_list.model;

import java.util.List;

public interface I_Coupon_ListDAO {

	public void insert(Coupon_ListVO coupon_ListVO);
	public void update(Coupon_ListVO coupon_ListVO);
	public void delete(Integer ci_no , Integer mem_no);
	public Coupon_ListVO findByPK(Integer ci_no,Integer mem_no);
	public List<Coupon_ListVO> getAll();
}