package com.pns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PasswordValidationWithJS_and_JAVA
 */
@WebServlet("/passwordvalidationurl")
public class PasswordValidationWithJS_and_JAVA extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pass = req.getParameter("pass1");
		String cnfpass = req.getParameter("pass2");
		PrintWriter pw = res.getWriter();
		Pattern p = null;
		Matcher m = null;
		List<String> errorList = new ArrayList<String>();
		//checking if password is null
		if(pass!=null && pass != "") {
			//checking if the string length is less than 8 characters 
			if(pass.length()<=8) {
				errorList.add("Password should be atleast 8 characters long");
			}
			//checking if it contains any number 0-9
			p = Pattern.compile("\\d");
			m = p.matcher(pass);
			if(!m.find()) {
				errorList.add("Password should contain atleast one number");
			}
			//should not contain any space
			p = Pattern.compile("\\s");
			m = p.matcher(pass);
			if(m.find()) {
				errorList.add("Password should not contain any whitespace");
			}
			//should contain at least one lower-case character
			p = Pattern.compile("[a-z]");
			m = p.matcher(pass);
			if(!m.find()) {
				errorList.add("Password should contain at least one lower-case character");
			}
			//should contain at least one upper-case character
			p = Pattern.compile("[A-Z]");
			m = p.matcher(pass);
			if(!m.find()) {
				errorList.add("Password should contain at least one upper-case character");
			}
			//should contain at least one special character
			p = Pattern.compile("[@.#$%^&()*\\[\\]:_!~+-]");
			m = p.matcher(pass);
			if(!m.find()) {
				errorList.add("Password should contain at least one special character");
			}
			//matching password with confirm password
			if(!pass.equals(cnfpass)) {
				errorList.add("Password should match with confirm password");
			}
			if(errorList.isEmpty()) {
				pw.print("<h1 style='color: green; text-align: center; font-family: Lucida-Sans;'>Password is sastisfying standards</h1>");
			}
			else {
				for(String s:errorList) {
					pw.print("<h1 style='color: red; text-align: center; font-family: Lucida-Sans;'>"+s+"</h1>");
				}
			}
		}
		else {
			pw.print("<h1>Password can not be empty.</h1>");
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
