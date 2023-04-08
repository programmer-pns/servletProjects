package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getEmployeeDetailsUrl")
public class GetEmployeeDetailsServlet extends HttpServlet {
	private static final String EMP_DETAIL_QUERY = "SELECT EID,ENAME,SAL,HIREDATE,AGE,JOB FROM EMPLOYEE";
	final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl", DB_USERNAME = "SYSTEM", DB_PASSWORD = "TIGER";
	int eID, eAge;
	float eSal;
	String eName, hireDate, eJob;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String ename = req.getParameter("name");
		List<String> al = new ArrayList<String>();
		if(ename != null) {
			System.out.println("triggered");
			System.out.println(ename);
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			} catch (SQLException e) {
				System.out.println("Cannot Establish Connection");
				e.printStackTrace();
			}
			try (PreparedStatement ps = conn.prepareStatement(EMP_DETAIL_QUERY)) {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					String str = "";
					str = rs.getString("ENAME");
					al.add(str);
					//String equals is not working then use trim()
					if (str.equalsIgnoreCase(ename.trim())) {
						eID = rs.getInt("EID");
						this.eName = rs.getString("ENAME");
						eSal = rs.getFloat("SAL");
						eAge = rs.getInt("AGE");
						hireDate = rs.getString("HIREDATE");
						eJob = rs.getString("JOB");
					}
				}
				if(eID == 0) {
					System.out.println("Some Error occured");
					res.setContentType("text/html");
					res.getWriter().print("<b style='color: red; text-align: center;'>Something went wrong!<\b>");
				}else {
					System.out.println("else line 67");
					res.setContentType("text/html");
					PrintWriter pw = res.getWriter();
					
					/***
					 *the code below sends the request to a jsp file which is printed as the ajax responseText
					 */
					req.setAttribute("ename", eName);
					req.setAttribute("esal", eSal);
					req.setAttribute("eid", eID);
					req.setAttribute("eage", eAge);
					req.setAttribute("ehiredate", hireDate);
					req.setAttribute("ejob", eJob);
					req.setAttribute("namelist", al);
					
					
					
					req.getRequestDispatcher("/responsejspurl").forward(req, res);
					
				
					/***
					//below code is the servlet approach to give response to ajax
					***/
					/*
					pw.print("<table border='1'>");
					pw.print("<tr>");
					pw.print("<td>NAME: </td>");
					pw.print("<td>"+ename+"</td>");
					pw.print("</tr>");
					pw.print("<tr>");
					pw.print("<td>SAL: </td>");
					pw.print("<td>"+eSal+"</td>");
					pw.print("</tr>");
					pw.print("<tr>");
					pw.print("<td>HIREDATE: </td>");
					pw.print("<td>"+hireDate+"</td>");
					pw.print("</tr>");
					pw.print("<tr>");
					pw.print("<td>JOB: </td>");
					pw.print("<td>"+eJob+"</td>");
					pw.print("</tr>");
					pw.print("</table>");
					*/
				}
			} catch (SQLException se) {
				System.out.println("Could not prepare the statement");
				se.printStackTrace();
			}
		}
		else {
			System.out.println("else");
			res.getWriter().print("<h1 style='color: red; text-align: center; font-size: 25px;'>Do not try change the code!</h1>");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
