package com.sec_product_class.model;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class ProductClassDAO implements I_ProductClassDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, productClassVO.getSpc_name());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productClassVO.setSpc_no(rs.getInt(1));
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
		return productClassVO;
	}

	@Override
	public void update(ProductClassVO productClassVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, productClassVO.getSpc_name());
			pstmt.setInt(2, productClassVO.getSpc_no());

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
	public void delete(Integer spc_no) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, spc_no);
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
	public ProductClassVO findByPK(Integer spc_no) {
		ProductClassVO productClass = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, spc_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productClass = new ProductClassVO();
				productClass.setSpc_no(rs.getInt("SPC_NO"));
				productClass.setSpc_name(rs.getString("SPC_NAME"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productClass = new ProductClassVO();
				productClass.setSpc_no(rs.getInt("SPC_NO"));
				productClass.setSpc_name(rs.getString("SPC_NAME"));
				listProductClass.add(productClass);
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
		return listProductClass;
	}
}
