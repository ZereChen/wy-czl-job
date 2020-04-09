$().ready(function() {
    $(".m-loginBox .mail-login").validate({
        rules:{
            Uname:{
                required:true,
                minlength:4,
            },
            Upwd:{
                minlength:4,
                required:true,
            }
        },
        messages:{
            Uname:{
                required:"请输入邮箱账号",
                minlength:"账号太短"
            },
            Upwd:{
                minlength:"密码太短",
                required:"请输入密码"
            }
        },
        errorPlacement: function(error, element) {
            // Append error within linked label
            error.insertAfter(element.parent());
        },
        submitHandler:function(form){
            alert("提交事件!");
            form.submit();
        }
    });
})

$(function () {
    // 输入框清空按钮的显示
    $(".m-loginBox .mail-login .inputGroup input").on("keyup",function () {
        if ($(this).val().length > 0){
            $(this).next("a").css("display","inline-block")
        }else{
            $(this).next("a").css("display","none")
        }
    })
    $(".m-loginBox .mail-login .inputGroup a").click(function () {
        $(this).prev("input").val("")
        $(this).css("display","none")
    })
})

function submit() {
    $.ajax({
        url : login_url,
        type : "get",
        data : {
            username: $(".m-loginBox .mail-login [name = Uname]").val(),
            password: $.md5($(".m-loginBox .mail-login [name = Upwd]").val())
        },
        dataType : "json",
        xhrFields: {
            withCredentials: true
        },
        success : function (result,response,xhr) {
            if (result.success == true){
                $.cookie('users', JSON.stringify(result.data), { expires: 7});
                var users = result.data;
                console.log(result.data);
                var token = xhr.getResponseHeader('x-token');
                localStorage.setItem('token',token);
                if(users != undefined && users != null){
                    var role = users.roleEntity;
                    if(role.id == '3'){
                        //买家
                        window.location.href='index_login_buyer.html';
                    }else if(role.id == '2'){
                        //卖家
                        window.location.href='index_login_seller.html';
                    }
                }
            } else if (result.success == false){
            // <label id="Upwd-error" class="error" for="Upwd">请输入密码</label>
                if ($("#account-error").length <= 0) {
                    $error = $("<label id=\"account-error\" class=\"error\">账号或者密码错误</label>")
                    $error.insertAfter($(".mail-login .inputGroup").eq(1))
                }
            }
        },
        error : function () {
        }
    })
}
function logout() {
    $.ajax({
        url : logout_url,
        type : "get",
        dataType : "json",
        xhrFields: {
            withCredentials: true
        },
        // headers:{'Content-Type':'application/json;charset=utf8','organId':'1333333333'},
        success : function (result,response) {
            if (result.success == true){
                $.cookie('users', null);
                window.location.href='index_nologin.html';
            } else if (result.success == false){
                alert("退出失败："+result.message);
            }
        },
        error : function () {
        }
    })
}