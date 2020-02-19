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
                $("#inerDate").attr("value",data[0].inerDate);
                $("#phone").attr("value",data[0].phone);
                $("#sex").attr("value",data[0].sex);
            }
        },
        error:function (res) {
            console.log(111);
        }
    });




    form.verify({
        phone: [
            /^1[3456789]\d{9}$/
            ,'请输入正确的手机号'
        ]
    });

    //修改资料
    form.on('submit(modify)',function (obj) {
        console.log(obj.field);
        return false;
    });




    $("#cancle").click(function () {
        // location.href = "./index.html";
        parent.location.href = context + "/views/index.html";
    });
});