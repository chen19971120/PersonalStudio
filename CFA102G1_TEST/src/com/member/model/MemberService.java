package com.member.model;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MemberService {
	private I_MemberDAO dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(
		    String mem_name, String mem_gender, String mem_phone, String mem_email,
			String mem_address, String mem_account, String mem_password, Date mem_birth, String mem_sta) {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_name(mem_name);
		memberVO.setMem_gender(mem_gender);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_account(mem_account);
		memberVO.setMem_password(mem_password);
		memberVO.setMem_birth(mem_birth);
		memberVO.setMem_sta(mem_sta);
		
		memberVO = dao.insert(memberVO);
		return memberVO;
	}
public MemberVO updateMember(
		Integer mem_no, String mem_name, String mem_gender, String mem_phone, String mem_email,
		String mem_address, String mem_account, String mem_password, Date mem_birth, String mem_sta
		) {
	MemberVO memberVO = new MemberVO();
	
	memberVO.setMem_no(mem_no);
	memberVO.setMem_name(mem_name);
	memberVO.setMem_gender(mem_gender);
	memberVO.setMem_phone(mem_phone);
	memberVO.setMem_email(mem_email);
	memberVO.setMem_address(mem_address);
	memberVO.setMem_account(mem_account);
	memberVO.setMem_password(mem_password);
	memberVO.setMem_birth(mem_birth);
	memberVO.setMem_sta(mem_sta);
	
	dao.update(memberVO);
	return memberVO;
    }
public void deleteMember(Integer mem_no) {
	dao.delete(mem_no);
    }
public MemberVO getOneMem(Integer mem_no) {
	return dao.findByMem_no(mem_no);
    }
public List<MemberVO> getName(String mem_name){
	return dao.getAll().stream().filter(i -> i.getMem_name().equals(mem_name)).collect(Collectors.toList());
	}
	
public List<MemberVO> getPhone(String mem_phone){
	return dao.getAll().stream().filter(i -> i.getMem_phone().equals(mem_phone)).collect(Collectors.toList());
    };
public List<MemberVO> getAll(){
	return dao.getAll();
    }
public MemberVO getOneMem_account(String mem_account, String mem_password) {
	return dao.findByMem_account(mem_account,mem_password);
}

public MemberVO getOneAccountMail(String mem_account, String mem_email) {
	return dao.findByMemAccountMail(mem_account,mem_email);

}
public MemberVO registerMember(String mem_name, String mem_gender, String mem_phone, String mem_email, String mem_address, String mem_account, String mem_password, Date mem_bitth) {

	MemberVO memberVO = new MemberVO();

	memberVO.setMem_name(mem_name);
	memberVO.setMem_gender(mem_gender);
	memberVO.setMem_phone(mem_phone);
	memberVO.setMem_email(mem_email);
	memberVO.setMem_address(mem_address);
	memberVO.setMem_account(mem_account);
	memberVO.setMem_password(mem_password);
	memberVO.setMem_birth(mem_bitth);

	dao.register_Member(memberVO);
	
	return memberVO;
}
public MemberVO findByEmail(String mem_email) {
	return dao.findByEmail(mem_email);
}
public void updatePwd(String mem_email, String mem_password) {
	dao.updatePwd(mem_email, mem_password);
}
public MemberVO loginCheck(String mem_account) {
	return dao.loginCheck(mem_account);
}
public MemberVO updateMemberStatus(Integer mem_no, String mem_sta) {
MemberVO memberVO = new MemberVO();
	
	memberVO.setMem_no(mem_no);
	memberVO.setMem_sta(mem_sta);
	dao.updateMemberStatus(mem_no,mem_sta);
	return memberVO;
}
}
