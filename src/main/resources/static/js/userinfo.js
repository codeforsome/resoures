//对时间进行格式
function formatDate(time) {
    var start='yyyy-mm-dd'.length;
    var end='Thh:ss:mm'.length;
    if(time==null) return;
    var  result=time.toString().substring(0,start);
    return result+' '+time.toString().substring(start+1,start+end);
};

var usernameTemp,nicknameTemp;
var userInfo=[];
var roleType=['普通用户','医者'];
var statusType=['禁用','正常'];
var userTable=new Vue({
    el:'#user-table',
    data:{
        userInfo:userInfo,
        statusType:statusType,
        page:1,
    },
    methods:{
    },
    filters: {
        formatDate(time) {
            return formatDate(time);
        }
    },
});

var  currentPage=1,pageSize=6;
var url='getinfo?currentPage='+currentPage+'&pageSize='+pageSize;
$.get(url, function(data) {
    userTable.userInfo=userInfo=data;
});

//定义点击下一页的事件
$('.next_page').on('click',function () {
    if(userTable.userInfo.length==pageSize){
        currentPage+=pageSize;
    }
    var url='getinfo?currentPage='+currentPage+'&pageSize='+pageSize;
    $.get(url, function(data) {
        userTable.userInfo=userInfo=data;
        userTable.page=parseInt(currentPage/pageSize)+1;
    });
});

//定义点击上一页的事件
$('.prev_page').on('click',function () {
    currentPage-=pageSize;
    userTable.page=parseInt(currentPage/pageSize)+1;
    if(currentPage<=0){
        currentPage=1;
    }
    var url='getinfo/?currentPage='+currentPage+'&pageSize='+pageSize;
    $.get(url, function(data) {
        userTable.userInfo=userInfo=data;
    });
});

var primaryModal=new Vue({
    el:'#primaryModal',
    data:{
        roleType:roleType,
        statusType:statusType,
    },
    methods:{
        choice:function(type){
            $('button#role').html(type);
            $('button#role').val(type);
        },
        statusChoice:function(type){
            $('button#status').html(type);
            $('button#status').val(type);
        },
    },
});

var  addUserModal=new Vue({
    el:'#addUserModal',
    data:{
        roleType:roleType,
        statusType:statusType,
    },
    methods:{
        choice:function(type){
            $('#addUserModal button.role').html(type);
            $('#addUserModal button.role').val(type);
        },
        statusChoice:function(type){
            $('#addUserModal button.status').html(type);
            $('#addUserModal button.status').val(type);
        },
    },
})

$(".dropdown-toggle").on("click",function () {
    setTimeout(function () {
        $("div.dropdown-menu")[0].style="";
        $("div.dropdown-menu")[1].style="";
        $("div.dropdown-menu")[2].style="";
    },300);
})
//定义修改框弹出事件
$('#primaryModal').on('show.bs.modal', function (e) {
    $('p.msg-tip').html('');
    $('span#error-tip').text('');

    var targetIndex=e.relatedTarget.getAttribute('data-index');
    usernameTemp=userInfo[targetIndex].username;
    nicknameTemp=userInfo[targetIndex].nickname;
    $('#save').attr('data-index',targetIndex);
    $('#primaryModal input.username').val(userInfo[targetIndex].username);
    $('#primaryModal input.nickname').val(userInfo[targetIndex].nickname);
    $('button#role').val(userInfo[targetIndex].role);
    $('button#role').html(userInfo[targetIndex].role);
    var status=userInfo[targetIndex].status==true ? statusType[1] :statusType[0];
    $('button#status').val(status);
    $('button#status').html(status);
});

//定义修改框保存按钮点击事件
$('button#save').on('click',function(){
    if($('input.username').val()==""  ){
        $('p.msg-tip').text('用户名不能为空！');
        return;
    }else if( $('input.nickname').val()==""){
        $('p.msg-tip').text('昵称名不能为空！');
        return;
    } else{
        var targetIndex = $('#save').attr('data-index');
        userInfo[targetIndex].username = $('input.username').val() + "";
        userInfo[targetIndex].nickname = $('input.nickname').val() + "";
        userInfo[targetIndex].status =statusType.indexOf($('button#status').val());
        userInfo[targetIndex].role = $('button#role').val()+ "";
        $.ajax({
            url: "update",
            type: "post",
            //如果提交的是json数据类型，则必须有此参数,表示提交的数据类型
            //dataType: "json", //表示返回值类型，不必须
            contentType: "application/json; charset=utf-8",
            // dataType: "json",
            data: JSON.stringify(userInfo[targetIndex]) ,
            success: function (result) {
                if(result.success==false){
                    userInfo[targetIndex].username=usernameTemp;
                    userInfo[targetIndex].nickname=nicknameTemp;
                    $('p.msg-tip').text(result.msg);
                    return;
                }
                $('p.msg-tip').text(result.msg);
                setTimeout(function () {
                    $('#primaryModal').modal('hide');
                },1200);
            },
            error:function (error) {
                console.log(error);
            }
        });
    }
});

$("#primaryModal input.username").blur(function(){
    if($('#primaryModal input.username').val()==""){
        $('p.msg-tip').html('用户名不能为空！');
    }
});

$('#dangerModal').on('show.bs.modal', function (e) {
    var targetIndex=e.relatedTarget.getAttribute('data-index');
    $('button.delete-user').attr('data-index',targetIndex);

});

$('button.delete-user').on('click',function(){
    var targetIndex = $('button.delete-user').attr('data-index');
    $('#dangerModal').modal('hide');
    $.ajax({
        url: "delete-user",
        type: "post",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(userInfo[targetIndex]) ,
        success: function (result) {
            if(result=="fail"){

            }else if(result=="ok"){
                var url='getinfo?currentPage='+currentPage+'&pageSize='+pageSize;
                $.get(url, function(data) {
                    userTable.userInfo=userInfo=data;
                });
            }
        }
    });

    userInfo.splice(targetIndex,1);

});

$('#addUserModal').on('show.bs.modal', function (e) {
    $('p.msg-tip').text('');
    $('#addUserModal input.username').focus();
});

$('#addUserModal').on('shown.bs.modal', function (e) {
    $('#addUserModal input.username').focus();
    $('#addUserModal input.dataRegiter').val()
});

$(' button.addUserBtn').on('click',function(){
    if($('#addUserModal input.username').val()==""){
        $('p.msg-tip').text('输入不能为空');
        return;
    }else{
        var temp={};
        temp.nickname = $('input.nickname').val() + "";
        temp.username = $('#addUserModal input.username').val() + "";
        temp.status = false;
        temp.role = $('#addUserModal button.role').val() + "";
        temp.dataRegiter = "";
        $.ajax({
            url: "insert",
            type: "post",
            contentType: "application/json; charset=utf-8",
            // dataType: "json",
            data: JSON.stringify(temp) ,
            success: function (result) {
                if(result.success==false){
                    $('p.msg-tip').text(result.msg);
                    return;
                }
                $('p.msg-tip').text(result.msg);
                setTimeout(function () {
                    $('#addUserModal').modal('hide');
                },1200);
            },
            error:function (error) {
                console.log(error);
            }
        });
    }
});

$("#addUserModal input.username").blur(function(){
    // if($('#addUserModal input.username').val()==""){
    //     $('p.msg-tip').html('用户名不能为空！');
    //     $('p.msg-tip').css('color','red');
    // }
});

