package com.reportProductReviews.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReportProductReviewsDAO implements I_ReportProductReviews{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO REPORT_PRODUCT_REVIEWS (pr_no,mem_no,report_content) VALUES(?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE REPORT_PRODUCT_REVIEWS set pr_no=?,mem_no=?,report_content=?,rep_status=?,rep_date=? where rep_no = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM REPORT_PRODUCT_REVIEWS where rep_no = ?";
	private static final String FIND_BY_PK = 
			"SELECT * FROM REPORT_PRODUCT_REVIEWS WHERE rep_no = ?";
	private static final String GET_ALL = 
			"SELECT * FROM REPORT_PRODUCT_REVIEWS";
	
	public ReportProductReviewsVO insert(ReportProductReviewsVO reportProductReviewsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, reportProductReviewsVO.getPr_no());
			pstmt.setInt(2, reportProductReviewsVO.getMem_no());
			pstmt.setString(3, reportProductReviewsVO.getReport_content());
				
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				reportProductReviewsVO.setRep_no(rs.getInt(1));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		return reportProductReviewsVO;
	}

	public void update(ReportProductReviewsVO reportProductReviewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, reportProductReviewsVO.getPr_no());
			pstmt.setInt(2, reportProductReviewsVO.getMem_no());
			pstmt.setString(3, reportProductReviewsVO.getReport_content());
			pstmt.setString(4, reportProductReviewsVO.getRep_status());
			pstmt.setTimestamp(5, reportProductReviewsVO.getRep_date());
			pstmt.setInt(6, reportProductReviewsVO.getRep_no());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void delete(Integer rep_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, rep_no);
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}

	public ReportProductReviewsVO findByPK(Integer rep_no) {
		ReportProductReviewsVO reportProductReviewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, rep_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportProductReviewsVO = new ReportProductReviewsVO();		
				reportProductReviewsVO.setRep_no(rs.getInt("rep_no"));
				reportProductReviewsVO.setPr_no(rs.getInt("pr_no"));
				reportProductReviewsVO.setMem_no(rs.getInt("mem_no"));
				reportProductReviewsVO.setReport_content(rs.getString("report_content"));
				reportProductReviewsVO.setRep_status(rs.getString("rep_status"));
				reportProductReviewsVO.setRep_date(rs.getTimestamp("rep_date"));			
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return reportProductReviewsVO;
	}

	public List<ReportProductReviewsVO> getAll() {
		List<ReportProductReviewsVO> list = new ArrayList<ReportProductReviewsVO>();
		ReportProductReviewsVO reportProductReviewsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportProductReviewsVO = new ReportProductReviewsVO();		
				reportProductReviewsVO.setRep_no(rs.getInt("rep_no"));
				reportProductReviewsVO.setPr_no(rs.getInt("pr_no"));
				reportProductReviewsVO.setMem_no(rs.getInt("mem_no"));
				reportProductReviewsVO.setReport_content(rs.getString("report_content"));
				reportProductReviewsVO.setRep_status(rs.getString("rep_status"));
				reportProductReviewsVO.setRep_date(rs.getTimestamp("rep_date"));			
				list.add(reportProductReviewsVO); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
