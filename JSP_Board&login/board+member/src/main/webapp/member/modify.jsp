<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Page</title>
</head>
<body>
	<h1>Modify Page</h1>
	<form action="/mem/modify" method="post">
		ID : <input type="email" name="id" value="${ses.id }" readonly><br>
		Password : <input type="text" name="pwd" value="${ses.pwd }"><br>
		Nick name : <input type="text" name="nick_name" value="${ses.nick_name }"><br>
		<button type="submit">submit</button>
	</form>
	<a href="/"><button>cancel</button></a>
</body>
</html>