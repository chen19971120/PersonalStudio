package com.rentalProductImages.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RentalProductImagesDAO implements I_RentalProductImagesDAO{
	
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
			"INSERT INTO RENTAL_PRODUCT_IMAGES (rc_no,rpi_img) VALUES(?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE RENTAL_PRODUCT_IMAGES set rc_no=?,rpi_img=? where rpi_no = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM RENTAL_PRODUCT_IMAGES where rpi_no = ?";
	private static final String FIND_BY_PK = 
			"SELECT * FROM RENTAL_PRODUCT_IMAGES WHERE rpi_no = ?";
	private static final String GET_ALL = 
			"SELECT * FROM RENTAL_PRODUCT_IMAGES";
	
	public RentalProductImagesVO insert(RentalProductImagesVO rentalProductImagesVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, rentalProductImagesVO.getRc_no());
			pstmt.setBytes(2, rentalProductImagesVO.getRpi_img());
			
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				rentalProductImagesVO.setRpi_no(rs.getInt(1));
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
		return rentalProductImagesVO;
	}

	public void update(RentalProductImagesVO rentalProductImagesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, rentalProductImagesVO.getRc_no());
			pstmt.setBytes(2, rentalProductImagesVO.getRpi_img());
			pstmt.setInt(3, rentalProductImagesVO.getRpi_no());
			
			pstmt.executeUpdate();

		}catch (SQLException se) {
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

	public void delete(Integer rpi_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, rpi_no);
			
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

	public RentalProductImagesVO findByPK(Integer rpi_no) {
		RentalProductImagesVO rentalProductImagesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, rpi_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalProductImagesVO = new RentalProductImagesVO();		
				rentalProductImagesVO.setRpi_no(rs.getInt("rpi_no"));
				rentalProductImagesVO.setRc_no(rs.getInt("rc_no"));
				rentalProductImagesVO.setRpi_img(rs.getBytes("rpi_img"));	
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
		return rentalProductImagesVO;
	}

	public List<RentalProductImagesVO> getAll() {
		List<RentalProductImagesVO> list = new ArrayList<RentalProductImagesVO>();
		RentalProductImagesVO rentalProductImagesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalProductImagesVO = new RentalProductImagesVO();		
				rentalProductImagesVO.setRpi_no(rs.getInt("rpi_no"));
				rentalProductImagesVO.setRc_no(rs.getInt("rc_no"));
				list.add(rentalProductImagesVO);
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
