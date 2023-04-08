<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register to Notebook Management System</title>
    <link rel="stylesheet" href="registerstyle.css">
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<style>
		a{
    		text-decoration: none;
			color: blue;
			font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
		}
		input{
    		width: 20rem;
    		font-size: 1.5rem;
			text-align: left;
			margin: 0.2rem;
			font-family: Georgia, 'Times New Roman', Times, serif;
		}
		h2{	
			text-align: center;
			color:rgb(255, 0, 0);
			border: 2px solid black;
		}
		#submit_btn{
			text-align: center;
		}
		ion-icon{
			position: relative;
			right: 2.7rem;
			font-size: 1.8rem;
			color: rgb(164, 161, 161);
		}
        p{
            margin: 0.2rem;
        }
	</style>
  </head>
  <body>
    <div class="container">
          <form action="servletloginurl" method="post">
          <table border="0" align="center">
			<tr>
				<td><h2 style="font-size: 1.5rem;">LOGIN TO YOUR ACCOUNT</h2></td>
			</tr>
          	<tr>
          	<td><input type="email" name="email" placeholder="Mail Address"></td>
          	</tr>
          	<tr>
          	<td><input type="password" name="pass" placeholder="Password"></td>
          	<td><span><ion-icon name="eye-outline" id="pass_eye_on" onclick="displayPassword()"></ion-icon><ion-icon name="eye-off-outline" id="pass_eye_off" style="display: none;" onclick="displayPassword()"></ion-icon></span></td>
          	</tr>
          	<tr>
          	<td><input type="checkbox" name="rememberme" placeholder="remember0"></td>
          	<td><span><ion-icon name="eye-outline" id="pass_eye_on" onclick="displayPassword()"></ion-icon><ion-icon name="eye-off-outline" id="pass_eye_off" style="display: none;" onclick="displayPassword()"></ion-icon></span></td>
          	</tr>
          	<td><input type="submit" value="LOGIN" id="submit_btn"></td>
          	</tr>	
			<tr>
          	<td><p style="font-size: 1.5rem; text-align: center">Forgot password? <a href="noteBookReset.jsp">Reset Here</a></p></td>
          	</tr>	
			<tr>
          	<td><p style="font-size: 1.5rem; text-align: center">Be one of us... <a href="noteBookRegister.jsp">Register with us</a></p></td>
          	</tr>	
          </table>
          </form>
          
     </div>
  </body>
  <script>
	function displayPassword(){
		var passfield = document.querySelector("[name=pass]");
		if(passfield.getAttribute("type")=="text"){
			passfield.setAttribute("type","password");
			document.getElementById("pass_eye_off").style.display="none";
			document.getElementById("pass_eye_on").style.display="block";
		}else{
			passfield.setAttribute("type","text");
			document.getElementById("pass_eye_off").style.display="block";
			document.getElementById("pass_eye_on").style.display="none";
		}
	}
  </script>
</html>
