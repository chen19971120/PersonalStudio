package com.reportProductReviews.model;

import com.productReviews.model.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class ReportProductReviewsService {
	private I_ReportProductReviews dao;
	
	public ReportProductReviewsService() {
		dao = new ReportProductReviewsDAO();
	}
	
	public ReportProductReviewsVO insertReportProductReviews(Integer pr_no, Integer mem_no, String report_content
			) {
		
		ReportProductReviewsVO reportProductReviewsVO = new ReportProductReviewsVO();
		
		reportProductReviewsVO.setPr_no(pr_no);
		reportProductReviewsVO.setMem_no(mem_no);
		reportProductReviewsVO.setReport_content(report_content);

		reportProductReviewsVO = dao.insert(reportProductReviewsVO);
		
		return reportProductReviewsVO;
	}
	
	public ReportProductReviewsVO updateReportProductReviews(Integer rep_no, Integer pr_no, Integer mem_no, String report_content,
			String rep_status, Timestamp rep_date) {

		ReportProductReviewsVO reportProductReviewsVO = new ReportProductReviewsVO();

		reportProductReviewsVO.setRep_no(rep_no);
		reportProductReviewsVO.setPr_no(pr_no);
		reportProductReviewsVO.setMem_no(mem_no);
		reportProductReviewsVO.setReport_content(report_content);
		reportProductReviewsVO.setRep_status(rep_status);
		reportProductReviewsVO.setRep_date(rep_date);

		dao.update(reportProductReviewsVO);

		return reportProductReviewsVO;
	}
	
	public ReportProductReviewsVO updateReportProductReviews(ReportProductReviewsVO reportProductReviewsVO) {

		dao.update(reportProductReviewsVO);
		return reportProductReviewsVO;
	}

	public void deleteReportProductReviews(Integer rep_no) {
		dao.delete(rep_no);
	}

	public ReportProductReviewsVO getOneReportProductReviews(Integer rep_no) {
		return dao.findByPK(rep_no);
	}

	public List<ReportProductReviewsVO> getAll() {
		return dao.getAll();
	}
	//檢舉不通過
	public void reportNotPass(Integer rep_no) {
		ReportProductReviewsVO repVO = dao.findByPK(rep_no);
		repVO.setRep_status("不通過");
		dao.update(repVO);
	}
	//檢舉通過
	public void reportPass(Integer rep_no) {
		ReportProductReviewsVO repVO = dao.findByPK(rep_no);
		repVO.setRep_status("通過");
		dao.update(repVO);
		
		ProductReviewsService prSvc = new ProductReviewsService();
		ProductReviewsVO prVO = prSvc.getOneProductReviews(repVO.getPr_no());
		prVO.setPr_status("下架");
		prSvc.updateProductReviews(prVO);
	}
	//依照rep_status傳回List
	public List<ReportProductReviewsVO> getListbyRepStatus(String rep_status) {
		List<ReportProductReviewsVO> list = dao.getAll().stream()
		        .filter(e -> e.getRep_status().equals(rep_status))
		        .collect(Collectors.toList());
		return list;
	}
}
