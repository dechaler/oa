
layui.use(['table','layer','form','upload'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.$;
    var upload = layui.upload;



    //表格工具函数
    tableRender = function (url,toolbar) {
        table.render({
            elem: '#file_list'
            ,url: url
            ,toolbar:toolbar
            ,parseData: function(res){ //res 即为原始返回的数据
                // console.log(res);
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            ,defaultToolbar: []
            ,skin:'line'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title: 'ID',width:70, sort: true}
                ,{field:'fileName', title: '文件名',width:500}
                ,{field:'filePath', title: '文件路径', hide: true}
                ,{field:'upTime', title: '上传时间',width:200, sort: true}
                ,{field:'emp_name', title: '员工',width:100,templet: function (res) {
                        return res.employee.name;
                    }}
                ,{field:'depart_name', title: '部门',templet: function (res) {
                        return res.employee.department.name;
                    }}
                ,
            ]]
            ,page:true
        });

        uploadFile('#upload','/file/upLoadFile');
    };

    //请求数据工具函数1
    reqDatatoTable = function (url,params,toolbar) {
        $.ajax({
            url:url,
            type: 'POST',
            dataType: 'json',
            data: params,
            success: function (res) {
                // console.log(res);
                table.render({
                    elem: '#file_list'
                    ,toolbar: toolbar
                    ,title: '文件表'
                    ,data:res.data
                    ,defaultToolbar: []
                    ,skin:'line'
                    ,cols: [[
                        {type: 'checkbox', fixed: 'left'}
                        ,{field:'id', title: 'ID',width:70, sort: true}
                        ,{field:'fileName', title: '文件名',width:500}
                        ,{field:'filePath', title: '文件路径', hide: true}
                        ,{field:'upTime', title: '上传时间',width:200, sort: true}
                        ,{field:'emp_name', title: '员工',width:100,templet: function (res) {
                                return res.employee.name;
                            }}
                        ,{field:'depart_name', title: '部门',templet: function (res) {
                                return res.employee.department.name;
                            }}
                        ,
                    ]]
                    ,page: true
                });
                uploadFile('#upload','/file/upLoadFile');
            },
            error: function () {
                layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
            }
        });
    };

    //请求数据工具函数2
    reqDatatoMyTable =function(url,params,toolbar){
        $.ajax({
            url:url,
            type: 'POST',
            dataType: 'json',
            data: params,
            success: function (res) {
                // console.log(res);
                table.render({
                    elem: '#file_list'
                    ,toolbar: toolbar
                    ,title: '文件表'
                    ,data:res.data
                    ,defaultToolbar: []
                    ,skin:'line'
                    ,cols: [[
                        {type: 'checkbox', fixed: 'left'}
                        ,{field:'id', title: 'ID',width:70, sort: true}
                        ,{field:'fileName', title: '文件名',width:500}
                        ,{field:'filePath', title: '文件路径', hide: true}
                        ,{field:'upTime', title: '上传时间',width:200, sort: true}
                        ,{field:'emp_name', title: '员工',width:100,templet: function (res) {
                                return res.employee.name;
                            }}
                        ,{field:'depart_name', title: '部门',templet: function (res) {
                                return res.employee.department.name;
                            }}
                        ,{fixed: 'right', title:'操作', toolbar: '#rowToolbar'}
                        ,
                    ]]
                    ,page: true
                });
                uploadFile('#upload1','/file/upLoadFile');
            },
            error: function () {
                layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
            }
        });
    };

    //文件上传函数
     uploadFile = function(elem,url){
        upload.render({
            elem: elem
            ,url: url
            // ,auto: false
            ,field: 'srcFile'
            ,accept: 'file' //普通文件
            ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                // layer.load(); //上传loading
                obj.preview(function(index, file) {
                    console.log(index); //得到文件索引
                    console.log(file); //得到文件对象
                })
            }
            ,done: function(res){
                if (res > 0) {
                    layer.msg("上传成功", {
                        icon: 1,
                        time: 1000,
                        offset: '100px'
                    });
                    $.ajax({
                        url:'/file/selectAllFile',
                        type: 'GET',
                        //标识
                        // data: {empId:status},
                        dataType: 'json',
                        success: function (res) {
                            table.render({
                                elem: '#file_list'
                                ,toolbar:'#hearToolbar'
                                ,defaultToolbar: []
                                ,data:res.data
                                ,skin:'line'
                                ,cols: [[
                                    {type: 'checkbox', fixed: 'left'}
                                    ,{field:'id', title: 'ID',width:70, sort: true}
                                    ,{field:'fileName', title: '文件名',width:500}
                                    ,{field:'upTime', title: '上传时间',width:200, sort: true}
                                    ,{field:'emp_name', title: '员工',width:100,templet: function (res) {
                                            return res.employee.name;
                                        }}
                                    ,{field:'depart_name', title: '部门',templet: function (res) {
                                            return res.employee.department.name;
                                        }}
                                ]]
                                ,page:true
                            });
                            uploadFile('#upload','/file/upLoadFile');
                        },
                        error: function () {
                            layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
                        }
                    });
                }else if (res === -1) {
                    layer.alert("请勿上传同名文件", {icon: 2, title: '提示'});
                }
            }
        });
    };

    //初始化页面渲染

    tableRender("/file/selectAllFile", "#hearToolbar");


    //表格头工具栏
    table.on('toolbar(file_list)', function(obj){
        switch (obj.event) {
            case 'findDepart':
                // layer.msg(obj.event);
                // alert(obj.event);
                reqDatatoTable("/file/selectFileByDepartIdAndEmpId",null,"#hearToolbar");
                break;
            case 'findAll':
                // layer.msg(obj.event);
                reqDatatoTable("/file/selectAllFile",null,"#hearToolbar");
                break;
            case 'down_more_File':
                var selectedData = table.checkStatus(obj.config.id);
                var length = selectedData.data.length;
                console.log(selectedData);
                layer.msg(obj.event);
                break;
            case 'del_more':
                // layer.msg('del_more');
                var selectedData = table.checkStatus(obj.config.id);
                var length = selectedData.data.length;
                var fIds = [];
                var fPaths = [];
                for (var i = 0; i < length; i++) {
                    fIds.push(selectedData.data[i].id);
                }
                for (var i = 0; i < length; i++) {
                    fPaths.push(selectedData.data[i].filePath);
                }
                if (length == 0){
                    layer.msg("请勾选要删除的文件", {icon: 3, time: 1000, offset: '100px'});
                }else {
                    layer.confirm('确定要删除' + length + '个文件吗？', {
                        icon: 3,
                        title: '提示',
                        offset: '100px'
                    }, function (index) {
                        $.ajax({
                            url: '/file/deleteFileByIds',
                            type: 'POST',
                            data: {"fIds":JSON.stringify(fIds),"fPaths":JSON.stringify(fPaths)},
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

                                reqDatatoMyTable('/file/selectFileByDepartIdAndEmpId',{empId:1},'#myHearToolbar');
                            },

                            error: function () {
                                layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
                            }
                        });
                    });
                }

                break;
            case 'myFile':
                // layer.msg('myFile');
                reqDatatoMyTable('/file/selectFileByDepartIdAndEmpId',{empId:1},'#myHearToolbar');
                break;
        }
    });

    //按文件名查找文件
    form.on('submit(find)',function (obj) {
        var val = $(".ipt").val();
        if (val == "") {
            layer.alert("请输入查询", {icon: 3, title: '提示'});
            return;
        }else{
            reqDatatoTable('/file/selectFileByFileName',obj.field,'#hearToolbar');
        }
    });

    form.on('submit(find1)',function (obj) {
        // layer.msg("haha");
        // console.log(obj.field);
        var val = $(".ipt").val();
        if (val == "") {
            layer.alert("请输入查询", {icon: 3, title: '提示'});
            return;
        }else{
            reqDatatoTable('/file/selectFileByFileName',obj.field,'#HearToolbar');
        }
    });


    //监听行工具栏
    table.on('tool(file_list)', function(obj){
        var data = obj.data;
        var fileId = data.id;
        var filePath = data.filePath;
        console.log(fileId);
        console.log(filePath);
        switch (obj.event) {
            case 'del':
                // layer.msg(obj.event);
                layer.confirm('确定要删除文件吗？', {
                    icon: 3,
                    title: '提示',
                    offset: '100px'
                }, function (index) {
                    $.ajax({
                        url: '/file/deleteFileById',
                        type: 'POST',
                        data: {"fileId":fileId, "filePath":filePath},
                        dataType: 'json',
                        success: function (res) {
                            if (res > 0) {
                                layer.msg("删除成功", {
                                    icon: 1,
                                    time: 1000,
                                    offset: '100px'
                                });
                            }
                            reqDatatoMyTable('/file/selectFileByDepartIdAndEmpId',{empId:1},'#myHearToolbar');
                        },
                        error: function () {
                            layer.alert("请求信息发生异常",{icon: 2,title:'提示'});
                        }
                    })
                });


        }
    });

});


