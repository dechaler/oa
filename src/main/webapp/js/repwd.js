layui.use(['table','layer','form','upload'], function(){
    var form = layui.form;
    var layer = layui.layer;
    var $ = layui.$;
    context = '/oa';

    form.on('submit(repwd)',function (obj) {
        console.log(obj.field);
        var pwd = obj.field.password;
        console.log(pwd);
        var repwd = obj.field.repassword;
        console.log(repwd);
        if (pwd !== repwd) {
            layer.msg('密码不一致', {
                offset: '15px'
                ,icon: 2
                ,time: 1000
            });
            return;
        }
        
        $.ajax({
            url: context + '/info/repassword',
            type: 'POST',
            data: {"password":pwd},
            dataType: 'json', 
            success:function (res) {
                if (res > 0) {
                    layer.msg('修改成功，请重新登录', {
                        offset: '15px'
                        ,icon: 1
                        ,time: 1000
                    },function(){
                        parent.location.href = context + '/views/login.html';
                    });
                }
            },
            error:function () {
                
            }
        });
    });



    // 验证
    form.verify({
        pwd: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
    });
});