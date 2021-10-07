package com.sec_product_class.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductClassJDBCDAO implements I_ProductClassDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.SEC_PRODUCT_CLASS(SPC_NAME) VALUES(?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.SEC_PRODUCT_CLASS SET SPC_NAME = ? WHERE SPC_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.SEC_PRODUCT_CLASS WHERE SPC_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.SEC_PRODUCT_CLASS WHERE SPC_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.SEC_PRODUCT_CLASS";
	
	@Override
	public ProductClassVO insert(ProductClassVO productClassVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, productClassVO.getSpc_name());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productClassVO.setSpc_no(rs.getInt(1));
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
		return productClassVO;
	}

	@Override
	public void update(ProductClassVO productClassVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, productClassVO.getSpc_name());
			pstmt.setInt(2, productClassVO.getSpc_no());

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
	public void delete(Integer spc_no) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, spc_no);
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
	public ProductClassVO findByPK(Integer spc_no) {
		ProductClassVO productClass = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, spc_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productClass = new ProductClassVO();
				productClass.setSpc_no(rs.getInt("SPC_NO"));
				productClass.setSpc_name(rs.getString("SPC_NAME"));
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
		return productClass;
	}

	@Override
	public List<ProductClassVO> getAll() {
		ProductClassVO productClass = null;
		List<ProductClassVO> listProductClass = new ArrayList<ProductClassVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productClass = new ProductClassVO();
				productClass.setSpc_no(rs.getInt("SPC_NO"));
				productClass.setSpc_name(rs.getString("SPC_NAME"));
				listProductClass.add(productClass);
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
		return listProductClass;
	}
	
	
//測試驗證>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static void main (String[] args) {
		
//		//測試INSERT(OK).........................................
//		ProductClassJDBCDAO dao = new ProductClassJDBCDAO();
//		ProductClassVO pclass = new ProductClassVO();
//		pclass.setSpc_name("隨便周邊");
//		pclass = dao.insert(pclass);
//		System.out.println(pclass.getSpc_no() + " : " + pclass.getSpc_name());
		
//		//測試DELETE(OK).........................................
//		ProductClassJDBCDAO dao = new ProductClassJDBCDAO();
//		dao.delete(8);
		
//		//測試UPDATE(OK).........................................
//		ProductClassJDBCDAO dao = new ProductClassJDBCDAO();
//		ProductClassVO pclass = new ProductClassVO();
//		pclass.setSpc_no(9);
//		pclass.setSpc_name("桌上型電腦");
//		dao.update(pclass);

//		//測試FIND_BY_PK(OK).........................................
//		ProductClassJDBCDAO dao = new ProductClassJDBCDAO();
//		ProductClassVO pclass = dao.findByPK(6);
//		System.out.println(pclass.getSpc_no() + ",");
//		System.out.println(pclass.getSpc_name());

//		//測試GET_ALL(OK).........................................
//		ProductClassJDBCDAO dao = new ProductClassJDBCDAO();
//		List<ProductClassVO> list = dao.getAll();
//		for(ProductClassVO pclass : list) {
//			System.out.println(pclass.getSpc_no() + " : " + pclass.getSpc_name() + ".");
//		}
		
//<<<<<<<<<<<<<<<<<<<<<<<<<測試驗證

	}

}
