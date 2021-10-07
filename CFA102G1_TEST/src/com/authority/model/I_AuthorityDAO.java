package com.authority.model;

import java.util.List;

public interface I_AuthorityDAO {
	public AuthorityVO insert(AuthorityVO authority);
	public void delete(Integer authority_no);
	public void update(AuthorityVO authority);
	public AuthorityVO findByAuthority_no(Integer authority_no);
	public List<AuthorityVO> getAll();
	   
}
