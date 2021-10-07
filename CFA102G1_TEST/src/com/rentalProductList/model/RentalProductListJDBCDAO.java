package com.rentalProductList.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalProductListJDBCDAO implements I_RentalProductListDAO{
	
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" 
			+ "rewriteBatchedStatements=true&serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
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
	private static final String FIND_BY_RC_NO = 
			"SELECT * FROM RENTAL_PRODUCT_LIST where RC_no=?";
	private static final String FIND_BY_RC_ITEM = 
			"SELECT * FROM rental_product_list where RC_NO in (select RC_NO from rental_class where rc_item =?)";
	
	
	public RentalProductListVO insert(RentalProductListVO rentalProductListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, rentalProductListVO.getRc_no());
			pstmt.setString(2, rentalProductListVO.getRpl_serialnum());
			pstmt.setString(3, rentalProductListVO.getRpl_note());
			
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				rentalProductListVO.setRpl_no(rs.getInt(1));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, rentalProductListVO.getRc_no());
			pstmt.setString(2, rentalProductListVO.getRpl_serialnum());
			pstmt.setString(3, rentalProductListVO.getRpl_note());
			pstmt.setString(4, rentalProductListVO.getRpl_status());
			pstmt.setInt(5, rentalProductListVO.getRpl_rentcount());
			pstmt.setTimestamp(6, rentalProductListVO.getRpl_jointtime());
			pstmt.setInt(7, rentalProductListVO.getRpl_no());
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, rpl_no);
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

	public List<RentalProductListVO> findbyRc_no(Integer rc_no) {
		
		List<RentalProductListVO> list = new ArrayList<RentalProductListVO>();
		RentalProductListVO rplVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_RC_NO);
			
			pstmt.setInt(1, rc_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rplVO = new RentalProductListVO();		
				
				rplVO.setRpl_no(rs.getInt("rpl_no"));
				rplVO.setRc_no(rs.getInt("rc_no"));
				rplVO.setRpl_serialnum(rs.getString("rpl_serialnum"));
				rplVO.setRpl_note(rs.getString("rpl_note"));
				rplVO.setRpl_status(rs.getString("rpl_status"));
				rplVO.setRpl_rentcount(rs.getInt("rpl_rentcount"));
				rplVO.setRpl_jointtime(rs.getTimestamp("rpl_jointtime"));	
				list.add(rplVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

	public List<RentalProductListVO> findbyRc_item(String rc_item) {
		List<RentalProductListVO> list = new ArrayList<RentalProductListVO>();
		RentalProductListVO rplVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_RC_ITEM);
			
			pstmt.setString(1, rc_item);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rplVO = new RentalProductListVO();		
				
				rplVO.setRpl_no(rs.getInt("rpl_no"));
				rplVO.setRc_no(rs.getInt("rc_no"));
				rplVO.setRpl_serialnum(rs.getString("rpl_serialnum"));
				rplVO.setRpl_note(rs.getString("rpl_note"));
				rplVO.setRpl_status(rs.getString("rpl_status"));
				rplVO.setRpl_rentcount(rs.getInt("rpl_rentcount"));
				rplVO.setRpl_jointtime(rs.getTimestamp("rpl_jointtime"));	
				list.add(rplVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
