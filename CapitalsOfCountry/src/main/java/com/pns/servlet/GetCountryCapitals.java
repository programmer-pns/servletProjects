package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCountryCapitals extends HttpServlet{
@Override
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String arr[] = {"NewDelhi","Islamabad","Washington DC","Beijng"};
	String countryName[]= {"India","Pakistan","USA","China"};
	int country = Integer.parseInt(req.getParameter("country"));
	res.setContentType("text/html");
	res.setIntHeader("refresh", 2);
	PrintWriter pw = res.getWriter();
	System.out.println("Refreshed");
	pw.print("<h1 style='color:blue;text-align:center'>The capital of "+countryName[country]+" is "+arr[country]+"</h1>");
}
}