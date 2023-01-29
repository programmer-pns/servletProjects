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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerurl")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INSERT_QUERY = "INSERT INTO NOTEBOOK_USERS VALUES (NOTEBOOK_USERS_SEQ.NEXTVAL,?,?,?)";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//Getting the data from html component
		String name = req.getParameter("username");	
		String email = req.getParameter("email");
		String password = req.getParameter("password2");
		//Getting the PrintWriter
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		//Form validation logic
		if(name==null || name==" ") {
			pw.print("<h1 style='color: red'>Name cannot be empty</h1>");
		}
		else {
			if(email==null || email==" ") {
				pw.print("<h1 style='color: red'>email cannot be empty</h1>");
			}
			else {
				if(password==null || password==" ") {
					pw.print("<h1 style='color: red'>Password cannot be empty</h1>");
				}
				else {
					//The logic to store data
					//Getting the ServletContext object
					ServletContext sc = getServletContext();
					//Getting connections to Database
					try {
						Class.forName(sc.getInitParameter("driverclass"));
					}catch(ClassNotFoundException cnfe){
						cnfe.printStackTrace();
						pw.print("<h1 style='color: red'>Internal Error Occured try again</h1>");
						return;
					}
					Connection conn = null;
					PreparedStatement ps=null;
					try {
						conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("dbuser"),sc.getInitParameter("dbpwd"));
						try {
							ps = conn.prepareStatement(INSERT_QUERY);
							ps.setString(1, name);
							ps.setString(2, email);
							ps.setString(3, password);
							int result = ps.executeUpdate();
							if(result==1) {
								pw.print("<h1 style='color: green'>User Registered successfully</h1>");
								HttpSession ses = req.getSession();
								ses.setAttribute("username", email);
								ses.setAttribute("password", password);
								ses.setAttribute("flag", "true");
								pw.print("<a href='loginurl'>Log In to your account!</a><br>");
								pw.print("<a href='index.html'>Home</a>");
								//forwarding to logged in page with the user id and password
//								pw.print("<h1 style='color: green'>Redirecting to login....</h1>");
//								res.sendRedirect("http://localhost:2020/NotesManagementSystem/loginurl");	
							}else {
								pw.print("<h1 style='color: red'>Some Error Occured try again</h1>");
							}
						}
						catch(SQLException se){
							//constraint violation checks
							if(se.getErrorCode()==00001) {
								pw.print("<h1 style='color: red'>Email is already taken.Please choose another email</h1>");
							}
							pw.print("<h1 style='color: red'>Error while inserting the data. try again</h1>");
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
			}
		}
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
