layui.use(['table','layer'], function(){
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    table.render({
        elem: '#sch_list'
        ,url:'/sch/selectSch'
        // ,toolbar: '#hearToolbar'
        ,title: '日程表'
        ,parseData: function(res){ //res 即为原始返回的数据
            // console.log(res.data);
            return {
                "code": res.code, //解析接口状态
                "msg": "", //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        }
        ,cols: [[
            // {type: 'checkbox', fixed: 'left'}
            // ,{field:'id', title:'ID', sort: true}
            {field:'schTask', title:'日程内容'}
            ,{field:'name', title:'发布人', templet: function(res){
                    return  res.employee.name;
                }}
            ,{field:'startTime', title:'时间',sort: true}
            ,{field:'depart_name', title:'所属部门',templet: function (res) {
                    return res.employee.department.name;
                }}
            // ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]]
        ,page: true
    });

    //头工具栏事件
    table.on('toolbar(sch_list)', function(obj){
        switch (obj.event) {
            case 'findToday':
                // layer.msg(obj.event);
                let date = new Date();
                let dateFormat = date.getFullYear() + "-" + (date.getMonth()+1) + "-";
                if(date.getDate() < 10) {
                    dateFormat += "0" + date.getDate();
                }else{
                    dateFormat += date.getDate();
                }
                // console.log(dateFormat);
                $.ajax({
                    url: '/sch/selectSch',
                    type: 'GET',
                    data: {"date":dateFormat},
                    dataType: 'json',
                    success: function (res) {
                        table.render({
                            elem: '#sch_list'
                            ,toolbar: '#hearToolbar'
                            ,title: '日程表'
                            ,data:res.data
                            ,cols: [[
                                // {type: 'checkbox', fixed: 'left'}
                                // ,{field:'id', title:'ID', sort: true}
                                {field:'schTask', title:'日程内容'}
                                ,{field:'name', title:'发布人', templet: function(res){
                                        return  res.employee.name;
                                    }}
                                ,{field:'startTime', title:'时间', sort: true}
                                ,{field:'depart_name', title:'所属部门',templet: function (res) {
                                        return res.employee.department.name;
                                    }}
                                // ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
                            ]]
                            ,page: true
                        });
                    },
                    error: function () {
                        layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
                    }
                });
                break;
            case 'findAll':
                // layer.msg(obj.event);
                $.ajax({
                    url: '/sch/selectSch',
                    type: 'GET',
                    dataType: 'json',
                    success: function (res) {
                        // console.log(res.data);
                        table.render({
                            elem: '#sch_list'
                            ,toolbar: '#hearToolbar'
                            ,title: '日程表'
                            ,data:res.data
                            ,cols: [[
                                // {type: 'checkbox', fixed: 'left'}
                                // ,{field:'id', title:'ID', sort: true}
                                {field:'schTask', title:'日程内容'}
                                ,{field:'name', title:'发布人', templet: function(res){
                                        return  res.employee.name;
                                    }}
                                ,{field:'startTime', title:'时间', sort: true}
                                ,{field:'depart_name', title:'所属部门',templet: function (res) {
                                        return res.employee.department.name;
                                    }}
                                // ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
                            ]]
                            ,page: true
                        });
                    },
                    error: function () {
                        layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
                    }
                });
                break;
        }
    });
});
