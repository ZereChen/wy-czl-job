$().ready(function() {
    $(".m-loginBox .mail-login").validate({
        rules:{
            Uname:{
                required:true,
                email:true
            },
            Upwd:{
                minlength:6,
                required:true,
            }
        },
        messages:{
            Uname:{
                required:"请输入邮箱账号",
                email:"请输入正确的邮箱格式"
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
    // console.log($(".m-loginBox .mail-login [name = Uname]").val())
    // console.log($(".m-loginBox .mail-login [name = Upwd]").val())
    $.ajax({
        url : "http://10.41.154.19/wwyx/php/login.php",
        type : "post",
        data : {
            Uname : $(".m-loginBox .mail-login [name = Uname]").val(),
            Upwd : $(".m-loginBox .mail-login [name = Upwd]").val()
        },
        dataType : "json",
        success : function (data) {
            var $result = data;
            console.log($result.status);
            if ($result.status == 1){
                window.location.href='../index.html';
            }
            else if ($result.status == 0){
            // <label id="Upwd-error" class="error" for="Upwd">请输入密码</label>
                if ($("#account-error").length <= 0) {
                    $error = $("<label id=\"account-error\" class=\"error\">账号或者密码错误</label>")
                    $error.insertAfter($(".mail-login .inputGroup").eq(1))
                }
            }
        },
        error : function () {
            console.log("sql error")
        }
    })
}
