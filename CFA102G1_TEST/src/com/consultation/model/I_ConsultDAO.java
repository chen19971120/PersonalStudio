package com.consultation.model;

import java.util.*;

public interface I_ConsultDAO {
	public ConsultVO insert(ConsultVO consultVO);
	public void update(ConsultVO consultVO);
	public void delete(Integer consult_no);
	public ConsultVO findByPrimaryKey(Integer consult_no);
	public List<ConsultVO> getAll();
}
