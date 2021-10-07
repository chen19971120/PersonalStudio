package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class AuthorityDAO implements I_AuthorityDAO {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
	
	
	

	@Override
	public AuthorityVO insert(AuthorityVO authority) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,authority.getAuthority_name());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				authority.setAuthority_no(rs.getInt(1));
			}
			
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_SQL);

			pstmt.setInt(1, authority_no);			
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			
			pstmt.setString(1,authority.getAuthority_name());
			pstmt.setInt(2, authority.getAuthority_no());
			
			pstmt.executeUpdate();
			
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_AUTHORITY_NO_SQL);
			pstmt.setInt(1,authority_no );
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				authority = new AuthorityVO();
				authority.setAuthority_no(authority_no);
				authority.setAuthority_name(rs.getString("AUTHORITY_NAME"));
				
				
			}
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				authorityVO = new AuthorityVO();
				
				authorityVO.setAuthority_no(rs.getInt("AUTHORITY_NO"));
				authorityVO.setAuthority_name(rs.getString("AUTHORITY_NAME"));
				
				
				list.add(authorityVO);
				
			}
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

}
