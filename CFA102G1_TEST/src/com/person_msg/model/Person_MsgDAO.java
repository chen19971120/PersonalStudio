package com.person_msg.model;

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

public class Person_MsgDAO implements I_Person_MsgDAO{
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.PERSON_MESSAGE(MEM_NO,PM_DATE,PM_CONTENT,PM_STATUS)"
			+ " VALUES(?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.PERSON_MESSAGE SET MEM_NO=?,PM_DATE=?,PM_CONTENT=?,"
			+ "PM_STATUS=?  WHERE PM_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.PERSON_MESSAGE WHERE PM_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.PERSON_MESSAGE WHERE PM_NO = ?";
	private static final String FIND_BY_MEM_NO = "SELECT * FROM CFA102G1.PERSON_MESSAGE WHERE MEM_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.PERSON_MESSAGE";
	
	@Override
	public Person_MsgVO insert(Person_MsgVO person_msgvo) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,person_msgvo.getMem_no());
			pstmt.setTimestamp(2,person_msgvo.getPm_date());
			pstmt.setString(3,person_msgvo.getPm_content());
			pstmt.setString(4,person_msgvo.getPm_status());	
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				person_msgvo.setPm_no(rs.getInt(1));
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
						e.printStackTrace();
				}
			}
		}
		return person_msgvo;
	}

	@Override
	public void update(Person_MsgVO person_msgvo) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1,person_msgvo.getMem_no());
			pstmt.setTimestamp(2,person_msgvo.getPm_date());
			pstmt.setString(3,person_msgvo.getPm_content());
			pstmt.setString(4,person_msgvo.getPm_status());	
			pstmt.setInt(5,person_msgvo.getPm_no());
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void delete(Integer pm_no) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, pm_no);
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public Person_MsgVO findByPK(Integer pm_no) {
		Person_MsgVO personmsgvo = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, pm_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				personmsgvo = new Person_MsgVO();
				personmsgvo.setPm_no(rs.getInt("PM_NO"));
				personmsgvo.setMem_no(rs.getInt("MEM_NO"));
				personmsgvo.setPm_date(rs.getTimestamp("PM_DATE"));
				personmsgvo.setPm_content(rs.getString("PM_CONTENT"));
				personmsgvo.setPm_status(rs.getString("PM_STATUS"));
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return personmsgvo;
	}

	@Override
	public List<Person_MsgVO> findByMEM_NO(Integer mem_no) {
		Person_MsgVO personmsgvo = null;
		List<Person_MsgVO> listPerson_MsgVO = new ArrayList<Person_MsgVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				personmsgvo = new Person_MsgVO();
				personmsgvo.setPm_no(rs.getInt("PM_NO"));
				personmsgvo.setMem_no(rs.getInt("MEM_NO"));
				personmsgvo.setPm_date(rs.getTimestamp("PM_DATE"));
				personmsgvo.setPm_content(rs.getString("PM_CONTENT"));
				personmsgvo.setPm_status(rs.getString("PM_STATUS"));
				listPerson_MsgVO.add(personmsgvo);
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listPerson_MsgVO;
	}

	@Override
	public List<Person_MsgVO> getAll() {
		Person_MsgVO personmsgvo = null;
		List<Person_MsgVO> listPerson_MsgVO = new ArrayList<Person_MsgVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				personmsgvo = new Person_MsgVO();
				personmsgvo.setPm_no(rs.getInt("PM_NO"));
				personmsgvo.setMem_no(rs.getInt("MEM_NO"));
				personmsgvo.setPm_date(rs.getTimestamp("PM_DATE"));
				personmsgvo.setPm_content(rs.getString("PM_CONTENT"));
				personmsgvo.setPm_status(rs.getString("PM_STATUS"));
				listPerson_MsgVO.add(personmsgvo);
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listPerson_MsgVO;
	}
}
