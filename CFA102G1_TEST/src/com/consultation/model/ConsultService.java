package com.consultation.model;

import java.util.List;

public class ConsultService {
	private I_ConsultDAO dao;

	public ConsultService() {
		dao = new ConsultDAO();
	}

	public ConsultVO addConsult(String consultant, String consult_phone, String consult_email, String consult_content,
			Integer staff_no, String consult_sta) {

		ConsultVO consultVO = new ConsultVO();
		
		consultVO.setConsultant(consultant);
		consultVO.setConsult_phone(consult_phone);
		consultVO.setConsult_email(consult_email);
		consultVO.setConsult_content(consult_content);
		consultVO.setStaff_no(staff_no);
		consultVO.setConsult_sta(consult_sta);
		
		consultVO = dao.insert(consultVO);
		return consultVO;
	}

	public ConsultVO updateConsult(Integer consult_no, String consultant, String	 consult_phone, String consult_email, String consult_content,
			Integer staff_no, String consult_sta) {

		ConsultVO consultVO = new ConsultVO();
		
		consultVO.setConsult_no(consult_no);
		consultVO.setConsultant(consultant);
		consultVO.setConsult_phone(consult_phone);
		consultVO.setConsult_email(consult_email);
		consultVO.setConsult_content(consult_content);
		consultVO.setStaff_no(staff_no);
		consultVO.setConsult_sta(consult_sta);
		dao.update(consultVO);

		return consultVO;
	}

	public void deleteConsult(Integer consult_no) {
		dao.delete(consult_no);
	}

	public ConsultVO getOneConsult(Integer consult_no) {
		return dao.findByPrimaryKey(consult_no);
	}

	public List<ConsultVO> getAll() {
		return dao.getAll();
	}
}
