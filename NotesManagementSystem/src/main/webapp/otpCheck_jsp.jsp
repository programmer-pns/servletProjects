<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="javax.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NoteBook Management System</title>
</head>
<body>

<%
String sentotp = (String) session.getAttribute("otpvalue");
String providedotp = request.getParameter("otp");
if(providedotp.equals(sentotp)) {
	session.removeAttribute("otpvalue");
	RequestDispatcher rd = request.getRequestDispatcher("/storedataurl");
	rd.include(request,response);
}else {
	out.print("<h2 style='color: red; font-size: 25px;'>Wrong OTP try again</h1>");
	out.print("<form action='otpcheckurl' method='post'>");
	out.print("<h2>Enter the OTP to be checked</h2>");
	out.print("<input type='number' name='otp'>");
	out.print("<input type='submit' value='Verify'>");
	out.print("</form>");
}
%>

</body>
</html>