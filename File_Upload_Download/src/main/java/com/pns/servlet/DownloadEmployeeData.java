package com.pns.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
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

@WebServlet("/downloademployeedataurl")
public class DownloadEmployeeData extends HttpServlet {
	private static final String PHOTO_QUERY = "SELECT PHOTO_PATH FROM UPLOAD_EMPLOYEE WHERE EID=?";
	private static final String RESUME_QUERY = "SELECT RESUME_PATH FROM UPLOAD_EMPLOYEE WHERE EID=?";
	private static String DOWNLOAD_QUERY=null;
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl",DB_USERNAME="SYSTEM",DB_PASSWORD="TIGER";
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		int empno=0;
		if(req.getParameter("resumeID")!= null) {
			DOWNLOAD_QUERY=RESUME_QUERY;
			empno = Integer.parseInt(req.getParameter("resumeID"));
		}else {
			DOWNLOAD_QUERY=PHOTO_QUERY;
			empno = Integer.parseInt(req.getParameter("photoID"));
		}
		try {
			Class.forName("jdbc.oracle.driver.OracleDriver");
		}catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(DOWNLOAD_QUERY);
				){
			ps.setInt(1, empno);
			String file_path = null;
			try(ResultSet rs = ps.executeQuery();){
				while(rs.next()) {
					file_path = rs.getString(1);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
			//Getting the file paths
			
			//Getting InputStream
			try {
				File file = new File(file_path);
				res.setContentLengthLong(file.length());
				ServletContext sc =getServletContext();
				String mimetype = sc.getMimeType(file_path);
				mimetype=mimetype!=null?mimetype:"application/octet-stream";//universal mimetype
				res.setContentType(mimetype);
				res.setHeader("Content-Disposition","inline");
//				res.setHeader("Content-Disposition","attachment;filename:"+file.getName());
				
				FileInputStream fis = new FileInputStream(file_path);
				OutputStream os = res.getOutputStream();
//				IOUtils.copy(fis,os);//add common-io-version.jar to buildpath
				//[or]
				int i;
				while((i=fis.read())!=-1) {
					os.write(i);
				}
				fis.close();os.close();
				
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
