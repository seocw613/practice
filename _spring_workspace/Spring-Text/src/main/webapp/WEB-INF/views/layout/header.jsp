<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="/member/">Navbar</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="/member/signup">회원가입</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/board/list">게시판</a>
				</li>
				<c:if test="${ses.id == null }">
					<li class="nav-item">
						<a class="nav-link active" aria-current="page" href="/member/login">로그인</a>
					</li>
				</c:if>
				<c:if test="${ses.id != null }">
					<li class="nav-item">
						<a class="nav-link active" aria-current="page" href="/member/logout">로그아웃</a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" aria-current="page" href="/board/register">글쓰기</a>
					</li>
					<li class="nav-item">
						${ses.id }님 안녕하세요!
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>

<script>
	const msg_signUp = '<c:out value="${msg_signUp}"/>';
	const msg_logIn = '<c:out value="${msg_logIn}"/>';
	const msg_logOut = '<c:out value="${msg_logOut}"/>';
	const msg_register = '<c:out value="${msg_register}"/>';
	const msg_modify = '<c:out value="${msg_modify}"/>';
	console.log("msg_logIn : "+msg_logIn);
	console.log("msg_logOut : "+msg_logOut);
	console.log("msg_signUp : "+msg_signUp);
	console.log("msg_register : "+msg_register);
	console.log("msg_modify : "+msg_modify);
	if(msg_logOut=='1'){
		alert('로그아웃 되었습니다.');
	}
	if(msg_register=='1'){
		alert('글이 등록 되었습니다.');
	}
	if(msg_modify=='1'){
		alert('글이 수정 되었습니다.');
	}
</script>