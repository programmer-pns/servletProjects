<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<table border='1'>
<tr>
<td>ID: </td>
<td><%=request.getAttribute("eid")%></td>
</tr>
<tr>
<td>NAME: </td>
<td><%=request.getAttribute("ename")%></td>
</tr>
<tr>
<tr>
<td>AGE: </td>
<td><%=request.getAttribute("eage")%></td>
</tr>
<td>SAL: </td>
<td><%=request.getAttribute("esal")%></td>
</tr>
<tr>
<td>HIREDATE: </td>
<td><%=request.getAttribute("ehiredate")%></td>
</tr>
<tr>
<td>JOB: </td>
<td><%=request.getAttribute("ejob")%></td>
</tr>
</table>