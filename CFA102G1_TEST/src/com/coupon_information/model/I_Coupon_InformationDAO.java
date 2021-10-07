package com.coupon_information.model;

import java.util.List;

public interface I_Coupon_InformationDAO {

	public Coupon_InformationVO insert(Coupon_InformationVO coupon_InformationVO);
	public void update(Coupon_InformationVO coupon_InformationVO);
	public void delete(Integer ci_no);
	public Coupon_InformationVO findByPK(Integer ci_no);
	public List<Coupon_InformationVO> getAll();
}