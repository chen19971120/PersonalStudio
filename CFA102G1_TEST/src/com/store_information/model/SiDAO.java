package com.store_information.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.store_information.model.SiVO;

public class SiDAO implements I_SiDAO {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO cfa102g1.store_information (si_no, si_address,si_open,si_phone,si_email,si_line) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT si_no,si_address,si_open,si_phone,si_email,si_line FROM cfa102g1.store_information";
	private static final String GET_ONE_STMT = "SELECT si_no,si_address,si_open,si_phone,si_email,si_line FROM cfa102g1.store_information where si_no=?";
	private static final String DELETE = "DELETE FROM cfa102g1.store_information where si_no = ?";
	private static final String UPDATE = "UPDATE cfa102g1.store_information set si_address=?, si_open=?, si_phone=?, si_email=?, si_line=? where si_no=?";

	@Override
	public void insert(SiVO siVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, siVO.getSi_no());
			pstmt.setString(2, siVO.getSi_address());
			pstmt.setString(3, siVO.getSi_open());
			pstmt.setString(4, siVO.getSi_phone());
			pstmt.setString(5, siVO.getSi_email());
			pstmt.setString(6, siVO.getSi_line());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void update(SiVO siVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, siVO.getSi_address());
			pstmt.setString(2, siVO.getSi_open());
			pstmt.setString(3, siVO.getSi_phone());
			pstmt.setString(4, siVO.getSi_email());
			pstmt.setString(5, siVO.getSi_line());
			pstmt.setInt(6, siVO.getSi_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void delete(Integer si_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, si_no);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public SiVO findByPrimaryKey(Integer si_no) {

		SiVO siVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, si_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				siVO = new SiVO();

				siVO.setSi_no(rs.getInt("SI_NO"));
				siVO.setSi_address(rs.getString("SI_ADDRESS"));
				siVO.setSi_open(rs.getString("SI_OPEN"));
				siVO.setSi_phone(rs.getString("SI_PHONE"));
				siVO.setSi_email(rs.getString("SI_EMAIL"));
				siVO.setSi_line(rs.getString("SI_LINE"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return siVO;
	}

	@Override
	public List<SiVO> getAll() {
		List<SiVO> list = new ArrayList<SiVO>();
		SiVO siVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// siVO 也稱為 Domain objects
				siVO = new SiVO();
				siVO.setSi_no(rs.getInt("SI_NO"));
				siVO.setSi_address(rs.getString("SI_ADDRESS"));
				siVO.setSi_open(rs.getString("SI_OPEN"));
				siVO.setSi_phone(rs.getString("SI_PHONE"));
				siVO.setSi_email(rs.getString("SI_EMAIL"));
				siVO.setSi_line(rs.getString("SI_LINE"));
				list.add(siVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
