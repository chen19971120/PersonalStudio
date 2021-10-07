package com.maintence_case_img.model;

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

public class Maintence_Case_ImgDAO implements I_Maintence_Case_ImgDAO{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CFA102G1.MAINTENANCE_CASE_IMAGES(MCA_NO, MCI_BEFORE_IMG,"
			+ "MCI_AFTER_IMG)VALUES(?,?,?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA102G1.MAINTENANCE_CASE_IMAGES SET MCA_NO=?, MCI_BEFORE_IMG=?,"
			+ "MCI_AFTER_IMG=? WHERE MCI_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.MAINTENANCE_CASE_IMAGES WHERE MCI_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.MAINTENANCE_CASE_IMAGES WHERE MCI_BEFORE_IMG=?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.MAINTENANCE_CASE_IMAGES";
	
	@Override
	public Maintence_Case_ImgVO insert(Maintence_Case_ImgVO maintenceCaseimgvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, maintenceCaseimgvo.getMca_no());
			pstmt.setString(2,maintenceCaseimgvo.getMci_before_img());
			pstmt.setBytes(3, maintenceCaseimgvo.getMci_after_img());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				maintenceCaseimgvo.setMci_no(rs.getInt(1));
			}

		} catch (SQLException e) {
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
		return maintenceCaseimgvo;
	}
	
	@Override
	public void update(Maintence_Case_ImgVO maintenceCaseimgvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, maintenceCaseimgvo.getMca_no());
			pstmt.setString(2,maintenceCaseimgvo.getMci_before_img());
			pstmt.setBytes(3, maintenceCaseimgvo.getMci_after_img());
			pstmt.setInt(4,maintenceCaseimgvo.getMci_no());

			pstmt.executeUpdate();
		} catch (SQLException e) {
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
	public void delete(Integer mci_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, mci_no);
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
	public Maintence_Case_ImgVO findByPK(String mci_before_img) {
		Maintence_Case_ImgVO maintenceCaseimgvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, mci_before_img);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				maintenceCaseimgvo = new Maintence_Case_ImgVO();
				maintenceCaseimgvo.setMci_no(rs.getInt("MCI_NO"));
				maintenceCaseimgvo.setMca_no(rs.getInt("MCA_NO"));
				maintenceCaseimgvo.setMci_before_img(rs.getString("MCI_BEFORE_IMG"));
				maintenceCaseimgvo.setMci_after_img(rs.getBytes("MCI_AFTER_IMG"));
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
		return maintenceCaseimgvo;
	}
	
	@Override
	public List<Maintence_Case_ImgVO> getAll() {
		Maintence_Case_ImgVO maintenceCaseimgvo = null;
		List<Maintence_Case_ImgVO> listMaintencecaseimg = new ArrayList<Maintence_Case_ImgVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				maintenceCaseimgvo = new Maintence_Case_ImgVO();
				maintenceCaseimgvo.setMci_no(rs.getInt("MCI_NO"));
				maintenceCaseimgvo.setMca_no(rs.getInt("MCA_NO"));
				maintenceCaseimgvo.setMci_before_img(rs.getString("MCI_BEFORE_IMG"));
				maintenceCaseimgvo.setMci_after_img(rs.getBytes("MCI_AFTER_IMG"));
				listMaintencecaseimg.add(maintenceCaseimgvo);
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
		return listMaintencecaseimg;
	}
}
