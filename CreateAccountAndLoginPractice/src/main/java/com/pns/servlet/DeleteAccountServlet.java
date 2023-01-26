package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAccountServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// setting contentType
		res.setContentType("text/html");
		// Getting printWriter
		PrintWriter pw = res.getWriter();
		// Getting request data
		String uname = req.getParameter("uname");
		String upass1 = req.getParameter("upass1");
		// Preparing string query
		String DELETE_QUERY = "DELETE FROM LOGIN_DATABASE WHERE USERID=? AND PASSWORD=?";
		// registering the jdbc driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "SYSTEM", "TIGER");
				PreparedStatement ps = conn.prepareStatement(DELETE_QUERY);) {
			ps.setString(1, uname);
			ps.setString(2, upass1);
			int result = ps.executeUpdate();
			if (result!=0) {
				pw.print("<body style='background-color: #B3D0EB'>");
				pw.print("<h1 style='color:green; text-align:center'>Account deleted successfully. <a href='index.html'>Back to home</a></h1>");
				pw.print("</body>");
			} else {
				pw.print("<body style='background-color: yellow'>");
				pw.print("<h1 style='color:red;text-align:center'>Invalid credentials <a href='deleteaccount.html'>Try Again</a></h1>");
				pw.print("</body>");
			}
		}
	catch(SQLException se)
	{
		se.printStackTrace();
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws 
	ServletException, IOException {
		doGet(req, res);
	}
}
