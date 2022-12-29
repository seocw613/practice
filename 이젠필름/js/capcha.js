var keylist="abcdefghijklmnopqrstuvwxyz0123456789";
var temp="";

function generatepass(plength){
    temp=''
    for (let i=0; i<plength; i++) temp+=keylist.charAt(Math.floor(Math.random()*keylist.length));
    return temp;
}

function populateform(enterlength){
    document.frm.output.value=generatepass(enterlength);
}

function check(){
    if (temp == document.frm.che.value){
        alert('확인완료!!');
        window.location = './userInfo.html';
    } else {
        alert('틀렸습니다 다시 시도해주세요');
    }
}