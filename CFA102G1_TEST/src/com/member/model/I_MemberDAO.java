package com.member.model;

import java.util.List;

public interface I_MemberDAO {
	public MemberVO insert(MemberVO member);
	public void update(MemberVO member);
	public void delete(Integer mem_no);
	public MemberVO register_Member(MemberVO memberVO);
	public void updatePwd(String mem_email, String mem_password);
	public MemberVO findByEmail(String mem_email);
    public MemberVO loginCheck(String mem_account);
	public MemberVO findByMem_no(Integer mem_no);
	public MemberVO findByMem_account(String mem_account, String mem_password);
	public MemberVO findByMemAccountMail(String mem_account,String mem_email);
    public List<MemberVO> findByMem_name(String mem_name);
    public List<MemberVO> findByMem_phone(String mem_phone);
    public List<MemberVO> getAll();
    public void updateMemberStatus(Integer mem_no, String mem_sta);
	
}
