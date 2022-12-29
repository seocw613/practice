const menu_btn = document.getElementById("menu_btn");
menu_btn.onclick = function(){
    const side_navigation = document.getElementById("side_navigation");
    side_navigation.classList.toggle("active");
    menu_btn.classList.toggle("active");
};

const to_top_btn = document.getElementById("to_top_btn");
to_top_btn.onclick = () => window.scroll({top: 0, behavior: "smooth"});

document.querySelector(".category > div").onclick = function(){
    document.getElementsByClassName("category").item(0).classList.toggle("active");

    document.getElementsByClassName("toggle_btn").item(0).classList.toggle("active");
}