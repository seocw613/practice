<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Post Page</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
	<img alt="사진 파일이 없습니다." src="/_fileUpload/${bvo.image_file }">
	No. : ${bvo.bno} <br>
	Title : ${bvo.title }<br>
	Writer : ${bvo.writer }<br>
	Registered at ${bvo.reg_date }<br>
	${bvo.read_count } views<br>
	Content<br>
	${bvo.content }<br>
	<div>
		Comment line<br>
		<input type="text" id="cmtWriter" value="${ses.nick_name }" readonly="readonly"><br>
		<input type="text" id="cmtText" placeholder="Add Comment"><br>
		<button type="button" id="cmtAddBtn">Reply</button>
	</div>
	<!-- 댓글이 표시될 영역 -->
	<div class="accordion" id="accordionExample">
		<!-- 각 댓글 표시 영역 -->
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingOne">
				<!-- data-bs-target="#collapseOne" : id 설정 -->
				<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					<!-- 토글 버튼 내용 -->
					cno, bno, writer
				</button>
			</h2>
			<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
				<div class="accordion-body">
					<!-- 댓글 내용 들어갈 자리 -->
					content, reg_at
				</div>
			</div>
    	</div>
	</div>
	
	<c:if test="${ses.nick_name eq bvo.writer }">
	<a href="/brd/modifyPage?bno=${bvo.bno }&writer=${bvo.writer}"><button>modify</button></a>
	<a href="/brd/remove?bno=${bvo.bno }"><button>remove</button></a>
	</c:if>
	<a href="/brd/pageList"><button>to board</button></a>
	
	<script type="text/javascript">
		const bnoVal='<c:out value="${bvo.bno }"/>';
	</script>
	<script src="/resources/board_detail.js"></script>
	<script type="text/javascript">  // board_detail.js의 내용이므로 board_detail.js가 호출된 다음 호출해야 한다.
		printCommentList(bnoVal);
	</script>
</body>
</html>