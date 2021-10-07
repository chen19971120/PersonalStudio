package com.rentalOrder.model;


import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RentalOrderDAO implements I_RentalOrderDAO{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO RENTAL_ORDER (mem_no,rpl_no,ro_ship_method,ro_ship_addrs,ro_starttime,ro_endtime,ro_day,ro_price,ro_totalprice,ro_deposit) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE RENTAL_ORDER set mem_no=?,rpl_no=?,ro_status=?,ro_ship_method=?,ro_ship_addrs=?,ro_starttime=?,ro_endtime=?,ro_oncerentendtime=?,ro_return_date=?,ro_day=?,ro_price=?,ro_totalprice=?,ro_deposit=?,ro_deposit_status=?,ro_return_status=?,ro_product_status=?,ro_repaircost=?,ro_delay_days=?,ro_return_deposit=? where ro_no = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM RENTAL_ORDER where ro_no = ?";
	private static final String FIND_BY_PK = 
			"SELECT * FROM RENTAL_ORDER WHERE ro_no = ?";
	private static final String GET_ALL = 
			"SELECT * FROM RENTAL_ORDER order by ro_no desc";
	
	public RentalOrderVO insert(RentalOrderVO rentalOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, rentalOrderVO.getMem_no());
			pstmt.setInt(2, rentalOrderVO.getRpl_no());
			pstmt.setString(3, rentalOrderVO.getRo_ship_method());
			pstmt.setString(4, rentalOrderVO.getRo_ship_addrs());
			pstmt.setDate(5, rentalOrderVO.getRo_starttime());
			pstmt.setDate(6, rentalOrderVO.getRo_endtime());
			pstmt.setInt(7, rentalOrderVO.getRo_day());
			pstmt.setInt(8, rentalOrderVO.getRo_price());
			pstmt.setInt(9, rentalOrderVO.getRo_totalprice());
			pstmt.setInt(10, rentalOrderVO.getRo_deposit());
			
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				rentalOrderVO.setRo_no(rs.getInt(1));
			}
			
		} catch (SQLException se) {
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
		return rentalOrderVO;
	}

	public void update(RentalOrderVO rentalOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, rentalOrderVO.getMem_no());
			pstmt.setInt(2, rentalOrderVO.getRpl_no());
			pstmt.setString(3, rentalOrderVO.getRo_status());	
			pstmt.setString(4, rentalOrderVO.getRo_ship_method());
			pstmt.setString(5, rentalOrderVO.getRo_ship_addrs());
			pstmt.setDate(6, rentalOrderVO.getRo_starttime());
			pstmt.setDate(7, rentalOrderVO.getRo_endtime());
			pstmt.setDate(8, rentalOrderVO.getRo_oncerentendtime());
			pstmt.setDate(9, rentalOrderVO.getRo_return_date());
			pstmt.setInt(10, rentalOrderVO.getRo_day());
			pstmt.setInt(11, rentalOrderVO.getRo_price());
			pstmt.setInt(12, rentalOrderVO.getRo_totalprice());
			pstmt.setInt(13, rentalOrderVO.getRo_deposit());
			pstmt.setString(14, rentalOrderVO.getRo_deposit_status());
			pstmt.setString(15, rentalOrderVO.getRo_return_status());
			pstmt.setString(16, rentalOrderVO.getRo_product_status());
			pstmt.setInt(17, rentalOrderVO.getRo_repaircost());
			pstmt.setInt(18, rentalOrderVO.getRo_delay_days());
			pstmt.setInt(19, rentalOrderVO.getRo_return_deposit());
			pstmt.setInt(20, rentalOrderVO.getRo_no());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
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

	public void delete(Integer ro_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, ro_no);
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
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

	public RentalOrderVO findByPK(Integer ro_no) {
		RentalOrderVO rentalOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, ro_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalOrderVO = new RentalOrderVO();		
				rentalOrderVO.setRo_no(rs.getInt("ro_no"));
				rentalOrderVO.setMem_no(rs.getInt("mem_no"));
				rentalOrderVO.setRpl_no(rs.getInt("rpl_no"));
				rentalOrderVO.setRo_status(rs.getString("ro_status"));
				rentalOrderVO.setRo_ship_method(rs.getString("ro_ship_method"));
				rentalOrderVO.setRo_ship_addrs(rs.getString("ro_ship_addrs"));
				rentalOrderVO.setRo_starttime(rs.getDate("ro_starttime"));
				rentalOrderVO.setRo_endtime(rs.getDate("ro_endtime"));				
				rentalOrderVO.setRo_oncerentendtime(rs.getDate("ro_oncerentendtime"));				
				rentalOrderVO.setRo_return_date(rs.getDate("ro_return_date"));				
				rentalOrderVO.setRo_day(rs.getInt("ro_day"));				
				rentalOrderVO.setRo_price(rs.getInt("ro_price"));				
				rentalOrderVO.setRo_totalprice(rs.getInt("ro_totalprice"));				
				rentalOrderVO.setRo_deposit(rs.getInt("ro_deposit"));				
				rentalOrderVO.setRo_deposit_status(rs.getString("ro_deposit_status"));				
				rentalOrderVO.setRo_return_status(rs.getString("ro_return_status"));								
				rentalOrderVO.setRo_product_status(rs.getString("ro_product_status"));				
				rentalOrderVO.setRo_repaircost(rs.getInt("ro_repaircost"));				
				rentalOrderVO.setRo_delay_days(rs.getInt("ro_delay_days"));				
				rentalOrderVO.setRo_return_deposit(rs.getInt("ro_return_deposit"));						
			}

		} catch (SQLException se) {
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
		return rentalOrderVO;
	}

	public List<RentalOrderVO> getAll() {
		List<RentalOrderVO> list = new ArrayList<RentalOrderVO>();
		RentalOrderVO rentalOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rentalOrderVO = new RentalOrderVO();		
				rentalOrderVO.setRo_no(rs.getInt("ro_no"));
				rentalOrderVO.setMem_no(rs.getInt("mem_no"));
				rentalOrderVO.setRpl_no(rs.getInt("rpl_no"));
				rentalOrderVO.setRo_status(rs.getString("ro_status"));
				rentalOrderVO.setRo_ship_method(rs.getString("ro_ship_method"));
				rentalOrderVO.setRo_ship_addrs(rs.getString("ro_ship_addrs"));
				rentalOrderVO.setRo_starttime(rs.getDate("ro_starttime"));
				rentalOrderVO.setRo_endtime(rs.getDate("ro_endtime"));				
				rentalOrderVO.setRo_oncerentendtime(rs.getDate("ro_oncerentendtime"));				
				rentalOrderVO.setRo_return_date(rs.getDate("ro_return_date"));				
				rentalOrderVO.setRo_day(rs.getInt("ro_day"));				
				rentalOrderVO.setRo_price(rs.getInt("ro_price"));				
				rentalOrderVO.setRo_totalprice(rs.getInt("ro_totalprice"));				
				rentalOrderVO.setRo_deposit(rs.getInt("ro_deposit"));				
				rentalOrderVO.setRo_deposit_status(rs.getString("ro_deposit_status"));				
				rentalOrderVO.setRo_return_status(rs.getString("ro_return_status"));							
				rentalOrderVO.setRo_product_status(rs.getString("ro_product_status"));				
				rentalOrderVO.setRo_repaircost(rs.getInt("ro_repaircost"));				
				rentalOrderVO.setRo_delay_days(rs.getInt("ro_delay_days"));				
				rentalOrderVO.setRo_return_deposit(rs.getInt("ro_return_deposit"));
				list.add(rentalOrderVO);
			}

		} catch (SQLException se) {
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
		return list;
	}
	
	public void Cancelunpaidorder() {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("update rental_product_list rpl set rpl_status='待租' where rpl_no in (select rpl_no from RENTAL_ORDER where ro_status='未付款')");		
			pstmt.executeUpdate();
			pstmt.clearParameters();
			pstmt = con.prepareStatement("update rental_order ro set ro_status='取消' where ro_status='未付款'");
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
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
	
	public void ProlongRoOnTime() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("update rental_order SET ro_status = '結束',ro_product_status ='續租'," + 
			"ro_return_status = '續租',ro_return_deposit = 0,ro_delay_days = 0 where DATEDIFF(ro_oncerentendtime,now()) = 0 and ro_status ='租賃中'");		
			pstmt.executeUpdate();
			pstmt.clearParameters();
			pstmt = con.prepareStatement("update rental_order SET ro_status = '租賃中' where DATEDIFF(ro_starttime,now()) = 0 and ro_status ='續租-付款完成'");
			pstmt.executeUpdate();
			pstmt.clearParameters();
			pstmt = con.prepareStatement("update rental_order SET ro_status = '取消' where DATEDIFF(ro_starttime,now()) = 0 and ro_status ='續租-未付款'");
			pstmt.executeUpdate();
					
		} catch (SQLException se) {
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
	
	public void DelayChangeRoByDay() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("Update rental_order set ro_status = '租賃中-逾期',ro_return_status = CONCAT('逾期',DATEDIFF(now(),ro_endtime),'天')," + 
			"ro_delay_days = DATEDIFF(now(),ro_endtime) WHERE   DATEDIFF(now(),ro_endtime) in (1,2,3,4,5,6,7,8) and ro_status in ('租賃中','租賃中-逾期')");		
			pstmt.executeUpdate();
			
					
		} catch (SQLException se) {
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
	
	public void DeadlineChangeRplandRo() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("update rental_product_list SET rpl_status = '逾期未還' where rpl_no in (select rpl_no from rental_order where DATEDIFF(now(),ro_endtime) = 9  and ro_status ='租賃中-逾期')");		
			pstmt.executeUpdate();
			pstmt.clearParameters();
			pstmt = con.prepareStatement("update rental_order set ro_status = '結束-逾期未還',ro_deposit_status ='沒收',ro_product_status ='逾期未還',ro_return_status = '逾期9天未還',ro_return_deposit = 0,ro_delay_days = DATEDIFF(now(),ro_endtime) where DATEDIFF(now(),ro_endtime) = 9 and ro_status ='租賃中-逾期'");
			pstmt.executeUpdate();
					
		} catch (SQLException se) {
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
	
	
}
