<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.regex.*,java.util.*,javax.servlet.RequestDispatcher,com.pns.javabean.NewUserData" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NoteBook Management System</title>
</head>
<body>
<%!
String name = null;
String email = null;
String pass = null;
String cnfrpass = null;
%>
<%
name = request.getParameter("fullname");
email = request.getParameter("email");
pass =  request.getParameter("pass");
cnfrpass = request.getParameter("cnfrpass");
Pattern p = null;
Matcher m = null;
boolean passFlag = false,nameFlag = false,emailFlag=false;
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
	if(!pass.equals(cnfrpass)) {
		errorList.add("Password should match with confirm password");
	}
	if(!errorList.isEmpty()) {
		for(String s:errorList) {
			out.print("<h1 style='color: red; text-align: center; font-family: Lucida-Sans;'>"+s+"</h1>");
		}
	}
	else {
		passFlag = true;
	}
}
else {
	out.print("<h1>Password can not be empty.</h1>");
}
//verifying the name
if(name == "" || name  == null){
	errorList.add("name can not be empty");
}else if(name.length()<10){
	errorList.add("name should be atleast 10 characters long");
}else{
	nameFlag = true;
}
//verifying the email
p = Pattern.compile("[@]");
m = p.matcher(email);
if(!m.find()){
	errorList.add("not a valid email");
}else{
	emailFlag = true;
}	
//if all conditions are matching then proceed to send mail
if(nameFlag && emailFlag && passFlag){
	RequestDispatcher rd = request.getRequestDispatcher("mailotpgeneratorurl");
	session.setAttribute("name", name);
	session.setAttribute("pass", pass);
	session.setAttribute("email", email);
	rd.include(request, response);
}
%>
</body>
</html>