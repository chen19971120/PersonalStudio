package com.sec_order_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SecOrderListJDBCDAO implements I_SecOrderListDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return secOrderList;
	}

	@Override
	public void update(SecOrderListVO secOrderList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, secOrderList.getSo_no());
			pstmt.setInt(2, secOrderList.getSpi_no());
			pstmt.setInt(3, secOrderList.getSol_proamot());
			pstmt.setInt(4, secOrderList.getSol_pri());
			pstmt.setInt(5, secOrderList.getSol_no());

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
	public void delete(Integer sol_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, sol_no);
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
	public SecOrderListVO findByPk(Integer sol_no) {
		SecOrderListVO secOrderList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return listSecOrderList;
	}
	
	@Override
	public void insertWithOrder(SecOrderListVO secOrderList, Connection con) {
		// TODO Auto-generated method stub
		
	}

	//測試驗證>>>>>>>>>>>>>>>>>>>>>>>>>>
//			public static void main (String[] args) {
//				
//				//測試INSERT(OK).........................................
//				SecOrderListJDBCDAO dao = new SecOrderListJDBCDAO();
//				SecOrderListVO secOrderList = new SecOrderListVO();
//				secOrderList.setSo_no(1);
//				secOrderList.setSpi_no(1);
//				secOrderList.setSol_proamot(4);
//				secOrderList.setSol_pri(150000);
//				secOrderList = dao.insert(secOrderList);
//				System.out.println(secOrderList.getSol_no());
//				System.out.println("OK!!");
//				
//				//測試UPDATE(OK).........................................
//				SecOrderListJDBCDAO dao = new SecOrderListJDBCDAO();
//				SecOrderListVO secOrderList = new SecOrderListVO();
//				secOrderList.setSol_no(1);
//				secOrderList.setSo_no(3);
//				secOrderList.setSpi_no(2);
//				secOrderList.setSol_proamot(1);
//				secOrderList.setSol_pri(30000);
//				dao.update(secOrderList);
//				System.out.println("OK!!");
//				
//				//測試DELETE(OK).........................................
//				SecOrderListJDBCDAO dao = new SecOrderListJDBCDAO();
//				dao.delete(3);
//				System.out.println("OK!!");
//
//				//測試FIND_BY_PK(OK).........................................
//				SecOrderListJDBCDAO dao = new SecOrderListJDBCDAO();
//				SecOrderListVO secOrderList = dao.findByPk(2);
//				System.out.println(secOrderList.getSol_no() + ", ");
//				System.out.println(secOrderList.getSo_no() + ", ");
//				System.out.println(secOrderList.getSpi_no() + ", ");
//				System.out.println(secOrderList.getSol_proamot() + ", ");
//				System.out.println(secOrderList.getSol_pri() + ", ");
//
//				//測試GET_ALL(OK).........................................
//				SecOrderListJDBCDAO dao = new SecOrderListJDBCDAO();
//				List<SecOrderListVO> list = dao.getAll();
//				for(SecOrderListVO secOrderList : list) {
//					System.out.println(secOrderList.getSol_no() + ",\t" +
//									secOrderList.getSo_no() + ",\t" +
//									secOrderList.getSpi_no() + ",\t" +
//									secOrderList.getSol_proamot() + ",\t" +
//									secOrderList.getSol_pri() + "."
//							);
//					System.out.println("................................................");
//				}
	//<<<<<<<<<<<<<<<<<<<<<<<<<測試驗證
//
//			}
}
