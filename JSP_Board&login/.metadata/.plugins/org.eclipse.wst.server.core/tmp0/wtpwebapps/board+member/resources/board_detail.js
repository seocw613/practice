async function getCommentListFromServer(bno){  // 서버(컨트롤러)에 bno에 해당하는 댓글 리스트를 요청
	try{
		const resp = await fetch("/cmt/list/"+bno);  // /cmt/list/26의 형식으로 보낸다.
		const cmtList = await resp.json();  // JSON 객체를 받아온다.
		return cmtList;
	}catch(error){
		console.log(error);	
	}
}

function spreadCommentList(result){  // result는 댓글의 list 형태
	let div = document.getElementById("accordionExample");
	div.innerHTML = '';
	for(let i=0; i<result.length; i++){
		// 이클립스에서 js를 사용할 때 호환이 덜 돼서 오류 표시가 많이 뜬다. - 실제 오류는 아님
		let html = '<div class="accordion-item">';
		html += `<h2 class="accordion-header" id="heading${i}">`;
		html += `<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`;
		html += `${result[i].cno}, ${result[i].bno}, ${result[i].writer}</button></h2>`;
		html += `<div id="collapse${i}" class="accordion-collapse collapse show" aria-labelledby="heading${i}" data-bs-parent="#accordionExample">`;
		html += `<div class="accordion-body">`;
		// 아래에서 data-cno 속성으로 해당 댓글을 선택
		html += `<button type="button" data-cno="${result[i].cno}" class="btn btn-sm btn-outline-warning cmtModBtn">%</button>`;  // 아코디언 코드에 추가한 수정 버튼
		html += `<button type="button" data-cno="${result[i].cno}" class="btn btn-sm btn-outline-danger cmtDelBtn">X</button>`;  // 아코디언 코드에 추가한 수정 버튼
		html += `<input type="text" class="form-control" id="cmtText1" value="${result[i].content}">`;
		html += `${result[i].reg_at}`;
		html += `</div></div></div>`;
		div.innerHTML += html;
	}
}

async function removeCommentFromServer(cnoVal){
	try{
		const url = '/cmt/remove/'+cnoVal;
		const config = {
			method: "post"
		}
		const resp = await fetch(url,config);
		const result = await resp.text();
		return result;
	}catch(error){
		console.log(error);
	}
}

async function updateCommentFromServer(cnoVal, cmtText1){
	try{
		const url = '/cmt/modify';
		const config = {
			method: 'post',
			header: {
				'Content-Type':'application/json; charset=utf-8'
			},
			body: JSON.stringify({cno:cnoVal, content:cmtText1})  // 보낼 정보
		}
		const resp = await fetch(url, config);
		const result = await resp.text();
		return result;
	}catch(error){
		console.log(error);
	}
}

document.addEventListener('click',(e)=>{
	// 댓글 수정 버튼
	if(e.target.classList.contains('cmtModBtn')){
		let cnoVal = e.target.dataset.cno;
		console.log(cnoVal);
		let div = e.target.closest('div');  // 내 타겟을 기준으로 가장 근처에 있는 div 추출
		let cmtText = div.querySelector('#cmtText1').value;  // 내용 가져오기
		updateCommentFromServer(cnoVal, cmtText).then(result => {  // 수정 함수 실행
			if(result > 0){
				alert("댓글수정 성공!");
				printCommentList(bnoVal);
			}
		})
	}
	// 댓글 삭제 버튼
	if(e.target.classList.contains('cmtDelBtn')){
		let cnoVal = e.target.dataset.cno;
		console.log(cnoVal);
		removeCommentFromServer(cnoVal).then(result =>{
			if(result > 0){
				alert('댓글 삭제 완료');
				printCommentList(bnoVal);
			}
		})
		
	}
})

function printCommentList(bno){
	// 호출할 함수를 위에서 미리 만들어두는 형태로 작성 -> 안정성↑?
	getCommentListFromServer(bno).then(result => {  // cmtList
		console.log(result);
		if(result.length>0){  // result가 0보다 큰 경우 == 해당 게시글에 댓글이 있는 경우 
			// 화면에 보여줄 실제 로직 호출
			spreadCommentList(result);
		}else{  // 해당 게시글에 댓글이 없는 경우
			let div = document.getElementById("accordionExample");
			div.innerText = "Comment가 없습니다.";
		}
	})
};

async function postCommentToServer(cmtData){
	try{
		const url = "/cmt/post";
		const config={
			method:'post',
			headers:{
				'Content-Type':'application/json; charset=utf-8'  // JSON 형태로 보내기 위해서 필요한 구문
				// 'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'  // 쿼리스트링 방식, 기본적으로 인식하는 방식
			},
			// JSON 객체를 string 타입으로 변환해서 body에 담아서 간다.
			body: JSON.stringify(cmtData)
		};
		/* /cmt/post에 config 객체를 보낸다 */
		const resp = await fetch(url, config);
		/* post에서 response에 쓴 값(여기서는 isOk)을 resp.text()를 사용하여 가져온다. -> result의 값으로 저장 */
		const result = await resp.text();
		return result;
	}catch(error){
		console.log(error);
	}
}

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
	const cmtText = document.getElementById('cmtText').value;
	if(cmtText == null || cmtText=='') {
		alert('댓글을 입력하세요.');
		return false;
	}else{
		let cmtData = {
			bno : bnoVal,
			writer : document.getElementById('cmtWriter').value,
			content : cmtText
		};
		postCommentToServer(cmtData).then(result => {  // 여기에서 result는 isOk 값
			if(result > 0){
				alert('댓글 등록 완료');
				/* 댓글 등록이 성공하면 다음 댓글을 등록할 수 있도록 비운다. */
				document.getElementById('cmtText').value = "";
			}
			printCommentList(cmtData.bno);
		})
	}
})