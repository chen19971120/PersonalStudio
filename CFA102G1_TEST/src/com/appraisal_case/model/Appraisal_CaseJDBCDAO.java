package com.appraisal_case.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.appraisal_case_images.model.Appraisal_Case_ImagesVO;

public class Appraisal_CaseJDBCDAO implements I_Appraisal_CaseDAO {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" + "rewriteBatchedStatements=true&" + "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.APPRAISAL_CASE(MEM_NO, ACA_ITM_ID, ACL_NO, ACA_ITM_SPEC,  ACA_DATE, ACA_ITM_MODE, ACA_FIRST_P, ACA_RECPT_DATE, ACA_FINAL_P, ACA_SHIPMENT_DATE, ACA_PICKUP_DATE, ACA_PAY, ACA_COMP_DATE, ACA_COD, ACA_ADRS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.APPRAISAL_CASE SET MEM_NO = ?,ACA_ITM_ID = ?, ACL_NO = ?, ACA_ITM_SPEC = ?, ACA_DATE = ?, ACA_ITM_MODE = ?, ACA_FIRST_P = ?, ACA_RECPT_DATE = ?, ACA_FINAL_P = ?, ACA_SHIPMENT_DATE = ?, ACA_PICKUP_DATE = ?, ACA_PAY = ?, ACA_COMP_DATE = ?, ACA_COD = ?, ACA_ADRS = ? WHERE ACA_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.APPRAISAL_CASE WHERE ACA_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.APPRAISAL_CASE WHERE ACA_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.APPRAISAL_CASE";

	@Override
	public Appraisal_CaseVO insert(Appraisal_CaseVO appraisal_CaseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, appraisal_CaseVO.getMem_no());
			pstmt.setString(2, appraisal_CaseVO.getAca_itm_id());
			pstmt.setInt(3, appraisal_CaseVO.getAcl_no());
			pstmt.setString(4, appraisal_CaseVO.getAca_itm_spec());
			pstmt.setTimestamp(5, appraisal_CaseVO.getAca_date());
			pstmt.setString(6, appraisal_CaseVO.getAca_itm_mode());
			pstmt.setInt(7, appraisal_CaseVO.getAca_first_p());
			pstmt.setTimestamp(8, appraisal_CaseVO.getAca_recpt_date());
			pstmt.setInt(9, appraisal_CaseVO.getAca_final_p());
			pstmt.setTimestamp(10, appraisal_CaseVO.getAca_shipment_date());
			pstmt.setTimestamp(11, appraisal_CaseVO.getAca_pickup_date());
			pstmt.setString(12, appraisal_CaseVO.getAca_pay());
			pstmt.setTimestamp(13, appraisal_CaseVO.getAca_comp_date());
			pstmt.setString(14, appraisal_CaseVO.getAca_cod());
			pstmt.setString(15, appraisal_CaseVO.getAca_adrs());

			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				appraisal_CaseVO.setAca_no(rs.getInt(1));
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
		return appraisal_CaseVO;
	}

	@Override
	public void update(Appraisal_CaseVO appraisal_CaseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, appraisal_CaseVO.getMem_no());
			pstmt.setString(2, appraisal_CaseVO.getAca_itm_id());
			pstmt.setInt(3, appraisal_CaseVO.getAcl_no());
			pstmt.setString(4, appraisal_CaseVO.getAca_itm_spec());
			pstmt.setTimestamp(5, appraisal_CaseVO.getAca_date());
			pstmt.setString(6, appraisal_CaseVO.getAca_itm_mode());
			pstmt.setInt(7, appraisal_CaseVO.getAca_first_p());
			pstmt.setTimestamp(8, appraisal_CaseVO.getAca_recpt_date());
			pstmt.setInt(9, appraisal_CaseVO.getAca_final_p());
			pstmt.setTimestamp(10, appraisal_CaseVO.getAca_shipment_date());
			pstmt.setTimestamp(11, appraisal_CaseVO.getAca_pickup_date());
			pstmt.setString(12, appraisal_CaseVO.getAca_pay());
			pstmt.setTimestamp(13, appraisal_CaseVO.getAca_comp_date());
			pstmt.setString(14, appraisal_CaseVO.getAca_cod());
			pstmt.setString(15, appraisal_CaseVO.getAca_adrs());
			pstmt.setInt(16, appraisal_CaseVO.getAca_no());

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
	public void delete(Integer aca_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, aca_no);

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
	public Appraisal_CaseVO findByPK(Integer aca_no) {
		Appraisal_CaseVO appraisal_case = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, aca_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_case = new Appraisal_CaseVO();
				appraisal_case.setAca_no(rs.getInt("ACA_NO"));
				appraisal_case.setMem_no(rs.getInt("MEM_NO"));
				appraisal_case.setAca_itm_id(rs.getString("ACA_ITM_ID"));
				appraisal_case.setAcl_no(rs.getInt("ACL_NO"));
				appraisal_case.setAca_itm_spec(rs.getString("ACA_ITM_SPEC"));
				appraisal_case.setAca_date(rs.getTimestamp("ACA_DATE"));
				appraisal_case.setAca_itm_mode(rs.getString("ACA_ITM_MODE"));
				appraisal_case.setAca_first_p(rs.getInt("ACA_FIRST_P"));
				appraisal_case.setAca_recpt_date(rs.getTimestamp("ACA_RECPT_DATE"));
				appraisal_case.setAca_final_p(rs.getInt("ACA_FINAL_P"));
				appraisal_case.setAca_shipment_date(rs.getTimestamp("ACA_SHIPMENT_DATE"));
				appraisal_case.setAca_pickup_date(rs.getTimestamp("ACA_PICKUP_DATE"));
				appraisal_case.setAca_pay(rs.getString("ACA_PAY"));
				appraisal_case.setAca_comp_date(rs.getTimestamp("ACA_COMP_DATE"));
				appraisal_case.setAca_cod(rs.getString("ACA_COD"));
				appraisal_case.setAca_adrs(rs.getString("ACA_ADRS"));

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
		return appraisal_case;
	}

	@Override
	public List<Appraisal_CaseVO> getAll() {
		List<Appraisal_CaseVO> appraisal_caseList = new ArrayList<Appraisal_CaseVO>();
		Appraisal_CaseVO appraisal_case = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_case = new Appraisal_CaseVO();
				appraisal_case.setAca_no(rs.getInt("ACA_NO"));
				appraisal_case.setMem_no(rs.getInt("MEM_NO"));
				appraisal_case.setAca_itm_id(rs.getString("ACA_ITM_ID"));
				appraisal_case.setAcl_no(rs.getInt("ACL_NO"));
				appraisal_case.setAca_itm_spec(rs.getString("ACA_ITM_SPEC"));
				appraisal_case.setAca_date(rs.getTimestamp("ACA_DATE"));
				appraisal_case.setAca_itm_mode(rs.getString("ACA_ITM_MODE"));
				appraisal_case.setAca_first_p(rs.getInt("ACA_FIRST_P"));
				appraisal_case.setAca_recpt_date(rs.getTimestamp("ACA_RECPT_DATE"));
				appraisal_case.setAca_final_p(rs.getInt("ACA_FINAL_P"));
				appraisal_case.setAca_shipment_date(rs.getTimestamp("ACA_SHIPMENT_DATE"));
				appraisal_case.setAca_pickup_date(rs.getTimestamp("ACA_PICKUP_DATE"));
				appraisal_case.setAca_pay(rs.getString("ACA_PAY"));
				appraisal_case.setAca_comp_date(rs.getTimestamp("ACA_COMP_DATE"));
				appraisal_case.setAca_cod(rs.getString("ACA_COD"));
				appraisal_case.setAca_adrs(rs.getString("ACA_ADRS"));
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
	public List<Appraisal_CaseVO> getAll(Map<String, String[]> map) {
		return null;
	}

//	public static void main(String[] args) {

//		測試insert
//		Appraisal_CaseJDBCDAO dao = new Appraisal_CaseJDBCDAO();
//		Appraisal_CaseVO acvo = new Appraisal_CaseVO();
//		acvo.setMem_no(7001);
//		acvo.setAca_itm_id("電腦");
//		acvo.setAcl_no(1);
//		acvo.setAca_itm_spec("9成新");
//		acvo.setAca_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_itm_mode("完成訂單");
//		acvo.setAca_first_p(7000);
//		acvo.setAca_recpt_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_final_p(6500);
//		acvo.setAca_shipment_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_pickup_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_pay("轉帳");
//		acvo.setAca_comp_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_cod("2022-08-23 23:00:00");
//		acvo.setAca_adrs("2022-08-23 23:00:00");
//		dao.insert(acvo);
//		System.out.println(acvo.getAca_no()+":"+acvo.getAca_itm_mode());
//		測試OK

//		測試update
//		Appraisal_CaseJDBCDAO dao = new Appraisal_CaseJDBCDAO();
//		Appraisal_CaseVO acvo = new Appraisal_CaseVO();
//		acvo.setMem_no(7001);
//		acvo.setAca_itm_id("筆電");
//		acvo.setAcl_no(1);
//		acvo.setAca_itm_spec("9成新");
//		acvo.setAca_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_itm_mode("完成訂單");
//		acvo.setAca_first_p(7000);
//		acvo.setAca_recpt_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_final_p(6500);
//		acvo.setAca_shipment_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_pickup_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_pay("轉帳");
//		acvo.setAca_comp_date(java.sql.Timestamp.valueOf("2022-08-23 23:00:00"));
//		acvo.setAca_cod("2022-08-23 23:00:00");
//		acvo.setAca_adrs("2022-08-23 23:00:00");
//		dao.update(acvo);
//		測試OK

//		測試delete
//		Appraisal_CaseJDBCDAO dao = new Appraisal_CaseJDBCDAO();
//		dao.delete(5);
//		測試OK

//		測試findByPK
//		Appraisal_CaseJDBCDAO dao = new Appraisal_CaseJDBCDAO();
//		Appraisal_CaseVO acvo = dao.findByPK(1);
//		System.out.println(acvo.getAca_no());
//		System.out.println(acvo.getMem_no());
//		System.out.println(acvo.getAca_itm_id());
//		System.out.println(acvo.getAcl_no());
//		System.out.println(acvo.getAca_itm_spec());
//		System.out.println(acvo.getAca_date());
//		System.out.println(acvo.getAca_itm_mode());
//		System.out.println(acvo.getAca_first_p());
//		System.out.println(acvo.getAca_recpt_date());
//		System.out.println(acvo.getAca_final_p());
//		System.out.println(acvo.getAca_shipment_date());
//		System.out.println(acvo.getAca_pickup_date());
//		System.out.println(acvo.getAca_pay());
//		System.out.println(acvo.getAca_comp_date());
//		System.out.println(acvo.getAca_cod());
//		System.out.println(acvo.getAca_adrs());
//		測試OK

//		測試GetAll
//		Appraisal_CaseJDBCDAO dao = new Appraisal_CaseJDBCDAO();
//		List<Appraisal_CaseVO> list = dao.getAll();
//		for (Appraisal_CaseVO acvo : list) {
//			System.out.println(acvo.getAca_no());
//			System.out.println(acvo.getMem_no());
//			System.out.println(acvo.getAca_itm_id());
//			System.out.println(acvo.getAcl_no());
//			System.out.println(acvo.getAca_itm_spec());
//			System.out.println(acvo.getAca_date());
//			System.out.println(acvo.getAca_itm_mode());
//			System.out.println(acvo.getAca_first_p());
//			System.out.println(acvo.getAca_recpt_date());
//			System.out.println(acvo.getAca_final_p());
//			System.out.println(acvo.getAca_shipment_date());
//			System.out.println(acvo.getAca_pickup_date());
//			System.out.println(acvo.getAca_pay());
//			System.out.println(acvo.getAca_comp_date());
//			System.out.println(acvo.getAca_cod());
//			System.out.println(acvo.getAca_adrs());
//		}
//		測試OK
//	}
}