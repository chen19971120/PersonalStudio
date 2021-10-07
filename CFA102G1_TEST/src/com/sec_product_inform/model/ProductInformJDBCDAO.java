package com.sec_product_inform.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductInformJDBCDAO implements I_ProductInformDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return productInformVO;
	}

	@Override
	public void update(ProductInformVO productInformVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, productInformVO.getSpi_name());
			pstmt.setInt(2, productInformVO.getSpc_no());
			pstmt.setString(3, productInformVO.getSpi_content());
			pstmt.setInt(4, productInformVO.getSpi_pri());
			pstmt.setInt(5, productInformVO.getSpi_stock());
			pstmt.setString(6, productInformVO.getSpi_sta());
			pstmt.setInt(7, productInformVO.getSpi_no());
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
	public void delete(Integer spi_no) {

		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, spi_no);
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
	public ProductInformVO findByPK(Integer spi_no) {
		ProductInformVO productInform = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return productInform;
	}

	@Override
	public ProductInformVO findBySPI_Name(String spi_name) {
		ProductInformVO productInform = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return listProductInform;
	}
	
	
	//測試驗證>>>>>>>>>>>>>>>>>>>>>>>>>>
//		public static void main (String[] args) {
//			
//			//測試INSERT(OK).........................................
//			ProductInformJDBCDAO dao = new ProductInformJDBCDAO();
//			ProductInformVO productInform = new ProductInformVO();
//			productInform.setSpi_name("宏碁電腦");
//			productInform.setSpc_no(1);
//			productInform.setSpi_content("超強宏碁電腦啦!!!!!超強宏碁電腦啦!!!!!超強宏碁電腦啦!!!!!超強宏碁電腦啦!!!!!");
//			productInform.setSpi_pri(20000);
//			productInform.setSpi_stock(1);
//			productInform.setSpi_sta("上架");
//			productInform = dao.insert(productInform);
//			System.out.println(productInform.getSpi_no());
//			
//			//測試UPDATE(OK).........................................
//			ProductInformJDBCDAO dao = new ProductInformJDBCDAO();
//			ProductInformVO productInform = new ProductInformVO();
//			productInform.setSpi_no(3);
//			productInform.setSpi_name("宏碁電腦");
//			productInform.setSpc_no(9);
//			productInform.setSpi_content("超強宏碁電腦啦!!!!!超強宏碁電腦啦!!!!!超強宏碁電腦啦!!!!!超強宏碁電腦啦!!!!!");
//			productInform.setSpi_pri(20000);
//			productInform.setSpi_stock(0);
//			productInform.setSpi_sta("下架");
//			dao.update(productInform);
//			
//			//測試DELETE(OK).........................................
//			ProductInformJDBCDAO dao = new ProductInformJDBCDAO();
//			dao.delete(3);
//
//			//測試FIND_BY_PK(OK).........................................
//			ProductInformJDBCDAO dao = new ProductInformJDBCDAO();
//			ProductInformVO productInform = dao.findByPK(2);
//			System.out.println(productInform.getSpi_no() + ",");
//			System.out.println(productInform.getSpi_name() + ",");
//			System.out.println(productInform.getSpc_no() + ",");
//			System.out.println(productInform.getSpi_content() + ",");
//			System.out.println(productInform.getSpi_pri() + ",");
//			System.out.println(productInform.getSpi_stock() + ",");
//			System.out.println(productInform.getSpi_sta() + ".");
//			
//			//測試FIND_BY_NAME().........................................
//			ProductInformJDBCDAO dao = new ProductInformJDBCDAO();
//			ProductInformVO productInform = dao.findBySPI_Name("華碩電腦");
//			System.out.println(productInform.getSpi_no() + ",");
//			System.out.println(productInform.getSpi_name() + ",");
//			System.out.println(productInform.getSpc_no() + ",");
//			System.out.println(productInform.getSpi_content() + ",");
//			System.out.println(productInform.getSpi_pri() + ",");
//			System.out.println(productInform.getSpi_stock() + ",");
//			System.out.println(productInform.getSpi_sta() + ".");
//
//			//測試GET_ALL().........................................
//			ProductInformJDBCDAO dao = new ProductInformJDBCDAO();
//			List<ProductInformVO> list = dao.getAll();
//			for(ProductInformVO productInform : list) {
//				System.out.println(productInform.getSpi_no() + ",");
//				System.out.println(productInform.getSpi_name() + ",");
//				System.out.println(productInform.getSpc_no() + ",");
//				System.out.println(productInform.getSpi_content() + ",");
//				System.out.println(productInform.getSpi_pri() + ",");
//				System.out.println(productInform.getSpi_stock() + ",");
//				System.out.println(productInform.getSpi_sta() + ".");
//				System.out.println(".............................................");
//			}
	//<<<<<<<<<<<<<<<<<<<<<<<<<測試驗證
//		}
}
