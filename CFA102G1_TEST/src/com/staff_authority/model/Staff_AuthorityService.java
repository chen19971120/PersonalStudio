package com.staff_authority.model;

import java.util.List;

public class Staff_AuthorityService {
private I_Staff_AuthorityDAO dao;
	
	public Staff_AuthorityService() {
		dao = new Staff_AuthorityDAO();
	}
public Staff_AuthorityVO addStaff_Authority(
		Integer staff_no, Integer authority_no) {
	Staff_AuthorityVO staff_authority = new Staff_AuthorityVO();
	
	staff_authority.setStaff_no(staff_no);
	staff_authority.setAuthority_no(authority_no);
	dao.insert(staff_authority);
	return staff_authority;
}
public Staff_AuthorityVO getOneStaff_Authority(Integer staff_no) {
	return dao.findByStaff_no(staff_no);
 }
public List<Staff_AuthorityVO> getAll(){
	return dao.getAll();
	}
}
