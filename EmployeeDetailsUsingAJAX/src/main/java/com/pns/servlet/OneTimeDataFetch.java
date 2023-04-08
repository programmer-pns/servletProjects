package com.pns.servlet;

import java.io.IOException;
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

/**
 * Servlet implementation class OneTimeDataFetch
 */
@WebServlet("/firstTimeDataFetch")
public class OneTimeDataFetch extends HttpServlet {
	private static final String EMP_DETAIL_QUERY = "SELECT EID,ENAME,SAL,HIREDATE,AGE,JOB FROM EMPLOYEE";
	final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl", DB_USERNAME = "SYSTEM", DB_PASSWORD = "TIGER";
	int eID, eAge;
	float eSal;
	String eName, hireDate, eJob;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String ename = req.getParameter("name");
		List<String> al = new ArrayList<String>();
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
					al.add(rs.getString("ENAME"));
				}
					req.setAttribute("namelist", al);
					RequestDispatcher rd = req.getRequestDispatcher("/printdataurl");
					rd.forward(req, res);
			} catch (SQLException se) {
				System.out.println("Could not prepare the statement");
				se.printStackTrace();
			}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
