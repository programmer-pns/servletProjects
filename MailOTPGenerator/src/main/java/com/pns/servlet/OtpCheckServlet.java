package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OtpCheckServlet
 */
@WebServlet
public class OtpCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		HttpSession ses = req.getSession();
		String sentotp = (String) ses.getAttribute("otpvalue");
		String providedotp = req.getParameter("otp");
		if(providedotp.equals(sentotp)) {
			pw.print("<h1 style='color: green; font-size: 50px;'>OTP Verified Successfully</h1>");
			pw.print("<a href='index.html' style='text-decoration:none;color: blue; font-size: 35px;'>Home Link</a>");
			ses.removeAttribute("otpvalue");
		}else {
			pw.print("<h2 style='color: red; font-size: 25px;'>Wrong OTP try again</h1>");
			pw.print("<form action='otpcheckurl' method='post'>");
			pw.print("<h2>Enter the OTP to be checked</h2>");
			pw.print("<input type='number' name='otp'>");
			pw.print("<input type='submit' value='Verify'>");
			pw.print("</form>");
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
