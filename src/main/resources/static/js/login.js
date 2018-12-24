var loginBtn=document.querySelector('.login_btn');
var username=document.querySelector('.username');
var password=document.querySelector('.password');
var errorTip=document.querySelector('.error_tip>p');
loginBtn.addEventListener("click",function () {
    login('/login/verify','/index');
} );
//所需单据绑定回车键
password.addEventListener('keydown',function(event){
    if(event.keyCode == "13")
    {
        login('/login/verify','/index');
    }
});
