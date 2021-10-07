package com.rentalClass.model;

import java.util.*;
import java.util.stream.Collectors;

public class TestRentalClassJDBCDAO {
	public static void main(String[] args) {
		I_RentalClassDAO dao = new RentalClassJDBCDAO();
		
		//查詢全部				
		List<RentalClassVO> listAll = dao.getAll().stream()
				.filter(e -> e.getRc_status().equals("上架"))
				.collect(Collectors.toList());
		Collections.sort(listAll);
		List<RentalClassVO> list = new ArrayList<RentalClassVO>();	
		int num =4;
		
		for(RentalClassVO rcVO:listAll) {
			for(int i=0;i<num;i++) {
				if(list.get(i)!=null)
				list.add(list.get(i));
			}
		}
			
//		System.out.println("名稱:" + rcVO.getRc_name() + "\t租賃次數: " + rcVO.getRc_total_count());
//		
//		Collections.sort(listAll);

//		Collections.sort(list);
//		return list;
		
	}
}
