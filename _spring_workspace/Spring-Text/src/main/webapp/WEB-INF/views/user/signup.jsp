<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../layout/header.jsp"/>

<h1>Sign up Page</h1>
<form action="/member/signup" method="post">
	ID : <input type="text" name="id"><br>
	Password : <input type="password" name="pw"><br>
	Age : <input type="Number" name="age"><br>
	Name : <input type="text" name="name"><br>
	Email : <input type="text" name="email"><br>
	Home : <input type="text" name="home"><br>
	<button type="submit">sign up</button>
</form>

<script>
	const msg_signUp = '<c:out value="${msg_signUp}"/>';
	if(msg_signUp==='0'){
		console.log(msg_signUp);
		alert('중복된 아이디입니다.');
	}
</script>

<jsp:include page="../layout/footer.jsp"/>