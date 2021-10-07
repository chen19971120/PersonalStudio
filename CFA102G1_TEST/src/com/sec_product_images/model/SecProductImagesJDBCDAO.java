package com.sec_product_images.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SecProductImagesJDBCDAO implements I_SecProductImagesDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.SEC_PRODUCT_IMAGES(SPI_NO, SPIM_IMG)VALUES(?, ?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA102G1.SEC_PRODUCT_IMAGES SET SPI_NO=?, SPIM_IMG=? WHERE SPIM_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.SEC_PRODUCT_IMAGES WHERE SPIM_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.SEC_PRODUCT_IMAGES WHERE SPIM_NO=?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.SEC_PRODUCT_IMAGES";
	
	@Override
	public SecProductImagesVO insert(SecProductImagesVO secProductImages) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, secProductImages.getSpi_no());
			pstmt.setBytes(2, secProductImages.getSpim_img());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				secProductImages.setSpim_no(rs.getInt(1));
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
		return secProductImages;
	}
	
	@Override
	public void update(SecProductImagesVO secProductImages) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, secProductImages.getSpi_no());
			pstmt.setBytes(2, secProductImages.getSpim_img());
			pstmt.setInt(3, secProductImages.getSpim_no());

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
	public void delete(Integer spim_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, spim_no);
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
	public SecProductImagesVO findByPk(Integer spim_no) {
		SecProductImagesVO secProductImages = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, spim_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				secProductImages = new SecProductImagesVO();
				secProductImages.setSpim_no(rs.getInt("SPIM_NO"));
				secProductImages.setSpi_no(rs.getInt("SPI_NO"));
				secProductImages.setSpim_img(rs.getBytes("SPIM_IMG"));
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
		return secProductImages;
	}
	
	@Override
	public List<SecProductImagesVO> getAll() {
		SecProductImagesVO secProductImages = null;
		List<SecProductImagesVO> lsitSecProductImages = new ArrayList<SecProductImagesVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				secProductImages = new SecProductImagesVO();
				secProductImages.setSpim_no(rs.getInt("SPIM_NO"));
				secProductImages.setSpi_no(rs.getInt("SPI_NO"));
				secProductImages.setSpim_img(rs.getBytes("SPIM_IMG"));
				lsitSecProductImages.add(secProductImages);
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
		return lsitSecProductImages;
	}              
	
	
//測試驗證>>>>>>>>>>>>>>>>>>>>>>>>>>
//			public static void main (String[] args) {
//				
//				//測試INSERT(OK).........................................
//				SecProductImagesJDBCDAO dao = new SecProductImagesJDBCDAO();
//				SecProductImagesVO secProductImages = new SecProductImagesVO();
//				secProductImages.setSpi_no(2);
//				secProductImages.setSpim_img(getPictureByteArray("Test_IMG\\123.PNG"));
//				secProductImages = dao.insert(secProductImages);
//				System.out.println(secProductImages.getSpim_no());
//				
//				//測試UPDATE(OK).........................................
//				SecProductImagesJDBCDAO dao = new SecProductImagesJDBCDAO();
//				SecProductImagesVO secProductImages = new SecProductImagesVO();
//				secProductImages.setSpim_no(3);
//				secProductImages.setSpi_no(2);
//				secProductImages.setSpim_img(getPictureByteArray("C:\\CFA102G1\\CFA102G1\\Test_IMG\\宏碁電腦_1.jpg"));
//				dao.update(secProductImages);
//				System.out.println("OK!!");
//				
//				//測試DELETE(OK).........................................
//				SecProductImagesJDBCDAO dao = new SecProductImagesJDBCDAO();
//				dao.delete(3);
//				System.out.println("OK!!");
//
//				//測試FIND_BY_PK(OK).........................................
//				SecProductImagesJDBCDAO dao = new SecProductImagesJDBCDAO();
//				SecProductImagesVO secProductImages = dao.findByPk(4);
//				readPicture(secProductImages.getSpim_img());
//				System.out.println(secProductImages.getSpim_no() + ",\t");
//				System.out.println(secProductImages.getSpi_no() + ".");
//
//				//測試GET_ALL(OK).........................................
//				SecProductImagesJDBCDAO dao = new SecProductImagesJDBCDAO();
//				List<SecProductImagesVO> list = dao.getAll();
//				
//				for(SecProductImagesVO secProductImages : list) {
//					readPicture(secProductImages.getSpim_img());
//					System.out.println(secProductImages.getSpim_no() + ",\t");
//					System.out.println(secProductImages.getSpi_no() + ".");
//					System.out.println("..........................................");
//				}
//
//
//			}
//			
//			// 使用byte[]方式將照片存入BLOB
//			public static byte[] getPictureByteArray(String path){
//				FileInputStream fis;
//				byte[] buffer = null;
//				try {
//					fis = new FileInputStream(path);
//					buffer = new byte[fis.available()];  //根據輸入資料設定buffer的長度
//					fis.read(buffer);
//					fis.close();
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return buffer;
//			}
//			
//			//byte[]方法將讀到的照片存下來
//			public static void readPicture(byte[] bytes){
//				FileOutputStream fos;
//				try {
//					fos = new FileOutputStream("C:\\CFA102G1\\CFA102G1_TEST\\Test_IMG\\out.png");
//					fos.write(bytes);
//					fos.flush();
//					fos.close();
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//<<<<<<<<<<<<<<<<<<<<<<<<<測試驗證
}
