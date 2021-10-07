package com.rentalProductImages.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		doGet(req, res);
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String action = req.getParameter("action");

		if ("showRcFirstImg".equals(action)) { 

			try {
				Statement stmt = con.createStatement();
				String id = req.getParameter("id").trim();
				ResultSet rs = stmt.executeQuery(
					"SELECT rpi_img FROM RENTAL_PRODUCT_IMAGES where rc_no=" + id +" limit 1");

				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("rpi_img"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
					
		}
		
		
		if ("showImgByRpino".equals(action)) { 
			
			try {
				Statement stmt = con.createStatement();
				String id = req.getParameter("id").trim();
				ResultSet rs = stmt.executeQuery(
					"SELECT rpi_img FROM RENTAL_PRODUCT_IMAGES where rpi_no=" + id);

				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("rpi_img"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}		
		}
		
		
		
	}

	public void init() throws ServletException {
	  	try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA102G1");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}