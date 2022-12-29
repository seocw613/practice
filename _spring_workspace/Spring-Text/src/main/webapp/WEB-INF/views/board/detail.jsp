<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<jsp:include page="../layout/header.jsp"/>

<table border="1">
	<tr>
		<th>글번호</th>
		<td>${bvo.bno }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${bvo.title }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${bvo.writer }</td>
	</tr>
	<tr>
		<th>게시일</th>
		<td>${bvo.registerDate }</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${bvo.read_count }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${bvo.content }</td>
	</tr>
</table>

<!-- 파일 표현 -->
<div class="form-group">
	<ul>
		<c:forEach items="${fList }" var="fvo">
			<li>
				<c:choose>
					<c:when test="${fvo.file_type>0 }">
						<div>
							<img src="/upload/${fn:replace(fvo.save_dir,'\\','/') }/${fvo.uuid}_th_${fvo.file_name}">
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<!-- 파일모양 아이콘을 넣어서 일반 파일임을 표현 -->
						</div>
					</c:otherwise>
				</c:choose>
				<!-- 파일이름, 등록일, 사이즈 -->
				<div class="ms-2 me-auto">
					<div class="fw-bold">${fvo.file_name }</div>
					${fvo.reg_at }
				</div>
				<span class="badge bg-secondary rounded-pill">${fvo.file_size } Bytes</span>
			</li>
		</c:forEach>
	</ul>
</div>

<c:if test="${bvo.writer == ses.id }">
	<a href="/board/modify?bno=${bvo.bno }"><button>수정</button></a>
	<a href="/board/remove?bno=${bvo.bno }"><button>삭제</button></a>
</c:if>

<!-- comment line -->
<div class="container">
	<div class="input-group my-3">
		<span class="input-group-text" id="cmtWriter">${ses.id }</span>
		<input type="text" class="form-control" id="cmtText" placeholder="Test Add Comment ">
	<button class="btn btn-success" id="cmtPostBtn" type="button">등록</button>
	</div>
	<ul class="list-group list-group-flush" id="cmtListArea">
		<li class="list-group-item d-flex justify-content-between align-items-start">
			<div class="ms-2 me-auto">
				<div class="fw-bold">작성자</div>
				Content for Comment
			</div>
			<span class="badge bg-dark rounded-pill">수정일</span>
		</li>
	</ul>
</div>


<script src="/resources/js/boardComment.js"></script>
<script type="text/javascript">
	const bnoVal = '<c:out value="${bvo.bno}"/>';
	console.log(bnoVal);
	getCommentList(bnoVal);
</script>



<a href="/board/list"><button>목록</button></a>

<jsp:include page="../layout/footer.jsp"/>