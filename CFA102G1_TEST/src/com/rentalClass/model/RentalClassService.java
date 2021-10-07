package com.rentalClass.model;

import java.util.*;
import java.util.stream.Collectors;

public class RentalClassService {
	private I_RentalClassDAO dao;
	
	public RentalClassService() {
		dao = new RentalClassDAO();
	}
	
	public RentalClassVO insertRentalClass(String rc_name, String rc_item, String rc_detail, Integer rc_deposit, Integer rc_price) {
		
		RentalClassVO rentalClassVO = new RentalClassVO();
		
		rentalClassVO.setRc_name(rc_name);
		rentalClassVO.setRc_item(rc_item);
		rentalClassVO.setRc_detail(rc_detail);
		rentalClassVO.setRc_deposit(rc_deposit);
		rentalClassVO.setRc_price(rc_price);
		rentalClassVO = dao.insert(rentalClassVO);
		rentalClassVO.setRc_status("下架");
		rentalClassVO.setRc_storage(0);
		rentalClassVO.setRc_total_count(0);
		rentalClassVO.setRc_total_score(0);
		
		return rentalClassVO;
	}
	
	public RentalClassVO updateRentalClass(Integer rc_no,String rc_name, String rc_item, String rc_detail, Integer rc_deposit, Integer rc_price,Integer rc_total_count,Integer rc_total_score,Integer rc_storage,String rc_status) {

		RentalClassVO rentalClassVO = new RentalClassVO();

		rentalClassVO.setRc_no(rc_no);
		rentalClassVO.setRc_name(rc_name);
		rentalClassVO.setRc_item(rc_item);
		rentalClassVO.setRc_detail(rc_detail);
		rentalClassVO.setRc_deposit(rc_deposit);
		rentalClassVO.setRc_price(rc_price);
		rentalClassVO.setRc_total_count(rc_total_count);
		rentalClassVO.setRc_total_score(rc_total_score);
		rentalClassVO.setRc_storage(rc_storage);
		rentalClassVO.setRc_status(rc_status);
		dao.update(rentalClassVO);

		return rentalClassVO;
	}

	public void deleteRentalClass(Integer rc_no) {
		dao.delete(rc_no);
	}

	public RentalClassVO getOneRentalClass(Integer rc_no) {
		return dao.findByPK(rc_no);
	}

	public List<RentalClassVO> getAll() {
		return dao.getAll();
	}
	//一個按鍵更改類別狀態	
	public void changeRcRentStatus(Integer rc_no) {
		
		RentalClassVO rcVO = dao.findByPK(rc_no);
		if(rcVO.getRc_status().equals("上架")) {
			rcVO.setRc_status("下架");
			dao.update(rcVO);
		}
		else if(rcVO.getRc_status().equals("下架")) {
			rcVO.setRc_status("上架");
			dao.update(rcVO);
		}	
	}
	
	//回傳所有商品種類
	public List<String> getAllRc_Item() {
		List<RentalClassVO> list = dao.getAll();
		Set<String> set = new HashSet<String>();
		for(RentalClassVO rcVO: list)
			set.add(rcVO.getRc_item());
		List<String> allRc_itemList = new ArrayList<String>(set);	
		return allRc_itemList;
	}

	//用種類取回RC list
	public List<RentalClassVO> getOneRc_item(String rc_item) {
		List<RentalClassVO> list = dao.getAll();
		List<RentalClassVO> Rc_itemlist = list.stream()
				.filter(e -> e.getRc_item().equals(rc_item))
				.collect(Collectors.toList());
		return Rc_itemlist;
	}
	
	//回傳 RC 租賃前幾名 list
		public List<RentalClassVO> getRcRentHotList(Integer num) {
			List<RentalClassVO> listAll = dao.getAll().stream()
					.filter(e -> e.getRc_status().equals("上架"))
					.collect(Collectors.toList());	
			List<RentalClassVO> list = new ArrayList<RentalClassVO>();	
			Collections.sort(listAll,Collections.reverseOrder());
			if(num>listAll.size())
				num = listAll.size();
			for(int i=0;i<num;i++) {
				if(listAll.get(i)!=null)
				list.add(listAll.get(i));
			}
			Collections.sort(list,Collections.reverseOrder());
			return list;
		}
}
