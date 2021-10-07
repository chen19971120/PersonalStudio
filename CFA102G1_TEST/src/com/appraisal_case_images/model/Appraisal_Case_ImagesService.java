package com.appraisal_case_images.model;

import java.util.List;

public class Appraisal_Case_ImagesService {

	private I_Appraisal_Case_ImagesDAO dao;

	public Appraisal_Case_ImagesService() {
		dao = new Appraisal_Case_ImagesDAO();
	}

	public Appraisal_Case_ImagesVO addA_Case_Image(Integer aca_no, byte[] aci_img) {

		Appraisal_Case_ImagesVO a_case_imageVO = new Appraisal_Case_ImagesVO();

		a_case_imageVO.setAca_no(aca_no);
		a_case_imageVO.setAci_img(aci_img);
		a_case_imageVO = dao.insert(a_case_imageVO);

		return a_case_imageVO;
	}

	public Appraisal_Case_ImagesVO updateA_Case_Image(Integer aci_no, Integer aca_no, byte[] aci_img) {

		Appraisal_Case_ImagesVO a_case_imageVO = new Appraisal_Case_ImagesVO();

		a_case_imageVO.setAci_no(aci_no);
		a_case_imageVO.setAca_no(aca_no);
		a_case_imageVO.setAci_img(aci_img);
		dao.update(a_case_imageVO);

		return a_case_imageVO;
	}

	public void deleteA_Case_Image(Integer aci_no) {
		dao.delete(aci_no);
	}

	public Appraisal_Case_ImagesVO getOneA_Case_Image(Integer aci_no) {
		return dao.findByPK(aci_no);
	}

	public List<Appraisal_Case_ImagesVO> getAll() {
		return dao.getAll();
	}
}
