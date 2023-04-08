<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.pns.javabean.NewUserData,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NoteBook Management System</title>
</head>
<body>
<%
String name = (String)session.getAttribute("name");
String pass = (String)session.getAttribute("pass");
String email = (String)session.getAttribute("email");

NewUserData nud = new NewUserData();
nud.setEmail(email);
nud.setFullname(name);
nud.setPassword(pass);
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("P:/NBMS/Objects/beanobject1.ser"));
oos.writeObject(nud);
if(oos!=null)
	oos.close();
request.getRequestDispatcher("/servletregisterurl").include(request,response);
%>
</body>
</html>