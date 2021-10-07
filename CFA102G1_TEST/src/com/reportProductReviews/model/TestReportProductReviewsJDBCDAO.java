package com.reportProductReviews.model;

import java.sql.Timestamp;
import java.util.List;

public class TestReportProductReviewsJDBCDAO {
	public static void main(String[] args) {
		I_ReportProductReviews dao = new ReportProductReviewsJDBCDAO();
		ReportProductReviewsVO rep1 = new ReportProductReviewsVO();
		//新增
		rep1.setPr_no(1);
		rep1.setMem_no(4);
		rep1.setReport_content("12321");
		rep1.setRep_status("on");
		rep1 = dao.insert(rep1);
		System.out.println(rep1.getRep_no());
		//修改
//		rep1.setRep_no(2);
//		rep1.setPr_no(6);
//		rep1.setMem_no(4);
//		rep1.setReport_content("update");
//		rep1.setRep_status("update");
//		Timestamp stamp = new Timestamp(0);
//		rep1.setRep_date(stamp);
//		dao.update(rep1);
		
		//刪除
//		dao.delete(5);
		
		//查詢
//		ReportProductReviewsVO rep1 = dao.findByPK(6);
//		System.out.println(rep1.getReport_content());
		//查詢全部
		List<ReportProductReviewsVO> list = dao.getAll();
		for(ReportProductReviewsVO rep : list ) 
			System.out.println(rep.getReport_content());
	}
}
