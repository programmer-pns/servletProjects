<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Servlet Communication</title>
</head>
<body>
<form action="sendredirectionurl" method="post">

<table>
<tr>
<td>Enter the word to be searched: </td>
<td><input type="text" name="searchquery"></td>
</tr>

<tr>
<td>Select the search engine: </td>
<td><select name="searchengine">
<option value="google">Google</option>
<option value="yahoo">Yahoo</option>
<option value="bing">Bing</option>
</select></td>
</tr>
<tr><td><input type="submit" value="Search"></td></tr>
</table>
</form>
</body>
</html>