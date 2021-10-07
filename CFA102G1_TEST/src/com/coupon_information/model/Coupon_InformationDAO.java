package com.coupon_information.model;

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

public class Coupon_InformationDAO implements I_Coupon_InformationDAO {
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
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, coupon_InformationVO.getCi_name());
			pstmt.setString(2, coupon_InformationVO.getCi_code());
			pstmt.setTimestamp(3, coupon_InformationVO.getCi_start_time());
			pstmt.setTimestamp(4, coupon_InformationVO.getCi_end_time());
			pstmt.setInt(5, coupon_InformationVO.getDiscount());
			pstmt.setString(6, coupon_InformationVO.getCi_content());
			pstmt.setInt(7, coupon_InformationVO.getCi_no());

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
	public void delete(Integer ci_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, ci_no);

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
	public Coupon_InformationVO findByPK(Integer ci_no) {
		Coupon_InformationVO coupon_information = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
}