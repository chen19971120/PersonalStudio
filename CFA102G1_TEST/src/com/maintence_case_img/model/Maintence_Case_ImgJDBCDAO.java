package com.maintence_case_img.model;

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

public class Maintence_Case_ImgJDBCDAO implements I_Maintence_Case_ImgDAO {
	
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";

	private static final String INSERT_STMT = "INSERT INTO CFA102G1.MAINTENANCE_CASE_IMAGES(MCA_NO, MCI_BEFORE_IMG,"
			+ "MCI_AFTER_IMG)VALUES(?,?,?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA102G1.MAINTENANCE_CASE_IMAGES SET MCA_NO=?, MCI_BEFORE_IMG=?,"
			+ "MCI_AFTER_IMG=? WHERE MCI_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.MAINTENANCE_CASE_IMAGES WHERE MCI_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.MAINTENANCE_CASE_IMAGES WHERE MCI_BEFORE_IMG=?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.MAINTENANCE_CASE_IMAGES";
	
	@Override
	public Maintence_Case_ImgVO insert(Maintence_Case_ImgVO maintenceCaseimgvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, maintenceCaseimgvo.getMca_no());
			pstmt.setString(2,maintenceCaseimgvo.getMci_before_img());
			pstmt.setBytes(3, maintenceCaseimgvo.getMci_after_img());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				maintenceCaseimgvo.setMci_no(rs.getInt(1));
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
		return maintenceCaseimgvo;
	}
	
	@Override
	public void update(Maintence_Case_ImgVO maintenceCaseimgvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, maintenceCaseimgvo.getMca_no());
			pstmt.setString(2,maintenceCaseimgvo.getMci_before_img());
			pstmt.setBytes(3, maintenceCaseimgvo.getMci_after_img());
			pstmt.setInt(4,maintenceCaseimgvo.getMci_no());

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
	public void delete(Integer mci_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, mci_no);
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
	public Maintence_Case_ImgVO findByPK(String mci_before_img) {
		Maintence_Case_ImgVO maintenceCaseimgvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, mci_before_img);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				maintenceCaseimgvo = new Maintence_Case_ImgVO();
				maintenceCaseimgvo.setMci_no(rs.getInt("MCI_NO"));
				maintenceCaseimgvo.setMca_no(rs.getInt("MCA_NO"));
				maintenceCaseimgvo.setMci_before_img(rs.getString("MCI_BEFORE_IMG"));
				maintenceCaseimgvo.setMci_after_img(rs.getBytes("MCI_AFTER_IMG"));
			}
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
		return maintenceCaseimgvo;
	}
	@Override
	public List<Maintence_Case_ImgVO> getAll() {
		Maintence_Case_ImgVO maintenceCaseimgvo = null;
		List<Maintence_Case_ImgVO> listMaintencecaseimg = new ArrayList<Maintence_Case_ImgVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				maintenceCaseimgvo = new Maintence_Case_ImgVO();
				maintenceCaseimgvo.setMci_no(rs.getInt("MCI_NO"));
				maintenceCaseimgvo.setMca_no(rs.getInt("MCA_NO"));
				maintenceCaseimgvo.setMci_before_img(rs.getString("MCI_BEFORE_IMG"));
				maintenceCaseimgvo.setMci_after_img(rs.getBytes("MCI_AFTER_IMG"));
				listMaintencecaseimg.add(maintenceCaseimgvo);
			}
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
		return listMaintencecaseimg;
	}
	
	
	
	//驗證功能----------------------------------------------------------------------

//	public static void main (String[] args) {
//		
//		
//	//測試INSERT OK
//	Maintence_Case_ImgJDBCDAO dao = new Maintence_Case_ImgJDBCDAO();
//	Maintence_Case_ImgVO mciv= new Maintence_Case_ImgVO();
//	mciv.setMca_no(2);
//	mciv.setMci_before_img(getPictureByteArray("C:\\CFA102_G1專題\\CFA102G1\\testimg\\lenovo1.jpg"));
//	mciv.setMci_after_img(getPictureByteArray("C:\\\\CFA102_G1專題\\\\CFA102G1\\\\testimg\\\\lenovo1.jpg"));
//	mciv=dao.insert(mciv);
//	System.out.println(mciv.getMci_no());


		
	//測試UPDATE(OK).........................................
//	Maintence_Case_ImgJDBCDAO dao = new Maintence_Case_ImgJDBCDAO();
//	Maintence_Case_ImgVO mciv = new Maintence_Case_ImgVO();
//	
//	mciv.setMca_no(2);
//	mciv.setMci_before_img(getPictureByteArray("C:\\CFA102_G1專題\\CFA102G1\\testimg\\lenovo1.jpg"));
//	mciv.setMci_after_img(getPictureByteArray("C:\\CFA102_G1專題\\\\CFA102G1\\testimg\\lenovo1.jpg"));
//	mciv.setMci_no(1);
//	dao.update(mciv);
//	System.out.println("UPDATE成功");
		
    //測試DELETE OK
//	Maintence_Case_ImgJDBCDAO dao = new Maintence_Case_ImgJDBCDAO();
//	dao.delete(3);
//	System.out.println("DELETE成功");
	
	//測試FIND_BY_PK(OK)
//	Maintence_Case_ImgJDBCDAO dao = new Maintence_Case_ImgJDBCDAO();
//	Maintence_Case_ImgVO mciv = dao.findByPK(1);
//	readPicture(mciv.getMci_before_img());
//	readPicture(mciv.getMci_after_img());
//	System.out.println(mciv.getMci_no() + ",\t");
//	System.out.println(mciv.getMca_no() + ".");
//
	//測試GET_ALL(OK)
//	Maintence_Case_ImgJDBCDAO dao = new Maintence_Case_ImgJDBCDAO();
//	List<Maintence_Case_ImgVO> list = dao.getAll();
//		
//	for(Maintence_Case_ImgVO mciv : list) {
//	readPicture(mciv.getMci_before_img());
//	readPicture(mciv.getMci_after_img());
//	System.out.println(mciv.getMci_no());
//	System.out.println(mciv.getMca_no());
//	System.out.println("----------------------------------------------");
//	}	
//}

	
	// 使用byte[]方式將照片存入BLOB
//	public static byte[] getPictureByteArray(String path){
//		FileInputStream fis;
//		byte[] buffer = null;
//		try {
//			fis = new FileInputStream(path);
//			buffer = new byte[fis.available()];  //根據輸入資料設定buffer的長度
//			fis.read(buffer);
//			fis.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return buffer;
//	}
	
	//byte[]方法將讀到的照片存下來
//	public static void readPicture(byte[] bytes){
//		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream("C:\\CFA102_G1專題\\CFA102G1\\testimg\\ouput2.jpg");
//			fos.write(bytes);
//			fos.flush();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
