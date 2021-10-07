package com.appraisal_case.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.appraisal_case_images.model.Appraisal_Case_ImagesVO;

public interface I_Appraisal_CaseDAO {

	public Appraisal_CaseVO insert(Appraisal_CaseVO appraisal_CaseVO);

	public void update(Appraisal_CaseVO appraisal_CaseVO);

	public void delete(Integer aca_no);

	public Appraisal_CaseVO findByPK(Integer aca_no);

	public List<Appraisal_CaseVO> getAll();

	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<Appraisal_CaseVO> getAll(Map<String, String[]> map);
	
}