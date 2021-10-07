package com.appraisal_case_images.model;

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

public class Appraisal_Case_ImagesDAO implements I_Appraisal_Case_ImagesDAO {
//	建立連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CFA102G1.APPRAISAL_CASE_IMAGES(ACA_NO,ACI_IMG) VALUES(?,?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.APPRAISAL_CASE_IMAGES SET ACI_IMG = ? WHERE ACI_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.APPRAISAL_CASE_IMAGES WHERE ACI_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.APPRAISAL_CASE_IMAGES WHERE ACI_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.APPRAISAL_CASE_IMAGES";

	@Override
	public Appraisal_Case_ImagesVO insert(Appraisal_Case_ImagesVO appraisal_Case_ImagesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, appraisal_Case_ImagesVO.getAca_no());
			pstmt.setBytes(2, appraisal_Case_ImagesVO.getAci_img());

			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				appraisal_Case_ImagesVO.setAci_no(rs.getInt(1));
			}
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
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
		return appraisal_Case_ImagesVO;
	}

	@Override
	public void update(Appraisal_Case_ImagesVO appraisal_Case_ImagesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setBytes(1, appraisal_Case_ImagesVO.getAci_img());
			pstmt.setInt(2, appraisal_Case_ImagesVO.getAci_no());

			pstmt.executeUpdate();
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
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

	@Override
	public void delete(Integer aci_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, aci_no);

			pstmt.executeUpdate();
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
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

	@Override
	public Appraisal_Case_ImagesVO findByPK(Integer aci_no) {
		Appraisal_Case_ImagesVO appraisal_case_images = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, aci_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_case_images = new Appraisal_Case_ImagesVO();
				appraisal_case_images.setAci_no(rs.getInt("ACI_NO"));
				appraisal_case_images.setAca_no(rs.getInt("ACA_NO"));
				appraisal_case_images.setAci_img(rs.getBytes("ACI_IMG"));

			}
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
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
		return appraisal_case_images;
	}

	@Override
	public List<Appraisal_Case_ImagesVO> getAll() {
		List<Appraisal_Case_ImagesVO> appraisal_case_imagesList = new ArrayList<Appraisal_Case_ImagesVO>();
		Appraisal_Case_ImagesVO appraisal_case_images = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_case_images = new Appraisal_Case_ImagesVO();
				appraisal_case_images.setAci_no(rs.getInt("ACI_NO"));
				appraisal_case_images.setAca_no(rs.getInt("ACA_NO"));
				appraisal_case_images.setAci_img(rs.getBytes("ACI_IMG"));
				appraisal_case_imagesList.add(appraisal_case_images);

			}
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
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
		return appraisal_case_imagesList;
	}
}