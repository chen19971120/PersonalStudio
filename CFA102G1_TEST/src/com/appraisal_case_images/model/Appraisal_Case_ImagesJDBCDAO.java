package com.appraisal_case_images.model;

import java.io.FileInputStream;
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

public class Appraisal_Case_ImagesJDBCDAO implements I_Appraisal_Case_ImagesDAO {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/CFA102G1?" + "rewriteBatchedStatements=true&" + "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";

	private static final String INSERT_STMT = "INSERT INTO CFA102G1.APPRAISAL_CASE_IMAGES(ACA_NO,ACI_IMG) VALUES(?,?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.APPRAISAL_CASE_IMAGES SET ACI_IMG = ? WHERE ACI_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.APPRAISAL_CASE_IMAGES WHERE ACI_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.APPRAISAL_CASE_IMAGES WHERE ACI_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.APPRAISAL_CASE_IMAGES";

	@Override
	public Appraisal_Case_ImagesVO insert(Appraisal_Case_ImagesVO appraisal_Case_ImagesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, appraisal_Case_ImagesVO.getAca_no());
			pstmt.setBytes(2, appraisal_Case_ImagesVO.getAci_img());

			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				appraisal_Case_ImagesVO.setAci_no(rs.getInt(1));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return appraisal_Case_ImagesVO;
	}

	@Override
	public void update(Appraisal_Case_ImagesVO appraisal_Case_ImagesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setBytes(1, appraisal_Case_ImagesVO.getAci_img());
			pstmt.setInt(2, appraisal_Case_ImagesVO.getAci_no());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer aci_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, aci_no);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Appraisal_Case_ImagesVO findByPK(Integer aci_no) {
		Appraisal_Case_ImagesVO appraisal_case_images = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, aci_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_case_images = new Appraisal_Case_ImagesVO();
				appraisal_case_images.setAci_no(rs.getInt("ACI_NO"));
				appraisal_case_images.setAca_no(rs.getInt("ACA_NO"));
				appraisal_case_images.setAci_img(rs.getBytes("ACI_IMG"));

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return appraisal_case_images;
	}

	@Override
	public List<Appraisal_Case_ImagesVO> getAll() {
		List<Appraisal_Case_ImagesVO> appraisal_case_imagesList = new ArrayList<Appraisal_Case_ImagesVO>();
		Appraisal_Case_ImagesVO appraisal_case_images = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appraisal_case_images = new Appraisal_Case_ImagesVO();
				appraisal_case_images.setAci_no(rs.getInt("ACI_NO"));
				appraisal_case_images.setAca_no(rs.getInt("ACA_NO"));
				appraisal_case_images.setAci_img(rs.getBytes("ACI_IMG"));
				appraisal_case_imagesList.add(appraisal_case_images);

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
//			se.printStackTrace();
//			錯誤拋到前台
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return appraisal_case_imagesList;
	}

	public static void main(String[] args) {

//		測試insert
		Appraisal_Case_ImagesJDBCDAO dao = new Appraisal_Case_ImagesJDBCDAO();
		Appraisal_Case_ImagesVO acivo = new Appraisal_Case_ImagesVO();
		acivo.setAca_no(9);
		acivo.setAci_img(getPictureByteArray("D:\\Git\\GitSample\\CFA102TEST\\WebContent\\back_end\\appraisal_case_images\\images\\popcat.png"));
		dao.insert(acivo);
		System.out.println(acivo.getAci_no());
//		測試OK

//		測試update
//		Appraisal_Case_ImagesJDBCDAO dao = new Appraisal_Case_ImagesJDBCDAO();
//		Appraisal_Case_ImagesVO acivo = new Appraisal_Case_ImagesVO();
//		acivo.setAci_img(acivo.getPictureByteArray("images/電腦.jpg"));
//		acivo.setAci_no(103);
//		dao.update(acivo);
//		測試OK

//		測試delete
//		Appraisal_Case_ImagesJDBCDAO dao = new Appraisal_Case_ImagesJDBCDAO();
//		dao.delete(4);
//		測試OK

//		測試findByPK
//		Appraisal_Case_ImagesJDBCDAO dao = new Appraisal_Case_ImagesJDBCDAO();
//		Appraisal_Case_ImagesVO acivo = dao.findByPK(103);
//		System.out.println(acivo.getAci_no());
//		System.out.println(acivo.getAca_no());
//		readPicture(acivo.getAci_img());
//		測試OK

//		測試GetAll
//		Appraisal_Case_ImagesJDBCDAO dao = new Appraisal_Case_ImagesJDBCDAO();
//		List<Appraisal_Case_ImagesVO> list = dao.getAll();
//		for(Appraisal_Case_ImagesVO acivo:list) {
//			System.out.println(acivo.getAci_no());
//			System.out.println(acivo.getAca_no());
//			readPicture(acivo.getAci_img());
//		}
//		測試OK
	}
//	
	// 使用byte[]方式寫入資料庫
	public static byte[] getPictureByteArray(String path) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(path);
			buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return buffer;
	}
//	
//	// 使用byte[]方式讀取圖片
//	public static void readPicture(byte[] bytes){
//		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream("Output/download.png");
//			fos.write(bytes);
//			fos.flush();
//			fos.close();
//		}catch(IOException ie){
//			ie.printStackTrace();
//		}
//	}
}