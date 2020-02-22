layui.use(['table','layer','form','upload'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.$;
    context = '/oa';

    $.ajax({
        url:context + '/info/selectInfo',
        type: 'POST',
        dataType: 'json',
        success: function (res) {
            console.log(res.data);
            if (res.count > 0) {
                var data = res.data;
                $("#id").attr("value",data[0].id);
                $("#name").attr("value",data[0].name);
                $("#department").attr("value",data[0].department.name);
                $("#job").attr("value",data[0].job.name);
                $("#age").attr("value",data[0].age);
                $("#inerDate").attr("value",data[0].inerDate);
                $("#phone").attr("value",data[0].phone);
                if (data[0].sex == '男') {
                    $("#man").attr("checked",true);
                } else {
                    $("#woman").attr("checked",true);
                }
            }
            //重新渲染
            form.render();
        },
        error:function (res) {
            console.log(111);
        }
    });




    form.verify({
        phone: [
            /^1[3456789]\d{9}$/
            ,'请输入正确的手机号'
        ],
        age:[
            /^(?:[1-9][0-9]?|1[01][0-9]|120)$/
            ,'请输入正确的年龄'
        ]
    });

    //修改资料
    form.on('submit(modify)',function (obj) {
        console.log(obj.field);
        $.ajax({
            url: context + '/info/update',
            type: 'POST',
            dataType: 'json',
            data: obj.field,
            success: function (res) {
                if (res > 0) {
                    layer.msg('更新成功', {
                        offset: '15px'
                        , icon: 1
                        , time: 1000
                        },function(){
                            parent.location.href = context + '/views/index.html'; //主页
                    });
                }
            }
        });
        return false;
    });




    $("#cancle").click(function () {
        // location.href = "./index.html";
        parent.location.href = context + "/views/index.html";
    });
});