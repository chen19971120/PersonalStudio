package com.consultation.model;

import java.util.*;
import java.sql.*;

public class ConsultJDBCDAO implements I_ConsultDAO {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" + "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";

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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, consultVO.getConsultant());
			pstmt.setString(2, consultVO.getConsult_phone());
			pstmt.setString(3, consultVO.getConsult_email());
			pstmt.setString(4, consultVO.getConsult_content());
			pstmt.setInt(5, consultVO.getStaff_no());
			pstmt.setString(6, consultVO.getConsult_sta());
			pstmt.setInt(7, consultVO.getConsult_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(Integer consult_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, consult_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public ConsultVO findByPrimaryKey(Integer consult_no) {

		ConsultVO consultVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

	// 測試驗證
//	public static void main(String[] args) {
//	ConsultJDBCDAO dao = new ConsultJDBCDAO();
	// 新增
//	ConsultVO consultVO1 = new ConsultVO();
//	consultVO1.setConsultant("吉娃娃");
//	consultVO1.setConsult_phone("03555555");
//	consultVO1.setConsult_email("123");
//	consultVO1.setConsult_content("1234564");
//	consultVO1.setStaff_no(1);
//	consultVO1.setConsult_sta("12");
//	consultVO1 = dao.insert(consultVO1);
//	System.out.println(consultVO1.getConsult_no());

		// 修改
//	ConsultVO consultVO2 = new ConsultVO();
//	consultVO2.setConsult_no(4);
//	consultVO2.setConsultant("吉娃1");
//	consultVO2.setConsult_phone("03555355");
//	consultVO2.setConsult_email("235");
//	consultVO2.setConsult_content("2343564");
//	consultVO2.setStaff_no(1);
//	consultVO2.setConsult_sta("已回覆");
//	dao.update(consultVO2);

		// 刪除
//	dao.delete(3);

		// 查詢
//	ConsultVO consultVO3 = dao.findByPrimaryKey(1);
//	System.out.print(consultVO3.getConsult_no()+",");
//	System.out.print(consultVO3.getConsultant()+",");
//	System.out.print(consultVO3.getConsult_phone()+",");
//	System.out.print(consultVO3.getConsult_email()+",");
//	System.out.print(consultVO3.getConsult_content()+",");
//	System.out.print(consultVO3.getStaff_no()+",");
//	System.out.println(consultVO3.getConsult_sta());
//	System.out.println("---------------------");

		// 查詢
//	List<ConsultVO> list = dao.getAll();
//	for (ConsultVO consultVO1 : list) {
//	System.out.print(consultVO1.getConsult_no()+",");
//	System.out.print(consultVO1.getConsultant()+",");
//	System.out.print(consultVO1.getConsult_phone()+",");
//	System.out.print(consultVO1.getConsult_email()+",");
//	System.out.print(consultVO1.getConsult_content()+",");
//	System.out.print(consultVO1.getStaff_no()+",");
//	System.out.print(consultVO1.getConsult_sta());
//	System.out.println();
//	}
//	}
}
