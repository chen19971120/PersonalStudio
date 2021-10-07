package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements I_MemberDAO{
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
			"INSERT INTO CFA102G1.MEMBER (MEM_NAME,MEM_GENDER,MEM_PHONE,MEM_EMAIL,MEM_ADDRESS,MEM_ACCOUNT,MEM_PASSWORD,MEM_BIRTH,MEM_STA)VALUES(?,?,?,?,?,?,?,?,?)";    
	private static final String UPDATE_SQL = 
			"UPDATE CFA102G1.MEMBER SET  MEM_NAME = ?, MEM_GENDER = ?, MEM_PHONE = ?, MEM_EMAIL = ?, MEM_ADDRESS = ?, MEM_ACCOUNT = ?, MEM_PASSWORD = ?, MEM_BIRTH = ?,MEM_STA = ? WHERE MEM_NO = ?";
	private static final String DELETE_SQL = 
			"DELETE FROM CFA102G1.MEMBER where MEM_NO = ?";
	private static final String FIND_BY_MEM_NO_SQL = 
			"SELECT * FROM CFA102G1.MEMBER WHERE MEM_NO = ?";
	private static final String FIND_BY_MEM_NAME_SQL =
			"SELECT MEM_NO,MEM_NAME,MEM_GENDER,MEM_PHONE,MEM_EMAIL,MEM_ADDRESS,MEM_ACCOUNT,MEM_PASSWORD,MEM_BIRTH,MEM_STA FROM CFA102G1.MEMBER WHERE UPPER(MEM_NAME) LIKE UPPER(?)";
	private static final String FIND_BY_MEM_PHONE_SQL =
			"SELECT MEM_NO,MEM_NAME,MEM_GENDER,MEM_PHONE,MEM_EMAIL,MEM_ADDRESS,MEM_ACCOUNT,MEM_PASSWORD,MEM_BIRTH,MEM_STA FROM CFA102G1.MEMBER WHERE UPPER(MEM_PHONE) LIKE UPPER(?)";
	private static final String GET_ALL =
			"SELECT MEM_NO,MEM_NAME,MEM_GENDER,MEM_PHONE,MEM_EMAIL,MEM_ADDRESS,MEM_ACCOUNT,MEM_PASSWORD,MEM_BIRTH,MEM_STA FROM CFA102G1.MEMBER";
	private static final String GET_ONE_LOGIN = 
			"SELECT MEM_NO,MEM_NAME,MEM_GENDER,MEM_PHONE,MEM_EMAIL,MEM_ADDRESS,MEM_ACCOUNT,MEM_PASSWORD,MEM_BIRTH,MEM_STA FROM MEMBER WHERE MEM_ACCOUNT = ? AND MEM_PASSWORD = ?";		
	private static final String GET_ONE_FORGOT = 
			"SELECT MEM_NO,MEM_NAME,MEM_GENDER,MEM_PHONE,MEM_EMAIL,MEM_ADDRESS,MEM_ACCOUNT,MEM_PASSWORD,MEM_BIRTH,MEM_STA FROM MEMBER WHERE MEM_ACCOUNT = ? AND MEM_EMAIL = ?";
	private static final String REGISTER_MEMBER = 
			"INSERT INTO CFA102G1.MEMBER (MEM_NAME,MEM_GENDER,MEM_PHONE,MEM_EMAIL,MEM_ADDRESS,MEM_ACCOUNT,MEM_PASSWORD,MEM_BIRTH,MEM_STA) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String FIND_BY_EMAIL = 
			"SELECT * FROM CFA102G1.MEMBER WHERE MEM_EMAIL=?";
	private static final String UPDATE_PWD_BY_EMAIL = 
			"UPDATE CFA102G1.MEMBER SET MEM_PASSWORD=? WHERE MEM_EMAIL=?";
	private static final String LOGIN_STMT = 
				"SELECT * FROM CFA102G1.MEMBER WHERE MEM_ACCOUNT=? AND MEM_PASSWORD";
	private static final String UPDATE_STA = 
			"UPDATE CFA102G1.MEMBER SET  MEM_STA = ? WHERE MEM_NO = ?";
	
	
	@Override
	public MemberVO insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,memberVO.getMem_name());
			pstmt.setString(2, memberVO.getMem_gender());
			pstmt.setString(3, memberVO.getMem_phone());
			pstmt.setString(4, memberVO.getMem_email());
			pstmt.setString(5, memberVO.getMem_address());
			pstmt.setString(6, memberVO.getMem_account());
			pstmt.setString(7, memberVO.getMem_password());
			pstmt.setDate(8, memberVO.getMem_birth());
			pstmt.setString(9, memberVO.getMem_sta());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				memberVO.setMem_no(rs.getInt(1));
			}
					}catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());}finally {
			if(con !=null) {
				try {
					con.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return memberVO;
	}

	@Override
	public void update(MemberVO memberVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			
			pstmt.setString(1,memberVO.getMem_name());
			pstmt.setString(2, memberVO.getMem_gender());
			pstmt.setString(3, memberVO.getMem_phone());
			pstmt.setString(4, memberVO.getMem_email());
			pstmt.setString(5, memberVO.getMem_address());
			pstmt.setString(6, memberVO.getMem_account());
			pstmt.setString(7, memberVO.getMem_password());
			pstmt.setDate(8, memberVO.getMem_birth());
			pstmt.setString(9, memberVO.getMem_sta());
			pstmt.setInt(10, memberVO.getMem_no());
			
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
	public void delete(Integer mem_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_SQL);

			pstmt.setInt(1, mem_no);			
			
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
	public MemberVO findByMem_no(Integer mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM_NO_SQL);
			pstmt.setInt(1,mem_no );
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_name(rs.getString("MEM_NAME"));
				memberVO.setMem_gender(rs.getString("MEM_GENDER"));
				memberVO.setMem_phone(rs.getString("MEM_PHONE"));
				memberVO.setMem_email(rs.getString("MEM_EMAIL"));
				memberVO.setMem_address(rs.getString("MEM_ADDRESS"));
				memberVO.setMem_account(rs.getString("MEM_ACCOUNT"));
				memberVO.setMem_password(rs.getString("MEM_PASSWORD"));
				memberVO.setMem_birth(rs.getDate("MEM_BIRTH"));
				memberVO.setMem_sta(rs.getString("MEM_STA"));
				
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
		
		return memberVO;
	}	
	
		

	@Override
	public List<MemberVO> findByMem_name(String mem_name) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM_NAME_SQL);
			pstmt.setString(1,mem_name);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				member = new MemberVO();
				member.setMem_no(rs.getInt("MEM_NO"));
				member.setMem_name(rs.getString("MEM_NAME"));
				member.setMem_gender(rs.getString("MEM_GENDER"));
				member.setMem_phone(rs.getString("MEM_PHONE"));
				member.setMem_email(rs.getString("MEM_EMAIL"));
				member.setMem_address(rs.getString("MEM_ADDRESS"));
				member.setMem_account(rs.getString("MEM_ACCOUNT"));
				member.setMem_password(rs.getString("MEM_PASSWORD"));
				member.setMem_birth(rs.getDate("MEM_BIRTH"));
				member.setMem_sta(rs.getString("MEM_STA"));
				list.add(member);
				
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

	@Override
	public List<MemberVO> findByMem_phone(String mem_phone) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM_PHONE_SQL);
			pstmt.setString(1,mem_phone);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				member = new MemberVO();
				member.setMem_no(rs.getInt("MEM_NO"));
				member.setMem_name(rs.getString("MEM_NAME"));
				member.setMem_gender(rs.getString("MEM_GENDER"));
				member.setMem_phone(rs.getString("MEM_PHONE"));
				member.setMem_email(rs.getString("MEM_EMAIL"));
				member.setMem_address(rs.getString("MEM_ADDRESS"));
				member.setMem_account(rs.getString("MEM_ACCOUNT"));
				member.setMem_password(rs.getString("MEM_PASSWORD"));
				member.setMem_birth(rs.getDate("MEM_BIRTH"));
				member.setMem_sta(rs.getString("MEM_STA"));
				list.add(member);
				
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

	@Override
	public List<MemberVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberVO = new MemberVO();
				
				memberVO.setMem_no(rs.getInt("MEM_NO"));
				memberVO.setMem_name(rs.getString("MEM_NAME"));
				memberVO.setMem_gender(rs.getString("MEM_GENDER"));
				memberVO.setMem_phone(rs.getString("MEM_PHONE"));
				memberVO.setMem_email(rs.getString("MEM_EMAIL"));
				memberVO.setMem_address(rs.getString("MEM_ADDRESS"));
				memberVO.setMem_account(rs.getString("MEM_ACCOUNT"));
				memberVO.setMem_password(rs.getString("MEM_PASSWORD"));
				memberVO.setMem_birth(rs.getDate("MEM_BIRTH"));
				memberVO.setMem_sta(rs.getString("MEM_STA"));
				
				list.add(memberVO);
				
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

	@Override
	public MemberVO findByMem_account(String mem_account, String mem_password) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_LOGIN);
			pstmt.setString(1, mem_account);
			pstmt.setString(2, mem_password);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("MEM_NO"));
				memberVO.setMem_name(rs.getString("MEM_NAME"));
				memberVO.setMem_gender(rs.getString("MEM_GENDER"));
				memberVO.setMem_phone(rs.getString("MEM_PHONE"));
				memberVO.setMem_email(rs.getString("MEM_EMAIL"));
				memberVO.setMem_address(rs.getString("MEM_ADDRESS"));
				memberVO.setMem_account(rs.getString("MEM_ACCOUNT"));
				memberVO.setMem_password(rs.getString("MEM_PASSWORD"));
				memberVO.setMem_birth(rs.getDate("MEM_BIRTH"));
				memberVO.setMem_sta(rs.getString("MEM_STA"));
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
		
		return memberVO;
	}	
	
	@Override
	public MemberVO findByMemAccountMail(String mem_account, String mem_email) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FORGOT);
			pstmt.setString(1, mem_account);
			pstmt.setString(2, mem_email);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("MEM_NO"));
				memberVO.setMem_name(rs.getString("MEM_NAME"));
				memberVO.setMem_gender(rs.getString("MEM_GENDER"));
				memberVO.setMem_phone(rs.getString("MEM_PHONE"));
				memberVO.setMem_email(rs.getString("MEM_EMAIL"));
				memberVO.setMem_address(rs.getString("MEM_ADDRESS"));
				memberVO.setMem_account(rs.getString("MEM_ACCOUNT"));
				memberVO.setMem_password(rs.getString("MEM_PASSWORD"));
				memberVO.setMem_birth(rs.getDate("MEM_BIRTH"));
				memberVO.setMem_sta(rs.getString("MEM_STA"));
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
		
		return memberVO;
	}

	@Override
	public MemberVO register_Member(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(REGISTER_MEMBER, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, memberVO.getMem_name());
			pstmt.setString(2, memberVO.getMem_gender());
			pstmt.setString(3, memberVO.getMem_phone());
			pstmt.setString(4, memberVO.getMem_email());
			pstmt.setString(5, memberVO.getMem_address());
			pstmt.setString(6, memberVO.getMem_account());
			pstmt.setString(7, memberVO.getMem_password());
			pstmt.setDate(8, memberVO.getMem_birth());
			pstmt.setString(9, memberVO.getMem_sta());
		

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				memberVO.setMem_no(rs.getInt(1));
			}
					}catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());}finally {
			if(con !=null) {
				try {
					con.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return memberVO;
	}

	@Override
	public void updatePwd(String mem_email, String mem_password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PWD_BY_EMAIL);
			
			pstmt.setString(1, mem_password);
			pstmt.setString(2, mem_email);
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
		

	@Override
	public MemberVO findByEmail(String mem_email) {
MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_EMAIL);
			 pstmt.setString(1, mem_email);
			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 memberVO = new MemberVO();
				 memberVO.setMem_no(rs.getInt("MEM_NO"));
				 memberVO.setMem_name(rs.getString("MEM_NAME"));
			     memberVO.setMem_gender(rs.getString("MEM_GENDER"));
				 memberVO.setMem_phone(rs.getString("MEM_PHONE"));
				 memberVO.setMem_email(rs.getString("MEM_EMAIL"));
				 memberVO.setMem_address(rs.getString("MEM_ADDRESS"));
				 memberVO.setMem_account(rs.getString("MEM_ACCOUNT"));
			     memberVO.setMem_password(rs.getString("MEM_PASSWORD"));
				 memberVO.setMem_birth(rs.getDate("MEM_BIRTH"));
				 memberVO.setMem_sta(rs.getString("MEM_STA"));
			 }
		
		}catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memberVO;
		
	}

	@Override
	public MemberVO loginCheck(String mem_account) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN_STMT);
			
			pstmt.setString(1, mem_account);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				 memberVO.setMem_no(rs.getInt("MEM_NO"));
				 memberVO.setMem_name(rs.getString("MEM_NAME"));
			     memberVO.setMem_gender(rs.getString("MEM_GENDER"));
				 memberVO.setMem_phone(rs.getString("MEM_PHONE"));
				 memberVO.setMem_email(rs.getString("MEM_EMAIL"));
				 memberVO.setMem_address(rs.getString("MEM_ADDRESS"));
				 memberVO.setMem_account(rs.getString("MEM_ACCOUNT"));
			     memberVO.setMem_password(rs.getString("MEM_PASSWORD"));
				 memberVO.setMem_birth(rs.getDate("MEM_BIRTH"));
				 memberVO.setMem_sta(rs.getString("MEM_STA"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memberVO;
	}

	@Override
	public void updateMemberStatus(Integer mem_no, String mem_sta) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STA);
			

			pstmt.setString(1, mem_sta);
			pstmt.setInt(2, mem_no);
			
			pstmt.executeUpdate();

			// Handle any SQL errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
}
