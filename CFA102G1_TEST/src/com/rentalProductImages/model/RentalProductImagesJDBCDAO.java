package com.rentalProductImages.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalProductImagesJDBCDAO implements I_RentalProductImagesDAO{
	
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" 
			+ "rewriteBatchedStatements=true&serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO RENTAL_PRODUCT_IMAGES (rc_no,rpi_img) VALUES(?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE RENTAL_PRODUCT_IMAGES set rc_no=?,rpi_img=? where rpi_no = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM RENTAL_PRODUCT_IMAGES where rpi_no = ?";
	private static final String FIND_BY_PK = 
			"SELECT * FROM RENTAL_PRODUCT_IMAGES WHERE rpi_no = ?";
	private static final String GET_ALL = 
			"SELECT * FROM RENTAL_PRODUCT_IMAGES";
	private static final String FIND_BY_RC_NO = 
			"SELECT * FROM RENTAL_PRODUCT_IMAGES where RC_no=?";
	
	public RentalProductImagesVO insert(RentalProductImagesVO rentalProductImagesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, rentalProductImagesVO.getRc_no());
			pstmt.setBytes(2, rentalProductImagesVO.getRpi_img());
			
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				rentalProductImagesVO.setRpi_no(rs.getInt(1));
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
		return rentalProductImagesVO;
	}

	public void update(RentalProductImagesVO rentalProductImagesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, rentalProductImagesVO.getRc_no());
			pstmt.setBytes(2, rentalProductImagesVO.getRpi_img());
			pstmt.setInt(3, rentalProductImagesVO.getRpi_no());
			
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

	public void delete(Integer rpi_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, rpi_no);
			
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

	public RentalProductImagesVO findByPK(Integer rpi_no) {
		RentalProductImagesVO rentalProductImagesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, rpi_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalProductImagesVO = new RentalProductImagesVO();		
				rentalProductImagesVO.setRpi_no(rs.getInt("rpi_no"));
				rentalProductImagesVO.setRc_no(rs.getInt("rc_no"));			
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
		return rentalProductImagesVO;
	}

	public List<RentalProductImagesVO> getAll() {
		List<RentalProductImagesVO> list = new ArrayList<RentalProductImagesVO>();
		RentalProductImagesVO rentalProductImagesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalProductImagesVO = new RentalProductImagesVO();		
				rentalProductImagesVO.setRpi_no(rs.getInt("rpi_no"));
				rentalProductImagesVO.setRc_no(rs.getInt("rc_no"));
				list.add(rentalProductImagesVO);
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

	public List<RentalProductImagesVO> findbyRc_no(Integer rc_no) {
		List<RentalProductImagesVO> list = new ArrayList<RentalProductImagesVO>();
		RentalProductImagesVO rentalProductImagesVO = null;

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
				rentalProductImagesVO = new RentalProductImagesVO();		
				rentalProductImagesVO.setRpi_no(rs.getInt("rpi_no"));
				rentalProductImagesVO.setRc_no(rs.getInt("rc_no"));
				list.add(rentalProductImagesVO);
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
