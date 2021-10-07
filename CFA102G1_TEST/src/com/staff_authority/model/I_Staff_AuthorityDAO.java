package com.staff_authority.model;

import java.util.List;


public interface I_Staff_AuthorityDAO {
	public void insert(Staff_AuthorityVO staff_authority);
	public Staff_AuthorityVO findByStaff_no(Integer staff_no);
	public List<Staff_AuthorityVO> getAll();
}
