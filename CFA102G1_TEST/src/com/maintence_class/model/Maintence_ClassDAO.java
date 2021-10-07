package com.maintence_class.model;

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

public class Maintence_ClassDAO implements I_Maintence_ClassDAO{

	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,maintence_classvo.getMcl_id());
						
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				maintence_classvo.setMcl_no(rs.getInt(1));
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
		return maintence_classvo;
	}
	
	@Override
	public void update(Maintence_ClassVO maintence_classvo) {
			Connection con = null; 
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);
			
				pstmt.setString(1,maintence_classvo.getMcl_id());
				pstmt.setInt(2,maintence_classvo.getMcl_no());
				
				pstmt.executeUpdate();
				
			}catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			}finally {
				if(con != null) {
					try {
						con.close();
					}catch (SQLException e) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, mcl_no);
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
	public Maintence_ClassVO findByPK(Integer mcl_no) {
		Maintence_ClassVO maintenceclassvo = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, mcl_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				maintenceclassvo = new Maintence_ClassVO();
				maintenceclassvo.setMcl_no(rs.getInt("MCL_NO"));
				maintenceclassvo.setMcl_id(rs.getString("MCL_ID"));		
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				maintenceclassvo = new Maintence_ClassVO();
				maintenceclassvo.setMcl_no(rs.getInt("MCL_NO"));
				maintenceclassvo.setMcl_id(rs.getString("MCL_ID"));
				listMaintence_ClassVO.add(maintenceclassvo);
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
		return listMaintence_ClassVO;
	}
}
