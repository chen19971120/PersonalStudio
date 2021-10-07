package com.sec_order_list.model;

import java.sql.Connection;
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

public class SecOrderListDAO implements I_SecOrderListDAO{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.SEC_ORDER_LIST(SO_NO, SPI_NO, SOL_PROAMOT, SOL_PRI)VALUES(?, ?, ?, ?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA102G1.SEC_ORDER_LIST SET SO_NO=?, SPI_NO=?, SOL_PROAMOT=?, SOL_PRI=? WHERE SOL_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.SEC_ORDER_LIST WHERE SOL_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.SEC_ORDER_LIST WHERE SOL_NO=?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.SEC_ORDER_LIST";
	
	@Override
	public SecOrderListVO insert(SecOrderListVO secOrderList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, secOrderList.getSo_no());
			pstmt.setInt(2, secOrderList.getSpi_no());
			pstmt.setInt(3, secOrderList.getSol_proamot());
			pstmt.setInt(4, secOrderList.getSol_pri());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				secOrderList.setSol_no(rs.getInt(1));
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
		return secOrderList;
	}

	@Override
	public void update(SecOrderListVO secOrderList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, secOrderList.getSo_no());
			pstmt.setInt(2, secOrderList.getSpi_no());
			pstmt.setInt(3, secOrderList.getSol_proamot());
			pstmt.setInt(4, secOrderList.getSol_pri());
			pstmt.setInt(5, secOrderList.getSol_no());

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
	public void delete(Integer sol_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, sol_no);
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
	public SecOrderListVO findByPk(Integer sol_no) {
		SecOrderListVO secOrderList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, sol_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				secOrderList = new SecOrderListVO();
				secOrderList.setSol_no(rs.getInt("SOL_NO"));
				secOrderList.setSo_no( rs.getInt("SO_NO"));
				secOrderList.setSpi_no(rs.getInt("SPI_NO"));
				secOrderList.setSol_proamot(rs.getInt("SOL_PROAMOT"));
				secOrderList.setSol_pri(rs.getInt("SOL_PRI"));
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
		return secOrderList;
	}

	@Override
	public List<SecOrderListVO> getAll() {
		SecOrderListVO secOrderList = null;
		List<SecOrderListVO> listSecOrderList = new ArrayList<SecOrderListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				secOrderList = new SecOrderListVO();
				secOrderList.setSol_no(rs.getInt("SOL_NO"));
				secOrderList.setSo_no( rs.getInt("SO_NO"));
				secOrderList.setSpi_no(rs.getInt("SPI_NO"));
				secOrderList.setSol_proamot(rs.getInt("SOL_PROAMOT"));
				secOrderList.setSol_pri(rs.getInt("SOL_PRI"));
				listSecOrderList.add(secOrderList);
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
		return listSecOrderList;
	}
	
	@Override
	public void insertWithOrder(SecOrderListVO secOrderList, Connection con) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, secOrderList.getSo_no());
			pstmt.setInt(2, secOrderList.getSpi_no());
			pstmt.setInt(3, secOrderList.getSol_proamot());
			pstmt.setInt(4, secOrderList.getSol_pri());
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			if (con!=null) {
				try {
					System.err.println("secOrderList 執行 rollback!!");
					con.rollback();
				} catch (SQLException e1) {
					throw new RuntimeException("執行rollback失敗 : " + e1.getMessage());
				}
			}
		}finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw new RuntimeException("關閉pstmt失敗  : " + e.getMessage());
				}
			}
		}
		
	}
}
