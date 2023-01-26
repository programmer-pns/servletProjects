package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws
	ServletException,IOException{
		//setting contentType
		res.setContentType("text/html");
		//Getting printWriter
		PrintWriter pw = res.getWriter();
		//Getting request data
		String fname=req.getParameter("fname");
		String lname=req.getParameter("lname");
		String uid = req.getParameter("uid");
		String  password = req.getParameter("password");
		String  dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		//Preparing string query
		String LOGIN_QUERY = "INSERT INTO LOGIN_DATABASE(FNAME,LNAME,USERID,PASSWORD,DOB,GENDER) VALUES (?,?,?,?,?,?)";
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
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, uid);
			ps.setString(4, password);
			//Converting string date value to java.util.Date class obj
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			java.util.Date udob = sdf.parse(dob);
			//Converting util class date object to sql class date object
			long ms = udob.getTime();
			java.sql.Date dateOfBirth = new java.sql.Date(ms);
			//Setting value for the dob parameter
			ps.setDate(5, dateOfBirth);
			ps.setString(6, gender);
			int result = ps.executeUpdate();
			if(result!=0) {
						pw.print("<body bgcolor:aliceblue>");
						pw.print("<h1 style='color:green;text-align:center'>Dear."+fname.toUpperCase()+"! You are successfully registered. Try <a href='index.html'>LogIn.</a></h1>");
						pw.print("</body>");
					}
					else {
						pw.print("<body bgcolor:yellow>");
						pw.print("<h1 style='color:red;text-align:center'>Dear "+fname.toUpperCase()+"! Some error happened. Let me check...f</h1>");
						pw.print("</body>");
					}
		}
		catch(SQLException se) {
			if(se.getErrorCode()==00001) {
				pw.print("<h1 style='color:red;text-align:center'>The email or mobile number is already registered with us</h1>");
				se.printStackTrace();
			}
			if(se.getErrorCode()==01400)
				pw.print("<h1 style='color:red;text-align:center'>Please provide a email or mobile number</h1>");
			else
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