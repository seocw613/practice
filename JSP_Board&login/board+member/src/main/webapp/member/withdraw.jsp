<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Withdraw Page</title>
</head>
<body>
	<form action="/mem/withdraw" method="post">
		Check password : <input type="text" name="pwd"><br>
		<button type="submit">submit</button>
	</form>
	<a href="/"><button>cancel</button></a>
	
	<script type="text/javascript">
		const msg_withdraw = '<c:out value="${msg_withdraw}"/>';
		if(msg_withdraw === "0") alert('비밀번호가 틀렸습니다.');
	</script>
</body>
</html>