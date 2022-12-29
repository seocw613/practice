// 삭제
async function deleteFile(uuid){
    try {
        const url = "/board/fileRemove";
        const config = {
            method : 'delete',
            headers : {
                'content-type' : 'application/json; charset = utf-8'
            },
            body : uuid
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
    if(e.target.classList.contains('file-x')){
        let li = e.target.closest('li');
        let uuid = e.target.dataset.uuid;
        console.log(uuid);

        deleteFile(uuid).then(result => {
            console.log(111);
            console.log(result);
            if(result > 0) {
                console.log(222);
                e.target.closest('li').remove();
            }
        })
    }
})