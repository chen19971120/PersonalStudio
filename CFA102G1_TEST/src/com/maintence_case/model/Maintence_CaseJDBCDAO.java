package com.maintence_case.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Maintence_CaseJDBCDAO implements I_Maintence_CaseDAO {
	
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
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
		return maintence_casevo;
	}
	
	@Override
	public void update(Maintence_CaseVO maintence_casevo) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	public void delete(Integer mca_no) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, mca_no);
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
	public Maintence_CaseVO findByPK(Integer mca_no) {
		Maintence_CaseVO maintencecasevo = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return listMaintence_CaseVO;
	}
	
//驗證功能----------------------------------------------------------------------
//	
//	public static void main(String[] args) {
//		
//	    //驗證INSERT OK
//		Maintence_CaseJDBCDAO dao = new Maintence_CaseJDBCDAO();
//		Maintence_CaseVO mcv = new Maintence_CaseVO();
//		
//		mcv.setMem_no(2);
//		mcv.setMca_itm_id("筆電");
//		mcv.setMcl_no(1);
//		mcv.setMca_itm_spec("四核心筆電");
//		mcv.setMca_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_itm_mode("已出貨");
//		mcv.setMca_first_p(1000);
//		mcv.setMca_recpt_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_final_p(1200);
//		mcv.setMca_shipment_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_pickup_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_pay("現金");
//		mcv.setMca_comp_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_cod("自取");
//		mcv.setMca_adrs("台北市大安路1號");
//		mcv.setMca_context("已更換cpu");
//		
//		mcv=dao.insert(mcv);
//		System.out.println(mcv.getMca_no() + " : " + mcv.getMca_itm_id());
		
		//驗證UPDATE OK
//		Maintence_CaseJDBCDAO dao = new Maintence_CaseJDBCDAO();
//		Maintence_CaseVO mcv = new Maintence_CaseVO();
//		
//		mcv.setMem_no(2);
//		mcv.setMca_itm_id("Acer筆電");
//		mcv.setMcl_no(1);
//		mcv.setMca_itm_spec("四核心筆電");
//		mcv.setMca_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_itm_mode("確認收款");
//		mcv.setMca_first_p(1200);
//		mcv.setMca_recpt_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_final_p(1800);
//		mcv.setMca_shipment_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_pickup_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_pay("信用卡");
//		mcv.setMca_comp_date(new Timestamp(System.currentTimeMillis()));
//		mcv.setMca_cod("超商取貨");
//		mcv.setMca_adrs("台北市和平東路1號");
//		mcv.setMca_context("已更換cpu");
//		mcv.setMca_no(2);
//		dao.update(mcv);
//		
		//驗證DELETE OK
//		Maintence_CaseJDBCDAO dao = new Maintence_CaseJDBCDAO();
//		dao.delete(6);
		
		//驗證findByPK OK
//		Maintence_CaseJDBCDAO dao = new Maintence_CaseJDBCDAO();
//		Maintence_CaseVO maintencecasevo = dao.findByPK(2);
//		
//		System.out.println("維修案件編號 :"+ maintencecasevo.getMca_no());
//		System.out.println("會員編號 :"+ maintencecasevo.getMem_no());
//		System.out.println("維修商品名稱 :"+ maintencecasevo.getMca_itm_id());
//		System.out.println("維修類別編號 :"+ maintencecasevo.getMcl_no());
//		System.out.println("維修商品內容(規格) :"+ maintencecasevo.getMca_itm_spec());
//		System.out.println("案件日期 :"+ maintencecasevo.getMca_date());
//		System.out.println("案件狀態 :"+ maintencecasevo.getMca_itm_mode());
//		System.out.println("報價 :"+ maintencecasevo.getMca_first_p());
//		System.out.println("門市收貨日期 :"+ maintencecasevo.getMca_recpt_date());
//		System.out.println("成交價 :"+ maintencecasevo.getMca_final_p());
//		System.out.println("出貨日期 :"+ maintencecasevo.getMca_shipment_date());
//		System.out.println("取貨日期 :"+ maintencecasevo.getMca_pickup_date());
//		System.out.println("付款方式 :"+ maintencecasevo.getMca_pay());
//		System.out.println("完成日期 :"+ maintencecasevo.getMca_comp_date());
//		System.out.println("運送方式 :"+ maintencecasevo.getMca_cod());
//		System.out.println("配送地址 :"+ maintencecasevo.getMca_adrs());
//		System.out.println("維修品項 :"+ maintencecasevo.getMca_context());
//		System.out.println("------------------------------------");
		
		//驗證findByMEM_NO OK
//		Maintence_CaseJDBCDAO dao = new Maintence_CaseJDBCDAO();
//		List<Maintence_CaseVO> list = dao.findByMEM_NO(2);
//		for(Maintence_CaseVO maintencecasevo : list) {
//			
//		System.out.println("維修案件編號 :"+ maintencecasevo.getMca_no());
//		System.out.println("會員編號 :"+ maintencecasevo.getMem_no());
//		System.out.println("維修商品名稱 :"+ maintencecasevo.getMca_itm_id());
//		System.out.println("維修類別編號 :"+ maintencecasevo.getMcl_no());
//		System.out.println("維修商品內容(規格) :"+ maintencecasevo.getMca_itm_spec());
//		System.out.println("案件日期 :"+ maintencecasevo.getMca_date());
//		System.out.println("案件狀態 :"+ maintencecasevo.getMca_itm_mode());
//		System.out.println("報價 :"+ maintencecasevo.getMca_first_p());
//		System.out.println("門市收貨日期 :"+ maintencecasevo.getMca_recpt_date());
//		System.out.println("成交價 :"+ maintencecasevo.getMca_final_p());
//		System.out.println("出貨日期 :"+ maintencecasevo.getMca_shipment_date());
//		System.out.println("取貨日期 :"+ maintencecasevo.getMca_pickup_date());
//		System.out.println("付款方式 :"+ maintencecasevo.getMca_pay());
//		System.out.println("完成日期 :"+ maintencecasevo.getMca_comp_date());
//		System.out.println("運送方式 :"+ maintencecasevo.getMca_cod());
//		System.out.println("配送地址 :"+ maintencecasevo.getMca_adrs());
//		System.out.println("維修品項 :"+ maintencecasevo.getMca_context());
//		System.out.println("------------------------------------");	
//		}
				
		//驗證getAll OK
//		Maintence_CaseJDBCDAO dao = new Maintence_CaseJDBCDAO();
//		List<Maintence_CaseVO> list = dao.getAll();
//		for(Maintence_CaseVO maintencecasevo : list) {
//		System.out.println("維修案件編號 :"+ maintencecasevo.getMca_no());
//		System.out.println("會員編號 :"+ maintencecasevo.getMem_no());
//		System.out.println("維修商品名稱 :"+ maintencecasevo.getMca_itm_id());
//		System.out.println("維修類別編號 :"+ maintencecasevo.getMcl_no());
//		System.out.println("維修商品內容(規格) :"+ maintencecasevo.getMca_itm_spec());
//		System.out.println("案件日期 :"+ maintencecasevo.getMca_date());
//		System.out.println("案件狀態 :"+ maintencecasevo.getMca_itm_mode());
//		System.out.println("報價 :"+ maintencecasevo.getMca_first_p());
//		System.out.println("門市收貨日期 :"+ maintencecasevo.getMca_recpt_date());
//		System.out.println("成交價 :"+ maintencecasevo.getMca_final_p());
//		System.out.println("出貨日期 :"+ maintencecasevo.getMca_shipment_date());
//		System.out.println("取貨日期 :"+ maintencecasevo.getMca_pickup_date());
//		System.out.println("付款方式 :"+ maintencecasevo.getMca_pay());
//		System.out.println("完成日期 :"+ maintencecasevo.getMca_comp_date());
//		System.out.println("運送方式 :"+ maintencecasevo.getMca_cod());
//		System.out.println("配送地址 :"+ maintencecasevo.getMca_adrs());
//		System.out.println("維修品項 :"+ maintencecasevo.getMca_context());
//		System.out.println("------------------------------------");
//		}	
	}
	
//驗證功能----------------------------------------------------------------------


//}
