const login_btn2 = document.getElementById("login_btn2");
const loginmodal2 = document.getElementsByClassName("loginmodal2").item(0);

login_btn2.onclick = () => loginmodal2.classList.toggle("active");
