package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/servletloginurl")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_QUERY = "SELECT USERNAME FROM NOTEBOOK_USERS WHERE EMAIL=? AND PASSWORD=?";
      
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			String username = req.getParameter("email");
			String password = req.getParameter("pass");
			//Getting the printwriter
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
				ServletContext sc = getServletContext();
				//Getting the connection to database
				try {
					Class.forName(sc.getInitParameter("driverclass"));
				} catch (ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
					return;
				}
				Connection conn = null;
				PreparedStatement ps=null;
				ResultSet rs = null;
				try {
					conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("dbuser"),sc.getInitParameter("dbpwd"));
					try {
						ps = conn.prepareStatement(LOGIN_QUERY);
						ps.setString(1, username);
						ps.setString(2, password);
						rs = ps.executeQuery();
						if(rs!=null) {
							if(rs.next())
								pw.print("<h1 style='color: green'>Welcome to Notebooks Dear! "+rs.getString(1)+"</h1>");
							//home hyperlink
							pw.print("<a href='servletloginurl'>Home</a>");						
						}else {
							pw.print("<h1 style='color: red'>No user found please check credentials</h1>");
							//home hyperlink
							pw.print("<a href='servletloginurl'>Home</a>");	
						}
					}
					catch(SQLException se){
						pw.print("<h1 style='color: red'>Error. try again</h1>");
						se.printStackTrace();
					}
				}catch(SQLException se) {
					pw.print("<h1 style='color: red'>Internal Error Occured try again</h1>");
					se.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					//closing the connections
					try {
						ps.close();//prepared statement closed
					} catch (SQLException se) {
						se.printStackTrace();
					}
					try {
						conn.close();//connection closed
					} catch (SQLException se) {
						se.printStackTrace();
					}
					pw.close();//print writer closed
				}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
