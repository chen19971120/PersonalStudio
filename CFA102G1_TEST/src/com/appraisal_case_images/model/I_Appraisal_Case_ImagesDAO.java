package com.appraisal_case_images.model;

import java.util.List;

public interface I_Appraisal_Case_ImagesDAO {

	public Appraisal_Case_ImagesVO insert(Appraisal_Case_ImagesVO appraisal_Case_ImagesVO);
	public void update(Appraisal_Case_ImagesVO appraisal_Case_ImagesVO);
	public void delete(Integer aci_no);
	public Appraisal_Case_ImagesVO findByPK(Integer aci_no);
	public List<Appraisal_Case_ImagesVO> getAll();
}