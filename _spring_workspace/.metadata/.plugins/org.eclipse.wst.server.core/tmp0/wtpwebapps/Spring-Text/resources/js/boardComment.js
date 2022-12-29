async function postCommentToServer(cmtData){
    try {
        const uri = '/comment/post';
        const config = {
            method : 'post',
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(uri, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('cmtPostBtn').addEventListener('click', () => {
    const cmtText = document.getElementById('cmtText').value;
    console.log(cmtText);
    if(cmtText==''){
        alert('댓글을 입력하세요.');
        cmtText.focus();
        return false;
    }else{
        let cmtData = {
            bno : bnoVal,
            writer : document.getElementById('cmtWriter').innerText,
            content : cmtText
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result => {
            if(result > 0){
                alert('댓글 등록 성공!');
            }
            // 화면에 출력
            getCommentList(cmtData.bno);
        })
    }
})

// 현재 게시글의 댓글 가져오기
async function spreadCommentFromServer(bno){
    console.log(bno);
    try {
        const resp = await fetch('/comment/'+bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 화면에 댓글 출력
function getCommentList(bno){
    spreadCommentFromServer(bno).then(result => {
        console.log(result);
        const ul = document.getElementById('cmtListArea');
        if(result.length > 0){
            ul.innerHTML = "";
            for(let cvo of result){
                let li = `<li data-cno="${cvo.cno}" class="list-group-item d-flex justify-content-between align-items-start">`;
                li += `<div class="ms-2 me-auto"><div class="fw-bold">${cvo.writer}</div>`;
                li += `<input type="text" class="form-control" id="cmtTextMod" value="${cvo.content}"></div>`;
                li += `<span class="badge bg-dark rounded-pill">${cvo.mod_at}</span>`;
                li += `<button class="btn btn-sm mod" id="cmtPostBtn" type="button">수정</button>`;
                li += `<button class="btn btn-sm del" id="cmtPostBtn" type="button">삭제</button></li>`;
                ul.innerHTML += li;
            }
        }else{
            let li = `<li class="list-group-item d-flex justify-content-between align-items-start">댓글이 없습니다.</li>`;
            ul.innerHTML = li;
        }
    })
}

// 수정
async function editCommentToServer(cmtDataMod){
    try {
        const url ="/comment/"+cmtDataMod.cno;
        const config = {
            method : 'put',
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 삭제
async function deleteCommentToServer(cmtDataDel){
    try {
        const url = "/comment/"+cmtDataDel.cno;
        const config = {
            method : 'delete',
            headers : {
                'content-type' : 'application/json; charset = utf-8'
            },
            body : JSON.stringify(cmtDataDel)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 수정 및 삭제 처리
document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('mod')){
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        let textContent = li.querySelector('#cmtTextMod').value;
        console.log(textContent);

        let cmtDataMod = {
            cno : cnoVal,
            content : textContent
        };
        console.log(cmtDataMod);
        editCommentToServer(cmtDataMod).then(result => {
            if(result > 0) alert('댓글이 수정되었습니다.');
            getCommentList(bnoVal);
        });
    }else if(e.target.classList.contains('del')){
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        console.log(li.querySelector('.fw-bold').innerText);
        let writer = li.querySelector('.fw-bold').innerText;
        
        let cmtDataDel = {
            cno : cnoVal,
            writer : writer
        };
        console.log(cmtDataDel);
        deleteCommentToServer(cmtDataDel).then(result => {
            if(result == 0) alert('댓글이 삭제되었습니다.');
            if(result == 1) alert('작성자만 삭제할 수 있습니다.');
            getCommentList(bnoVal);
        })
    }
})