<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.time.LocalDateTime"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Weather App</title>
<link rel="stylesheet" href="main.css" />
</head>
<body>
	<div class="app-wrap">
		<header>
			<form action="getweatherinfourl" method="post">
				<input type="text" autocomplete="off" class="search_box"
					placeholder="Search for a city..." name="cityname" /> <input
					type="submit" value="GetWeatherInfo" class="btn">
			</form>
		</header>
		<main>
			<section class="location">
			<%if(request.getAttribute("error").equals("invalidcity")){ %>
				<div class="city">No City with the name <b><%=request.getParameter("cityname") %></b> is found, try again</div>
				<%}
			else if(request.getAttribute("error").equals("emptycity")){%>
				<div class="city">City name could not be empty.</div>
			<%}else{%>
				<div class="city">Internal Error Occured, try again.</div>
			<%}
				LocalDateTime ldt = LocalDateTime.now();
				String monthArray[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
						"October", "November", "December" };
				%>
				<div class="date">
					<%=ldt.getDayOfWeek()%>
					<%=ldt.getDayOfMonth()%>
					<%=monthArray[ldt.getMonthValue() - 1]%>
					<%=ldt.getYear()%>
				</div>
			</section>
		</main>
	</div>
	<script src="main.js"></script>
</body>
</html>