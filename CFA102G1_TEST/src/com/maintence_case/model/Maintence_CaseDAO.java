package com.maintence_case.model;

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

public class Maintence_CaseDAO implements I_Maintence_CaseDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.MAINTENANCE_CASE(MEM_NO,MCA_ITM_ID,"
			+ "MCL_NO,MCA_ITM_SPEC,MCA_DATE,MCA_ITM_MODE,MCA_FIRST_P,MCA_RECPT_DATE,MCA_FINAL_P,MCA_SHIPMENT_DATE,"
			+ "MCA_PICKUP_DATE,MCA_PAY,MCA_COMP_DATE,MCA_COD,MCA_ADRS,MCA_CONTEXT) VALUES(?,?,?,?,?,?,?,?,?,?,?"
			+ ",?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.MAINTENANCE_CASE SET MEM_NO =?,MCA_ITM_ID=?,MCL_NO=?,MCA_ITM_SPEC = ?,"
			+ "MCA_DATE = ?,MCA_ITM_MODE = ?,MCA_FIRST_P = ?,MCA_RECPT_DATE = ?,MCA_FINAL_P = ?,MCA_SHIPMENT_DATE =?,"
			+ "MCA_PICKUP_DATE = ?,MCA_PAY = ?,MCA_COMP_DATE = ?,MCA_COD =?,MCA_ADRS =?,MCA_CONTEXT=? WHERE MCA_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.MAINTENANCE_CASE WHERE MCA_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.MAINTENANCE_CASE WHERE MCA_NO = ?";
	private static final String FIND_BY_MEM_NO = "SELECT * FROM CFA102G1.MAINTENANCE_CASE WHERE MEM_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.MAINTENANCE_CASE";
	
	@Override
	public Maintence_CaseVO insert(Maintence_CaseVO  maintence_casevo) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1,maintence_casevo.getMem_no());
			pstmt.setString(2,maintence_casevo.getMca_itm_id());
			pstmt.setInt(3,maintence_casevo.getMcl_no());
			pstmt.setString(4,maintence_casevo.getMca_itm_spec());
			pstmt.setTimestamp(5,maintence_casevo.getMca_date());
			pstmt.setString(6,maintence_casevo.getMca_itm_mode());
			pstmt.setInt(7,maintence_casevo.getMca_first_p());
			pstmt.setTimestamp(8,maintence_casevo.getMca_recpt_date());
			pstmt.setInt(9,maintence_casevo.getMca_final_p());
			pstmt.setTimestamp(10,maintence_casevo.getMca_shipment_date());
			pstmt.setTimestamp(11,maintence_casevo.getMca_pickup_date());
			pstmt.setString(12,maintence_casevo.getMca_pay());
			pstmt.setTimestamp(13,maintence_casevo.getMca_comp_date());
			pstmt.setString(14,maintence_casevo.getMca_cod());
			pstmt.setString(15,maintence_casevo.getMca_adrs());
			pstmt.setString(16,maintence_casevo.getMca_context());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				maintence_casevo.setMca_no(rs.getInt(1));
			}
			
		} catch (SQLException e) {
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
		return maintence_casevo;
	}
	
	@Override
	public void update(Maintence_CaseVO maintence_casevo) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1,maintence_casevo.getMem_no());
			pstmt.setString(2,maintence_casevo.getMca_itm_id());
			pstmt.setInt(3,maintence_casevo.getMcl_no());
			pstmt.setString(4,maintence_casevo.getMca_itm_spec());
			pstmt.setTimestamp(5,maintence_casevo.getMca_date());
			pstmt.setString(6,maintence_casevo.getMca_itm_mode());
			pstmt.setInt(7,maintence_casevo.getMca_first_p());
			pstmt.setTimestamp(8,maintence_casevo.getMca_recpt_date());
			pstmt.setInt(9,maintence_casevo.getMca_final_p());
			pstmt.setTimestamp(10,maintence_casevo.getMca_shipment_date());
			pstmt.setTimestamp(11,maintence_casevo.getMca_pickup_date());
			pstmt.setString(12,maintence_casevo.getMca_pay());
			pstmt.setTimestamp(13,maintence_casevo.getMca_comp_date());
			pstmt.setString(14,maintence_casevo.getMca_cod());
			pstmt.setString(15,maintence_casevo.getMca_adrs());
			pstmt.setString(16,maintence_casevo.getMca_context());
			pstmt.setInt(17,maintence_casevo.getMca_no());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
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
	public void delete(Integer mca_no) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, mca_no);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
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
	public Maintence_CaseVO findByPK(Integer mca_no) {
		Maintence_CaseVO maintencecasevo = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, mca_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				maintencecasevo = new Maintence_CaseVO();
				maintencecasevo.setMca_no(rs.getInt("MCA_NO"));
				maintencecasevo.setMem_no(rs.getInt("MEM_NO"));
				maintencecasevo.setMca_itm_id(rs.getString("MCA_ITM_ID"));
				maintencecasevo.setMcl_no(rs.getInt("MCL_NO"));
				maintencecasevo.setMca_itm_spec(rs.getString("MCA_ITM_SPEC"));
				maintencecasevo.setMca_date(rs.getTimestamp("MCA_DATE"));
				maintencecasevo.setMca_itm_mode(rs.getString("MCA_ITM_MODE"));
				maintencecasevo.setMca_first_p(rs.getInt("MCA_FIRST_P"));
				maintencecasevo.setMca_recpt_date(rs.getTimestamp("MCA_RECPT_DATE"));
				maintencecasevo.setMca_final_p(rs.getInt("MCA_FINAL_P"));
				maintencecasevo.setMca_shipment_date(rs.getTimestamp("MCA_SHIPMENT_DATE"));
				maintencecasevo.setMca_pickup_date(rs.getTimestamp("MCA_PICKUP_DATE"));
				maintencecasevo.setMca_pay(rs.getString("MCA_PAY"));
				maintencecasevo.setMca_comp_date(rs.getTimestamp("MCA_COMP_DATE"));
				maintencecasevo.setMca_cod(rs.getString("MCA_COD"));
				maintencecasevo.setMca_adrs(rs.getString("MCA_ADRS"));
				maintencecasevo.setMca_context(rs.getString("MCA_CONTEXT"));
				
			}
			
		} catch (SQLException e) {
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
		return maintencecasevo;
	}
	
	@Override
	public List<Maintence_CaseVO> findByMEM_NO(Integer mem_no) {
		Maintence_CaseVO maintencecasevo = null;
		List<Maintence_CaseVO> listMaintence_CaseVO = new ArrayList<Maintence_CaseVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				maintencecasevo = new Maintence_CaseVO();
				maintencecasevo.setMca_no(rs.getInt("MCA_NO"));
				maintencecasevo.setMem_no(rs.getInt("MEM_NO"));
				maintencecasevo.setMca_itm_id(rs.getString("MCA_ITM_ID"));
				maintencecasevo.setMcl_no(rs.getInt("MCL_NO"));
				maintencecasevo.setMca_itm_spec(rs.getString("MCA_ITM_SPEC"));
				maintencecasevo.setMca_date(rs.getTimestamp("MCA_DATE"));
				maintencecasevo.setMca_itm_mode(rs.getString("MCA_ITM_MODE"));
				maintencecasevo.setMca_first_p(rs.getInt("MCA_FIRST_P"));
				maintencecasevo.setMca_recpt_date(rs.getTimestamp("MCA_RECPT_DATE"));
				maintencecasevo.setMca_final_p(rs.getInt("MCA_FINAL_P"));
				maintencecasevo.setMca_shipment_date(rs.getTimestamp("MCA_SHIPMENT_DATE"));
				maintencecasevo.setMca_pickup_date(rs.getTimestamp("MCA_PICKUP_DATE"));
				maintencecasevo.setMca_pay(rs.getString("MCA_PAY"));
				maintencecasevo.setMca_comp_date(rs.getTimestamp("MCA_COMP_DATE"));
				maintencecasevo.setMca_cod(rs.getString("MCA_COD"));
				maintencecasevo.setMca_adrs(rs.getString("MCA_ADRS"));
				maintencecasevo.setMca_context(rs.getString("MCA_CONTEXT"));
				listMaintence_CaseVO.add(maintencecasevo);
			}
			
		} catch (SQLException e) {
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
		return listMaintence_CaseVO;
	}
	
	@Override
	public List<Maintence_CaseVO> getAll() {
		Maintence_CaseVO maintencecasevo = null;
		List<Maintence_CaseVO> listMaintence_CaseVO = new ArrayList<Maintence_CaseVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				maintencecasevo = new Maintence_CaseVO();
				maintencecasevo.setMca_no(rs.getInt("MCA_NO"));
				maintencecasevo.setMem_no(rs.getInt("MEM_NO"));
				maintencecasevo.setMca_itm_id(rs.getString("MCA_ITM_ID"));
				maintencecasevo.setMcl_no(rs.getInt("MCL_NO"));
				maintencecasevo.setMca_itm_spec(rs.getString("MCA_ITM_SPEC"));
				maintencecasevo.setMca_date(rs.getTimestamp("MCA_DATE"));
				maintencecasevo.setMca_itm_mode(rs.getString("MCA_ITM_MODE"));
				maintencecasevo.setMca_first_p(rs.getInt("MCA_FIRST_P"));
				maintencecasevo.setMca_recpt_date(rs.getTimestamp("MCA_RECPT_DATE"));
				maintencecasevo.setMca_final_p(rs.getInt("MCA_FINAL_P"));
				maintencecasevo.setMca_shipment_date(rs.getTimestamp("MCA_SHIPMENT_DATE"));
				maintencecasevo.setMca_pickup_date(rs.getTimestamp("MCA_PICKUP_DATE"));
				maintencecasevo.setMca_pay(rs.getString("MCA_PAY"));
				maintencecasevo.setMca_comp_date(rs.getTimestamp("MCA_COMP_DATE"));
				maintencecasevo.setMca_cod(rs.getString("MCA_COD"));
				maintencecasevo.setMca_adrs(rs.getString("MCA_ADRS"));
				maintencecasevo.setMca_context(rs.getString("MCA_CONTEXT"));
				listMaintence_CaseVO.add(maintencecasevo);
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
		return listMaintence_CaseVO;
	}
}
