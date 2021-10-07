package com.appraisal_class.model;

import java.util.List;
import java.util.Set;

import com.appraisal_case.model.Appraisal_CaseVO;

public class Appraisal_ClassService {

	private I_Appraisal_ClassDAO dao;

	public Appraisal_ClassService() {
		dao = new Appraisal_ClassDAO();
	}

	public Appraisal_ClassVO addA_Class(String acl_id) {

		Appraisal_ClassVO a_classVO = new Appraisal_ClassVO();

		a_classVO.setAcl_id(acl_id);
		a_classVO = dao.insert(a_classVO);

		return a_classVO;
	}

	public Appraisal_ClassVO updateA_Class(Integer acl_no, String acl_id) {

		Appraisal_ClassVO a_classVO = new Appraisal_ClassVO();

		a_classVO.setAcl_no(acl_no);
		a_classVO.setAcl_id(acl_id);
		dao.update(a_classVO);

		return a_classVO;
	}

	public void deleteA_Class(Integer acl_no) {
		dao.delete(acl_no);
	}

	public Appraisal_ClassVO getOneA_Class(Integer acl_no) {
		return dao.findByPK(acl_no);
	}

	public List<Appraisal_ClassVO> getAll() {
		return dao.getAll();
	}
	public Set<Appraisal_CaseVO> getA_CaseByA_Class(Integer acl_no){
		return dao.getA_CaseByA_Class(acl_no);
	}
}
