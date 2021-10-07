package com.promotion_list.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PromotionListDAO implements I_PromotionListDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.PROMOTION_LIST(PROMOTIONS_NO, SPI_NO, SPI_PRICE) VALUES(?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.PROMOTION_LIST SET SPI_PRICE=? WHERE PROMOTIONS_NO = ? and SPI_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.PROMOTION_LIST WHERE PROMOTIONS_NO = ? and SPI_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.PROMOTION_LIST WHERE PROMOTIONS_NO = ? and SPI_NO=?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.PROMOTION_LIST";

	@Override
	public void insert(PromotionListVO promotionList) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, promotionList.getPromotions_no());
			pstmt.setInt(2, promotionList.getSpi_no());
			pstmt.setInt(3, promotionList.getSpi_price());
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
	public void update(PromotionListVO promotionList) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, promotionList.getSpi_price());
			pstmt.setInt(2, promotionList.getPromotions_no());
			pstmt.setInt(3, promotionList.getSpi_no());
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
	public void delete(Integer promotions_no, Integer spi_no) {

		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, promotions_no);
			pstmt.setInt(2, spi_no);
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
	public PromotionListVO findByPK(Integer promotions_no, Integer spi_no) {
		PromotionListVO promotionList = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, promotions_no);
			pstmt.setInt(2, spi_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				promotionList = new PromotionListVO();
				promotionList.setPromotions_no(rs.getInt("PROMOTIONS_NO"));
				promotionList.setSpi_no(rs.getInt("SPI_NO"));
				promotionList.setSpi_price(rs.getInt("SPI_PRICE"));
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
		return promotionList;
	}

	@Override
	public List<PromotionListVO> getAll() {
		PromotionListVO promotionList = null;
		List<PromotionListVO> listPromotionList = new ArrayList<PromotionListVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				promotionList = new PromotionListVO();
				promotionList.setPromotions_no(rs.getInt("PROMOTIONS_NO"));
				promotionList.setSpi_no(rs.getInt("SPI_NO"));
				promotionList.setSpi_price(rs.getInt("SPI_PRICE"));
				listPromotionList.add(promotionList);
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
		return listPromotionList;
	}
}
