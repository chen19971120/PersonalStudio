package com.sec_order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;
import com.sec_order_list.model.SecOrderListDAO;
import com.sec_order_list.model.SecOrderListVO;

import sun.security.ec.ECDHKeyAgreement;

public class SecOrderDAO implements I_SecOrderDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.SEC_ORDER("
								+ "SO_PURTIME,MEM_NO,SO_STA,SO_PAY_STA,SO_SHIP_STA,CI_NO,SO_TOTALPRI,SO_PRODEL,SO_DELADRS,SO_PAYMTHD,SO_SHIPDATE,SO_DELCOST,SO_DISCOUNT_PRICE) "
								+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_STMT = "UPDATE CFA102G1.SEC_ORDER SET "
								+ "SO_PURTIME=?,MEM_NO=?,SO_STA=?,SO_PAY_STA=?,SO_SHIP_STA=?,CI_NO=?,SO_TOTALPRI=?,SO_PRODEL=?,SO_DELADRS=?,SO_PAYMTHD=?,SO_SHIPDATE=?,SO_DELCOST=?,SO_DISCOUNT_PRICE=? "
								+ "WHERE SO_NO = ?";
	
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.SEC_ORDER WHERE SO_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.SEC_ORDER WHERE SO_NO = ?";
	private static final String FIND_BY_MEM_NO = "SELECT * FROM CFA102G1.SEC_ORDER WHERE MEM_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.SEC_ORDER";

	@Override
	public SecOrderVO insert(SecOrderVO secOrder) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setTimestamp(1, secOrder.getSo_purtime());
			pstmt.setInt(2, secOrder.getMem_no());
			pstmt.setString(3, secOrder.getSo_sta());
			pstmt.setString(4, secOrder.getSo_pay_sta());
			pstmt.setString(5, secOrder.getSo_ship_sta());
			pstmt.setInt(6, secOrder.getCi_no());
			pstmt.setInt(7, secOrder.getSo_totalpri());
			pstmt.setString(8, secOrder.getSo_prodel());
			pstmt.setString(9, secOrder.getSo_deladrs());
			pstmt.setString(10, secOrder.getSo_paymthd());
			pstmt.setTimestamp(11, secOrder.getSo_shipdate());
			pstmt.setInt(12, secOrder.getSo_delcost());
			pstmt.setInt(13, secOrder.getSo_discount_price());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				secOrder.setSo_no(rs.getInt(1));
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
		return secOrder;
	}

	@Override
	public void update(SecOrderVO secOrder) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setTimestamp(1, secOrder.getSo_purtime());
			pstmt.setInt(2, secOrder.getMem_no());
			pstmt.setString(3, secOrder.getSo_sta());
			pstmt.setString(4, secOrder.getSo_pay_sta());
			pstmt.setString(5, secOrder.getSo_ship_sta());
			pstmt.setInt(6, secOrder.getCi_no());
			pstmt.setInt(7, secOrder.getSo_totalpri());
			pstmt.setString(8, secOrder.getSo_prodel());
			pstmt.setString(9, secOrder.getSo_deladrs());
			pstmt.setString(10, secOrder.getSo_paymthd());
			pstmt.setTimestamp(11, secOrder.getSo_shipdate());
			pstmt.setInt(12, secOrder.getSo_delcost());
			pstmt.setInt(13, secOrder.getSo_discount_price());
			pstmt.setInt(14, secOrder.getSo_no());
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
	public void delete(Integer so_no) {

		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, so_no);
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
	public SecOrderVO findByPK(Integer so_no) {
		SecOrderVO secOrder = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, so_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				secOrder = new SecOrderVO();
				secOrder.setSo_no(rs.getInt("SO_NO"));
				secOrder.setSo_purtime(rs.getTimestamp("SO_PURTIME"));
				secOrder.setMem_no(rs.getInt("MEM_NO"));
				secOrder.setSo_sta(rs.getString("SO_STA"));
				secOrder.setSo_pay_sta(rs.getString("SO_PAY_STA"));
				secOrder.setSo_ship_sta(rs.getString("SO_SHIP_STA"));
				secOrder.setCi_no(rs.getInt("CI_NO"));
				secOrder.setSo_totalpri(rs.getInt("SO_TOTALPRI"));
				secOrder.setSo_prodel(rs.getString("SO_PRODEL"));
				secOrder.setSo_deladrs(rs.getString("SO_DELADRS"));
				secOrder.setSo_paymthd(rs.getString("SO_PAYMTHD"));
				secOrder.setSo_shipdate(rs.getTimestamp("SO_SHIPDATE"));
				secOrder.setSo_delcost(rs.getInt("SO_DELCOST"));
				secOrder.setSo_discount_price(rs.getInt("SO_DISCOUNT_PRICE"));
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
		return secOrder;
	}

	@Override
	public List<SecOrderVO> findByMem_NO(Integer mem_no) {
		SecOrderVO secOrder = null;
		List<SecOrderVO> listSecOrder = new ArrayList<SecOrderVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				secOrder = new SecOrderVO();
				secOrder.setSo_no(rs.getInt("SO_NO"));
				secOrder.setSo_purtime(rs.getTimestamp("SO_PURTIME"));
				secOrder.setMem_no(rs.getInt("MEM_NO"));
				secOrder.setSo_sta(rs.getString("SO_STA"));
				secOrder.setSo_pay_sta(rs.getString("SO_PAY_STA"));
				secOrder.setSo_ship_sta(rs.getString("SO_SHIP_STA"));
				secOrder.setCi_no(rs.getInt("CI_NO"));
				secOrder.setSo_totalpri(rs.getInt("SO_TOTALPRI"));
				secOrder.setSo_prodel(rs.getString("SO_PRODEL"));
				secOrder.setSo_deladrs(rs.getString("SO_DELADRS"));
				secOrder.setSo_paymthd(rs.getString("SO_PAYMTHD"));
				secOrder.setSo_shipdate(rs.getTimestamp("SO_SHIPDATE"));
				secOrder.setSo_delcost(rs.getInt("SO_DELCOST"));
				secOrder.setSo_discount_price(rs.getInt("SO_DISCOUNT_PRICE"));
				listSecOrder.add(secOrder);
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
		return listSecOrder;
	}

	@Override
	public List<SecOrderVO> getAll() {
		SecOrderVO secOrder = null;
		List<SecOrderVO> listSecOrder = new ArrayList<SecOrderVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				secOrder = new SecOrderVO();
				secOrder.setSo_no(rs.getInt("SO_NO"));
				secOrder.setSo_purtime(rs.getTimestamp("SO_PURTIME"));
				secOrder.setMem_no(rs.getInt("MEM_NO"));
				secOrder.setSo_sta(rs.getString("SO_STA"));
				secOrder.setSo_pay_sta(rs.getString("SO_PAY_STA"));
				secOrder.setSo_ship_sta(rs.getString("SO_SHIP_STA"));
				secOrder.setCi_no(rs.getInt("CI_NO"));
				secOrder.setSo_totalpri(rs.getInt("SO_TOTALPRI"));
				secOrder.setSo_prodel(rs.getString("SO_PRODEL"));
				secOrder.setSo_deladrs(rs.getString("SO_DELADRS"));
				secOrder.setSo_paymthd(rs.getString("SO_PAYMTHD"));
				secOrder.setSo_shipdate(rs.getTimestamp("SO_SHIPDATE"));
				secOrder.setSo_delcost(rs.getInt("SO_DELCOST"));
				secOrder.setSo_discount_price(rs.getInt("SO_DISCOUNT_PRICE"));
				listSecOrder.add(secOrder);
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
		return listSecOrder;
	}
	
	@Override
	public SecOrderVO insertWithList(SecOrderVO secOrder, List<SecOrderListVO> secOrderListVOs) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			//1.設定手動交易
			con.setAutoCommit(false);
			
			//2.新增訂單
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setTimestamp(1, secOrder.getSo_purtime());
			pstmt.setInt(2, secOrder.getMem_no());
			pstmt.setString(3, secOrder.getSo_sta());
			pstmt.setString(4, secOrder.getSo_pay_sta());
			pstmt.setString(5, secOrder.getSo_ship_sta());
			pstmt.setInt(6, secOrder.getCi_no());
			pstmt.setInt(7, secOrder.getSo_totalpri());
			pstmt.setString(8, secOrder.getSo_prodel());
			pstmt.setString(9, secOrder.getSo_deladrs());
			pstmt.setString(10, secOrder.getSo_paymthd());
			pstmt.setTimestamp(11, secOrder.getSo_shipdate());
			pstmt.setInt(12, secOrder.getSo_delcost());
			pstmt.setInt(13, secOrder.getSo_discount_price());
			pstmt.executeUpdate();
			
			//3.取回訂單PK
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				secOrder.setSo_no(rs.getInt(1));
			}
			
			//4.開始新增訂單明細
			SecOrderListDAO dao = new SecOrderListDAO();
			
			for(SecOrderListVO secOrderList : secOrderListVOs) {
				secOrderList.setSo_no(secOrder.getSo_no());
				dao.insertWithOrder(secOrderList, con);
			}
			
			//5.完成資料新增，並設定回自動交易
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			if(con!=null) {
				try {
					System.err.println("新增訂單失敗!!!");
					System.err.println("由secOrder執行rollback!!!");
					System.err.println("錯誤訊息 : " + e.getMessage());
					con.rollback();
				} catch (SQLException e1) {
					throw new RuntimeException("rollback執行失敗 : " + e1.getMessage());
					
				}
			}
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new RuntimeException("連線關閉失敗 : " + e.getMessage());
				}
			}
		}
		return secOrder;
		
	}
}
