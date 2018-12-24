
var loginBtn=document.querySelector('.login_btn');
var username=document.querySelector('.username');
var password=document.querySelector('.password');
var errorTip=document.querySelector('.error_tip>p');
loginBtn.addEventListener("click",function () {
    login('/admin/verify','/admin/index/');
} );
password.addEventListener('keydown',function(event){
    if(event.keyCode == "13")
    {
        login('/admin/verify','/admin/index/');
    }
});
