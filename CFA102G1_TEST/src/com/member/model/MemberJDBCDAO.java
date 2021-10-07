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


public class MemberJDBCDAO implements I_MemberDAO{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102G1?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
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
			"INSERT INTO CFA102G1.MEMBER (MEM_NAME,MEM_GENDER,MEM_PHONE,MEM_EMAIL,MEM_ADDRESS,MEM_ACCOUNT,MEM_PASSWORD,MEM_BIRTH,MEM_STA) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String FIND_BY_EMAIL = 
			"SELECT * FROM CFA102G1.MEMBER WHERE MEM_EMAIL=?";
	private static final String UPDATE_PWD_BY_EMAIL = 
			"UPDATE CFA102G1.MEMBER SET MEM_PASSWORD=? WHERE MEM_EMAIL=?";
	private static final String LOGIN_STMT = 
				"SELECT * FROM CFA102G1.MEMBER WHERE MEM_ACCOUNT=?";
	private static final String UPDATE_STA = 
			"UPDATE CFA102G1.MEMBER SET  MEM_STA = ? WHERE MEM_NO = ?";
	static {
		try {
			Class.forName(DRIVER);
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public MemberVO insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return memberVO;
	}

	@Override
	public void update(MemberVO memberVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	public void delete(Integer mem_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);

			pstmt.setInt(1, mem_no);			
			
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
	public MemberVO findByMem_no(Integer mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEM_NO_SQL);
			pstmt.setInt(1,mem_no );
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				member = new MemberVO();
				member.setMem_no(mem_no);
				member.setMem_name(rs.getString("MEM_NAME"));
				member.setMem_gender(rs.getString("MEM_GENDER"));
				member.setMem_phone(rs.getString("MEM_PHONE"));
				member.setMem_email(rs.getString("MEM_EMAIL"));
				member.setMem_address(rs.getString("MEM_ADDRESS"));
				member.setMem_account(rs.getString("MEM_ACCOUNT"));
				member.setMem_password(rs.getString("MEM_PASSWORD"));
				member.setMem_birth(rs.getDate("MEM_BIRTH"));
				member.setMem_sta(rs.getString("MEM_STA"));
				
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
		
		return member;
	}	
	
		

	@Override
	public List<MemberVO> findByMem_name(String mem_name) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
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

	@Override
	public List<MemberVO> findByMem_phone(String mem_phone) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
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

	@Override
	public List<MemberVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
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

	@Override
	public MemberVO findByMem_account(String mem_account, String mem_password) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
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
	
		return memberVO;
	}

	@Override
	public MemberVO findByMemAccountMail(String mem_account, String mem_email) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = DriverManager.getConnection(URL,USER,PASSWORD);
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
	
		return memberVO;
	}

	@Override
	public MemberVO register_Member(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

			// Handle any SQL errors
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				memberVO.setMem_no(rs.getInt(1));
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
		return memberVO;
	}
	//改變密碼
	public void updatePwd(String mem_email,String mem_password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	//會員驗證信
	public MemberVO findByEmail(String mem_email) {
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	//檢查帳號狀態
	public MemberVO loginCheck(String mem_account) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	public void updateMemberStatus(Integer mem_no, String mem_sta) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

	

//測試開始
//	public static void main(String[] args) {
		
		//新增會員資料
//		MemberJDBCDAO dao = new MemberJDBCDAO();
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMem_name("ima55555555i");
//		memberVO.setMem_gender("男");
//		memberVO.setMem_phone("0985612236");
//		memberVO.setMem_email("9977885@gmail.com");
//		memberVO.setMem_address("台北市士林區中山路一段7號");
//		memberVO.setMem_account("000555tyy5566");
//		memberVO.setMem_password("r564815");
//		memberVO.setMem_birth("2004.01.02");
//		memberVO.setMem_sta("正常");
//		memberVO = dao.insert(member);
//		System.out.println(memberVO.getMem_no());
//		
		
		//System.out.println("---------------------------------------------------");
		//用會員編號查詢
//		MemberJDBCDAO dao = new MemberJDBCDAO();
//		MemberVO list1 = dao.findByMem_no(3);
//		    System.out.print(list1.getMem_no()+"\t");
//		    System.out.print(list1.getMem_name()+"\t");
//		    System.out.print(list1.getMem_gender()+"\t");
//		    System.out.print(list1.getMem_phone()+"\t");
//		    System.out.print(list1.getMem_email()+"\t");
//		    System.out.print(list1.getMem_address()+"\t");
//		    System.out.print(list1.getMem_account()+"\t");
//		    System.out.print(list1.getMem_password()+"\t");
//		  	System.out.print(list1.getMem_birth()+"\t");
//		  	System.out.print(list1.getMem_sta()+"\t");
//		  	System.out.println();
	
		//System.out.println("---------------------------------------------------");
		//用名字查詢
//		MemberJDBCDAO dao = new MemberJDBCDAO();
//		List<MemberVO> nameList = dao.findByMem_name("Akito");
//		for (MemberVO memberVOName : nameList) {
//			
//			System.out.print(memberVOName.getMem_no()+"\t");
//			System.out.print(memberVOName.getMem_name()+"\t");
//			System.out.print(memberVOName.getMem_gender()+"\t");
//			System.out.print(memberVOName.getMem_phone()+"\t");
//			System.out.print(memberVOName.getMem_email()+"\t");
//			System.out.print(memberVOName.getMem_address()+"\t");
//			System.out.print(memberVOName.getMem_account()+"\t");
//			System.out.print(memberVOName.getMem_password()+"\t");
//			System.out.print(memberVOName.getMem_birth()+"\t");
//			System.out.print(memberVOName.getMem_sta()+"\t");
//			System.out.println();
//		}
		//System.out.println("---------------------------------------------------");
		//搜尋全部
//		MemberJDBCDAO dao = new MemberJDBCDAO();
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO memberVO : list ) {			
//		    System.out.print(memberVO.getMem_no()+"\t");
//	     	System.out.print(memberVO.getMem_name()+"\t");
//		    System.out.print(memberVO.getMem_gender()+"\t");
//		    System.out.print(memberVO.getMem_phone()+"\t");
//		    System.out.print(memberVO.getMem_email()+"\t");
//		    System.out.print(memberVO.getMem_address()+"\t");
//		    System.out.print(memberVO.getMem_account()+"\t");
//		    System.out.print(memberVO.getMem_password()+"\t");
//		    System.out.print(memberVO.getMem_birth()+"\t");
//		    System.out.print(memberVO.getMem_sta()+"\t");
//		    System.out.println();
//		}
//		System.out.println("-------------------------------------------");
		
		//用手機號麻搜尋
//		MemberJDBCDAO dao = new MemberJDBCDAO();
//		List<MemberVO> list = dao.findByMem_phone(988648216);
//		for (MemberVO memberphone : list) {
//			System.out.print(memberphone.getMem_no()+"\t");
//			System.out.print(memberphone.getMem_name()+"\t");
//			System.out.print(memberphone.getMem_gender()+"\t");
//			System.out.print(memberphone.getMem_phone()+"\t");
//			System.out.print(memberphone.getMem_email()+"\t");
//			System.out.print(memberphone.getMem_address()+"\t");
//			System.out.print(memberphone.getMem_account()+"\t");
//			System.out.print(memberphone.getMem_password()+"\t");
//			System.out.print(memberphone.getMem_birth()+"\t");
//			System.out.print(memberphone.getMem_sta()+"\t");
//			System.out.println();
//		}
		//System.out.println("---------------------------------------------------");
		
		//修改
//		MemberVO memberVO = new MemberVO();
//		MemberJDBCDAO dao = new MemberJDBCDAO();
//		memberVO.setMem_name("kaido");
//		memberVO.setMem_gender("男");
//		memberVO.setMem_phone(978991553);	
//		memberVO.setMem_email("mmmbb223@gmail.com");
//		memberVO.setMem_address("桃園市新屋區中正路一段87號");
//		memberVO.setMem_account("t05sdf5566");
//		memberVO.setMem_password("rasdfs38165");
//		memberVO.setMem_birth("1999.04.29");
//		memberVO.setMem_sta("停權");
//		memberVO.setMem_no(4);
//		
//		dao.update(memberVO);
//		
//	}
	//System.out.println("---------------------------------------------------");
//		MemberJDBCDAO dao = new MemberJDBCDAO();
//		dao.delete(4);
//		   System.out.println("已刪除");
//		   System.out.println("-------------------------------------------");
	// login
//	MemberJDBCDAO dao = new MemberJDBCDAO();
//	dao.findByMem_account("ves1122", "no1122");
//	System.out.println("----------------------------------------------------");
//	
//	
//	}
}