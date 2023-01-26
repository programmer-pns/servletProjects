package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getemployeedataurl")
public class EmployeeData extends HttpServlet {
	private static final String EMP_DETAIL_QUERY = "SELECT EID,ENAME,EADD,RESUME_PATH,PHOTO_PATH FROM UPLOAD_EMPLOYEE";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl",DB_USERNAME="SYSTEM",DB_PASSWORD="TIGER";
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//getting the print writer ready
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		pw.print("<table border='1' align='center'>");
		pw.print("<thead>");
		pw.print("<th>EMP ID</th>");
		pw.print("<th>ENAME</th>");
		pw.print("<th>ADDRESS</th>");
		pw.print("<th>RESUME</th>");
		pw.print("<th>PHOTO</th>");
		pw.print("</thead>");
		
		//getting the connection to database ready
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		try(Connection conn=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
			PreparedStatement ps= conn.prepareStatement(EMP_DETAIL_QUERY);
			ResultSet rs = ps.executeQuery();
				){
			//Getting the details from UPDATE_EMPLOYEE table
			pw.print("<tbody>");
			if(rs!=null) {
				while(rs.next()) {
					pw.print("<tr>");
					pw.print("<td>"+rs.getInt(1)+"</td>");
					pw.print("<td>"+rs.getString(2)+"</td>");
					pw.print("<td>"+rs.getString(3)+"</td>");
					pw.print("<td><a href='downloademployeedataurl?resumeID="+rs.getInt(1)+"'>Resume</a></td>");
					pw.print("<td><a href='downloademployeedataurl?photoID="+rs.getInt(1)+"'>PHOTO</a></td>");
					pw.print("</tr>");
					
				}
			pw.print("</tbody>");
			}
			else {
				//no data found
				pw.print("<h1 style='color:red'>No data found on the employee table</h1>");
			}
			
			//add home hyperlink
			pw.print("<a href='EmployeeDetails.html'>Home</a>");
		}catch(SQLException sqe) {
			sqe.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		pw.print("</table>");//closing table
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
