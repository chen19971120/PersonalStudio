package com.rentalClass.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalClassJDBCDAO implements I_RentalClassDAO{

	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" 
			+ "rewriteBatchedStatements=true&serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO RENTAL_CLASS (rc_name,rc_item,rc_detail,rc_deposit,rc_price) VALUES(?,?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE RENTAL_CLASS set rc_name=?,rc_item=?,rc_detail=?,rc_deposit=?,rc_price=?,rc_total_count=?,rc_total_score=?,rc_storage=?,rc_status=? where rc_no = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM RENTAL_CLASS where rc_no = ?";
	private static final String FIND_BY_PK = 
			"SELECT * FROM RENTAL_CLASS WHERE rc_no = ?";
	private static final String GET_ALL = 
			"SELECT * FROM RENTAL_CLASS";
	private static final String Update_One_Status = 
			"UPDATE RENTAL_CLASS set rc_status=? where rc_no = ?";
	private static final String GETALLRCITEM = 
			"select distinct rc_item from rental_class";
	private static final String FIND_BY_Rc_item = 
			"select * from rental_class where rc_item=?";
	
	
	
	public RentalClassVO insert(RentalClassVO rentalClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, rentalClassVO.getRc_name());
			pstmt.setString(2, rentalClassVO.getRc_item());
			pstmt.setString(3, rentalClassVO.getRc_detail());
			pstmt.setInt(4, rentalClassVO.getRc_deposit());
			pstmt.setInt(5, rentalClassVO.getRc_price());
			
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				rentalClassVO.setRc_no(rs.getInt(1));
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
		return rentalClassVO;
	}
	
	public void update(RentalClassVO rentalClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, rentalClassVO.getRc_name());
			pstmt.setString(2, rentalClassVO.getRc_item());
			pstmt.setString(3, rentalClassVO.getRc_detail());
			pstmt.setInt(4, rentalClassVO.getRc_deposit());
			pstmt.setInt(5, rentalClassVO.getRc_price());
			pstmt.setInt(6, rentalClassVO.getRc_total_count());
			pstmt.setInt(7, rentalClassVO.getRc_total_score());
			pstmt.setInt(8, rentalClassVO.getRc_storage());
			pstmt.setString(9, rentalClassVO.getRc_status());
			pstmt.setInt(10, rentalClassVO.getRc_no());
			
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

	public void delete(Integer rc_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, rc_no);
			
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

	public RentalClassVO findByPK(Integer rc_no) {
		
		RentalClassVO rentalClassVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, rc_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalClassVO = new RentalClassVO();		
				rentalClassVO.setRc_no(rs.getInt("rc_no"));
				rentalClassVO.setRc_name(rs.getString("rc_name"));
				rentalClassVO.setRc_item(rs.getString("rc_item"));
				rentalClassVO.setRc_detail(rs.getString("rc_detail"));
				rentalClassVO.setRc_deposit(rs.getInt("rc_deposit"));
				rentalClassVO.setRc_price(rs.getInt("rc_price"));
				rentalClassVO.setRc_total_count(rs.getInt("rc_total_count"));
				rentalClassVO.setRc_total_score(rs.getInt("rc_total_score"));
				rentalClassVO.setRc_storage(rs.getInt("rc_storage"));
				rentalClassVO.setRc_status(rs.getString("rc_status"));				
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
		return rentalClassVO;
	}

	public List<RentalClassVO> getAll() {
		List<RentalClassVO> list = new ArrayList<RentalClassVO>();
		RentalClassVO rentalClassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalClassVO = new RentalClassVO();		
				rentalClassVO.setRc_no(rs.getInt("rc_no"));
				rentalClassVO.setRc_name(rs.getString("rc_name"));
				rentalClassVO.setRc_item(rs.getString("rc_item"));
				rentalClassVO.setRc_detail(rs.getString("rc_detail"));
				rentalClassVO.setRc_deposit(rs.getInt("rc_deposit"));
				rentalClassVO.setRc_price(rs.getInt("rc_price"));
				rentalClassVO.setRc_total_count(rs.getInt("rc_total_count"));
				rentalClassVO.setRc_total_score(rs.getInt("rc_total_score"));
				rentalClassVO.setRc_storage(rs.getInt("rc_storage"));
				rentalClassVO.setRc_status(rs.getString("rc_status"));	
				list.add(rentalClassVO); 
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

	public void changeRc_status(Integer rc_no,String rc_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(Update_One_Status);
			
			pstmt.setString(1, rc_status);
			pstmt.setInt(2, rc_no);
			
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

	public List<String> getAllRc_Item() {
		
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GETALLRCITEM);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String rc_item = rs.getString("rc_item");
				list.add(rc_item); 
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
		return list;
	}

	public List<RentalClassVO> findByRc_item(String rc_item) {
		
		List<RentalClassVO> list = new ArrayList<RentalClassVO>();
		RentalClassVO rentalClassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_Rc_item);
			
			pstmt.setString(1, rc_item);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalClassVO = new RentalClassVO();		
				rentalClassVO.setRc_no(rs.getInt("rc_no"));
				rentalClassVO.setRc_name(rs.getString("rc_name"));
				rentalClassVO.setRc_item(rs.getString("rc_item"));
				rentalClassVO.setRc_detail(rs.getString("rc_detail"));
				rentalClassVO.setRc_deposit(rs.getInt("rc_deposit"));
				rentalClassVO.setRc_price(rs.getInt("rc_price"));
				rentalClassVO.setRc_total_count(rs.getInt("rc_total_count"));
				rentalClassVO.setRc_total_score(rs.getInt("rc_total_score"));
				rentalClassVO.setRc_storage(rs.getInt("rc_storage"));
				rentalClassVO.setRc_status(rs.getString("rc_status"));	
				list.add(rentalClassVO); 
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
