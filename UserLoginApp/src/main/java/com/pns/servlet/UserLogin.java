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

//DB Communication app
public class UserLogin extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws
	ServletException, IOException{
		long id = Long.parseLong(req.getParameter("UID"));
		String pass = req.getParameter("UPASS");
		PrintWriter pw=res.getWriter();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		String query="SELECT NAME FROM FACEBOOK_DATABASE WHERE MOB_NO=? AND PASSWORD=?";
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","TIGER");
				PreparedStatement ps = conn.prepareStatement(query);
				){
			ps.setLong(1, id);
			ps.setString(2, pass);
			try(ResultSet rs = ps.executeQuery();){
				if(rs.next()) {
				String user = rs.getString(1);
				pw.print("<h1 style='color:blue;text-align:center'>Welcome Mr. "+user+"</h1>");
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
