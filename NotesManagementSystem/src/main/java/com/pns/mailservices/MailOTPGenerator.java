package com.pns.mailservices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MailOTPGenerator
 */
@WebServlet("/mailotpgeneratorurl")
public class MailOTPGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static String TO,SUBJECT,BODY;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String mailid = req.getParameter("email");
		TO = mailid;
		SUBJECT = "OTP from Mail Sending App";
		
		String otp = Integer.toString(new Random().nextInt(999999));//generate random 6 character otp
		HttpSession ses = req.getSession();
		ses.setAttribute("otpvalue", otp);
		String name = (String)req.getSession().getAttribute("name");
		BODY = "Hi! "+name+" ...Welcome to NoteBookManagement System...Thank you for registering with us... The otp for email verification is===>"+otp;
		boolean b = send(TO,SUBJECT,BODY);
		//Getting PrintWriter
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		if(b) {
//			getting the form to check the otp
			pw.print("<form action='otpcheckurl' method='post'>");
			pw.print("<h2>Enter the OTP to be checked</h2>");
			pw.print("<input type='number' name='otp'>");
			pw.print("<input type='submit' value='Verify'>");
			pw.print("</form>");
		}else {
			pw.print("<h1 style='color: red;' align='center'>Message Sending Unsuccessful,please try again...</h1>");
			pw.print("<a href='index.html'>Home</a>");
		}
				
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public boolean send(String to,String subject,String text){
		boolean flag = false;
		String username = "npriyabrata120";
		String password = "yfwwbrycojtdnnne";
		Properties prt = new Properties();
		prt.put("mail.smtp.auth", true);
		prt.put("mail.smtp.starttls.enable", true);
		prt.put("mail.smtp.port", "587");
		prt.put("mail.smtp.host", "smtp.gmail.com");
		
		
		Session s = Session.getInstance(prt,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		try {
			Message m = new MimeMessage(s);
			m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(text);
			
			Transport.send(m);
			flag  = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
