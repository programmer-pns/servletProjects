package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		/*
		 * 
		 * Server side form validation
		 * 
		The requirements are 
		1. no field should be left empty
		2. address length should be at least 10
		3. age must be a numeric value
		4. age should be between 1 through 125
		*/
		List<String> errorList = new ArrayList<String>();
		if(name == "" || name.equals("")) {
			errorList.add("Name cannot be empty");
		}
		if(loc==""||loc.equals(""))
			errorList.add("Address cannot be empty");
		else if(loc.length()<=10)
			errorList.add("Address length should have atleast 10 characters");
		int age = 0;
		if(sage == "" || sage.equals(""))
			errorList.add("Age cannot be left empty");
		else{
		try {
			age = Integer.parseInt(sage);
			if(age>125 || age<=0)
				errorList.add("age should be between 1 through 125");
		}
		catch (NumberFormatException nfe) {
			errorList.add("Age must be a number");
		}
		}
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		Iterator<String> ite = errorList.iterator();
		if(errorList.size() != 0) {
			while(ite.hasNext())
				pw.println("<h1 style='color:red; text-align:center'>"+ite.next()+"</h>");
			return;
		}
		if(age>=18) {
			pw.println("<h1 style='color:green;text-align:center'>Hello Mr./Mrs."+name+"! You are eligible for vaccination. Please take the vaccine near "+loc+" as soon as possible.</h1>");
		}
		else
			pw.println("<h1 style='color:red;text-align:center'>Sorry Dear "+name+"! You are not eligible for vaccination as of now. Please wait for some more days.</h1>");
		pw.println("<a href='index.html'><img src='images/home-logo.png' width='100px' height='100px'></a>");
	}
}
