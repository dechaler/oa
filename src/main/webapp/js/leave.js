

layui.use(['table','layer','laydate','form'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var $ = layui.$;
    var laydate = layui.laydate;
    var form = layui.form;


    var reqDatas = function(url,data){
        $.ajax({
            url:url,
            // url:'/task/selectEmpTask',
            type: 'GET',
            dataType: 'json',
            data:data,
            success: function (res) {
                table.render({
                    elem: '#leave'
                    ,toolbar: '#headToolbar'
                    ,data:res.data
                    ,cols: [[
                        {field:'id', hide:true, title: 'ID', sort: true}
                        ,{
                            field: 'empId', title: '员工号', templet: function (res) {
                                return res.employee.id
                            }
                        }
                        , {
                            field: 'name', title: '员工名', templet: function (res) {
                                return res.employee.name
                            }
                        }
                        ,{field:'reason', title: '请假原因'}
                        ,{field:'startTime', title: '开始时间'}
                        ,{field:'endTime', title: '结束时间'}
                        ,{field:'status', title: '状态',templet:function (res) {
                                if (res.status == 0) {
                                    return '<span style="color: #ffff00;">' + '待审核';
                                } else if (res.status == 1) {
                                    return '<span style="color: #00ff00;">' + '已同意';
                                } else if (res.status == -1) {
                                    return '<span style="color: #ff0000;">' + '未同意';
                                }}}
                    ]]
                    ,page: true
                });
            }
        });
    };


    //监听表头工具栏按钮
    table.on('toolbar(leave)', function(obj) {
        // console.log(obj);
        // console.log(obj.event);
        switch (obj.event) {
            case 'add':
                //     // layer.msg(obj.event);
                //弹出层
                layer.open({
                    title: '请假',
                    offset: 't',
                    type: 1,
                    area: '500px',
                    content: $('#pop-add'),
                });

                break;
        }
    });



    //日期时间选择器
    laydate.render({
        elem: '#endTime-add'
        ,type: 'datetime'
    });
    laydate.render({
        elem: '#startTime-add'
        ,type: 'datetime'
    });

    //监听弹出层关闭按钮
    $('#close-btn').on('click', function(){
        layer.closeAll('page');
        $("#ipt-add").val("");
        $("#startTime-add").val("");
        $("#endTime-add").val("");
    });




    //监听添加弹出层提交
    form.on('submit(add)',function (obj) {
        console.log(obj.field);
        $.ajax({
            url:'/leave/addLeave',
            type: 'POST',
            data: obj.field,
            dataType: 'json',
            success: function (res) {
                if (res > 0) {
                    layer.msg("添加成功", {
                        icon: 1,
                        time: 1000,
                        offset: ''
                    });
                    // setTimeout('layer.closeAll(\'page\')', 1000);
                    //重置输入框
                    setTimeout(()=>layer.closeAll('page'),1000);
                    setTimeout(function () {
                        $("#ipt-add").val("");
                        $("#startTime-add").val("");
                        $("#endTime-add").val("");
                    },1050);
                    //重新刷新页面
                    // 方式一（会闪）
                    // setTimeout('window.location.reload()',1000);
                    reqDatas("/leave/selectLeaveInfo",null);
                }else{
                    layer.msg("添加失败", {
                        icon: 2,
                        time: 1000,
                        offset: '100px'
                    });
                }

            },
            error: function () {
                layer.alert("请求信息发生异常", {icon: 2, title: '提示', offset: '100px'});
            }
        })
    });


});