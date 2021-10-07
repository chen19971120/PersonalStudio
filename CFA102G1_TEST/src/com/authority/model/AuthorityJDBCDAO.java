package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class AuthorityJDBCDAO implements I_AuthorityDAO {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102G1?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	private static final String INSERT_SQL = 
			"INSERT INTO CFA102G1.AUTHORITY(AUTHORITY_NAME) VALUES (?)";
	private static final String UPDATE_SQL = 
			"UPDATE CFA102G1.AUTHORITY SET AUTHORITY_NAME = ? WHERE AUTHORITY_NO = ?";
	private static final String DELETE_SQL = 
			"DELETE FROM CFA102G1.AUTHORITY where AUTHORITY_NO = ?";
	private static final String FIND_BY_AUTHORITY_NO_SQL = 
			"SELECT * FROM CFA102G1.AUTHORITY WHERE AUTHORITY_NO = ?";
	private static final String GET_ALL =
			"SELECT AUTHORITY_NO,AUTHORITY_NAME FROM CFA102G1.AUTHORITY";
	
	static {
		try {
			Class.forName(DRIVER);
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	

	@Override
	public AuthorityVO insert(AuthorityVO authority) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,authority.getAuthority_name());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				authority.setAuthority_no(rs.getInt(1));
			}
			
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
		return authority;
	}

	@Override
	public void delete(Integer authority_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);

			pstmt.setInt(1, authority_no);			
			
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
	public void update(AuthorityVO authority) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			
			pstmt.setString(1,authority.getAuthority_name());
			pstmt.setInt(2, authority.getAuthority_no());
			
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
	public AuthorityVO findByAuthority_no(Integer authority_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuthorityVO authority = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_AUTHORITY_NO_SQL);
			pstmt.setInt(1,authority_no );
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				authority = new AuthorityVO();
				authority.setAuthority_no(authority_no);
				authority.setAuthority_name(rs.getString("AUTHORITY_NAME"));
				
				
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
		
		return authority;
	}	

	@Override
	public List<AuthorityVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				authorityVO = new AuthorityVO();
				
				authorityVO.setAuthority_no(rs.getInt("AUTHORITY_NO"));
				authorityVO.setAuthority_name(rs.getString("AUTHORITY_NAME"));
				
				
				list.add(authorityVO);
				
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
//			public static void main(String[] args) {
				
				//新增功能
//			AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
//			AuthorityVO authority = new AuthorityVO();
//			authority.setAuthority_name("未知");
//
//			authority = dao.insert(authority);
//			System.out.println(authority.getAuthority_no());
//				

   
//	}
			//System.out.println("---------------------------------------------------");
			//用功能編號查詢
//			AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
//			AuthorityVO list1 = dao.findByAuthority_no(997765);
//			    System.out.print(list1.getAuthority_no()+"\t");
//			    System.out.print(list1.getAuthority_name()+"\t"); 
//		
//		         System.out.println();
			
			//System.out.println("---------------------------------------------------");
		    
		  //修改
//			AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
//			AuthorityVO authouity = new AuthorityVO();
//			
//			authouity.setAuthority_name("刪除");
//			authouity.setAuthority_no(997766);
//			
//			dao.update(authouity);
			
//		}
		//System.out.println("---------------------------------------------------");
			
			//搜尋全部
//			AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
//			List<AuthorityVO> list = dao.getAll();
//			for (AuthorityVO authouityVO : list ) {
//				
//			    System.out.print(authouityVO.getAuthority_no()+"\t");
//			    System.out.print(authouityVO.getAuthority_name()+"\t");
//		
//			    System.out.println();
//			}
//			System.out.println("-------------------------------------------");
			//刪除
//			AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
//			dao.delete(998874);
//			    System.out.println("已刪除");
//			System.out.println("-------------------------------------------");
//		
//  }
}
