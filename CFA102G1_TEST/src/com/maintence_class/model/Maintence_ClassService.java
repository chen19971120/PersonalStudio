package com.maintence_class.model;

import java.util.List;

public class Maintence_ClassService {
	
	private I_Maintence_ClassDAO dao;
	
	public Maintence_ClassService() {
		dao = new Maintence_ClassDAO();
	}
	
	public Maintence_ClassVO insertMaintenceClass(String mcl_id) {
		
		Maintence_ClassVO maintenceclassVO = new Maintence_ClassVO();
		
		maintenceclassVO.setMcl_id(mcl_id);
		maintenceclassVO = dao.insert(maintenceclassVO);
		return maintenceclassVO;
	}

	public Maintence_ClassVO updateMaintenceClass(Integer mcl_no,String mcl_id) {
		
		Maintence_ClassVO maintenceclassVO = new Maintence_ClassVO();
		
		maintenceclassVO.setMcl_no(mcl_no);
		maintenceclassVO.setMcl_id(mcl_id);
		dao.update(maintenceclassVO);
		return maintenceclassVO;
	}

	public void deleteMaintenceClass(Integer mcl_no) {
		dao.delete(mcl_no);
	}

	public Maintence_ClassVO getOneMaintenceClass(Integer mcl_no) {
		return dao.findByPK(mcl_no);
	}

	public List<Maintence_ClassVO> getAll(){
		return dao.getAll();
	}

}
