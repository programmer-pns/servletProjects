package com.pns.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pns.javabean.NewUserData;
import com.pns.trials.ClassA;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/servletregisterurl")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INSERT_QUERY = "INSERT INTO NOTEBOOK_USERS VALUES (NOTEBOOK_USERS_SEQ.NEXTVAL,?,?,?)";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		NewUserData nud = null;
		String name=null,pass=null,email=null;
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("P:/NBMS/Objects/beanobject1.ser"));){
			nud = (NewUserData)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(nud!=null) {
			name = nud.getFullname();
			email = nud.getEmail();
			pass = nud.getPassword();
		}else {
			System.out.println("Nud is null");
			return;
		}
		ServletContext sc = getServletContext();
		//performing jdbc connections
		int result = 0;
		try {
			Class.forName(sc.getInitParameter("driverclass"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("dbuser"),sc.getInitParameter("dbpwd"));){
			if(conn!=null) {
				try(PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);){
					if(ps!=null) {
						ps.setString(1, name);
						ps.setString(2, email);
						ps.setString(3, pass);
						result = ps.executeUpdate();
					}
				}catch(Exception e) {
					e.getMessage();
				}
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(result == 1) {
			req.setAttribute("userid", email);
			req.setAttribute("userpass", pass);
			PrintWriter pw = res.getWriter();
			pw.print("<p style='color: green;text-align:center;font-size: 30px;'>");
			pw.print("Congratulations "+name+"! You are succefully registered with us.");
			pw.print("</p>");
			pw.print("<a href='loginjspurl' style='text-decoration: none;text-align: center;font-size: 2rem;font-family:cursive;'>Click Here to Login</a>");
		}else {
			//call error page
//			RequestDispatcher rd = req.getRequestDispatcher("registerurl");
//			rd.forward(req, res);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
