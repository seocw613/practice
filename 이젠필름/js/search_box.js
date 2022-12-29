var search_box_btn = document.getElementById("search_box_btn");
var search_box = document.getElementById("search_box");

search_box_btn.onclick = function(){
    console.log("hello world");
    search_box_btn.classList.add("active");
    search_box.classList.add("active");
};
search_box_btn.addEventListener("click", function(){
    console.log("hello world");
    search_box_btn.classList.add("active");
    search_box.classList.add("active");
});
