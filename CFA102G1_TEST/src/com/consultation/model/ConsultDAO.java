package com.consultation.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.consultation.model.ConsultVO;

public class ConsultDAO implements I_ConsultDAO {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO cfa102g1.consultation(consultant,consult_phone,consult_email,consult_content,staff_no,consult_sta) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT consult_no,consultant,consult_phone,consult_email,consult_content,staff_no,consult_sta FROM cfa102g1.consultation";
	private static final String GET_ONE_STMT = "SELECT consult_no,consultant,consult_phone,consult_email,consult_content,staff_no,consult_sta FROM cfa102g1.consultation where consult_no = ?";
	private static final String DELETE = "DELETE FROM cfa102g1.consultation where consult_no = ?";
	private static final String UPDATE = "UPDATE cfa102g1.consultation set consultant=?, consult_phone=?, consult_email=?, consult_content=?, staff_no=?, consult_sta=? where consult_no=?";

	@Override
	public ConsultVO insert(ConsultVO consultVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, consultVO.getConsultant());
			pstmt.setString(2, consultVO.getConsult_phone());
			pstmt.setString(3, consultVO.getConsult_email());
			pstmt.setString(4, consultVO.getConsult_content());
			pstmt.setInt(5, consultVO.getStaff_no());
			pstmt.setString(6, consultVO.getConsult_sta());

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				consultVO.setConsult_no(rs.getInt(1));
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return consultVO;
	}

	@Override
	public void update(ConsultVO consultVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, consultVO.getConsultant());
			pstmt.setString(2, consultVO.getConsult_phone());
			pstmt.setString(3, consultVO.getConsult_email());
			pstmt.setString(4, consultVO.getConsult_content());
			pstmt.setInt(5, consultVO.getStaff_no());
			pstmt.setString(6, consultVO.getConsult_sta());
			pstmt.setInt(7, consultVO.getConsult_no());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(Integer consult_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, consult_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public ConsultVO findByPrimaryKey(Integer consult_no) {

		ConsultVO consultVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, consult_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// consultVo 也稱為 Domain objects
				consultVO = new ConsultVO();

				consultVO.setConsult_no(rs.getInt("CONSULT_NO"));
				consultVO.setConsultant(rs.getString("CONSULTANT"));
				consultVO.setConsult_phone(rs.getString("CONSULT_PHONE"));
				consultVO.setConsult_email(rs.getString("CONSULT_EMAIL"));
				consultVO.setConsult_content(rs.getString("CONSULT_CONTENT"));
				consultVO.setStaff_no(rs.getInt("STAFF_NO"));
				consultVO.setConsult_sta(rs.getString("CONSULT_STA"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return consultVO;
	}

	@Override
	public List<ConsultVO> getAll() {
		List<ConsultVO> list = new ArrayList<ConsultVO>();
		ConsultVO consultVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// consultVO 也稱為 Domain objects
				consultVO = new ConsultVO();
				consultVO.setConsult_no(rs.getInt("CONSULT_NO"));
				consultVO.setConsultant(rs.getString("CONSULTANT"));
				consultVO.setConsult_phone(rs.getString("CONSULT_PHONE"));
				consultVO.setConsult_email(rs.getString("CONSULT_EMAIL"));
				consultVO.setConsult_content(rs.getString("CONSULT_CONTENT"));
				consultVO.setStaff_no(rs.getInt("STAFF_NO"));
				consultVO.setConsult_sta(rs.getString("CONSULT_STA"));
				list.add(consultVO); // Store the row in the list
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
