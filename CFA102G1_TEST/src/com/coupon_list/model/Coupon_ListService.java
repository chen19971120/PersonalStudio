package com.coupon_list.model;

import java.util.List;

public class Coupon_ListService {

	private I_Coupon_ListDAO dao;
	
	public Coupon_ListService() {
		dao = new Coupon_ListDAO();
	}
	
	public Coupon_ListVO addC_List(Integer ci_no, Integer mem_no, String cl_status) {
		
		Coupon_ListVO c_listVO = new Coupon_ListVO();
		
		c_listVO.setCi_no(ci_no);
		c_listVO.setMem_no(mem_no);
		c_listVO.setCl_status(cl_status);
		dao.insert(c_listVO);
		
		return c_listVO;

	}
	public Coupon_ListVO updateC_List(Integer ci_no, Integer mem_no, String cl_status) {

		Coupon_ListVO c_listVO = new Coupon_ListVO();
		c_listVO.setCi_no(ci_no);
		c_listVO.setMem_no(mem_no);
		c_listVO.setCl_status(cl_status);
		dao.update(c_listVO);

		return c_listVO;

	}
	
	public void deleteC_List(Integer ci_no, Integer mem_no) {
		dao.delete(ci_no, mem_no);
	}
	
	public Coupon_ListVO getOneC_List(Integer ci_no, Integer mem_no) {
		return dao.findByPK(ci_no, mem_no);

	}
	
	public List<Coupon_ListVO> getAll() {
		return dao.getAll();

		
	}
}
