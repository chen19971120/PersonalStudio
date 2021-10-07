package com.coupon_list.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Coupon_ListDAO implements I_Coupon_ListDAO {
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

	private static final String INSERT_STMT = "INSERT INTO CFA102G1.COUPON_LIST(CI_NO,MEM_NO,CL_STATUS) VALUES(?,?,?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.COUPON_LIST SET CL_STATUS = ? WHERE CI_NO = ? and MEM_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.COUPON_LIST WHERE CI_NO = ? and MEM_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.COUPON_LIST WHERE CI_NO = ? and MEM_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.COUPON_LIST";

	@Override
	public void insert(Coupon_ListVO coupon_ListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, coupon_ListVO.getCi_no());
			pstmt.setInt(2, coupon_ListVO.getMem_no());
			pstmt.setString(3, coupon_ListVO.getCl_status());

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
	public void update(Coupon_ListVO coupon_ListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, coupon_ListVO.getCl_status());
			pstmt.setInt(2, coupon_ListVO.getCi_no());
			pstmt.setInt(3, coupon_ListVO.getMem_no());

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
	public void delete(Integer ci_no, Integer mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, ci_no);
			pstmt.setInt(2, mem_no);

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
	public Coupon_ListVO findByPK(Integer ci_no, Integer mem_no) {
		Coupon_ListVO coupon_list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, ci_no);
			pstmt.setInt(2, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coupon_list = new Coupon_ListVO();
				coupon_list.setCi_no(rs.getInt("CI_NO"));
				coupon_list.setMem_no(rs.getInt("MEM_NO"));
				coupon_list.setCl_status(rs.getString("CL_STATUS"));
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
		return coupon_list;
	}

	@Override
	public List<Coupon_ListVO> getAll() {
		List<Coupon_ListVO> coupon_list_List = new ArrayList<>();
		Coupon_ListVO coupon_list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coupon_list = new Coupon_ListVO();
				coupon_list.setCi_no(rs.getInt("CI_NO"));
				coupon_list.setMem_no(rs.getInt("MEM_NO"));
				coupon_list.setCl_status(rs.getString("CL_STATUS"));
				coupon_list_List.add(coupon_list);
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
		return coupon_list_List;
	}
}