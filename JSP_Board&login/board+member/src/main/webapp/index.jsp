<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>
	<c:if test="${ses.id eq null }">
		<h1>Hello!!</h1>
		<a href="/mem/loginPage"><button>log in</button></a>
		<a href="/mem/signupPage"><button>sign up</button></a>
	</c:if>
	<c:if test="${ses.id ne null }">
		<h1>Hello, ${ses.nick_name }!!</h1>
		<a href="/brd/myList"><button>my list</button></a>
		<a href="/mem/logout"><button>log out</button></a>
		<a href="/mem/modifyPage"><button>account modify</button></a>
		<a href="/mem/withdrawPage"><button>withdraw</button></a>	
	</c:if>
	<a href="/brd/pageList"><button>to board</button></a>
</body>
</html>