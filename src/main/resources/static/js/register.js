var registerBtn=document.querySelector('.register_btn');
var username=document.querySelector('.username');
var password=document.querySelector('.password');
var errorTip=document.querySelector('.error_tip>p');
var maxLenth=16;

function  limit(value){
    value=value.replace(/[\W]/g,'');
    if(value.length>maxLenth){
        return value.substring(0,maxLenth);
    }else{
        return value;
    }
}
registerBtn.onclick=function () {
    if(username.value==""||password.value==""){
        return ;
    }else {
        var user={
            username:username.value,
            password:password.value
        };
        Ajax.post('/register/register-user',JSON.stringify(user),function (result) {
           var data=JSON.parse(result);
            if(data.success==false){
                errorTip.innerHTML=data.msg;
            }else if(data.success==true){
                errorTip.innerHTML=data.msg;
               setTimeout(function () {
                   window.location.href="/index";
               },1500);
            }
        });
    }

};
