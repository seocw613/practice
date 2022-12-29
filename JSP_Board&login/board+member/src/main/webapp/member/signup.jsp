<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up Page</title>
</head>
<body>
	<h1>Sign up Page</h1>
	<form action="/mem/signup" method="post">
		ID : <input type="email" name="id" placeholder="sample@domain.com"><br>
		Password : <input type="password" name="pwd"><br>
		Nick name : <input type="text" name="nick_name"><br>
		<button type="submit">sign up</button>
	</form>
</body>
</html>