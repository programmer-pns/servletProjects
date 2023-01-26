package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoronaVaccineEligibilityCheck extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) 
	throws IOException,ServletException
	{
		String name = req.getParameter("name");
		String loc = req.getParameter("loc");
		String sage = req.getParameter("age");
		int age = Integer.parseInt(sage);
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		if(age>=18) {
			pw.println("<h1 style='color:green;text-align:center'>Hello Mr./Mrs."+name+"! You are eligible for vaccination. Please take the vaccine near "+loc+" as soon as possible.</h1>");
		}
		else
			pw.println("<h1 style='color:red;text-align:center'>Sorry Dear "+name+"! You are not eligible for vaccination as of now. Please wait for some more days.</h1>");
		pw.println("<a href='index.html'><img src='images/home-logo.png' width='100px' height='100px'></a>");
	}
}
