package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse res) 
	throws IOException,ServletException
	{
		res.setIntHeader("refresh", 1);
		res.setContentType("text/html");
	     PrintWriter pw  = res.getWriter();
	     pw.println("<h1 style='color:red' align='center'>HelloWorld</h1>");
	     Date d = new Date();
	     pw.println("<h2 style='color:green' align='center'>"+d.toString()+"</h2>");
	}
}
