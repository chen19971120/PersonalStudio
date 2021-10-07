package com.productReviews.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductReviewsJDBCDAO implements I_ProductReviewsDAO{

	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" 
			+ "rewriteBatchedStatements=true&serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO PRODUCT_REVIEWS (rc_no,ro_no,pr_content,pr_images,pr_score,pr_status) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE PRODUCT_REVIEWS set rc_no=?,ro_no=?,pr_content=?,pr_images=?,pr_score=?,pr_status=? where pr_no = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM PRODUCT_REVIEWS where pr_no = ?";
	private static final String FIND_BY_PK = 
			"SELECT * FROM PRODUCT_REVIEWS WHERE pr_no = ?";
	private static final String GET_ALL = 
			"SELECT * FROM PRODUCT_REVIEWS";
	
	public ProductReviewsVO insert(ProductReviewsVO productReviewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, productReviewsVO.getRc_no());
			pstmt.setInt(2, productReviewsVO.getRo_no());
			pstmt.setString(3, productReviewsVO.getPr_content());
			pstmt.setBytes(4, productReviewsVO.getPr_images());
			pstmt.setInt(5, productReviewsVO.getPr_score());
			pstmt.setString(6, productReviewsVO.getPr_status());
				
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productReviewsVO.setPr_no(rs.getInt(1));
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
		return productReviewsVO;
	}

	public void update(ProductReviewsVO productReviewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, productReviewsVO.getRc_no());
			pstmt.setInt(2, productReviewsVO.getRo_no());
			pstmt.setString(3, productReviewsVO.getPr_content());
			pstmt.setBytes(4, productReviewsVO.getPr_images());
			pstmt.setInt(5, productReviewsVO.getPr_score());
			pstmt.setString(6, productReviewsVO.getPr_status());
			pstmt.setInt(7, productReviewsVO.getPr_no());
			
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

	public void delete(Integer pr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, pr_no);
			
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

	public ProductReviewsVO findByPK(Integer pr_no) {
		ProductReviewsVO productReviewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, pr_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productReviewsVO = new ProductReviewsVO();		
				productReviewsVO.setPr_no(rs.getInt("pr_no"));
				productReviewsVO.setRc_no(rs.getInt("rc_no"));
				productReviewsVO.setRo_no(rs.getInt("ro_no"));
				productReviewsVO.setPr_content(rs.getString("pr_content"));
				productReviewsVO.setPr_images(rs.getBytes("pr_images"));
				productReviewsVO.setPr_score(rs.getInt("pr_score"));
				productReviewsVO.setPr_status(rs.getString("pr_status"));			
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
		return productReviewsVO;
	}

	public List<ProductReviewsVO> getAll() {
		List<ProductReviewsVO> list = new ArrayList<ProductReviewsVO>();
		ProductReviewsVO productReviewsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productReviewsVO = new ProductReviewsVO();		
				productReviewsVO.setPr_no(rs.getInt("pr_no"));
				productReviewsVO.setRc_no(rs.getInt("rc_no"));
				productReviewsVO.setRo_no(rs.getInt("ro_no"));
				productReviewsVO.setPr_content(rs.getString("pr_content"));
				productReviewsVO.setPr_images(rs.getBytes("pr_images"));
				productReviewsVO.setPr_score(rs.getInt("pr_score"));
				productReviewsVO.setPr_status(rs.getString("pr_status"));		
				list.add(productReviewsVO); 
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
