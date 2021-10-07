package com.maintence_case.model;

import java.util.List;



public interface I_Maintence_CaseDAO {
	
	public Maintence_CaseVO insert (Maintence_CaseVO maintence_casevo);
	public void update (Maintence_CaseVO maintence_casevo);
	public void delete (Integer mca_no);
    public Maintence_CaseVO findByPK(Integer mca_no);
    public List<Maintence_CaseVO> findByMEM_NO(Integer mem_no);	
    public List<Maintence_CaseVO> getAll();
	

}
