$(function () {


    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });

    var $form = $("#login-form");

    $form.validate({
        debug:true,
        errorElement : 'span',
        errorClass : 'help-block',
        focusInvalid : true,
        rules : {
            nickname : {
                required : true ,
                minlength: 5,
                maxlength: 18
            },
            password : {
                required : true ,
                minlength: 2,
                maxlength: 18
            }
        },
        messages : {
            nickname : {
                required :"请输入登录账号."  ,
                minlength:"登录账号不应低于5位",
                maxlength:"登录账号不应超过18位"
            },
            password : {
                required :"请输入登录密码."  ,
                minlength:"登录密码不应低于5位",
                maxlength:"登录密码不应超过18位"
            }
        },
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        success : function(label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement : function(error, element) {
            element.parent('div').append(error);
        },
        submitHandler: function (validator, loginForm, submitButton) {
            // 使用Ajax提交form表单数据
            $.post($form.attr('action'), $form.serialize(), function (result) {
                if (result.code == 200) {
                    layer.msg(result.msg, {time: 3000}, function () {
                        window.location.href = "/index.html";
                    })
                } else {
                    layer.msg(result.msg);
                }
            }, 'json');
        },

    });
});
