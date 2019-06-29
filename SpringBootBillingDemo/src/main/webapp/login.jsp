<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: white;
}

* {
  box-sizing: border-box;
}

/* Add padding to containers */
.container {
  padding: 16px;
  background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}
.registerbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity: 1;
}
</style>
</head>
<body>
  
    <h1 style="text-align:center;">Login to the website!</h1>
 
  	${SPRING_SECURITY_LAST_EXCEPTION.message}
   <form action="login" method='POST'>
   
      <table>
         <tr>
            <td>Username:</td>
            <td><input type='text' name='username' placeholder='Enter Username' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password'  placeholder='Enter Password'/></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="Login" class="registerbtn"/></td>
         </tr>
      </table>
      <div class="container signin">
    <p>Create an account? <a href="/index.html">Sign up</a>.</p>
   
  </div>
  </form>
</body>
</html>