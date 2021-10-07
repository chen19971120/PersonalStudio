package com.sec_product_inform.model;

import java.sql.Connection;
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

public class ProductInformDAO implements I_ProductInformDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.SEC_PRODUCT_INFORMATION(SPI_NAME, SPC_NO, SPI_CONTENT, SPI_PRI, SPI_STOCK, SPI_STA) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.SEC_PRODUCT_INFORMATION SET SPI_NAME=?, SPC_NO=?, SPI_CONTENT=?, SPI_PRI=?, SPI_STOCK=?, SPI_STA=? WHERE SPI_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.SEC_PRODUCT_INFORMATION WHERE SPI_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.SEC_PRODUCT_INFORMATION WHERE SPI_NO = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM CFA102G1.SEC_PRODUCT_INFORMATION WHERE SPI_NAME = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.SEC_PRODUCT_INFORMATION";
	
	@Override
	public ProductInformVO insert(ProductInformVO productInformVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, productInformVO.getSpi_name());
			pstmt.setInt(2, productInformVO.getSpc_no());
			pstmt.setString(3, productInformVO.getSpi_content());
			pstmt.setInt(4, productInformVO.getSpi_pri());
			pstmt.setInt(5, productInformVO.getSpi_stock());
			pstmt.setString(6, productInformVO.getSpi_sta());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productInformVO.setSpi_no(rs.getInt(1));
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
		return productInformVO;
	}

	@Override
	public void update(ProductInformVO productInformVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, productInformVO.getSpi_name());
			pstmt.setInt(2, productInformVO.getSpc_no());
			pstmt.setString(3, productInformVO.getSpi_content());
			pstmt.setInt(4, productInformVO.getSpi_pri());
			pstmt.setInt(5, productInformVO.getSpi_stock());
			pstmt.setString(6, productInformVO.getSpi_sta());
			pstmt.setInt(7, productInformVO.getSpi_no());
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
	public void delete(Integer spi_no) {

		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, spi_no);
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
	public ProductInformVO findByPK(Integer spi_no) {
		ProductInformVO productInform = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, spi_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productInform = new ProductInformVO();
				productInform.setSpi_no(rs.getInt("SPI_NO"));
				productInform.setSpi_name(rs.getString("SPI_NAME"));
				productInform.setSpc_no(rs.getInt("SPC_NO"));
				productInform.setSpi_content(rs.getString("SPI_CONTENT"));
				productInform.setSpi_pri(rs.getInt("SPI_PRI"));
				productInform.setSpi_stock(rs.getInt("SPI_STOCK"));
				productInform.setSpi_sta(rs.getString("SPI_STA"));
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
		return productInform;
	}

	@Override
	public ProductInformVO findBySPI_Name(String spi_name) {
		ProductInformVO productInform = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_NAME);
			pstmt.setString(1, spi_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productInform = new ProductInformVO();
				productInform.setSpi_no(rs.getInt("SPI_NO"));
				productInform.setSpi_name(rs.getString("SPI_NAME"));
				productInform.setSpc_no(rs.getInt("SPC_NO"));
				productInform.setSpi_content(rs.getString("SPI_CONTENT"));
				productInform.setSpi_pri(rs.getInt("SPI_PRI"));
				productInform.setSpi_stock(rs.getInt("SPI_STOCK"));
				productInform.setSpi_sta(rs.getString("SPI_STA"));
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
		return productInform;
	}

	@Override
	public List<ProductInformVO> getAll() {
		ProductInformVO productInform = null;
		List<ProductInformVO> listProductInform = new ArrayList<ProductInformVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productInform = new ProductInformVO();
				productInform.setSpi_no(rs.getInt("SPI_NO"));
				productInform.setSpi_name(rs.getString("SPI_NAME"));
				productInform.setSpc_no(rs.getInt("SPC_NO"));
				productInform.setSpi_content(rs.getString("SPI_CONTENT"));
				productInform.setSpi_pri(rs.getInt("SPI_PRI"));
				productInform.setSpi_stock(rs.getInt("SPI_STOCK"));
				productInform.setSpi_sta(rs.getString("SPI_STA"));
				listProductInform.add(productInform);
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
		return listProductInform;
	}
}
