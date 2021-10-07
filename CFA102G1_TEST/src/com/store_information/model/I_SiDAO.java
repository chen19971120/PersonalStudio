package com.store_information.model;

import java.util.*;

public interface I_SiDAO {
	public void insert(SiVO siVO);
	public void update(SiVO siVO);
	public void delete(Integer si_nO);
	public SiVO findByPrimaryKey(Integer si_no);
	public List<SiVO> getAll();
}
