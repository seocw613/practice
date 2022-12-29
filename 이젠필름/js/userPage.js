let userHistory =[
    '이터널 선샤인',
    '대부',
    '파이트 클럽',
    '완벽한 타인',
    '한산 - 용의출현',
    '범죄도시2',
    '패트리어트 - 사막의 여우',
    '고지전'
];
//document.getElementById('contents').innerHTML="";  
document.getElementById('onebyAll').addEventListener('click',()=>{
    document.getElementById('ul').innerText = '';
    document.getElementById('list').innerHTML ="<br><span>시청기록</span>";
    let ulTag = document.getElementById('ul');
    userHistory.forEach((elem)=>{
        let liTag = document.createElement('li');   //<li></li>
        let aTag = document.createElement('a');     //<a></a>
        liTag.innerText = elem; 
        aTag.setAttribute('href',`#${elem.toLowerCase()}`);        //<a href="#~">HTML</a>        
        aTag.appendChild(liTag);    //<a href="#~"><li>HTML</li></a>               
        ulTag.appendChild(aTag);   //<ul><a href="#~"><li>HTML</li></a></ul>
    });           
});

//찜목록
let jjimList = [
    '이터널스',
    '와칸다 포에버',
    '탑건 - 메버릭',
    '사랑은 비를 타고',
    '바닐라 스카이',
    '포레스트 검프',
    '박물관이 살아있다',
    '레미제라블'
];
document.getElementById('jjim').addEventListener('click',()=>{
    document.getElementById('ul').innerText = '';
    document.getElementById('list').innerHTML ="<br><span>찜목록</span>";
    let ulTag = document.getElementById('ul');
    jjimList.forEach((elem)=>{
        let liTag = document.createElement('li');   //<li></li>
        let aTag = document.createElement('a');     //<a></a>
        liTag.innerText = elem; 
        aTag.setAttribute('href',`#${elem.toLowerCase()}`);                
        aTag.appendChild(liTag);
        ulTag.appendChild(aTag);   //<ul><li><a href="#~">HTML</a></li></ul>
    });           
});
//개인정보변경

//하단 메모장 기능
var memo =[];
var addMemo = document.querySelector("#add");
addMemo.addEventListener("click", addList); 

function addList() {
    event.preventDefault(); //입력후 엔터쳐도 추가되게, 이럴경우 버튼타입이 submit 이어야함
    var item = document.querySelector("#item").value;

    if (item != null) {
        memo.push(item);                            //입력값을 배열에 추가
        document.querySelector("#item").value = "";     //입력 창 초기화
        document.querySelector("#item").focus();
    }            
    showList();
}

function showList() {  
    var list = "<ul>";

    for (var i=0; i<memo.length; i++) {  //입력 값을 배열에 넣고 그 수에 따라 리스트 추가
        list += "<li>" + memo[i] + "<span class='close' id=" + i + " style>X</span></li>"; // 늘어난 리스트 수만큼 X 도 추가
    }

    list += "</ul>";

    document.querySelector('#itemList').innerHTML = list;

    var remove = document.querySelectorAll(".close");   

    for(var i = 0; i < remove.length; i++) {
        remove[i].addEventListener("click", removeList);
    }
}

function removeList() {
    var id = this.getAttribute("id");
    memo.splice(id, 1);
    showList();
}