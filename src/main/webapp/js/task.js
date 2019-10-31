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
        elem: '#endTime'
        ,type: 'datetime'
    });
    laydate.render({
        elem: '#startTime'
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
    $('#close-btn').on('click', function(){
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
                    content: $('#pop'),
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

    //监听弹出层提交
    form.on('submit(add)',function (obj) {
        $.ajax({
            url:'/task/upTask',
            type: 'GET',
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

                    //重新刷新页面
                    // 方式一（会闪）
                    // setTimeout('window.location.reload()',1000);
                    reqDatas("/task/selectEmpTask",res.data);

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

    //监听行工具事件
    table.on('tool(task)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            layer.prompt({
                formType: 2
                ,value: data.email
            }, function(value, index){
                obj.update({
                    email: value
                });
                layer.close(index);
            });
        }
    });
});
