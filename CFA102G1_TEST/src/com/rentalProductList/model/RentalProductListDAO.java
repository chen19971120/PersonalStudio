package com.rentalProductList.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RentalProductListDAO implements I_RentalProductListDAO{
	
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
			"INSERT INTO RENTAL_PRODUCT_LIST (rc_no,rpl_serialnum,rpl_note) VALUES(?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE RENTAL_PRODUCT_LIST set rc_no=?,rpl_serialnum=?,rpl_note=?,rpl_status=?,rpl_rentcount=?,rpl_jointtime=? where rpl_no = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM RENTAL_PRODUCT_LIST where rpl_no = ?";
	private static final String FIND_BY_PK = 
			"SELECT * FROM RENTAL_PRODUCT_LIST WHERE rpl_no = ?";
	private static final String GET_ALL = 
			"SELECT * FROM RENTAL_PRODUCT_LIST";
	
	public RentalProductListVO insert(RentalProductListVO rentalProductListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, rentalProductListVO.getRc_no());
			pstmt.setString(2, rentalProductListVO.getRpl_serialnum());
			pstmt.setString(3, rentalProductListVO.getRpl_note());
			
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				rentalProductListVO.setRpl_no(rs.getInt(1));
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
		return rentalProductListVO;
	}
	
	public void update(RentalProductListVO rentalProductListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, rentalProductListVO.getRc_no());
			pstmt.setString(2, rentalProductListVO.getRpl_serialnum());
			pstmt.setString(3, rentalProductListVO.getRpl_note());
			pstmt.setString(4, rentalProductListVO.getRpl_status());
			pstmt.setInt(5, rentalProductListVO.getRpl_rentcount());
			pstmt.setTimestamp(6, rentalProductListVO.getRpl_jointtime());
			pstmt.setInt(7, rentalProductListVO.getRpl_no());
			
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
	
	public void delete(Integer rpl_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, rpl_no);
			
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

	public RentalProductListVO findByPK(Integer rpl_no) {
		RentalProductListVO rentalProductListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, rpl_no);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				rentalProductListVO = new RentalProductListVO();		
				rentalProductListVO.setRpl_no(rs.getInt("rpl_no"));
				rentalProductListVO.setRc_no(rs.getInt("rc_no"));
				rentalProductListVO.setRpl_serialnum(rs.getString("rpl_serialnum"));
				rentalProductListVO.setRpl_note(rs.getString("rpl_note"));
				rentalProductListVO.setRpl_status(rs.getString("rpl_status"));
				rentalProductListVO.setRpl_rentcount(rs.getInt("rpl_rentcount"));
				rentalProductListVO.setRpl_jointtime(rs.getTimestamp("rpl_jointtime"));			
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
		return rentalProductListVO;
	}

	public List<RentalProductListVO> getAll() {
		List<RentalProductListVO> list = new ArrayList<RentalProductListVO>();
		RentalProductListVO rentalProductListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalProductListVO = new RentalProductListVO();		
				rentalProductListVO.setRpl_no(rs.getInt("rpl_no"));
				rentalProductListVO.setRc_no(rs.getInt("rc_no"));
				rentalProductListVO.setRpl_serialnum(rs.getString("rpl_serialnum"));
				rentalProductListVO.setRpl_note(rs.getString("rpl_note"));
				rentalProductListVO.setRpl_status(rs.getString("rpl_status"));
				rentalProductListVO.setRpl_rentcount(rs.getInt("rpl_rentcount"));
				rentalProductListVO.setRpl_jointtime(rs.getTimestamp("rpl_jointtime"));	
				
				list.add(rentalProductListVO); // Store the row in the vector
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
	
}
