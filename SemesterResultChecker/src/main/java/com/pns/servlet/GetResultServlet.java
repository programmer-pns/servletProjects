package com.pns.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetResultServlet
 */
@WebServlet("/getresulturl")
public class GetResultServlet extends HttpServlet {
	
	final String SELECT_QUERY = "SELECT ROLL_NO,NAME,DM,DS,DBE,CSA,OS,DSLAB,DBELAB,OSLAB,SGPA,CGPA FROM STUDENTS_RESULT WHERE ROLL_NO=?";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String redgno="";
		redgno = (String)req.getParameter("redgno");
		if(redgno.length()!=10) {
			//PRINT ERROR MESSAGE WITH ERROR CODE LENGTH OF THE REDGNO IS LESS
			System.out.println("lesslength");
		}
		else {
			
			long roll_no =0L;
			try {
				roll_no = Long.parseLong(redgno);
			}catch(NumberFormatException nfe) {
				//PRINT ERROR REDGNO SHOULD BE A NUMBER
			}
			if(roll_no!=0) {
				//get servlet context object
				ServletContext sc = req.getServletContext();
				//create connection to database and get the result
				String dbuser = sc.getInitParameter("dbuser").toString();
				String dbpass = sc.getInitParameter("dbpass").toString();
				String url = sc.getInitParameter("url").toString();
				
				try {
					Class.forName(sc.getInitParameter("driver").toString());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(url,dbuser,dbpass);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(conn!=null) {
					PreparedStatement ps = null;
					try {
						ps = conn.prepareStatement(SELECT_QUERY);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ps!=null) {
						ResultSet rs = null;
						try {
							ps.setLong(1, roll_no);
							rs = ps.executeQuery();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if(rs!=null) {
							try {
								boolean found = rs.next();
								req.setAttribute("foundData", found);
								if(found) {
									BeanClass beanobj = new BeanClass();
//									System.out.println("valid");
									beanobj.setRollno(Integer.parseInt(rs.getString("ROLL_NO")));
									beanobj.setName(rs.getString("NAME"));
									String grades[] = {rs.getString("DM"),rs.getString("DS"),rs.getString("DBE"),rs.getString("CSA"),rs.getString("OS"),rs.getString("DSLAB"),rs.getString("DBELAB"),rs.getString("OSLAB")};
									beanobj.setGrades(grades);
									beanobj.setCgpa(Float.parseFloat(rs.getString("CGPA")));
									beanobj.setSgpa(Float.parseFloat(rs.getString("SGPA")));
									beanobj.setMail(req.getParameter("mail"));
									req.setAttribute("OBJECT", beanobj);
									boolean sentMail = new CreateGradeSheetPDF().sendMail(beanobj);
									req.setAttribute("sentMail", sentMail);
								}
								RequestDispatcher rd = req.getRequestDispatcher("/printdataurl");
								rd.forward(req, res);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
