layui.define(['index', "form"], function(){
    var $ = layui.$
        ,setter = layui.setter
        ,admin = layui.admin
        ,form = layui.form;
    alert("3");
    form.render();
    alert("2");

    // 验证
    form.verify({
        empId: [
            /^[0-9]{6}$/
            ,'必须为6位员工号码'
        ],
        pwd: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]

        //     pwd: [
        //     /^[\S]{6,12}$/
        //     ,'密码必须6到12位，且不能出现空格'
        // ]
    });

    //提交
    form.on('submit(LAY-user-login-submit)', function(obj){
        //请求登入接口
        // $.ajax({
        //         //     ur1:'/emp/login?verifyCode=' + verifyCode
        //         //     }
        //         // )
        admin.req({
            url: '/emp/login' //实际使用请改成服务端真实接口
            ,data: obj.field
            ,done: function(res){
                //请求成功后，写入 access_token
                layui.data(setter.tableName, {
                    key: setter.request.tokenName
                    ,value: res.data.access_token
                });
                //登入成功的提示与跳转
                layer.msg('登入成功', {
                    offset: '15px'
                    ,icon: 1
                    ,time: 1000
                }, function(){
                    location.href = '..'; //后台主页
                });
            }
        });

    });
    exports('login', {});
})