package com.authority.model;

import java.util.List;

public class AuthorityService {
	private I_AuthorityDAO dao;
	
	public AuthorityService() {
		dao = new AuthorityDAO();
	}
	
	public AuthorityVO addAuthority(
			 String authority_name) {
		
		AuthorityVO authority = new AuthorityVO();
		
		
		authority.setAuthority_name(authority_name);
		authority = dao.insert(authority);
		return authority;
	}
	public AuthorityVO updateAuthority(
			Integer authority_no, String authority_name) {
		AuthorityVO authority = new AuthorityVO();
		
		authority.setAuthority_no(authority_no);
		authority.setAuthority_name(authority_name);
		dao.update(authority);
	    return authority;
	}
	public void deleteAuthority(Integer authority_no) {
		dao.delete(authority_no);
	}
	public AuthorityVO getOneAuthority(Integer authority_no) {
		return dao.findByAuthority_no(authority_no);
		}
	public List<AuthorityVO> getAll(){
		return dao.getAll();
		
	}
}
