package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MarriageEligibility extends HttpServlet {

	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String name = req.getParameter("name");
		String sage = req.getParameter("age");
		String gender = req.getParameter("gender");
		int age = Integer.parseInt(sage);
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		if(age>=21 && gender.equals("male")) {
			pw.println("<h1 style='color:green;text-align:center'>Congrats <b style='color:blue'>Mr."+name+"!</b> You are eligible for marriage...</h1>");
		}
		else if(age>=18 && gender.equals("female")) {
			pw.println("<h1 style='color:green;text-align:center'>Congrats <b style='color:blue'>Miss"+name+"!</b> You are eligible for marriage...</h1>");
		}
		else {
			if(gender.equals("male")) {
				pw.println("<h1 style='color:red;text-align:center'>Sorry <b style='color:black'>Mr."+name+"!</b> You are not eligible for marriage.Please wait "+(21-age)+" years more...</h1>");
			}
			else {
				pw.println("<h1 style='color:red;text-align:center'>Sorry <b style='color:black'>Miss "+name+"!</b> You are not eligible for marriage.Please wait "+(18-age)+" years more...</h1>");
			}
		}
	}
}
