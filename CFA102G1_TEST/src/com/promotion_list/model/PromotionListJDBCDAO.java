package com.promotion_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionListJDBCDAO implements I_PromotionListDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.PROMOTION_LIST(PROMOTIONS_NO, SPI_NO, SPI_PRICE) VALUES(?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.PROMOTION_LIST SET SPI_PRICE=? WHERE PROMOTIONS_NO = ? and SPI_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.PROMOTION_LIST WHERE PROMOTIONS_NO = ? and SPI_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.PROMOTION_LIST WHERE PROMOTIONS_NO = ? and SPI_NO=?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.PROMOTION_LIST";

	@Override
	public void insert(PromotionListVO promotionList) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, promotionList.getPromotions_no());
			pstmt.setInt(2, promotionList.getSpi_no());
			pstmt.setInt(3, promotionList.getSpi_price());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
	public void update(PromotionListVO promotionList) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, promotionList.getSpi_price());
			pstmt.setInt(2, promotionList.getPromotions_no());
			pstmt.setInt(3, promotionList.getSpi_no());
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
	public void delete(Integer promotions_no, Integer spi_no) {

		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, promotions_no);
			pstmt.setInt(2, spi_no);
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
	public PromotionListVO findByPK(Integer promotions_no, Integer spi_no) {
		PromotionListVO promotionList = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, promotions_no);
			pstmt.setInt(2, spi_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				promotionList = new PromotionListVO();
				promotionList.setPromotions_no(rs.getInt("PROMOTIONS_NO"));
				promotionList.setSpi_no(rs.getInt("SPI_NO"));
				promotionList.setSpi_price(rs.getInt("SPI_PRICE"));
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
		return promotionList;
	}

	@Override
	public List<PromotionListVO> getAll() {
		PromotionListVO promotionList = null;
		List<PromotionListVO> listPromotionList = new ArrayList<PromotionListVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				promotionList = new PromotionListVO();
				promotionList.setPromotions_no(rs.getInt("PROMOTIONS_NO"));
				promotionList.setSpi_no(rs.getInt("SPI_NO"));
				promotionList.setSpi_price(rs.getInt("SPI_PRICE"));
				listPromotionList.add(promotionList);
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
		return listPromotionList;
	}
	

//測試驗證>>>>>>>>>>>>>>>>>>>>>>>>>>
//	public static void main (String[] args) {
//		
//		//測試INSERT(OK).........................................
//		PromotionListJDBCDAO dao = new PromotionListJDBCDAO();
//		PromotionListVO promotionList = new PromotionListVO();
//		promotionList.setPromotions_no(1);
//		promotionList.setSpi_no(4);
//		promotionList.setSpi_price(15000);
//		dao.insert(promotionList);
//		
//		//測試UPDATE(OK).........................................
//		PromotionListJDBCDAO dao = new PromotionListJDBCDAO();
//		PromotionListVO promotionList = new PromotionListVO();
//		promotionList.setPromotions_no(1);
//		promotionList.setSpi_no(4);
//		promotionList.setSpi_price(17000);
//		dao.update(promotionList);
//		
//		//測試DELETE(OK).........................................
//		PromotionListJDBCDAO dao = new PromotionListJDBCDAO();
//		dao.delete(1,4);
//
//		//測試FIND_BY_PK(OK).........................................
//		PromotionListJDBCDAO dao = new PromotionListJDBCDAO();
//		PromotionListVO promotionList = dao.findByPK(1, 2);
//		System.out.println(promotionList.getPromotions_no() + ",");
//		System.out.println(promotionList.getSpi_no() + ",");
//		System.out.println(promotionList.getSpi_price() + ".");
//
//
//		//測試GET_ALL(OK).........................................
//		PromotionListJDBCDAO dao = new PromotionListJDBCDAO();
//		List<PromotionListVO> list = dao.getAll();
//		for(PromotionListVO promotionList : list) {
//			System.out.println(promotionList.getPromotions_no() + ",");
//			System.out.println(promotionList.getSpi_no() + ",");
//			System.out.println(promotionList.getSpi_price() + ".");
//			System.out.println(".............................................");
//		}
//<<<<<<<<<<<<<<<<<<<<<<<<<測試驗證
//	}

}
