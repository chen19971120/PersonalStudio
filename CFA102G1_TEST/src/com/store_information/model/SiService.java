package com.store_information.model;

import java.util.List;

public class SiService {
	
	private I_SiDAO dao;

	public SiService() {
		dao = new SiDAO();
	}

	public SiVO addSi(Integer si_no, String si_address, String si_open, String si_phone,
			String si_email, String si_line) {

		SiVO siVO = new SiVO();
		
		siVO.setSi_no(si_no);
		siVO.setSi_address(si_address);
		siVO.setSi_open(si_open);
		siVO.setSi_phone(si_phone);
		siVO.setSi_email(si_email);
		siVO.setSi_line(si_line);
		
		dao.insert(siVO);
		return siVO;
	}

	public SiVO updateSi(Integer si_no, String si_address, String si_open,
			String si_phone, String si_email, String si_line) {

		SiVO siVO = new SiVO();

		siVO.setSi_no(si_no);
		siVO.setSi_address(si_address);
		siVO.setSi_open(si_open);
		siVO.setSi_phone(si_phone);
		siVO.setSi_email(si_email);
		siVO.setSi_line(si_line);
		dao.update(siVO);

		return siVO;
	}

	public void deleteSi(Integer si_no) {
		dao.delete(si_no);
	}

	public SiVO getOneSi(Integer si_no) {
		return dao.findByPrimaryKey(si_no);
	}

	public List<SiVO> getAll() {
		return dao.getAll();
	}
}


