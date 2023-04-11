<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
        .main{
            width: 100vw;
            height: 100vh;
            background-image: url("background.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            display: block;
            font-size: 1.3rem;
            font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
        }
        .container{
            width: 30vw;
            height: 30vh;
            position: relative;
            top: 23vh;
            left: 60vw;
            border: 3px solid yellow;
            border-radius: 5px;
            display: flex;
            justify-content: center;
            align-items: center;
            box-shadow: 3px 3px 15px whitesmoke;
        }
        input{
            border: none;
            width: 300px;
            height: 30px;
            margin: 10px 0px;
            border-bottom: 2px solid #f2fc83;
            background: transparent;
            outline: 0;
            font-family: Georgia, 'Times New Roman', Times, serif;
            font-size: 20px;
            color: rgb(6, 68, 255);
        }
        input[type=submit]{
            background-color: aqua;
            border: none;
            border-radius: 10px;
            box-shadow: 2px 2px 10px rgb(1, 150, 177);
            color: rgb(0, 0, 0);

        }
        input[type=submit]:hover{
            cursor: pointer;
        }
        .form{
            display: block;
        }
    </style>
</head>
<body>
    <div class="main">
        <div class="container">
            <form action="getresulturl" method="get">
                <div>
                    <label for="redgno">Enter Your Registration Number</label>
                    <input type="text" name="redgno" id="redgno" required>
                </div>
                <div>
                    <label for="mail">Email Address</label>
                    <input type="email" name="mail" id="mail"  required>
                </div>
                <div>
                    <input type="submit" value="Get Result">
                </div>
            </form>
        </div>
    </div>
</body>
</html>