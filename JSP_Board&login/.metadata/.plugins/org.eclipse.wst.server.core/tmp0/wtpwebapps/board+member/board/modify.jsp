<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post Modify Page</title>
</head>
<body>
	<form action="/brd/modify" method="post" enctype="multipart/form-data">
		No. : <input type="text" name="bno" value="${bvo.bno }" readonly><br>
		Title : <input type="text" name="title" value="${bvo.title }"><br>
		Writer : <input type="text" name="writer" value="${mvo.nick_name }"><br>
		Content
		<textarea rows="3" cols="50" name="content">${bvo.content }</textarea><br>
		Image file :
		<input type="hidden" name="image_file" value="${bvo.image_file }">
		<input type="file" name="image_file" accept="image/png, image/jpg, image/jpeg, image/gif"><br>
		<button type="submit">submit</button>
	</form>
	<a href="/brd/detail"><button>cancel</button></a>
</body>
</html>