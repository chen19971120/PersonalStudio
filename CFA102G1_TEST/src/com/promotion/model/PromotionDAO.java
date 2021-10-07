package com.promotion.model;

import java.sql.Connection;
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

public class PromotionDAO implements I_PromotionDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.PROMOTIONS(PROMOTIONS_NAME,PROMOTIONS_START_DATE,PROMOTIONS_END_DATE)VALUES(?, ?, ?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA102G1.PROMOTIONS SET PROMOTIONS_NAME=? ,PROMOTIONS_START_DATE=? ,PROMOTIONS_END_DATE=? WHERE PROMOTIONS_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.PROMOTIONS WHERE PROMOTIONS_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.PROMOTIONS WHERE PROMOTIONS_NO=?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.PROMOTIONS";
	
	@Override
	public PromotionVO insert(PromotionVO promotionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, promotionVO.getPromotion_name());
			pstmt.setTimestamp(2, promotionVO.getPromotion_start_date());
			pstmt.setTimestamp(3, promotionVO.getPromotion_end_date());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				promotionVO.setPromotion_no(rs.getInt(1));
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return promotionVO;
	}

	@Override
	public void update(PromotionVO promotionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, promotionVO.getPromotion_name());
			pstmt.setTimestamp(2, promotionVO.getPromotion_start_date());
			pstmt.setTimestamp(3, promotionVO.getPromotion_end_date());
			pstmt.setInt(4, promotionVO.getPromotion_no());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(Integer promotion_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, promotion_no);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public PromotionVO findByPk(Integer promotion_no) {
		PromotionVO promotion = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, promotion_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				promotion = new PromotionVO();
				promotion.setPromotion_no(rs.getInt("PROMOTIONS_NO"));
				promotion.setPromotion_name(rs.getString("PROMOTIONS_NAME"));
				promotion.setPromotion_start_date(rs.getTimestamp("PROMOTIONS_START_DATE"));
				promotion.setPromotion_end_date(rs.getTimestamp("PROMOTIONS_END_DATE"));
			}
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return promotion;
	}

	@Override
	public List<PromotionVO> getAll() {
		PromotionVO promotion = null;
		List<PromotionVO> listPromotion = new ArrayList<PromotionVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				promotion = new PromotionVO();
				promotion.setPromotion_no(rs.getInt("PROMOTIONS_NO"));
				promotion.setPromotion_name(rs.getString("PROMOTIONS_NAME"));
				promotion.setPromotion_start_date(rs.getTimestamp("PROMOTIONS_START_DATE"));
				promotion.setPromotion_end_date(rs.getTimestamp("PROMOTIONS_END_DATE"));
				listPromotion.add(promotion);
			}
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listPromotion;
	}
}
