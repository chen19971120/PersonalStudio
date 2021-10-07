package com.sec_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sec_order_list.model.SecOrderListVO;

public class SecOrderJDBCDAO implements I_SecOrderDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return secOrder;
	}

	@Override
	public void update(SecOrderVO secOrder) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	public void delete(Integer so_no) {

		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, so_no);
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
	public SecOrderVO findByPK(Integer so_no) {
		SecOrderVO secOrder = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return listSecOrder;
	}
	
	@Override
	public SecOrderVO insertWithList(SecOrderVO secOrder, List<SecOrderListVO> secOrderListVOs) {
		// TODO Auto-generated method stub
		return secOrder;
	}
	
	//測試驗證>>>>>>>>>>>>>>>>>>>>>>>>>>
//	public static void main (String[] args) {
//		
//		//測試INSERT(OK).........................................
//		SecOrderJDBCDAO dao = new SecOrderJDBCDAO();
//		SecOrderVO secOrder = new SecOrderVO();
//		secOrder.setSo_purtime(new Timestamp(System.currentTimeMillis()));
//		secOrder.setMem_no(1);
//		secOrder.setSo_sta("完成訂單");
//		secOrder.setSo_pay_sta("已付款");
//		secOrder.setSo_ship_sta("已出貨");
//		secOrder.setCi_no(1);
//		secOrder.setSo_totalpri(10000);
//		secOrder.setSo_prodel("自取");
//		secOrder.setSo_deladrs(" ");
//		secOrder.setSo_paymthd("信用卡");
//		secOrder.setSo_shipdate(new Timestamp(System.currentTimeMillis()));
//		secOrder.setSo_delcost(60);
//		secOrder.setSo_discount_price(9000);
//		secOrder = dao.insert(secOrder);
//		System.out.println(secOrder.getSo_no());
//		
//		//測試UPDATE(OK).........................................
//		SecOrderJDBCDAO dao = new SecOrderJDBCDAO();
//		SecOrderVO secOrder = new SecOrderVO();
//		secOrder.setSo_no(3);
//		secOrder.setSo_purtime(new Timestamp(System.currentTimeMillis()));
//		secOrder.setMem_no(1);
//		secOrder.setSo_sta("完成訂單");
//		secOrder.setSo_pay_sta("已付款付款");
//		secOrder.setSo_ship_sta("已出貨");
//		secOrder.setCi_no(1);
//		secOrder.setSo_totalpri(10000);
//		secOrder.setSo_prodel("宅配");
//		secOrder.setSo_deladrs("桃園市中壢區TIBAME");
//		secOrder.setSo_paymthd("信用卡");
//		secOrder.setSo_shipdate(new Timestamp(System.currentTimeMillis()));
//		secOrder.setSo_delcost(60);
//		secOrder.setSo_discount_price(9000);	
//		dao.update(secOrder);
//		
//		//測試DELETE(OK).........................................
//		SecOrderJDBCDAO dao = new SecOrderJDBCDAO();
//		dao.delete(4);
//
//		//測試FIND_BY_PK(OK).........................................
//		SecOrderJDBCDAO dao = new SecOrderJDBCDAO();
//		SecOrderVO secOrder = dao.findByPK(3);
//		System.out.println(secOrder.getSo_no() + ",");
//		System.out.println(secOrder.getSo_purtime() + ",");
//		System.out.println(secOrder.getMem_no() + ",");
//		System.out.println(secOrder.getSo_sta() + ",");
//		System.out.println(secOrder.getSo_pay_sta() + ",");
//		System.out.println(secOrder.getSo_ship_sta() + ",");
//		System.out.println(secOrder.getCi_no() + ",");
//		System.out.println(secOrder.getSo_totalpri() + ",");
//		System.out.println(secOrder.getSo_prodel() + ",");
//		System.out.println(secOrder.getSo_deladrs() + ",");
//		System.out.println(secOrder.getSo_paymthd() + ",");
//		System.out.println(secOrder.getSo_shipdate() + ",");
//		System.out.println(secOrder.getSo_delcost() + ",");
//		System.out.println(secOrder.getSo_discount_price() + ".");
//
//		//測試FIND_BY_NAME(OK).........................................
//		SecOrderJDBCDAO dao = new SecOrderJDBCDAO();
//		List<SecOrderVO> list = dao.findByMem_NO(4);
//		for(SecOrderVO secOrder : list) {
//			System.out.println(secOrder.getSo_no() + ",");
//			System.out.println(secOrder.getSo_purtime() + ",");
//			System.out.println(secOrder.getMem_no() + ",");
//			System.out.println(secOrder.getSo_sta() + ",");
//			System.out.println(secOrder.getSo_pay_sta() + ",");
//			System.out.println(secOrder.getSo_ship_sta() + ",");
//			System.out.println(secOrder.getCi_no() + ",");
//			System.out.println(secOrder.getSo_totalpri() + ",");
//			System.out.println(secOrder.getSo_prodel() + ",");
//			System.out.println(secOrder.getSo_deladrs() + ",");
//			System.out.println(secOrder.getSo_paymthd() + ",");
//			System.out.println(secOrder.getSo_shipdate() + ",");
//			System.out.println(secOrder.getSo_delcost() + ",");
//			System.out.println(secOrder.getSo_discount_price() + ".");
//			System.out.println(".............................................");
//		}
//
//		//測試GET_ALL().........................................
//		SecOrderJDBCDAO dao = new SecOrderJDBCDAO();
//		List<SecOrderVO> list = dao.getAll();
//		for(SecOrderVO secOrder : list) {
//			System.out.println(secOrder.getSo_no() + ",");
//			System.out.println(secOrder.getSo_purtime() + ",");
//			System.out.println(secOrder.getMem_no() + ",");
//			System.out.println(secOrder.getSo_sta() + ",");
//			System.out.println(secOrder.getSo_pay_sta() + ",");
//			System.out.println(secOrder.getSo_ship_sta() + ",");
//			System.out.println(secOrder.getCi_no() + ",");
//			System.out.println(secOrder.getSo_totalpri() + ",");
//			System.out.println(secOrder.getSo_prodel() + ",");
//			System.out.println(secOrder.getSo_deladrs() + ",");
//			System.out.println(secOrder.getSo_paymthd() + ",");
//			System.out.println(secOrder.getSo_shipdate() + ",");
//			System.out.println(secOrder.getSo_delcost() + ",");
//			System.out.println(secOrder.getSo_discount_price() + ".");
//			System.out.println(".............................................");
//		}
//<<<<<<<<<<<<<<<<<<<<<<<<<測試驗證
//	}

}
