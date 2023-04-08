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
 * Servlet implementation class SendRedirectionServlet
 */
@WebServlet("/sendredirectionurl")
public class SendRedirectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String word = null;
	String searchengine = null;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		word = req.getParameter("searchquery");
		searchengine = req.getParameter("searchengine");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
//		Uncomment below to use search by <a href=""> tag of html
//		redirectionByAnchor(pw);
//		Uncomment below to use search by send redirection
		redirectionBySendRedirect(req,res);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void redirectionByAnchor(PrintWriter pw) throws IOException{
		//get the printwriter
		if(searchengine.equalsIgnoreCase("google")) {
			pw.print("<a href='https://www.google.com/search?q="+word+"'>Click Here</a>");
		}else if(searchengine.equalsIgnoreCase("yahoo")) {
			pw.print("<a href='https://in.search.yahoo.com/search?p="+word+"'>Click Here</a>");
		}else {
			pw.print("<a href='https://www.bing.com/search?q="+word+"'>Click Here</a>");
		}
	}
	public void redirectionBySendRedirect(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String url = null;
		if(searchengine.equalsIgnoreCase("google")) {
			url = "https://www.google.com/search?q="+word;
		}else if(searchengine.equalsIgnoreCase("yahoo")) {
			url = "https://in.search.yahoo.com/search?p="+word;
		}else {
			url = "https://www.bing.com/search?q="+word;
		}
		res.sendRedirect(url);
	}
}
