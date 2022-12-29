let casts = document.getElementById('casts');
let castCount = 0;

printCast();

document.addEventListener('click',(e)=>{
    console.log(e.target.id);
    if(e.target.id=='add-cast-button'){
        castCount++;
        printCast();
    }

    if(e.target.className=='delete-cast-button'){
        castCount--;
        printCast();
    }
});

function printCast(){
    casts.innerHTML = 
    `<input type="text" class="info-cast" id="info-cast-L">
    <input type="text" class="info-cast" id="info-cast-R">
    <button type="button" id="add-cast-button">추가</button>`;
    for(let i=0; i<castCount; i++){
        casts.innerHTML += 
        `<input type="text" class="info-cast" id="info-cast-L${castCount}">
        <input type="text" class="info-cast" id="info-cast-R${castCount}">
        <button type="button" class="delete-cast-button"">삭제</button>`;
    }
}
// document.getElementById('add-cast-button').addEventListener('click',()=>{
//     casts.innerHTML +=
//     `<input type="text" class="info-cast" id="info-cast-L${castCount}">
//     <input type="text" class="info-cast" id="info-cast-R${castCount}">
//     <button type="button" id="delete-cast-button">삭제</button>`
//     castCount++;
//     console.log(1);
// });