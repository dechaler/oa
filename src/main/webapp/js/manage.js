context = '/oa';
layui.use(['layer', 'form','element','table','laydate'], function() {
    var $ = layui.$;
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var laydate = layui.laydate;

    //渲染部门
    var renderDepart = function(dom){
        $.ajax({
            type: 'get',
            url:context + '/depart/selectDepart',
            async: false,
            success: function (res) {
                if (res.code == 0) {
                    var data = res.data;
                    // console.log(data);
                    $(dom).empty();
                    $(dom).append("<option value=''> 请选择部门 </option>");
                    data.forEach(function (item) {
                        $(dom).append("<option value='" + item.id + "'> " + item.name + " </option>");
                    });
                    form.render();
                }
            }
        });
    };

    //渲染岗位
    var renderJob = function(dom) {
        $.ajax({
            type: 'get',
            url:context + '/job/selectAllJob',
            async: false,
            success: function (res) {
                if (res.code == 0) {
                    var data = res.data;
                    // console.log(data);
                    $(dom).empty();
                    $(dom).append("<option value=''> 请选择岗位 </option>");
                    data.forEach(function (item) {
                        $(dom).append("<option value='" + item.id + "'>" + item.name + " </option>");
                    });
                    form.render();
                }
            }
        });
    };


    renderDepart("#departId");
    renderJob("#jobId");
    //渲染入职时间
    laydate.render({
        elem: '#innerDate'
    });
    form.verify({
        empId: [
            /^[0-9]{6}$/
            // /^[0-9]{4}$/
            ,'必须为6位员工号'
        ],
        phone: [
            /^1[3456789]\d{9}$/
            ,'请输入正确的手机号'
        ],
        age:[
            /^(?:[1-9][0-9]?|1[01][0-9]|120)$/
            ,'请输入正确的年龄'
        ]});

    //员工管理-搜索员工
    form.on('submit(search-from-btn)',function (obj) {
        var name = $("#empName").val();
        $.ajax({
            url:context + '/emp/selectAllEmp',
            type: 'GET',
            dataType: 'json',
            data: {"name":name},
            success: function (res) {
                table.render({
                    elem: '#employee_list',
                    data: res.data,
                    cols: [[
                        {field: 'id', title: '员工号', sort: true}
                        , {field: 'name', title: '员工名'}
                        , {field: 'sex', title: '性别'}
                        , {field: 'age', title: '年龄', sort: true}
                        , {field: 'inerDate', title: '入职时间', sort: true}
                        , {field: 'job_name', title: '职位', templet: function (d) {
                                return d.job.name;
                            }}
                        , {field: 'department_name', title: '所属部门', templet: function (d) {
                                return d.department.name;
                            }}
                        ,{fixed: 'right', title:'操作', toolbar: '#rowToolbar'}

                    ]]
                    ,page: true
                });
            },
            error: function () {
                layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
            }
        });
    });

    //添加员工
    form.on('submit(formSubmit-zh)',function (obj) {
        // console.log(obj.field);
        $.ajax({
            type: 'post',
            url:context + '/emp/addEmp',
            data:obj.field,
            success: function (res) {
                console.log(res);
                if (res > 0) {
                    layer.msg('添加成功', {
                        offset: '15px'
                        , icon: 1
                        , time: 1000
                    }, function () {
                        location.href = context + '/views/manage/empMage.html'; //主页
                    });
                }
                    if (res === -1) {
                        layer.msg('员工号已存在', {
                            offset: '15px'
                            , icon: 2
                            , time: 1000
                        });
                    }
                },
            error: function () {
                    layer.msg('请求异常', {
                        offset: '15px'
                        , icon: 2
                        , time: 1000
                    });
                }
        });

    });

    //监听行工具栏按钮
    table.on('tool(empManage)', function(obj){
        var data = obj.data;
        switch (obj.event) {
            case 'del':
                layer.confirm('确定要删除吗？', {
                    icon: 3,
                    title: '提示',
                    offset: '100px'
                },function (index) {
                    $.ajax({
                        type: 'post',
                        url: context + '/emp/delEmp',
                        data: {id: data.id},
                        success: function (res) {
                            console.log(res);
                            if (res > 0) {
                                layer.msg('删除成功', {
                                    offset: '15px'
                                    , icon: 1
                                    , time: 1000
                                }, function () {
                                    location.href = context + '/views/manage/empMage.html'; //主页
                                });
                            }
                        },
                        error: function () {
                            layer.msg('请求异常', {
                                offset: '15px'
                                , icon: 2
                                , time: 1000
                            });
                        }
                    });
                });
                break;

            case 'edit':
                renderDepart("#pop-departId");
                renderJob("#pop-jobId");
                //渲染入职时间
                laydate.render({
                    elem: '#pop-innerDate'
                });
                $("#pop-empId").attr('value',data.id);
                //控制部门选中状态
                var departId = data.department.id;
                $("#pop-departId option[value = " + departId + "]").attr('selected', 'selected');
                $("#pop-innerDate").attr('value',data.inerDate);
                //控制岗位选中状态
                var jobId = data.job.id;
                $("#pop-jobId option[value = " + jobId + "]").attr('selected', 'selected');

                $("#pop-innerDate").attr('value',data.inerDate);
                $("#pop-name").attr('value',data.name);
                $("#pop-age").attr('value',data.age);
                $("#pop-phone").attr('value',data.phone);
                $("#pop-empId").attr('value',data.id);
                if (data.sex == '男') {
                    $("#pop-man").attr("checked",true);
                } else {
                    $("#pop-woman").attr("checked",true);
                }
                form.render();
                layer.open({
                    title: '修改员工',
                    offset: 't',
                    type: 1,
                    area:['400px','550px'],
                    content: $('#pop-edit'),
                });
                break;
        }
    });

    //修改员工
    form.on('submit(pop-formSubmit-zh)',function (obj) {
        // console.log(obj.field);
        $.ajax({
            type: 'post',
            url:context + '/emp/updateEmp',
            data:obj.field,
            success: function (res) {
                console.log(res);
                if (res > 0) {
                    layer.msg('修改成功', {
                        offset: '15px'
                        , icon: 1
                        , time: 1000
                    }, function () {
                        location.href = context + '/views/manage/empMage.html'; //主页
                    });
                }
            },
            error: function () {
                layer.msg('请求异常', {
                    offset: '15px'
                    , icon: 2
                    , time: 1000
                });
            }
        });

    });

    //监听取消按钮
    $('#pop-cancel').on('click', function(){
        layer.closeAll('page');
    });



    //监听人事管理-请假管理
    table.on('tool(leaveManage)', function(obj){
        var data = obj.data;
        console.log(data);
        switch (obj.event) {
            case 'agree':
                // alert(obj.event);
                var status = 1;
                $.ajax({
                    url: context + '/leave/updateStatusById',
                    type: 'POST',
                    async:false,
                    data:{"status":status,"id":data.id},
                    success: function (res) {
                        table.reload('table');
                    },
                    error: function () {
                        layer.alert("请求信息发生异常", {icon: 2, title: '提示'});
                    }
                });
                table.reload('table');
                break;

            case 'disagree':
                // alert(obj.event);
                var status = -1;
                $.ajax({
                    url: context + '/leave/updateStatusById',
                    type: 'POST',
                    async:false,
                    data:{"status":status,"id":data.id},
                    success: function (res) {
                        table.reload('table');
                    },
                    error: function () {
                        layer.alert("请求信息发生异常", {icon: 2, title: '提示'});
                    }
                });
                table.reload('table');
                break;
        }
    });

    //退出
    $("#logout").on('click',function(e){
        e.preventDefault();
        layer.confirm('确定退出吗？', {icon: 3, title:'提示'},function(index){
            //do something
            // layer.close(index);
            $.ajax({
                url: "/oa/emp/logout",
                type: "POST",
                success: function (res) {
                    if (res == 1) {
                        location.href="./login.html";
                    }
                }
            })

        });
    });
});