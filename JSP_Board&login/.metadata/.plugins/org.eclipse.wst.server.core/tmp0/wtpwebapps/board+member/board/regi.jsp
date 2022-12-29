<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post Registration Page</title>
</head>
<body>
	<form action="/brd/regi" method="post" enctype="multipart/form-data">
		Title : <input type="text" name="title"><br>
		Writer : <input type="text" name="writer" value="${mvo.id }" readonly><br>
		Content : <br>
		<textarea rows="3" cols="50" name="content"></textarea>
		Image file : <input type="file" name="image_file"
		accept="image/png, image/jpg, image/jpeg, image/gif"><br>
		<button type="submit">submit</button>
	</form>
	<a href="/brd/list?pageCount=${pageCount }"><button>cancel</button></a>
	
	<script type="text/javascript">
		const msg_write = '<c:out value="${msg_write}"/>';
		if(msg_write === "0") alert('제목을 입력하세요.');
	</script>
</body>
</html>