package com.promotion.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PromotionJDBCDAO implements I_PromotionDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.PROMOTIONS(PROMOTIONS_NAME,PROMOTIONS_START_DATE,PROMOTIONS_END_DATE)VALUES(?, ?, ?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA102G1.PROMOTIONS SET PROMOTIONS_NAME=? ,PROMOTIONS_START_DATE=? ,PROMOTIONS_END_DATE=? WHERE PROMOTIONS_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.PROMOTIONS WHERE PROMOTIONS_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.PROMOTIONS WHERE PROMOTIONS_NO=?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.PROMOTIONS";
	
	@Override
	public PromotionVO insert(PromotionVO promotionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, promotionVO.getPromotion_name());
			pstmt.setTimestamp(2, promotionVO.getPromotion_start_date());
			pstmt.setTimestamp(3, promotionVO.getPromotion_end_date());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				promotionVO.setPromotion_no(rs.getInt(1));
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
		return promotionVO;
	}

	@Override
	public void update(PromotionVO promotionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, promotionVO.getPromotion_name());
			pstmt.setTimestamp(2, promotionVO.getPromotion_start_date());
			pstmt.setTimestamp(3, promotionVO.getPromotion_end_date());
			pstmt.setInt(4, promotionVO.getPromotion_no());
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
	public void delete(Integer promotion_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, promotion_no);
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
	public PromotionVO findByPk(Integer promotion_no) {
		PromotionVO promotion = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, promotion_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				promotion = new PromotionVO();
				promotion.setPromotion_no(rs.getInt("PROMOTIONS_NO"));
				promotion.setPromotion_name(rs.getString("PROMOTIONS_NAME"));
				promotion.setPromotion_start_date(rs.getTimestamp("PROMOTIONS_START_DATE"));
				promotion.setPromotion_end_date(rs.getTimestamp("PROMOTIONS_END_DATE"));
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
		return promotion;
	}

	@Override
	public List<PromotionVO> getAll() {
		PromotionVO promotion = null;
		List<PromotionVO> listPromotion = new ArrayList<PromotionVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				promotion = new PromotionVO();
				promotion.setPromotion_no(rs.getInt("PROMOTIONS_NO"));
				promotion.setPromotion_name(rs.getString("PROMOTIONS_NAME"));
				promotion.setPromotion_start_date(rs.getTimestamp("PROMOTIONS_START_DATE"));
				promotion.setPromotion_end_date(rs.getTimestamp("PROMOTIONS_END_DATE"));
				listPromotion.add(promotion);
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
		return listPromotion;
	}
	
//測試驗證>>>>>>>>>>>>>>>>>>>>>>>>>>
//		public static void main (String[] args) {
//			
//			//測試INSERT(OK).........................................
//			PromotionJDBCDAO dao = new PromotionJDBCDAO();
//			PromotionVO promotion = new PromotionVO();
//			promotion.setPromotion_name("快來賣賣賣!!!");
//			promotion.setPromotion_start_date(new Timestamp(System.currentTimeMillis()));
//			promotion.setPromotion_end_date(new Timestamp(System.currentTimeMillis()));
//			promotion = dao.insert(promotion);
//			System.out.println(promotion.getPromotion_no());
//			
//			//測試UPDATE(OK).........................................
//			PromotionJDBCDAO dao = new PromotionJDBCDAO();
//			PromotionVO promotion = new PromotionVO();
//			promotion.setPromotion_no(2);
//			promotion.setPromotion_name("測試改名字2!!!!");
//			promotion.setPromotion_start_date(new Timestamp(System.currentTimeMillis()));
//			promotion.setPromotion_end_date(new Timestamp(System.currentTimeMillis()));
//			dao.update(promotion);
//			System.out.println("OK!!");
			
//			//測試DELETE(OK).........................................
//			PromotionJDBCDAO dao = new PromotionJDBCDAO();
//			dao.delete(3);
//			System.out.println("OK!!");

//			//測試FIND_BY_PK(OK).........................................
//			PromotionJDBCDAO dao = new PromotionJDBCDAO();
//			PromotionVO promotion = dao.findByPk(4);
//			System.out.println(promotion.getPromotion_no() + ", ");
//			System.out.println(promotion.getPromotion_name() + ", ");
//			System.out.println(promotion.getPromotion_start_date() + ", ");
//			System.out.println(promotion.getPromotion_end_date() +".");

//			//測試GET_ALL(OK).........................................
//			PromotionJDBCDAO dao = new PromotionJDBCDAO();
//			List<PromotionVO> list = dao.getAll();
//			for(PromotionVO promotion : list) {
//				System.out.println(promotion.getPromotion_no() + ",\t" +
//						promotion.getPromotion_name() + ",\t" +
//						promotion.getPromotion_start_date() + ",\t" +
//						promotion.getPromotion_end_date() +".");
//			}
//<<<<<<<<<<<<<<<<<<<<<<<<<測試驗證
//
//		}
	

}
