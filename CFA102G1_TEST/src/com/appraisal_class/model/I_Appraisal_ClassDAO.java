package com.appraisal_class.model;

import java.util.List;
import java.util.Set;

import com.appraisal_case.model.Appraisal_CaseVO;

public interface I_Appraisal_ClassDAO {

	public Appraisal_ClassVO insert(Appraisal_ClassVO appraisal_ClassVO);
	public void update(Appraisal_ClassVO appraisal_ClassVO);
	public void delete(Integer acl_no);
	public Appraisal_ClassVO findByPK(Integer acl_no);
	public List<Appraisal_ClassVO> getAll();
    //查詢某估價類別的案件(一對多)(回傳 Set)
	public Set<Appraisal_CaseVO> getA_CaseByA_Class(Integer acl_no);
}