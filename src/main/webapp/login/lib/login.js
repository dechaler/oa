layui.define(['index', "form"], function(exports){
    var $ = layui.$
        ,form = layui.form;
    form.render();
    // 验证
    form.verify({
        empId: [
            // /^[0-9]{6}$/
            /^[0-9]{4}$/
            ,'必须为6位员工号码'
        ],
        pwd: [
            // /^[\S]{6,12}$/
            /^[0-9]{3}$/
            ,'密码必须6到12位，且不能出现空格'
        ]

        //     pwd: [
        //     /^[\S]{6,12}$/
        //     ,'密码必须6到12位，且不能出现空格'
        // ]
    });

    //点击刷新验证码

    // $("#LAY-user-login-vercode").attr("value",22);

    $("#LAY-user-get-vercode").click(function () {
       this.src = "/kaptcha/verifyCode?t=" + (new Date).getTime()
    });
    //刷新验证码和置空
    var reflush = function(){
        $("#LAY-user-login-vercode").val("");
        // $("#LAY-user-login-vercode").attr("value",""); 没效果
        $("#LAY-user-get-vercode").attr("src","/kaptcha/verifyCode?t=\" + (new Date).getTime()");
    }


    //提交
    form.on('submit(LAY-user-login-submit)', function(obj){
        //请求登入接口
        // $.ajax({
        //         //     ur1:'/emp/login?verifyCode=' + verifyCode
        //         //     }
        //         // )

        // var verifyCode = $("#LAY-user-login-vercode").val();
        // console.log("verifyCode= " + verifyCode);
        // var id = $("#LAY-user-login-username").val();
        // console.log("id= " + id);
        // var password = $("#LAY-user-login-password").val();
        // console.log("password= " + password);

        console.log(obj.field);
        $.ajax({
            url: '/emp/login',
            type: 'POST',
            data: obj.field,
            dataType: 'json',
            success: function (res) {
                if (res.code == 0) {
                    console.log(res);
                    layer.msg('登入成功', {
                        offset: '15px'
                        ,icon: 1
                        ,time: 1000
                    }, function(){
                        location.href = './index.html'; //主页
                    });
                }else if (res.code == -2) {
                    console.log(res);
                    layer.msg('验证码输入有误', {
                        offset: '15px'
                        ,icon: 2
                        ,time: 1000
                    }, function(){
                        reflush();
                        // location.href = './login.html';
                        var a = $("#LAY-user-login-vercode").val();
                        console.log(a);
                    });
                }else {
                    console.log(res);
                    layer.msg('登陆失败，信息有误，请重新填写', {
                        offset: '15px'
                        ,icon: 1
                        ,time: 1000
                    }, function(){
                        location.href = './login.html';

                    });
                }
            },
            error: function () {
                layer.msg('登陆异常，请重新登陆', {
                    offset: '15px'
                    ,icon: 1
                    ,time: 1000
                }, function(){
                    // location.href = './login.html'; //后台主页
                });
            }
        })

        // admin.req({
        //     // url: '/emp/login?verifyCode='+verifyCode+'&id='+id+'&password='+password//实际使用请改成服务端真实接口
        //     url: '/emp/login'   //实际使用请改成服务端真实接口
        //     ,data: obj.field
        //     ,type:"POST"
        //     ,done: function(res){
        //         //请求成功后，写入 access_token
        //         // layui.data(setter.tableName, {
        //         //     key: setter.request.tokenName
        //         //     ,value: res.data.access_token
        //         // });
        //         //登入成功的提示与跳转
        //         layer.msg('登入成功', {
        //             offset: '15px'
        //             ,icon: 1
        //             ,time: 1000
        //         }, function(){
        //             location.href = './index.html'; //后台主页
        //         });
        //     }
        // });

    });
    exports('login', {});
})