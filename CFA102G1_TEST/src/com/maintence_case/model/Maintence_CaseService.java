package com.maintence_case.model;

import java.sql.Timestamp;
import java.util.List;

public class Maintence_CaseService {
	
	private I_Maintence_CaseDAO dao;
	
	public Maintence_CaseService() {
		dao = new Maintence_CaseDAO();
	}
	
	public Maintence_CaseVO insertMaintenceCase(Integer mem_no, String mca_itm_id,Integer mcl_no, String mca_itm_spec,Timestamp mca_date,
		String mca_itm_mode,Integer mca_first_p,Timestamp mca_recpt_date,Integer mca_final_p,Timestamp mca_shipment_date,Timestamp mca_pickup_date,
		String mca_pay,Timestamp mca_comp_date,String mca_cod,
		String mca_adrs,String mca_context){
		
		Maintence_CaseVO maintenceCaseVO = new Maintence_CaseVO();
		
		maintenceCaseVO.setMem_no(mem_no);
		maintenceCaseVO.setMca_itm_id(mca_itm_id);
		maintenceCaseVO.setMcl_no(mcl_no);
		maintenceCaseVO.setMca_itm_spec(mca_itm_spec);
		maintenceCaseVO.setMca_date(mca_date);
		maintenceCaseVO.setMca_itm_mode(mca_itm_mode);
		maintenceCaseVO.setMca_first_p(mca_first_p);
		maintenceCaseVO.setMca_recpt_date(mca_recpt_date);
		maintenceCaseVO.setMca_final_p(mca_final_p);
		maintenceCaseVO.setMca_shipment_date(mca_shipment_date);
		maintenceCaseVO.setMca_pickup_date(mca_pickup_date);
		maintenceCaseVO.setMca_pay( mca_pay);
		maintenceCaseVO.setMca_comp_date(mca_comp_date);
		maintenceCaseVO.setMca_cod(mca_cod);
		maintenceCaseVO.setMca_adrs(mca_adrs);
		maintenceCaseVO.setMca_context(mca_context);
		maintenceCaseVO = dao.insert(maintenceCaseVO);
		return maintenceCaseVO;
	}

	public Maintence_CaseVO updateMaintenceCase (Integer mca_no,Integer mem_no, String mca_itm_id,Integer mcl_no, String mca_itm_spec,
		Timestamp mca_date,String mca_itm_mode,Integer mca_first_p,Timestamp mca_recpt_date,Integer mca_final_p,Timestamp mca_shipment_date,
		Timestamp mca_pickup_date,String mca_pay,Timestamp mca_comp_date,String mca_cod,String mca_adrs,String mca_context) {
		
		Maintence_CaseVO maintenceCaseVO = new Maintence_CaseVO();
		
		maintenceCaseVO.setMca_no(mca_no);
		maintenceCaseVO.setMem_no(mem_no);
		maintenceCaseVO.setMca_itm_id(mca_itm_id);
		maintenceCaseVO.setMcl_no(mcl_no);
		maintenceCaseVO.setMca_itm_spec(mca_itm_spec);
		maintenceCaseVO.setMca_date(mca_date);
		maintenceCaseVO.setMca_itm_mode(mca_itm_mode);
		maintenceCaseVO.setMca_first_p(mca_first_p);
		maintenceCaseVO.setMca_recpt_date(mca_recpt_date);
		maintenceCaseVO.setMca_final_p(mca_final_p);
		maintenceCaseVO.setMca_shipment_date(mca_shipment_date);
		maintenceCaseVO.setMca_pickup_date(mca_pickup_date);
		maintenceCaseVO.setMca_pay( mca_pay);
		maintenceCaseVO.setMca_comp_date(mca_comp_date);
		maintenceCaseVO.setMca_cod(mca_cod);
		maintenceCaseVO.setMca_adrs(mca_adrs);
		maintenceCaseVO.setMca_context(mca_context);
		dao.update(maintenceCaseVO);
		return maintenceCaseVO;
	}

	public void deleteMaintenceCase(Integer mca_no) {
		dao.delete(mca_no);
	}

	public  Maintence_CaseVO getOneMaintenceCase(Integer mca_no) {
		return dao.findByPK(mca_no);
	}
	
	public List<Maintence_CaseVO> getOneMaintenceCaseByMemNO(Integer mem_no){
		return dao.findByMEM_NO(mem_no);
	}

	public List<Maintence_CaseVO> getAll(){
		return dao.getAll();
	}

}
