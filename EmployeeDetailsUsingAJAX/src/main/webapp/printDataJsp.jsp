<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Details</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
	<% List<String> nameList = (List<String>)request.getAttribute("namelist");%>
	
	<select id='display'>
	<% for(String name:nameList){ %>
	<option value='
		<%=name%>
		'>
		<%=name%>
	</option>
	<%} %>
	</select>
	<div id="demo">
	
	</div>
	
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#display').change(function(){
			$.ajax({
		         type: 'POST',
		         url:  'getEmployeeDetailsUrl',
		         data: { name:$('#display').val() },
		      })
		         .done( function (responseText) {
		            // Triggered if response status code is 200 (OK)
		            $('#demo').html(responseText);
		         })
		         .fail( function (jqXHR, status, error) {
		            // Triggered if response status code is NOT 200 (OK)
		            alert(jqXHR.responseText);
		         })
		         .always( function() {
		            // Always run after .done() or .fail()
		            console.log("always");
		         });
		});
	});
</script>
</html>