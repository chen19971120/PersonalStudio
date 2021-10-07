package com.staff_authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class Staff_AuthorityJDBCDAO implements I_Staff_AuthorityDAO{
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102G1?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	private static final String INSERT_SQL = 
			"INSERT INTO CFA102G1.STAFF_AUTHORITY(STAFF_NO,AUTHORITY_NO) VALUES (?,?)";
	private static final String FIND_BY_STAFF_NO_SQL = 
			"SELECT * FROM CFA102G1.STAFF_AUTHORITY WHERE STAFF_NO = ?";
	private static final String GET_ALL =
			"SELECT * FROM CFA102G1.STAFF_AUTHORITY";

	@Override
	public void insert(Staff_AuthorityVO staff_authority) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setInt(1, staff_authority.getStaff_no());
			pstmt.setInt(2,staff_authority.getAuthority_no());
			
			pstmt.executeUpdate();
			
			}catch (SQLException se) {
			se.printStackTrace();
		}finally {
			if(con !=null) {
				try {
					con.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}


	@Override
	public Staff_AuthorityVO findByStaff_no(Integer staff_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Staff_AuthorityVO staff_authority = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_STAFF_NO_SQL);
			pstmt.setInt(1,staff_no );
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				staff_authority = new Staff_AuthorityVO();
				staff_authority.setStaff_no(staff_no);
				staff_authority.setAuthority_no(rs.getInt("AUTHORITY_NO"));
				
				
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return staff_authority;
	}	

	@Override
	public List<Staff_AuthorityVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Staff_AuthorityVO> list = new ArrayList<Staff_AuthorityVO>();
		Staff_AuthorityVO staff_authority = null;
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				staff_authority = new Staff_AuthorityVO();
				
				staff_authority.setStaff_no(rs.getInt("STAFF_NO"));
				staff_authority.setAuthority_no(rs.getInt("AUTHORITY_NO"));
				
				
				list.add(staff_authority);
				
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
		}finally {	
			if(con != null) {
				try {
					con.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return list;
	}
	//測試開始
//	public static void main(String[] args) {
		
		//新增功能
//		Staff_AuthorityJDBCDAO dao = new Staff_AuthorityJDBCDAO();
//		Staff_AuthorityVO staff_authority = new Staff_AuthorityVO();
//		staff_authority.setStaff_no(006);
//		staff_authority.setAuthority_no(8888);
//
//		dao.insert(staff_authority);
//		
		//System.out.println("---------------------------------------------------");
		//用功能編號查詢
//		Staff_AuthorityJDBCDAO dao = new Staff_AuthorityJDBCDAO();
//		Staff_AuthorityVO list1 = dao.findByStaff_no(997765);
//		   System.out.print(list1.getStaff_no()+"\t");
//		   System.out.print(list1.getAuthority_no()+"\t"); 
//	
//	    System.out.println();
		
		//System.out.println("---------------------------------------------------");
		//搜尋全部
//		Staff_AuthorityJDBCDAO dao = new Staff_AuthorityJDBCDAO();
//		List<Staff_AuthorityVO> list = dao.getAll();
//		for (Staff_AuthorityVO authouityVO : list ) {
//			
//		   System.out.print(authouityVO.getStaff_no()+"\t");
//		   System.out.print(authouityVO.getAuthority_no()+"\t");
//	
//		   System.out.println();
//		
//		}
//		System.out.println("-------------------------------------------");
		
//   }
}