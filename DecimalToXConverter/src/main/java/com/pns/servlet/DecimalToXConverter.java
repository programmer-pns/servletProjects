package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DecimalToXConverter extends HttpServlet{
	PrintWriter pw = null;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//Getting Request Object
		res.setContentType("text/html");
		int value = Integer.parseInt(req.getParameter("option"));
		int number = Integer.parseInt(req.getParameter("number"));
		pw=res.getWriter();
		switch(value) {
		case 1:
			getBinary(number);
			break;
		case 2:
			getHexaDecimal(number);
			break;
		}
	}
	public void getBinary(int num) {
		StringBuffer str = new StringBuffer("");
		while(num>0) {
			str.append(num%2);
			num=num/2;
		}
		pw.println("<h1 style='color:green'>The BINARY is " + str.reverse() +"</h1>");
	}
	public void getHexaDecimal(int num) {
				
		pw.println("<h1 style='color:green'>The HEXA-DECIMAL is " +"</h1>");
	}
}
