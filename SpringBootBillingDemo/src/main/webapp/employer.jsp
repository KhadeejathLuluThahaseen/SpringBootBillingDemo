<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: white;
}

{
box-sizing


:

 

border-box


;
}

/* Add padding to containers */
.container {
	padding: 16px;
	background-color: white;
}

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

/* Set a style for the submit button */
.registerbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
	float: right;
}

.registerbtn:hover {
	opacity: 1;
}

.signin {
	background-color: #f1f1f1;
	text-align: center;
}

.right {
	text-align: right;
	float: right;
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}
</style>
</head>
<body>
	<div class="right">
		<a href="/logouts">Logout</a>
	</div>
	<div class="container signin">
		<h1 style="text-align: center;">Hello ${loggedInUser }</h1>
		<p style="text-align: center;">RETAIL WEBSITE</p>
	</div>
	<p>
		<a href="/products.html">Buy A Product</a>.
	</p>
	<p>
	<a href="/mycart">My Cart</a>
	</p>

</body>
</html>