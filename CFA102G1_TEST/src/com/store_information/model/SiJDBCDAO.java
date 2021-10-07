package com.store_information.model;

import java.util.*;


import java.sql.*;

public class SiJDBCDAO implements I_SiDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT =
		"INSERT INTO cfa102g1.store_information (si_no, si_address,si_open,si_phone,si_email,si_line) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
		"SELECT si_no,si_address,si_open,si_phone,si_email,si_line FROM cfa102g1.store_information";
	private static final String GET_ONE_STMT =
		"SELECT si_no,si_address,si_open,si_phone,si_email,si_line FROM cfa102g1.store_information where si_no=?";
	private static final String DELETE =
		"DELETE FROM cfa102g1.store_information where si_no = ?";
	private static final String UPDATE =
		"UPDATE cfa102g1.store_information set si_address=?, si_open=?, si_phone=?, si_email=?, si_line=? where si_no=?";
	@Override
	public void insert(SiVO siVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, siVO.getSi_no());
			pstmt.setString(2, siVO.getSi_address());
			pstmt.setString(3, siVO.getSi_open());
			pstmt.setString(4, siVO.getSi_phone());
			pstmt.setString(5, siVO.getSi_email());
			pstmt.setString(6, siVO.getSi_line());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, siVO.getSi_address());
			pstmt.setString(2, siVO.getSi_open());
			pstmt.setString(3, siVO.getSi_phone());
			pstmt.setString(4, siVO.getSi_email());
			pstmt.setString(5, siVO.getSi_line());
			pstmt.setInt(6, siVO.getSi_no());

			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, si_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
//	public static void main(String[] args) {
//		SiJDBCDAO dao = new SiJDBCDAO();
//		
//		//新增
//		SiVO SiVO1 = new SiVO();
//		SiVO1.setSi_no(19);
//		SiVO1.setSi_address("USA");
//		SiVO1.setSi_open("10:00AM");
//		SiVO1.setSi_phone("06487");
//		SiVO1.setSi_email("1234567");
//		SiVO1.setSi_line("777");
//		dao.insert(SiVO1);
//		System.out.println("ok");
		
		//修改
//		SiVO SiVO2 = new SiVO();
//		SiVO2.setSi_no(1);
//		SiVO2.setSi_address("1");
//		SiVO2.setSi_open("1");
//		SiVO2.setSi_phone("1");
//		SiVO2.setSi_email("1");
//		SiVO2.setSi_line("1");
//		dao.update(SiVO2);
		
		//刪除
//		dao.delete(1);
		
		// 查詢
//		SiVO siVO3 = dao.findByPrimaryKey(2);
//		System.out.print(siVO3.getSi_no() + ",");
//		System.out.print(siVO3.getSi_address() + ",");
//		System.out.print(siVO3.getSi_open() + ",");
//		System.out.print(siVO3.getSi_phone() + ",");
//		System.out.print(siVO3.getSi_email() + ",");
//		System.out.println(siVO3.getSi_line());
//		System.out.println("---------------------");
		
		//查詢
//		List<SiVO> list = dao.getAll();
//		for (SiVO SiVO1 : list) {
//		System.out.print(SiVO1.getSi_no()+",");
//		System.out.print(SiVO1.getSi_address()+",");
//		System.out.print(SiVO1.getSi_open()+",");
//		System.out.print(SiVO1.getSi_phone()+",");
//		System.out.print(SiVO1.getSi_email()+",");
//		System.out.print(SiVO1.getSi_line());
//		System.out.println();
//		}
		
		
		
//	}
}
