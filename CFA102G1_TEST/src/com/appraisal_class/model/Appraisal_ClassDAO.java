package com.appraisal_class.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.appraisal_case.model.Appraisal_CaseVO;

public class Appraisal_ClassDAO implements I_Appraisal_ClassDAO {
//	建立連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CFA102G1.APPRAISAL_CLASS(ACL_ID) VALUES(?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.APPRAISAL_CLASS SET ACL_ID = ? WHERE ACL_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.APPRAISAL_CLASS WHERE ACL_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.APPRAISAL_CLASS WHERE ACL_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.APPRAISAL_CLASS";
	private static final String GET_CASE_BYACL_NO_ = "SELECT * FROM CFA102G1.APPRAISAL_CASE WHERE ACL_NO = ? ORDER BY ACA_NO";

	@Override
	public Appraisal_ClassVO insert(Appraisal_ClassVO appraisal_ClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, appraisal_ClassVO.getAcl_id());

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				appraisal_ClassVO.setAcl_no(rs.getInt(1));
			}
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, appraisal_ClassVO.getAcl_id());
			pstmt.setInt(2, appraisal_ClassVO.getAcl_no());

			pstmt.executeUpdate();
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, acl_no);

			pstmt.executeUpdate();
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, acl_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_class = new Appraisal_ClassVO();
				appraisal_class.setAcl_no(rs.getInt("ACL_NO"));
				appraisal_class.setAcl_id(rs.getString("ACL_ID"));

			}
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_case = new Appraisal_ClassVO();
				appraisal_case.setAcl_no(rs.getInt("ACL_NO"));
				appraisal_case.setAcl_id(rs.getString("ACL_ID"));
				appraisal_caseList.add(appraisal_case);

			}
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		Set<Appraisal_CaseVO> set = new LinkedHashSet<Appraisal_CaseVO>();
		Appraisal_CaseVO appraisalCaseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CASE_BYACL_NO_);
			pstmt.setInt(1, acl_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				appraisalCaseVO = new Appraisal_CaseVO();
				appraisalCaseVO.setAca_no(rs.getInt("ACA_NO"));
				appraisalCaseVO.setMem_no(rs.getInt("MEM_NO"));
				appraisalCaseVO.setAca_itm_id(rs.getString("ACA_ITM_ID"));
				appraisalCaseVO.setAcl_no(rs.getInt("ACL_NO"));
				appraisalCaseVO.setAca_itm_spec(rs.getString("ACA_ITM_SPEC"));
				appraisalCaseVO.setAca_date(rs.getTimestamp("ACA_DATE"));
				appraisalCaseVO.setAca_itm_mode(rs.getString("ACA_ITM_MODE"));
				appraisalCaseVO.setAca_first_p(rs.getInt("ACA_FIRST_P"));
				appraisalCaseVO.setAca_recpt_date(rs.getTimestamp("ACA_RECPT_DATE"));
				appraisalCaseVO.setAca_final_p(rs.getInt("ACA_FINAL_P"));
				appraisalCaseVO.setAca_shipment_date(rs.getTimestamp("ACA_SHIPMENT_DATE"));
				appraisalCaseVO.setAca_pickup_date(rs.getTimestamp("ACA_PICKUP_DATE"));
				appraisalCaseVO.setAca_pay(rs.getString("ACA_PAY"));
				appraisalCaseVO.setAca_comp_date(rs.getTimestamp("ACA_COMP_DATE"));
				appraisalCaseVO.setAca_cod(rs.getString("ACA_COD"));
				appraisalCaseVO.setAca_adrs(rs.getString("ACA_ADRS"));
				set.add(appraisalCaseVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}
}