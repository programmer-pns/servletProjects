package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCommunication1
 */
@WebServlet("/servlet1url")
public class ServletCommunication1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //give request to http://2020/ServletCommunication/servlet1url
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		pw.print("<b>I am servlet1 before rd.forward()</b><br>");
		RequestDispatcher rd = req.getRequestDispatcher("/servlet2url");
		rd.forward(req, res);
		pw.print("<b>I am servlet1 after rd.forward()</b><br>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
