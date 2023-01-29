const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener('click', () =>{
    container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener('click', () =>{
    container.classList.remove("sign-up-mode");
});

function showPassword(){
    var password= document.querySelector("[name=password]");
    if(password.getAttribute('type')==='password'){
        password.setAttribute('type','text');
        document.getElementById("eye1").style.display = "none";
        document.getElementById("eyeoff1").style.display = "block";

    }else{
        password.setAttribute('type','password');
        document.getElementById("eye1").style.display = "block";
        document.getElementById("eyeoff1").style.display = "none";
    }
}
function showPassword2(){
    var password= document.querySelector("[name=password2]");
    if(password.getAttribute('type')==='password'){
        password.setAttribute('type','text');
        document.getElementById("eye2").style.display = "none";
        document.getElementById("eyeoff2").style.display = "block";

    }else{
        password.setAttribute('type','password');
        document.getElementById("eye2").style.display = "block";
        document.getElementById("eyeoff2").style.display = "none";
    }
}