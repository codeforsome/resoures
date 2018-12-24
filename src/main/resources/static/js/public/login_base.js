var password=document.querySelector("input.password");
var username=document.querySelector("input.username");

username.focus();
username.onblur=function () {
    if(username.value=="") {
        username.setAttribute("placeholder", "用户名不能为空");
        username.classList.add("error");
    }
}

password.onblur=function () {
    if(password.value=="") {
        password.setAttribute("placeholder", "密码不能为空");
        password.classList.add("error");
    }
}

function login(url,successUrl) {
   var user={
       username:username.value,
       password:password.value,
   }
    if(user.username==""||user.password==""){
        return ;
    }else {
        Ajax.post(url,JSON.stringify(user),function (result) {
            var data=JSON.parse(result);
            if(data.success==false){
                errorTip.innerHTML=data.msg;
            }else if(data.success==true){
                window.location.href=successUrl;
            }
        });
    }
}
