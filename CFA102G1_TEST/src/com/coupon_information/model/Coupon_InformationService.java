package com.coupon_information.model;

import java.sql.Timestamp;
import java.util.List;

public class Coupon_InformationService {

	private I_Coupon_InformationDAO dao;

	public Coupon_InformationService() {
		dao = new Coupon_InformationDAO();
	}

	public Coupon_InformationVO addC_Information(String ci_name, String ci_code, Timestamp ci_start_time,
			Timestamp ci_end_time, Integer discount, String ci_content) {

		Coupon_InformationVO c_informationVO = new Coupon_InformationVO();
		c_informationVO.setCi_name(ci_name);
		c_informationVO.setCi_code(ci_code);
		c_informationVO.setCi_start_time(ci_start_time);
		c_informationVO.setCi_end_time(ci_end_time);
		c_informationVO.setDiscount(discount);
		c_informationVO.setCi_content(ci_content);
		c_informationVO = dao.insert(c_informationVO);

		return c_informationVO;

	}

	public Coupon_InformationVO updateC_Information(Integer ci_no, String ci_name, String ci_code,
			Timestamp ci_start_time, Timestamp ci_end_time, Integer discount, String ci_content) {

		Coupon_InformationVO c_informationVO = new Coupon_InformationVO();
		c_informationVO.setCi_no(ci_no);
		c_informationVO.setCi_name(ci_name);
		c_informationVO.setCi_code(ci_code);
		c_informationVO.setCi_start_time(ci_start_time);
		c_informationVO.setCi_end_time(ci_end_time);
		c_informationVO.setDiscount(discount);
		c_informationVO.setCi_content(ci_content);
		dao.update(c_informationVO);

		return c_informationVO;

	}

	public void deleteC_Information(Integer ci_no) {
		dao.delete(ci_no);
	}

	public Coupon_InformationVO getOneC_Information(Integer ci_no) {
		return dao.findByPK(ci_no);
	}

	public List<Coupon_InformationVO> getAll() {
		return dao.getAll();

	}
}
