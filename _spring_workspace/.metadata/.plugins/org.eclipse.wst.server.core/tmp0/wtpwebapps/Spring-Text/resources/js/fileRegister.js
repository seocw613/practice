document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('files').click();
});

// 정규표현식을 이용하여 등록 가능한 파일 제한
const regExp = new RegExp("\.(exe|sh|bat|msi|dll|js)$");  // 등록 제한할 파일
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$");  // 등록 가능한 파일
const maxSize = 1024*1024*20;  // 최대 파일 용량 20 MB

// fileload 시 형식 제한 함수
function fileSizeValidation(fileName, fileSize){
    // .test(값) : 값이 정규표현식에 맞으면 true 틀리면 false 반환
    if(regExp.test(fileName)) return 0;
    else if(!regExpImg.test(fileName)) return 0;
    else if(fileSize>maxSize) return 0;
    else return 1;
}

document.addEventListener('change',(e)=>{
    if(e.target.id == "files"){
        // input 태그 중 type="file"인 요소는 fileObject 객체로 들여온다
        const fileObject = document.getElementById('files').files;
        console.log(fileObject);

        let div = document.getElementById('fileZone');
        div.innerHTML = "";
        let ul = '<ul class="list-group list-group-flush">';
        let isOk = 1;
        for(let file of fileObject){
            let validResult = fileSizeValidation(file.name, file.size);
            isOk *= validResult;  // 모든 첨부파일의 결과가 1이어야 값이 최종 결과가 1;
            ul += `<li class="list-group-item d-flex justify-content-between align-items-start">`;
            ul += `<div class="fw-bold">${validResult ? '업로드 가능' : '업로드 불가'}</div>`;
            ul += `${file.name}`;
            ul += `<span class="badge bg-${validResult ? 'success':'danger'} rounded-pill">${file.size} Bytes</span></li>`;
        }
        ul += `</ul>`;
        console.log(ul);
        div.innerHTML = ul;
    }
})