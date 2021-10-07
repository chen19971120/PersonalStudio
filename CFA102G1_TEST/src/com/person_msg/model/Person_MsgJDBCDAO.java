package com.person_msg.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Person_MsgJDBCDAO implements I_Person_MsgDAO {

	
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1,person_msgvo.getMem_no());
			pstmt.setTimestamp(2,person_msgvo.getPm_date());
			pstmt.setString(3,person_msgvo.getPm_content());
			pstmt.setString(4,person_msgvo.getPm_status());	
			pstmt.setInt(5,person_msgvo.getPm_no());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, pm_no);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
	
//驗證功能----------------------------------------------------------------------
//	public static void main(String[] args) {
		
	    //驗證INSERT OK
//		Person_MsgJDBCDAO pmo = new Person_MsgJDBCDAO();
//		Person_MsgVO pmv = new Person_MsgVO();
//		pmv.setMem_no(1);
//		pmv.setPm_date(new Timestamp(System.currentTimeMillis()));
//		pmv.setPm_content("您的付帳成功");
//		pmv.setPm_status("未讀");			
//		pmv=pmo.insert(pmv);
//		System.out.println(pmv.getPm_no());
		
		//驗證UPDATE OK
//		Person_MsgJDBCDAO pmo = new Person_MsgJDBCDAO();
//		Person_MsgVO pmv = new Person_MsgVO();
//		pmv.setMem_no(2);
//		pmv.setPm_date(new Timestamp(System.currentTimeMillis()));
//		pmv.setPm_content("您的付帳成功");
//		pmv.setPm_status("已讀");
//		pmv.setPm_no(4);
//		pmo.update(pmv);
//		System.out.println("更新成功");
		
		//驗證DELETE OK
//		Person_MsgJDBCDAO pmo = new Person_MsgJDBCDAO();
//		pmo.delete(3);
		
		//驗證findByPK OK
//		Person_MsgJDBCDAO pmo = new Person_MsgJDBCDAO();
//		Person_MsgVO personmsgvo = pmo.findByPK(1);			
//		System.out.println("訊息編號 :"+ personmsgvo.getPm_no());
//		System.out.println("會員編號 :"+ personmsgvo.getMem_no());
//		System.out.println("發送日期 :"+ personmsgvo.getPm_date());
//		System.out.println("訊息內容 :"+ personmsgvo.getPm_content());
//		System.out.println("訊息狀態 :"+ personmsgvo.getPm_status());
//		System.out.println("------------------------------------");
		
		//驗證findByMEM_NO OK
//		Person_MsgJDBCDAO pmo = new Person_MsgJDBCDAO();
//		List<Person_MsgVO> list = pmo.findByMEM_NO(2);
//		for(Person_MsgVO personmsgvo : list) {
//			
//		System.out.println("訊息編號 :"+ personmsgvo.getPm_no());
//		System.out.println("會員編號 :"+ personmsgvo.getMem_no());
//		System.out.println("發送日期 :"+ personmsgvo.getPm_date());
//		System.out.println("訊息內容 :"+ personmsgvo.getPm_content());
//		System.out.println("訊息狀態 :"+ personmsgvo.getPm_status());
//		System.out.println("------------------------------------");
//		}		
		
		//驗證getAll OK
//		Person_MsgJDBCDAO pmo = new Person_MsgJDBCDAO();
//		List<Person_MsgVO> list = pmo.getAll();
//		for(Person_MsgVO personmsgvo : list) {
//			System.out.println("訊息編號 :"+ personmsgvo.getPm_no());
//			System.out.println("會員編號 :"+ personmsgvo.getMem_no());
//			System.out.println("發送日期 :"+ personmsgvo.getPm_date());
//			System.out.println("訊息內容 :"+ personmsgvo.getPm_content());
//			System.out.println("訊息狀態 :"+ personmsgvo.getPm_status());
//		System.out.println("------------------------------------");
//		}
	}
//驗證功能----------------------------------------------------------------------
//}
