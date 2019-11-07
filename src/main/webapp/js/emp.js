layui.use(['table','layer','form'], function(){
    var table = layui.table;
    var $ = layui.$;
    var form = layui.form;
    var layer = layui.layer;

    /*form.on('select(selecting)', function(data){
        console.log(data.elem); //得到select原始DOM对象
        console.log(data.value); //得到被选中的值
    });*/

    //页面初始化
    table.render({
        elem: '#employee_list',
        url:'/emp/selectAllEmp',
        type: 'GET',
        toolbar: '#empToolbar',
        // contentType:'application/json',
        request: {
            pageName: 'page', //页码的参数名称，默认：page
            limitName: 'limit' //每页数据量的参数名，默认：limit
        },
        response: {
            statusName: 'code' //规定数据状态的字段名称，默认：code
            ,statusCode: 0 //规定成功的状态码，默认：0
            ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
            ,countName: 'count' //规定数据总数的字段名称，默认：count
            ,dataName: 'data' //规定数据列表的字段名称，默认：data
        },
        parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": "", //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        },
        cols: [[
            {field:'id', title: '员工号',sort: true}
            ,{field:'name', title: '员工名'}
            ,{field:'sex',  title: '性别'}
            ,{field:'age',  title: '年龄',sort: true}
            ,{field:'inerDate', title: '入职时间',sort: true}
            ,{field:'job_name', title:'职位',templet: '#job_name'}
            ,{field:'department_name',  title: '所属部门',templet: '#depart_name'}
        ]]
        ,page: true
    });

    // 监听查找
    var params = {};
    form.on('submit(find)', function(data){
        var val = $("#ipt").val();
        // console.log(val);
        if (val == ""){
            layer.alert("请输入查询",{icon: 3,title:'提示'});
            return;
        }else {
            // console.log("拿到val= " + val)
            var selected = data.field.selected;
                if (selected == "") {
                    layer.alert("请选择查询类型",{icon: 3,title:'提示'});
                    return;
                } else {
                    if (selected == 1) {
                        if (!isNaN(val)) { //isNaN 判断是否为非数字，
                            params = {
                                empId: val
                            }
                        } else {
                            // console.log("selected == " + val)
                            layer.alert("请输入正确的员工号",{icon: 5,title:'提示'});
                            return;

                        }
                    }
                    if (selected == 2) {
                        if (isNaN(val)) { //isNaN 判断是否为非数字，
                            params = {
                                name: val
                            }
                        } else {
                            // console.log("selected == " + val)
                            layer.alert("请输入正确的员工名",{icon: 5,title:'提示'});
                            return;

                        }
                    }

                    if (selected == 3) {
                        if (isNaN(val)) { //isNaN 判断是否为非数字，
                            params = {
                                departName: val
                            }
                        } else {
                            // console.log("selected == " + val)
                            layer.alert("请输入正确的部门名",{icon: 5,title:'提示'});
                            return;
                        }
                    }
                }

        }
            // return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。


        reqDatatoTable(params);
    });

    //显示全部
    form.on('submit(show_all)', function(){
        var params = {};
        reqDatatoTable(params);
    });


    reqDatatoTable = function (params) {
        $.ajax({
            url:'/emp/selectAllEmp',
            type: 'GET',
            dataType: 'json',
            data: params,
            success: function (res) {
                table.render({
                    elem: '#employee_list',
                    data: res.data,
                    toolbar: '#empToolbar',
                    cols: [[
                        {field:'id', title: '员工号',sort: true}
                        ,{field:'name', title: '员工名'}
                        ,{field:'sex',  title: '性别'}
                        ,{field:'age',  title: '年龄',sort: true}
                        ,{field:'inerDate', title: '入职时间',sort: true}
                        ,{field:'job_name', title:'职位',templet: '#job_name'}
                        ,{field:'department_name',  title: '所属部门',templet: '#depart_name'}
                    ]]
                    ,page: true
                });
            },
            error: function () {
                layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
            }
        })
    }


});
