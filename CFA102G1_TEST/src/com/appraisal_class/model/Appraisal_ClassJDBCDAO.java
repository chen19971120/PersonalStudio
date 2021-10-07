package com.appraisal_class.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.appraisal_case.model.Appraisal_CaseVO;

public class Appraisal_ClassJDBCDAO implements I_Appraisal_ClassDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" + "rewriteBatchedStatements=true&" + "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.APPRAISAL_CLASS(ACL_ID) VALUES(?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.APPRAISAL_CLASS SET ACL_ID = ? WHERE ACL_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.APPRAISAL_CLASS WHERE ACL_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.APPRAISAL_CLASS WHERE ACL_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.APPRAISAL_CLASS";
	
	@Override
	public Appraisal_ClassVO insert(Appraisal_ClassVO appraisal_ClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,appraisal_ClassVO.getAcl_id()); 

			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				appraisal_ClassVO.setAcl_no(rs.getInt(1));
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
		return appraisal_ClassVO;
	}

	@Override
	public void update(Appraisal_ClassVO appraisal_ClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, appraisal_ClassVO.getAcl_id());
			pstmt.setInt(2, appraisal_ClassVO.getAcl_no());

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
	public void delete(Integer acl_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, acl_no);

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
	public Appraisal_ClassVO findByPK(Integer acl_no) {
		Appraisal_ClassVO appraisal_class = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, acl_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_class = new Appraisal_ClassVO();
				appraisal_class.setAcl_no(rs.getInt("ACL_NO"));
				appraisal_class.setAcl_id(rs.getString("ACL_ID"));

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
		return appraisal_class;
	}

	@Override
	public List<Appraisal_ClassVO> getAll() {
		List<Appraisal_ClassVO> appraisal_caseList = new ArrayList<Appraisal_ClassVO>();
		Appraisal_ClassVO appraisal_case = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_case = new Appraisal_ClassVO();
				appraisal_case.setAcl_no(rs.getInt("ACL_NO"));
				appraisal_case.setAcl_id(rs.getString("ACL_ID"));
				appraisal_caseList.add(appraisal_case);

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
		return appraisal_caseList;
	}

	@Override
	public Set<Appraisal_CaseVO> getA_CaseByA_Class(Integer acl_no) {
		// TODO Auto-generated method stub
		return null;
	}

//	public static void main(String[] args) {
		
//		測試insert
//		Appraisal_ClassJDBCDAO dao = new Appraisal_ClassJDBCDAO();
//		Appraisal_ClassVO acvo = new Appraisal_ClassVO();
//		acvo.setAcl_id("電腦");
//		dao.insert(acvo);
//		System.out.println(acvo.getAcl_no()+":"+acvo.getAcl_id());
//		測試OK

//		測試update
//		Appraisal_ClassJDBCDAO dao = new Appraisal_ClassJDBCDAO();
//		Appraisal_ClassVO acvo = new Appraisal_ClassVO();
//		acvo.setAcl_id("手機");
//		acvo.setAcl_no(5);
//		dao.update(acvo);
//		測試OK
		
//		測試delete
//		Appraisal_ClassJDBCDAO dao = new Appraisal_ClassJDBCDAO();
//		dao.delete(5);
//		測試OK
		
//		測試findByPK
//		Appraisal_ClassJDBCDAO dao = new Appraisal_ClassJDBCDAO();
//		Appraisal_ClassVO acvo = dao.findByPK(2);
//		System.out.println(acvo.getAcl_no());
//		System.out.println(acvo.getAcl_id());
//		測試OK
		
//		測試GetAll
//		Appraisal_ClassJDBCDAO dao = new Appraisal_ClassJDBCDAO();
//		List<Appraisal_ClassVO> list = dao.getAll();
//		for(Appraisal_ClassVO acvo:list) {
//			System.out.println(acvo.getAcl_no());
//			System.out.println(acvo.getAcl_id());
//		}
//		測試OK
//	}
}