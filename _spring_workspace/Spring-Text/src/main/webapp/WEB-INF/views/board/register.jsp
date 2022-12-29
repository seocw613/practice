<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../layout/header.jsp"/>

<h1>Board Register Page</h1>
<form action="/board/register" method="post" enctype="multipart/form-data">
	제목 : <input type="text" name="title"><br>
	작성자 : <input type="text" name="writer" value="${ses.id }" readonly><br>
	내용 : <br><textarea rows="5" cols="30" name="content"></textarea><br>
	<div class="col-12 d-grid">
		<input class="form-control" type="file" style="display: none;" id="files" name="files" multiple>
		<button type="button" id="trigger" class="btn btn-outline-primary btn-block d-block">Files Upload</button>
	</div>		
	<div class="col-12" id="fileZone">
		
	</div>
	<button type="submit">등록</button>
</form>

<script src="/resources/js/fileRegister.js"></script>
<jsp:include page="../layout/footer.jsp"/>