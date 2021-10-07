package com.staff_authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Staff_AuthorityDAO implements I_Staff_AuthorityDAO{
	
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setInt(1, staff_authority.getStaff_no());
			pstmt.setInt(2,staff_authority.getAuthority_no());
			
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
	public Staff_AuthorityVO findByStaff_no(Integer staff_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Staff_AuthorityVO staff_authority = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_STAFF_NO_SQL);
			pstmt.setInt(1,staff_no );
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				staff_authority = new Staff_AuthorityVO();
				staff_authority.setStaff_no(staff_no);
				staff_authority.setAuthority_no(rs.getInt("AUTHORITY_NO"));
				
				
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				staff_authority = new Staff_AuthorityVO();
				
				staff_authority.setStaff_no(rs.getInt("STAFF_NO"));
				staff_authority.setAuthority_no(rs.getInt("AUTHORITY_NO"));
				
				
				list.add(staff_authority);
				
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
