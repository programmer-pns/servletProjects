/**
 * 
 */
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

/**
 * @author Priyabrata Nayak
 *
 */
public class LoginServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws
	ServletException,IOException{
		//setting contentType
		res.setContentType("text/html");
		//Getting printWriter
		PrintWriter pw = res.getWriter();
		//Getting request data
		String uname=req.getParameter("uname");
		String upass=req.getParameter("upass");
		//Preparing string query
		String LOGIN_QUERY = "SELECT FNAME FROM LOGIN_DATABASE WHERE USERID=? AND PASSWORD=?";
		//registering the jdbc driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		try(Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","TIGER");
				PreparedStatement ps = conn.prepareStatement(LOGIN_QUERY);)
		{
			ps.setString(1, uname);
			ps.setString(2, upass);
			try(ResultSet rs = ps.executeQuery();){
				if(rs!=null) {
					if(rs.next()) {
						pw.print("<body style='background-color: #B3D0EB'>");
						pw.print("<h1 style='color:green; text-align:center'>Dear "+rs.getString(1).toUpperCase()+"! You are successfully Logged In.</h1>");
						pw.print("<h1 style='color:red; text-align:center;font-size:30px'><a href='deleteaccount.html'>Delete Account</h1>");
						pw.print("</body>");
					}
					else {
						pw.print("<body style='background-color: yellow'>");
						pw.print("<h1 style='color:red;text-align:center'>Dear "+uname.toUpperCase()+"! You are not registered with us. <a href='index.html'>Register Here</a></h1>");
						pw.print("</body>");
					}
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws 
	ServletException, IOException {
		doGet(req, res);
	}
}
