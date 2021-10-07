package com.staff.model;

import java.util.List;

import com.member.model.MemberVO;


public interface I_StaffDAO {
	public StaffVO insert(StaffVO staff);
	public void update(StaffVO staff);
	public void delete(Integer stff_no);
	public StaffVO findByStaff_no(Integer staff_no);
	public StaffVO findByStaff_account(String staff_account, String staff_password);
	public List<StaffVO> findByStaff_name(String staff_name);
	public List<StaffVO> findByStaff_phone(String staff_phone);
	public StaffVO update_One_Staff(StaffVO staffVO);
	public List<StaffVO> getAll();
}
