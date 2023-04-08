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
				<div class="city"><%=request.getAttribute("location") %>, <%=request.getAttribute("country") %></div>
				<%
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
			<div class="current">
				<div class="temp">
					<%=request.getAttribute("temp") %> <span>°c</span>
				</div>
				<div class="weather"><%=request.getAttribute("weather") %></div>
				<div class="hi-low"><%=request.getAttribute("min_temp") %>°c / <%=request.getAttribute("max_temp") %>°c</div>
			</div>
		</main>
	</div>
	<script src="main.js"></script>
</body>
</html>