package com.appraisal_case.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.appraisal_case_images.model.Appraisal_Case_ImagesVO;

public class Appraisal_CaseService {

	private I_Appraisal_CaseDAO dao;

	public Appraisal_CaseService() {
		dao = new Appraisal_CaseDAO();
	}

	public Appraisal_CaseVO addA_Case(Integer mem_no, String aca_itm_id, Integer acl_no, String aca_itm_spec,
			Timestamp aca_date, String aca_itm_mode, Integer aca_first_p, Timestamp aca_recpt_date, Integer aca_final_p,
			Timestamp aca_shipment_date, Timestamp aca_pickup_date, String aca_pay, Timestamp aca_comp_date,
			String aca_cod, String aca_adrs) {
		Appraisal_CaseVO a_caseVO = new Appraisal_CaseVO();

		a_caseVO.setMem_no(mem_no);
		a_caseVO.setAca_itm_id(aca_itm_id);
		a_caseVO.setAcl_no(acl_no);
		a_caseVO.setAca_itm_spec(aca_itm_spec);
		a_caseVO.setAca_date(aca_date);
		a_caseVO.setAca_itm_mode(aca_itm_mode);
		a_caseVO.setAca_first_p(aca_first_p);
		a_caseVO.setAca_recpt_date(aca_recpt_date);
		a_caseVO.setAca_final_p(aca_final_p);
		a_caseVO.setAca_shipment_date(aca_shipment_date);
		a_caseVO.setAca_pickup_date(aca_pickup_date);
		a_caseVO.setAca_pay(aca_pay);
		a_caseVO.setAca_comp_date(aca_comp_date);
		a_caseVO.setAca_cod(aca_cod);
		a_caseVO.setAca_adrs(aca_adrs);
		a_caseVO = dao.insert(a_caseVO);

		return a_caseVO;
	}

	public Appraisal_CaseVO updateA_Case(Integer aca_no, Integer mem_no, String aca_itm_id, Integer acl_no,
			String aca_itm_spec, Timestamp aca_date, String aca_itm_mode, Integer aca_first_p, Timestamp aca_recpt_date,
			Integer aca_final_p, Timestamp aca_shipment_date, Timestamp aca_pickup_date, String aca_pay,
			Timestamp aca_comp_date, String aca_cod, String aca_adrs) {

		Appraisal_CaseVO a_caseVO = new Appraisal_CaseVO();

		a_caseVO.setAca_no(aca_no);
		a_caseVO.setMem_no(mem_no);
		a_caseVO.setAca_itm_id(aca_itm_id);
		a_caseVO.setAcl_no(acl_no);
		a_caseVO.setAca_itm_spec(aca_itm_spec);
		a_caseVO.setAca_date(aca_date);
		a_caseVO.setAca_itm_mode(aca_itm_mode);
		a_caseVO.setAca_first_p(aca_first_p);
		a_caseVO.setAca_recpt_date(aca_recpt_date);
		a_caseVO.setAca_final_p(aca_final_p);
		a_caseVO.setAca_shipment_date(aca_shipment_date);
		a_caseVO.setAca_pickup_date(aca_pickup_date);
		a_caseVO.setAca_pay(aca_pay);
		a_caseVO.setAca_comp_date(aca_comp_date);
		a_caseVO.setAca_cod(aca_cod);
		a_caseVO.setAca_adrs(aca_adrs);
		dao.update(a_caseVO);

		return a_caseVO;
	}

	public void deleteA_Case(Integer aca_no) {
		dao.delete(aca_no);
	}

	public Appraisal_CaseVO getOneA_Case(Integer aca_no) {
		return dao.findByPK(aca_no);
	}

	public List<Appraisal_CaseVO> getAll() {
		return dao.getAll();
	}

	public List<Appraisal_CaseVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
