<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Page</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>No.</th>
			<th>Title</th>
			<th>Written by</th>
			<th>Registered at</th>
			<th>View</th>
		</tr>
		<c:forEach items="${list }" var="bvo">
			<tr>
				<td>${bvo.bno }</td>
				<td>
					<a href="/brd/detail?bno=${bvo.bno }">
						<c:if test="${bvo.image_file ne '' && bvo.image_file ne null }">
							<img src="/_fileUpload/th_${bvo.image_file }">
						</c:if>
						${bvo.title }
					</a>
				</td>
				<td>${bvo.writer }</td>
				<td>${bvo.reg_date }</td>
				<td>${bvo.read_count }</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/brd/regiPage?pageCount=${pageCount }"><button>write</button></a>
	<a href="/"><button>to main</button></a>
	<br>
	<div>
		<c:if test="${pgh.prev }">
			<a href="/brd/page?pageNo=${pgh.startPage-1}&qty=${pgh.pageQty}">◀</a>
		</c:if>
		<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
			<a href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}">${i } ｜ </a>
		</c:forEach>
		<c:if test="${pgh.next }">
			<a href="/brd/page?pageNo=${pgh.endPage+1}&qty=${pgh.pageQty}">▶</a>
		</c:if>
	</div>
	<%-- <c:forEach begin="1" end="${totalPage }" var="page">
		<a href="/brd/list?pageCount=${page }">${page }</a>
		|
	</c:forEach> --%>
</body>
</html>