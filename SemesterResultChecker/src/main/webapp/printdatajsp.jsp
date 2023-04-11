<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.pns.servlet.BeanClass"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <title>Semester Result Checker</title>
    

    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        div{
            display: flex;
            flex-direction: column;
        }
        .inline_block{
            display: inline-block;
        }
        .flex{
            display: flex;
        }
        .info{
            width: 600px;
            height: 100px;
            flex-direction: row;
            justify-content: space-between;
            align-items: baseline;
            margin-bottom: 20px;
        }
        .main{
            width: 100vw;
            height: 100vh;
            /* background-image: url("background.jpg"); */
            background-repeat: no-repeat;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 1.3rem;
            font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
        }
        table{
            margin: 30px;
            box-shadow: 3px 3px 20px cyan;
        }
        tr{
        	text-align: center;
        }
        .container{
            width: 100vw;
            height: 100vh;
            position: relative;
            border: 3px solid yellow;
            border-radius: 5px;
            display: flex;
            justify-content: flex-start;
            align-items: center;
            box-shadow: 3px 3px 15px whitesmoke;
        }
        .result{
            display: flex;
            position: relative;
            align-self: center;
            margin-bottom: 20px;
            align-items: center;
        }
        .result div{
            display: flex;
            flex-direction: row;
            margin: 5px 0px;
        }
        .mailMsg{
        	font-family: monospace;
        }
        .right span{
        	color: blue;
            font-family: 'Josefin Sans', sans-serif;
        }
        .info span{        	
            margin: 5px 0px;
        }
        .form{
            display: block;
        }
    </style>
</head>
<body>
<%BeanClass object =(BeanClass)request.getAttribute("OBJECT");
String grades[] = object.getGrades();
%>
    <div class="main">
        <div class="container">
           <h1>Veer Surendra Sai University of Technology</h1>
           <h2>Odisha, Burla</h2>
           <span>GRADE SHEET</span>
           <div class="info flex">
                <div class="left">
                    <span>Registration Number</span>
                    <span>Name</span>
                    <span>Branch</span>
                </div>
                <div class="colons">
                    <span>:</span>
                    <span>:</span>
                    <span>:</span>
                </div>
                <div class="right">
                    <span><%=object.getRollno()%></span>
                    <span><%=object.getName()%></span>
                    <span>Master In Computer Application</span>
                </div>
            </div>
            <div class="table">
                <table border="3" style="display: grid;">
                    <thead>
                        <caption><h2>Semester : 1st</h2></caption>
                    </thead>
                    <tbody>
                        <tr>
                            <th>Subject Code</th>
                            <th>Subject Name</th>
                            <th>Credit</th>
                            <th>Grade</th>
                        </tr>
                        <tr>
                            <td>MCA2103</td>
                            <td>DISCRETE MATHEMATICS</td>
                            <td>3.00</td>
                            <td><%=grades[0]%></td>
                        </tr>
                        <tr>
                            <td>MCA2106</td>
                            <td>DATA STRUCTURES USING C</td>
                            <td>3.00</td>
                            <td><%=grades[1]%></td>
                        </tr>
                        <tr>
                            <td>MCA2107</td>
                            <td>DATABASE ENGINEERING</td>
                            <td>3.00</td>
                            <td><%=grades[2]%></td>
                        </tr>
                        <tr>
                            <td>MCA2108</td>
                            <td>COMPUTER SYSTEM ARCHITECTURE</td>
                            <td>3.00</td>
                            <td><%=grades[3]%></td>
                        </tr>
                        <tr>
                            <td>MCA2109</td>
                            <td>OPERATING SYSTEM</td>
                            <td>3.00</td>
                            <td><%=grades[4]%></td>
                        </tr>
                        <tr>
                            <td>MCA2195</td>
                            <td>DATA STRUCTURES USING C LAB</td>
                            <td>3.00</td>
                            <td><%=grades[5]%></td>
                        </tr>
                        <tr>
                            <td>MCA2197</td>
                            <td>DATABASE ENGINEERING LAB</td>
                            <td>3.00</td>
                            <td><%=grades[6]%></td>
                        </tr>
                        <tr>
                            <td>MCA2198</td>
                            <td>OPERATING SYSTEM LAB</td>
                            <td>3.00</td>
                            <td><%=grades[7]%></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="result">
                <div class="cgpa"><span>CGPA :&nbsp;</span><span><%=object.getCgpa()%></span></div>
                <div class="sgpa"><span>SGPA :&nbsp;</span><span><%=object.getSgpa()%></span></div>   
                <div class="promotion">
                	<%if(!grades.toString().contains("F")){%>
                		<%="Congratulations ! You are Pass ."%>
                	<%}else{%>
                		<%="Sorry ! You are Fail ."%>
                	<%}%>
                </div>
                <%if(request.getAttribute("sentMail").toString().contains("true")){ %>
                <div class="mailMsg" style="margin: 10px 0px;color: green;">
                	<%="The grade sheet has been sent to your mail"%>
                <%}else%>
                </div>
            </div>
        </div>
    </div>
</body>
</html>