<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../layout/header.jsp"/>

<table border="1">
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>게시일</th>
		<th>조회수</th>
	</tr>
 	<c:forEach items="${list }" var="bvo">
		<tr>
			<td>${bvo.bno }</td>
			<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
			<td>${bvo.writer }</td>
			<td>${bvo.registerDate }</td>
			<td>${bvo.read_count }</td>
		</tr>
	</c:forEach>
</table>

<div>
	<c:if test="${pgh.prev }">
		<a href="/board/list?pageNo=${pgh.startPage-1}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">◀</a>
	</c:if>
	<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
		<a href="/board/list?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${i } ｜ </a>
	</c:forEach>
	<c:if test="${pgh.next }">
		<a href="/board/list?pageNo=${pgh.endPage+1}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">▶</a>
	</c:if>
</div>

<div class="col-sm-12 col-md-6">
	<form action="/board/list" method="get">
		<div class="input-group mb-3">
			<!-- 값을 별도 저장 -->
			<c:set value="${pgh.pgvo.type }" var="typed"/>
			<select class="form-select" name="type">
				<option ${typed == null ? 'selected':'' }>Choose...</option>
				<option value="t" ${typed eq 't' ? 'selected':'' }>Title</option>
				<option value="c" ${typed eq 'c' ? 'selected':'' }>Content</option>
				<option value="w" ${typed eq 'w' ? 'selected':'' }>Writer</option>
			</select>
			<input class="form-control" type="text" name="keyword" placeholder="Search" value="${pgh.pgvo.keyword }">
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="${pgh.pgvo.qty }">
			<button type="submit" class="btn btn-success position-relative">
				Search
				<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					${pgh.totalCount }
					<span class="visually-hidden">unread messages</span>
				</span>
			</button>
		</div>
	</form>
</div>

<jsp:include page="../layout/footer.jsp"/>