$(function () {

})

$().ready(function() {
    $(".reg-block .container form").validate({
        rules:{
            Uname:{
                required:true,
                email:true
            },
            Upwd:{
                minlength:6,
                maxlength:16,
                required:true,
            },
            Urepwd:{
                equalTo:"#inpt-pw"
            },
            Uphone:{
                rangelength:[11,11],
                number:true
            },
            Umailver:{
                required:true,
                rangelength:[4,4],
            },
            Ucheck:{
                required:true
            }
        },
        messages:{
            Uname:{
                required:"请输入邮箱账号",
                email:"请输入正确的邮箱格式"
            },
            Upwd:{
                minlength:"密码太短",
                maxlength:"密码太长",
                required:"请输入密码"
            },
            Urepwd:{
                equalTo:"两次输入的密码必须一致"
            },
            Uphone:{
                rangelength:"请输入正确的手机号",
                number:"请输入正确的手机号"
            },
            Umailver:{
                required:"请输入验证码",
                rangelength:"请输入正确的验证码",
            },
            Ucheck:{
                required:"必须同意服务条款"
            }
        },
        errorPlacement: function(error, element) {
            // Append error within linked label
            error.insertAfter(element.parent().children(".error-info"));
        },
        submitHandler:function(form){
            // alert("提交事件!");
            // $(form).ajaxSubmit();
            $.ajax({
                url: "http://10.41.154.19/wwyx/php/register.php",
                type: "post",
                dataType: "json",
                data: {
                    Uname: $(".reg-block [name = Uname]").val(),
                    Upwd: $(".reg-block [name = Upwd]").val(),
                    Uphone: $(".reg-block [name = Uphone]").val(),
                    Umailver: $(".reg-block [name = Umailver]").val()
                },
                success: function (data) {
                    //要执行的代码
                    var $result = data;
                    console.log($result.status+$result.msg);
                    if ($result.status == 1){
                        window.location.href='index.html';
                    }
                    else if ($result.status == 0){
                        // <label id="Upwd-error" class="error" for="Upwd">请输入密码</label>
                        if ($("#submit-error").length <= 0) {
                            $error = $("<label id=\"submit-error\" class=\"error-info\" >"+$result.msg+"</label>")
                            $error.insertAfter($(".reg-block .submit"))
                        }
                        else {
                            console.log("信息已存在")
                        }
                    }
                },
                error : function () {
                    console.log("sql error")
                }
            })

        }
    });
})
