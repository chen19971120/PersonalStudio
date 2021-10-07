package com.sec_product_images.model;

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

public class SecProductImagesDAO implements I_SecProductImagesDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.SEC_PRODUCT_IMAGES(SPI_NO, SPIM_IMG)VALUES(?, ?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA102G1.SEC_PRODUCT_IMAGES SET SPI_NO=?, SPIM_IMG=? WHERE SPIM_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.SEC_PRODUCT_IMAGES WHERE SPIM_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.SEC_PRODUCT_IMAGES WHERE SPIM_NO=?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.SEC_PRODUCT_IMAGES";
	
	@Override
	public SecProductImagesVO insert(SecProductImagesVO secProductImages) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, secProductImages.getSpi_no());
			pstmt.setBytes(2, secProductImages.getSpim_img());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				secProductImages.setSpim_no(rs.getInt(1));
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
		return secProductImages;
	}
	
	@Override
	public void update(SecProductImagesVO secProductImages) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, secProductImages.getSpi_no());
			pstmt.setBytes(2, secProductImages.getSpim_img());
			pstmt.setInt(3, secProductImages.getSpim_no());

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
	public void delete(Integer spim_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, spim_no);
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
	public SecProductImagesVO findByPk(Integer spim_no) {
		SecProductImagesVO secProductImages = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, spim_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				secProductImages = new SecProductImagesVO();
				secProductImages.setSpim_no(rs.getInt("SPIM_NO"));
				secProductImages.setSpi_no(rs.getInt("SPI_NO"));
				secProductImages.setSpim_img(rs.getBytes("SPIM_IMG"));
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
		return secProductImages;
	}
	
	@Override
	public List<SecProductImagesVO> getAll() {
		SecProductImagesVO secProductImages = null;
		List<SecProductImagesVO> lsitSecProductImages = new ArrayList<SecProductImagesVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				secProductImages = new SecProductImagesVO();
				secProductImages.setSpim_no(rs.getInt("SPIM_NO"));
				secProductImages.setSpi_no(rs.getInt("SPI_NO"));
				//secProductImages.setSpim_img(rs.getBytes("SPIM_IMG")); //移除回傳圖片，使用findByPK來取得圖片
				lsitSecProductImages.add(secProductImages);
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
		return lsitSecProductImages;
	}              
}
