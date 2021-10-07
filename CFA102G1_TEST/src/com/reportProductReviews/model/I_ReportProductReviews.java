package com.reportProductReviews.model;

import java.util.List;

public interface I_ReportProductReviews {
	public ReportProductReviewsVO insert(ReportProductReviewsVO ReportProductReviewsVO);
	public void update(ReportProductReviewsVO ReportProductReviewsVO);
	public void delete(Integer rep_no);
	public ReportProductReviewsVO findByPK(Integer rep_no);
	public List<ReportProductReviewsVO> getAll();
}
