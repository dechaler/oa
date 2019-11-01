layui.use(['table','layer','laydate','form'], function(){
    var table = layui.table;
    var $ = layui.$;
    var laydate = layui.laydate;
    var form = layui.form;
    var layer = layui.layer;

    var reqDatas = function(url,data){
        $.ajax({
            url:url,
            // url:'/task/selectEmpTask',
            type: 'GET',
            dataType: 'json',
            data:data,
            success: function (res) {
                table.render({
                    elem: '#task_list'
                    ,toolbar: '#headToolbar'
                    ,title: '任务数据表'
                    ,data:res.data
                    ,cols: [[
                        {type: 'checkbox', fixed: 'left'}
                        ,{field:'id', title:'ID',hide: true}
                        ,{field:'content', title:'任务', }
                        ,{field:'startTime', title:'开始时间', sort: true}
                        ,{field:'endTime', title:'结束时间', sort: true}
                        ,{field:'empId', title:'员工号',templet: function(res){
                                return res.employee.id }}
                        ,{fixed: 'right', title:'操作', toolbar: '#rowToolbar'}
                    ]]
                    ,page: true
                });
            }
        });
    };

    //日期时间选择器
    laydate.render({
        elem: '#endTime-add'
        ,type: 'datetime'
    });
    laydate.render({
        elem: '#startTime-add'
        ,type: 'datetime'
    });
    laydate.render({
        elem: '#endTime-edit'
        ,type: 'datetime'
    });
    laydate.render({
        elem: '#startTime-edit'
        ,type: 'datetime'
    });



    //初始化渲染
    table.render({
        elem: '#task_list'
        ,url:'/task/selectEmpTask'
        ,toolbar: '#headToolbar'
        ,title: '用户数据表'
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        }
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID',hide: true}
            ,{field:'content', title:'任务', }
            ,{field:'startTime', title:'开始时间', sort: true}
            ,{field:'endTime', title:'结束时间', sort: true}
            ,{field:'empId', title:'员工号',templet: function(res){
                    return res.employee.id }}
            ,{fixed: 'right', title:'操作', toolbar: '#rowToolbar'}
        ]]
        ,page: true
    });




    //监听弹出层关闭按钮
    $('#close-btn-add').on('click', function(){
        layer.closeAll('page');
        $("#ipt-add").val("");
        $("#startTime-add").val("");
        $("#endTime-add").val("");
    });

    $('#close-btn-edit').on('click', function(){
        layer.closeAll('page');
    });


    //监听表头工具栏按钮
    table.on('toolbar(task)', function(obj){
        // console.log(obj);
        // console.log(obj.event);
        switch (obj.event) {
            case 'add':
                //     // layer.msg(obj.event);
                //弹出层
                layer.open({
                    title: '添加任务',
                    offset: 't',
                    type: 1,
                    content: $('#pop-add'),
                });
                break;
            case 'del_more':
                var selectedData = table.checkStatus(obj.config.id);
                var length = selectedData.data.length;
                var tIds = [];
                for (var i = 0; i < length; i++) {
                    tIds.push(selectedData.data[i].id);
                }
                if (length == 0){
                    layer.msg("请勾选要删除的信息", {icon: 3, time: 1000, offset: '100px'});
                }else {
                    layer.confirm('确定要删除' + length + '条信息吗？', {
                        icon: 3,
                        title: '提示',
                        offset: '100px'
                    }, function (index) {
                        $.ajax({
                            url: '/task/deleteTasks',
                            type: 'POST',
                            data: {"taskIds":JSON.stringify(tIds)},
                            dataType: 'json',
                            success: function (res) {
                                if (res > 0) {
                                    layer.msg("删除成功", {
                                        icon: 1,
                                        time: 1000,
                                        offset: '100px'
                                    });
                                }
                                // setTimeout(()=>layer.close(index),1000);

                                reqDatas("/task/selectEmpTask",res.data);
                            },

                            error: function () {
                                layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
                            }
                        });
                    });
                }
                break;
        }
    });

    //监听添加弹出层提交
    form.on('submit(add)',function (obj) {
        $.ajax({
            url:'/task/upTask',
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
                    setTimeout(()=>layer.closeAll('page'),1000);
                    //重置输入框
                    $("#ipt-add").val("");
                    $("#startTime-add").val("");
                    $("#endTime-add").val("");
                    //重新刷新页面
                    // 方式一（会闪）
                    // setTimeout('window.location.reload()',1000);
                    reqDatas("/task/selectEmpTask",null);

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

    //监听修改弹出层提交
    form.on('submit(edit)',function (obj) {
        console.log(obj.field);
        $.ajax({
            url:'/task/updateTask',
            type: 'POST',
            data: obj.field,
            dataType: 'json',
            success: function (res) {
                if (res > 0) {
                    layer.msg("修改成功", {
                        icon: 1,
                        time: 1000,
                        offset: ''
                    });
                    console.log(res);
                    // setTimeout('layer.closeAll(\'page\')', 1000);
                    setTimeout(()=>layer.closeAll('page'),1000);
                    //重新刷新页面
                    // 方式一（会闪）
                    // setTimeout('window.location.reload()',1000);
                    reqDatas("/task/selectEmpTask",null);
                }else{
                    layer.msg("您没修改任何信息", {
                        icon: 1,
                        time: 1000,
                        offset: '100px'
                    });
                    setTimeout(()=>layer.closeAll('page'),1000);
                }
            },
            error: function () {
                layer.alert("请求信息发生异常", {icon: 2, title: '提示', offset: '100px'});
            }
        })
    });



    //监听行工具事件
    table.on('tool(task)', function(obj){
        var data = obj.data;
        var id = data.id;
        switch (obj.event) {
            case 'edit':
                // layer.msg(obj.event);
                $("#taskId-edit").val(id);
                $("#ipt-edit").val(data.content);
                $("#startTime-edit").val(data.startTime);
                $("#endTime-edit").val(data.endTime);
                layer.open({
                    title: '修改任务',
                    offset: 't',
                    type: 1,
                    content: $('#pop-edit'),
                });
                break;
            case 'del':
                // layer.msg(obj.event);
                // layer.confirm()
                layer.confirm('确定要删除信息吗？', {
                    icon: 3,
                    title: '提示',
                    offset: '100px'
                }, function (index) {
                    $.ajax({
                        url: '/task/deleteTask',
                        type: 'POST',
                        data: {"taskId":id},
                        dataType: 'json',
                        success: function (res) {
                            if (res > 0) {
                                layer.msg("删除成功", {
                                    icon: 1,
                                    time: 1000,
                                    offset: '100px'
                                });
                            }
                            // setTimeout(()=>layer.close(index),1000);
                            reqDatas("/task/selectEmpTask",res.data);
                        },
                        error: function () {
                            layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
                        }
                    })
                });

                break;
        }
    });
});
