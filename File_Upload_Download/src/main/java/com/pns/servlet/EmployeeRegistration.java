package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadParameters;

@WebServlet("/employeeregistrationurl")
public class EmployeeRegistration extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO UPLOAD_EMPLOYEE VALUES (UPLOAD_EMP_NO.NEXTVAL,?,?,?,?)";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			MultipartFormDataRequest mfdr = new MultipartFormDataRequest(req);
			
			String empname = mfdr.getParameter("name");
			String empaddr = mfdr.getParameter("address");
			
			UploadBean upb = new UploadBean();
			
			upb.setFolderstore("C:/Study/My Practice Files/Uploaded Data");
			upb.setOverwrite(false);
			upb.store(mfdr);
			
			//Creating an array list to store the names of files uploaded 
			Vector<UploadParameters> vup = upb.getHistory();
			ArrayList<String> al = new ArrayList<String>();
			for(UploadParameters upv:vup) {
				al.add("C:/Study/My Practice Files/Uploaded Data/"+upv.getFilename());
			}
			//[or]
			/*
			vup.forEach(upvdata->{
					al.add("C:/Study/My Practice Files/Uploaded Data/"+upvdata.getFilename());
			});
			*/
			
			//getting printwriter
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			
			//creating connections to database
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","TIGER");
				PreparedStatement ps = conn.prepareStatement(INSERT_EMPLOYEE_QUERY);
				ps.setString(1, empname);
				ps.setString(2, empaddr);
				ps.setString(3, al.get(1));//as history have first resume then photo so closer one is photo
				ps.setString(4, al.get(0));
				
				int result = ps.executeUpdate();
				
				if(result==1) {
					pw.print("<b>Employee registered</b>");
				}else {
					pw.print("<b>Something went wrong</b>");
				}
				pw.print("<a href='EmployeeDetails.html'>Home</a>");
			}catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Storing the elements to the UPLOAD_EMPLOYEE
			
			
		} catch (UploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
}
