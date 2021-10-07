package com.rentalProductList.model;

import java.sql.Timestamp;
import java.util.List;

public class TestRentalProductListJDBCDAO {
	public static void main(String[] args) {
		I_RentalProductListDAO dao = new RentalProductListJDBCDAO();
		
		//新增
		RentalProductListVO rpl1 = new RentalProductListVO();
		rpl1.setRc_no(6);
		rpl1.setRpl_serialnum("testserialnum1");
		rpl1.setRpl_note("done");
		rpl1.setRpl_status("下架");
		rpl1 = dao.insert(rpl1);
		System.out.println(rpl1.getRpl_no());
		
		//修改
//		RentalProductListVO rpl1 = new RentalProductListVO();
//		rpl1.setRc_no(2);
//		rpl1.setRpl_serialnum("testserialnum3");
//		rpl1.setRpl_note("update");
//		rpl1.setRpl_status("update");
//		rpl1.setRpl_rentcount(200);
//		Timestamp stamp = new Timestamp(0);
//		rpl1.setRpl_jointtime(stamp);
//		rpl1.setRpl_no(7);
//		dao.update(rpl1);
		
		//刪除
//		dao.delete(4);
		
		//查詢
//		RentalProductListVO rpl1 = dao.findByPK(7);
//		System.out.println(rpl1.getRpl_jointtime());
		
		//查詢全部
//		List<RentalProductListVO> list = dao.getAll();
//		for(RentalProductListVO item:list)
//			System.out.println(item.getRpl_serialnum());
		
		
	}
}
