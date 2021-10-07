package com.staff.model;

import java.util.List;
import java.util.stream.Collectors;



public class StaffService {
	private I_StaffDAO dao;
	
	public StaffService() {
		dao = new StaffDAO();
	}
public StaffVO addStaff(
		String staff_name, String staff_gender, String staff_phone,String staff_email,
		String staff_address, String staff_account, String staff_password,
		String staff_sta) {
	StaffVO staffVO = new StaffVO();
	
	staffVO.setStaff_name(staff_name);
	staffVO.setStaff_gender(staff_gender);
	staffVO.setStaff_phone(staff_phone);
	staffVO.setStaff_email(staff_email);
	staffVO.setStaff_address(staff_address);
	staffVO.setStaff_account(staff_account);
	staffVO.setStaff_password(staff_password);
	staffVO.setStaff_sta(staff_sta);
	
	staffVO = dao.insert(staffVO);
	return staffVO;
	};
public StaffVO updateStaff(
		Integer staff_no, String staff_name, String staff_gender, String staff_phone,String staff_email,
		String staff_address, String staff_account, String staff_password,
		String staff_sta
		) {
	StaffVO staffVO = new StaffVO();
	
	staffVO.setStaff_no(staff_no);
	staffVO.setStaff_name(staff_name);
	staffVO.setStaff_gender(staff_gender);
	staffVO.setStaff_phone(staff_phone);
	staffVO.setStaff_email(staff_email);
	staffVO.setStaff_address(staff_address);
	staffVO.setStaff_account(staff_account);
	staffVO.setStaff_password(staff_password);
	staffVO.setStaff_sta(staff_sta);
	
	dao.update(staffVO);
	return staffVO;
}
public void deleteStaff(Integer staff_no) {
	dao.delete(staff_no);
}
public StaffVO getOneStaff(Integer staff_no) {
	return dao.findByStaff_no(staff_no);
	}
public List<StaffVO> getName(String staff_name){
	return dao.getAll().stream().filter(e -> e.getStaff_name().equals(staff_name)).collect(Collectors.toList());
	}
public List<StaffVO> getPhone(String staff_phone){
	return dao.getAll().stream().filter(e -> e.getStaff_phone().equals(staff_phone)).collect(Collectors.toList());
    }
public List<StaffVO> getAll(){
	return dao.getAll();
	}
public StaffVO getOneStaff_account(String staff_account, String staff_password) {
	return dao.findByStaff_account(staff_account,staff_password);
}
public StaffVO update_One_Staff(Integer staff_no,String staff_name,String staff_gender,String staff_phone,String staff_email,String staff_address,String staff_account,String staff_password) {
	
	StaffVO staffVO = new StaffVO();
	 staffVO.setStaff_no(staff_no);
     staffVO.setStaff_name(staff_name);
     staffVO.setStaff_gender(staff_gender);
	 staffVO.setStaff_phone(staff_phone);
	 staffVO.setStaff_email(staff_email);
	 staffVO.setStaff_address(staff_address);
	 staffVO.setStaff_account(staff_account);
	 staffVO.setStaff_password(staff_password);
	
	
	dao.update_One_Staff(staffVO);
	
	return staffVO;
}

}
