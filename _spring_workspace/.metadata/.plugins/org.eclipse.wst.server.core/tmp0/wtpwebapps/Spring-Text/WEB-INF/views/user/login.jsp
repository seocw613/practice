<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../layout/header.jsp"/>

<h1>Log in Page</h1>
<form action="/member/login" method="post">
	ID : <input type="text" name="id"><br>
	Password : <input type="password" name="pw"><br>
	<button type="submit">log in</button>
</form>

<script>
	const msg_logIn = '<c:out value="${msg_logIn}"/>';
	if(msg_logIn='0'){
		console.log(msg_logIn);
		alert('로그인 실패');
	}
</script>

<jsp:include page="../layout/footer.jsp"/>