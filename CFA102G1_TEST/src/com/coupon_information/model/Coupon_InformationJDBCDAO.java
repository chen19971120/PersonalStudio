package com.coupon_information.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Coupon_InformationJDBCDAO implements I_Coupon_InformationDAO {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" + "rewriteBatchedStatements=true&" + "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";

	private static final String INSERT_STMT = "INSERT INTO CFA102G1.COUPON_INFORMATION(CI_NAME, CI_CODE, CI_START_TIME, CI_END_TIME, DISCOUNT, CI_CONTENT) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.COUPON_INFORMATION SET CI_NAME= ?, CI_CODE= ?, CI_START_TIME= ?, CI_END_TIME= ?, DISCOUNT= ?, CI_CONTENT = ? WHERE CI_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.COUPON_INFORMATION WHERE CI_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.COUPON_INFORMATION WHERE CI_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.COUPON_INFORMATION";

	@Override
	public Coupon_InformationVO insert(Coupon_InformationVO coupon_InformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, coupon_InformationVO.getCi_name());
			pstmt.setString(2, coupon_InformationVO.getCi_code());
			pstmt.setTimestamp(3, coupon_InformationVO.getCi_start_time());
			pstmt.setTimestamp(4, coupon_InformationVO.getCi_end_time());
			pstmt.setInt(5, coupon_InformationVO.getDiscount());
			pstmt.setString(6, coupon_InformationVO.getCi_content());

			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				coupon_InformationVO.setCi_no(rs.getInt(1));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return coupon_InformationVO;
	}

	@Override
	public void update(Coupon_InformationVO coupon_InformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, coupon_InformationVO.getCi_name());
			pstmt.setString(2, coupon_InformationVO.getCi_code());
			pstmt.setTimestamp(3, coupon_InformationVO.getCi_start_time());
			pstmt.setTimestamp(4, coupon_InformationVO.getCi_end_time());
			pstmt.setInt(5, coupon_InformationVO.getDiscount());
			pstmt.setString(6, coupon_InformationVO.getCi_content());
			pstmt.setInt(7, coupon_InformationVO.getCi_no());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public void delete(Integer ci_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ci_no);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public Coupon_InformationVO findByPK(Integer ci_no) {
		Coupon_InformationVO coupon_information = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, ci_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coupon_information = new Coupon_InformationVO();
				coupon_information.setCi_no(rs.getInt("CI_NO"));
				coupon_information.setCi_name(rs.getString("CI_NAME"));
				coupon_information.setCi_code(rs.getString("CI_CODE"));
				coupon_information.setCi_start_time(rs.getTimestamp("CI_START_TIME"));
				coupon_information.setCi_end_time(rs.getTimestamp("CI_END_TIME"));
				coupon_information.setDiscount(rs.getInt("DISCOUNT"));
				coupon_information.setCi_content(rs.getString("CI_CONTENT"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return coupon_information;
	}

	@Override
	public List<Coupon_InformationVO> getAll() {
		List<Coupon_InformationVO> coupon_informationList = new ArrayList<>();
		Coupon_InformationVO coupon_information = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coupon_information = new Coupon_InformationVO();
				coupon_information.setCi_no(rs.getInt("CI_NO"));
				coupon_information.setCi_name(rs.getString("CI_NAME"));
				coupon_information.setCi_code(rs.getString("CI_CODE"));
				coupon_information.setCi_start_time(rs.getTimestamp("CI_START_TIME"));
				coupon_information.setCi_end_time(rs.getTimestamp("CI_END_TIME"));
				coupon_information.setDiscount(rs.getInt("DISCOUNT"));
				coupon_information.setCi_content(rs.getString("CI_CONTENT"));
				coupon_informationList.add(coupon_information);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return coupon_informationList;
	}

//	測試
//	public static void main(String[] args) {

//		測試insert
//		Coupon_InformationJDBCDAO dao = new Coupon_InformationJDBCDAO();
//		Coupon_InformationVO civo = new Coupon_InformationVO();
//		civo.setCi_name("必勝客 消費滿 199 元現折 50 元");
//		civo.setCi_code("PHAUG");
//		civo.setCi_start_time(java.sql.Timestamp.valueOf("2022-08-22 00:00:00"));
//		civo.setCi_end_time(java.sql.Timestamp.valueOf("2022-08-23 00:00:00"));
//		civo.setDiscount(50);
//		civo.setCi_content("必勝客 消費滿 199 元現折 50 元");
//		dao.insert(civo);
//		System.out.println(civo.getCi_no()+":"+civo.getCi_name());
//		測試OK

//		測試update
//		Coupon_InformationJDBCDAO dao = new Coupon_InformationJDBCDAO();
//		Coupon_InformationVO civo = new Coupon_InformationVO();
//		civo.setCi_no(3);
//		civo.setCi_name("現折60");
//		civo.setCi_code("SUM300");
//		civo.setCi_start_time(java.sql.Timestamp.valueOf("20220822 00:00:00"));
//		civo.setCi_end_time(java.sql.Timestamp.valueOf("20220901 00:00:00"));
//		civo.setDiscount(60);
//		civo.setCi_content("單筆消費滿300折60");
//		dao.update(civo);
//		測試OK

//		測試delete
//		Coupon_InformationJDBCDAO dao = new Coupon_InformationJDBCDAO();
//		dao.delete(6);
//		測試OK

//		測試findByPK
//		Coupon_InformationJDBCDAO dao = new Coupon_InformationJDBCDAO();
//		Coupon_InformationVO civo = dao.findByPK(3);
//		System.out.println(civo.getCi_no());
//		System.out.println(civo.getCi_name());
//		System.out.println(civo.getCi_code());
//		System.out.println(civo.getCi_start_time());
//		System.out.println(civo.getCi_end_time());
//		System.out.println(civo.getDiscount());
//		System.out.println(civo.getCi_content());
//		測試OK

//		測試GetAll
//		Coupon_InformationJDBCDAO dao = new Coupon_InformationJDBCDAO();
//		List<Coupon_InformationVO> list = dao.getAll();
//		for(Coupon_InformationVO civo:list) {
//			System.out.println(civo.getCi_no());
//			System.out.println(civo.getCi_name());
//			System.out.println(civo.getCi_code());
//			System.out.println(civo.getCi_start_time());
//			System.out.println(civo.getCi_end_time());
//			System.out.println(civo.getDiscount());
//			System.out.println(civo.getCi_content());
//		}
//		測試OK
//	}
}