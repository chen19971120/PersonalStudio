package com.rentalOrder.model;

import java.util.*;
import java.util.stream.Collectors;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.rentalProductList.model.*;

import java.sql.Timestamp;

public class TestRentalOrderJDBCDAO {
	public static void main(String[] args) {
		I_RentalOrderDAO dao = new RentalOrderJDBCDAO();
//		List<RentalProductListVO> rpllist = dao.getAll().stream()
//				.filter(e -> e.getRpl_status().equals("待租"))
//				.filter(e -> e.getRc_no().equals(2))
//				.collect(Collectors.toList());
//		Collections.sort(rpllist);
//		System.out.println(rpllist==null);
//		System.out.println(rpllist.size());
//		for(RentalProductListVO rplVO : rpllist)
//			System.out.println(rplVO.getRc_no() + " rent count= " + rplVO.getRpl_rentcount());
		
		
		List<RentalOrderVO> list= dao.getAll();
		Collections.sort(list,Collections.reverseOrder());
		for(RentalOrderVO roVO:list)
			System.out.println(roVO.getRo_no());
		
	}
	
	
}
