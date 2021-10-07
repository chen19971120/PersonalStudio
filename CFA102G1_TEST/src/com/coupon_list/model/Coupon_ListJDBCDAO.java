package com.coupon_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Coupon_ListJDBCDAO implements I_Coupon_ListDAO {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" + "rewriteBatchedStatements=true&" + "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";

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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, coupon_ListVO.getCi_no());
			pstmt.setInt(2, coupon_ListVO.getMem_no());
			pstmt.setString(3, coupon_ListVO.getCl_status());

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
	public void update(Coupon_ListVO coupon_ListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, coupon_ListVO.getCl_status());
			pstmt.setInt(2, coupon_ListVO.getCi_no());
			pstmt.setInt(3, coupon_ListVO.getMem_no());

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
	public void delete(Integer ci_no, Integer mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ci_no);
			pstmt.setInt(2, mem_no);

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
	public Coupon_ListVO findByPK(Integer ci_no, Integer mem_no) {
		Coupon_ListVO coupon_list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coupon_list = new Coupon_ListVO();
				coupon_list.setCi_no(rs.getInt("CI_NO"));
				coupon_list.setMem_no(rs.getInt("MEM_NO"));
				coupon_list.setCl_status(rs.getString("CL_STATUS"));
				coupon_list_List.add(coupon_list);
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
		return coupon_list_List;
	}

//測試
	public static void main(String[] args) {

//		測試insert
//		Coupon_ListJDBCDAO dao = new Coupon_ListJDBCDAO();
//		Coupon_ListVO clvo = new Coupon_ListVO();
//		clvo.setCi_no(5);
//		clvo.setMem_no(7002);
//		clvo.setCl_status("已使用");
//		dao.insert(clvo);
//		測試OK

//		測試update
//		Coupon_ListJDBCDAO dao = new Coupon_ListJDBCDAO();
//		Coupon_ListVO clvo = new Coupon_ListVO();
//		clvo.setCl_status("未使用");
//		clvo.setCi_no(3);
//		clvo.setMem_no(7001);
//		dao.update(clvo);
//		測試OK

//		測試delete
//		Coupon_ListJDBCDAO dao = new Coupon_ListJDBCDAO();
//		dao.delete(3,7001);
//		測試OK

//		測試findByPK
//		Coupon_ListJDBCDAO dao = new Coupon_ListJDBCDAO();
//		Coupon_ListVO clvo = dao.findByPK(3, 7001);
//		System.out.println(clvo.getCi_no());
//		System.out.println(clvo.getMem_no());
//		System.out.println(clvo.getCl_status());
//		測試OK

//		測試GetAll
//		Coupon_ListJDBCDAO dao = new Coupon_ListJDBCDAO();
//		List<Coupon_ListVO> list = dao.getAll();
//		for(Coupon_ListVO clvo:list) {
//		System.out.println(clvo.getCi_no());
//		System.out.println(clvo.getMem_no());
//		System.out.println(clvo.getCl_status());
//		}
//		測試OK
	}
}