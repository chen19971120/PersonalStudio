package com.maintence_class.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Maintence_ClassJDBCDAO implements I_Maintence_ClassDAO {
	
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA102G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO CFA102G1.MAINTENANCE_CLASS(MCL_ID) VALUES(?)";
	private static final String UPDATE_STMT = "UPDATE CFA102G1.MAINTENANCE_CLASS SET MCL_ID=? WHERE MCL_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA102G1.MAINTENANCE_CLASS WHERE MCL_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA102G1.MAINTENANCE_CLASS WHERE MCL_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA102G1.MAINTENANCE_CLASS";
	
	@Override
	public Maintence_ClassVO insert(Maintence_ClassVO maintence_classvo) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,maintence_classvo.getMcl_id());
						
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				maintence_classvo.setMcl_no(rs.getInt(1));
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
		return maintence_classvo;
	}
	
	@Override
	public void update(Maintence_ClassVO maintence_classvo) {
			Connection con = null; 
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setString(1,maintence_classvo.getMcl_id());
				pstmt.setInt(2,maintence_classvo.getMcl_no());
				
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
	public void delete(Integer mcl_no) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, mcl_no);
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
	public Maintence_ClassVO findByPK(Integer mcl_no) {
		Maintence_ClassVO maintenceclassvo = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, mcl_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				maintenceclassvo = new Maintence_ClassVO();
				maintenceclassvo.setMcl_no(rs.getInt("MCL_NO"));
				maintenceclassvo.setMcl_id(rs.getString("MCL_ID"));
								
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
		return maintenceclassvo;
	
	}
	@Override
	public List<Maintence_ClassVO> getAll() {
		Maintence_ClassVO maintenceclassvo = null;
		List<Maintence_ClassVO> listMaintence_ClassVO = new ArrayList<Maintence_ClassVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				maintenceclassvo = new Maintence_ClassVO();
				maintenceclassvo.setMcl_no(rs.getInt("MCL_NO"));
				maintenceclassvo.setMcl_id(rs.getString("MCL_ID"));
				listMaintence_ClassVO.add(maintenceclassvo);
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
		return listMaintence_ClassVO;
	}
	
//驗證功能----------------------------------------------------------------------
	
	public static void main(String[] args) {
			
		//驗證INSERT OK
		Maintence_ClassJDBCDAO dao = new Maintence_ClassJDBCDAO();
		Maintence_ClassVO mcv = new Maintence_ClassVO();
			
	    mcv.setMcl_id("風扇故障");
		mcv=dao.insert(mcv);
		System.out.println(mcv.getMcl_no());
		
		
		//驗證UPDATE OK
//		Maintence_ClassJDBCDAO dao = new Maintence_ClassJDBCDAO();
//		Maintence_ClassVO mcv = new Maintence_ClassVO();
//		mcv.setMcl_id("更新軟體1");
//		mcv.setMcl_no(2);	
//		dao.update(mcv);
		
		//驗證DELETE OK
//		Maintence_ClassJDBCDAO dao = new Maintence_ClassJDBCDAO();
//		dao.delete(6);
		
		//驗證findByPK OK
//		Maintence_ClassJDBCDAO dao = new Maintence_ClassJDBCDAO();
//		Maintence_ClassVO maintenceclassvo = dao.findByPK(2);
//		System.out.println("維修類別編號 :"+ maintenceclassvo.getMcl_no());
//		System.out.println("維修類別名稱 :"+ maintenceclassvo.getMcl_id());
//		System.out.println("------------------------------------");
		
		
		//驗證getAll OK
//		Maintence_ClassJDBCDAO dao = new Maintence_ClassJDBCDAO();
//		List<Maintence_ClassVO> list = dao.getAll();
//		for(Maintence_ClassVO maintenceclassvo : list) {
//		System.out.println("維修類別編號 :"+maintenceclassvo.getMcl_no());
//		System.out.println("維修類別名稱 :"+ maintenceclassvo.getMcl_id());
//		System.out.println("------------------------------------");
//		}
	}	
}
