<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in Page</title>
</head>
<body>
	<h1>Log in Page</h1>
	<form action="/mem/login" method="post">
		ID : <input type="text" name="id"><br>
		Password : <input type="password" name="pwd"><br>
		<button type="submit">log in</button>
	</form>
	
	<script type="text/javascript">
		const msg_login = '<c:out value="${msg_login}"/>';
		if(msg_login === "0") alert('로그인 실패');
		const msg_write = '<c:out value="${msg_write}"/>';
		if(msg_write === "0") alert('글을 작성하려면 로그인하세요.');
	</script>
</body>
</html>