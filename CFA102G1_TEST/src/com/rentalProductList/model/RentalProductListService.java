package com.rentalProductList.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.rentalClass.model.*;

public class RentalProductListService {
	private I_RentalProductListDAO dao;
	
	public RentalProductListService() {
		dao = new RentalProductListDAO();
	}
	
	public RentalProductListVO insertRentalProductList(Integer rc_no, String rpl_serialnum,String rpl_note) {
		
		RentalProductListVO rentalProductListVO = new RentalProductListVO();
		
		rentalProductListVO.setRc_no(rc_no);
		rentalProductListVO.setRpl_serialnum(rpl_serialnum);
		rentalProductListVO.setRpl_note(rpl_note);
		rentalProductListVO = dao.insert(rentalProductListVO);
		rentalProductListVO.setRpl_status("整備");
		rentalProductListVO.setRpl_rentcount(0);

		return rentalProductListVO;
	}
	
	public RentalProductListVO updateRentalProductList(Integer rpl_no, Integer rc_no, String rpl_serialnum, String rpl_note, String rpl_status,
			Integer rpl_rentcount, Timestamp rpl_jointtime) {

		RentalProductListVO rentalProductListVO = new RentalProductListVO();

		rentalProductListVO.setRpl_no(rpl_no);
		rentalProductListVO.setRc_no(rc_no);
		rentalProductListVO.setRpl_serialnum(rpl_serialnum);
		rentalProductListVO.setRpl_note(rpl_note);
		rentalProductListVO.setRpl_status(rpl_status);
		rentalProductListVO.setRpl_rentcount(rpl_rentcount);
		rentalProductListVO.setRpl_jointtime(rpl_jointtime);
		dao.update(rentalProductListVO);

		return rentalProductListVO;
	}
	
	public RentalProductListVO updateRentalProductListByVO(RentalProductListVO rplVO) {

		dao.update(rplVO);

		return rplVO;
	}

	public void deleteRentalProductList(Integer rpl_no) {
		dao.delete(rpl_no);
	}

	public RentalProductListVO getOneRentalProductList(Integer rpl_no) {
		return dao.findByPK(rpl_no);
	}

	public List<RentalProductListVO> getAll() {
		return dao.getAll();
	}
	
	public List<RentalProductListVO> getOneRentalClassList(Integer rc_no) {
		List<RentalProductListVO> list = dao.getAll().stream()
				.filter(e -> e.getRc_no().equals(rc_no))
				.collect(Collectors.toList());
		return list;
	}
	
	public List<RentalProductListVO> getOneRc_itemList(String rc_item) {
		RentalClassService rcSvc = new RentalClassService();
		List<RentalProductListVO> list = dao.getAll().stream()
				.filter(e -> rcSvc.getOneRentalClass(e.getRc_no()).getRc_item().equals(rc_item))
				.collect(Collectors.toList());
		return list;
	}
	
	//一個按鍵更改商品狀態(整備<->待租)
		public void changeRplStatus(Integer rpl_no) {
			RentalProductListVO rplVO = dao.findByPK(rpl_no);
			if(rplVO.getRpl_status().equals("整備")) {
				rplVO.setRpl_status("待租");
				dao.update(rplVO);
			}
			else if(rplVO.getRpl_status().equals("待租")) {
				rplVO.setRpl_status("整備");
				dao.update(rplVO);
			}
		}
	
}
