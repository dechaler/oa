

layui.use(['table','form','layer','laydate'], function(){
    var table = layui.table;
    var $ = layui.$;
    var form = layui.form;
    var layer = layui.layer;
    var laydate = layui.laydate;
    context = '/oa';

    //封装考勤页面ajax请求
    var reqDataFromkaoqin = function(url,data,kqWay,startDate,endDate){
        $.ajax({
            url:context + url,
            type: 'GET',
            dataType: 'json',
            data:data,
            async:false,
            success: function (res) {
                table.render({
                    elem: '#attendInfo'
                    ,toolbar: '#hearToolbar'
                    ,data:res.data
                    ,cols: [[
                        {field:'empId', title: '员工号', templet:function (res) {
                                return res.employee.id}}
                        ,{field:'name', title: '员工名',templet:function (res) {
                                return res.employee.name  }}
                        ,{field:'sex', title: '所属部门',templet:function (res) {
                                return res.employee.department.name }}
                        ,{field:'way', title: '打卡类型',templet:function (res) {
                                if(res.way == 0){
                                    return "上班";
                                }else{
                                    return "下班";
                                }}}
                        ,{field:'attendDate', title: '日期'}
                        ,{field:'flag', title: '标志', templet:function (res) {
                                if (res.flag == 0) {
                                    return '<span style="color: #ffff00;">' +  '未考勤';
                                }else if (res.flag == 1) {
                                    return '<span style="color: #00ff00;">' +  '出勤';
                                }else if (res.flag == 2) {
                                    return '<span style="color: #ff0000;">' +  '迟到';
                                }else if (res.flag == 3) {
                                    return '<span style="color: #ff0000;">' +  '早退';
                                }}}
                    ]]
                    ,page: true
                });
                laydate.render({
                    elem: '#dateScope'
                    , range: true
                    ,done: function(value, date, endDate){
                        var startDate = date.year + "-" + date.month + "-" + date.date;
                        var endDate = endDate.year + "-" + endDate.month + "-" + endDate.date;
                        $("#startDate").attr('value', startDate);
                        $("#endDate").attr('value', endDate);
                        console.log(startDate);
                        console.log(endDate);
                        var kqWay = $("#kqWay").val();
                        var datas = {"kqWay": kqWay,"startDate":startDate,"endDate":endDate};
                        reqDataFromkaoqin("/attendance/selectAttendInfo",datas,kqWay,startDate,endDate);
                    }
                });
                $("#kqWay").attr('value', kqWay);
                $("#startDate").attr('value', startDate);
                $("#endDate").attr('value', endDate);
            }
        });
    };


    //监听打卡页面
    table.on('tool(clockIn)', function(obj){
        var data = obj.data;
        switch (obj.event) {
            case 'clockIn':
                console.log(data);
                var way = data.way;
                var flag = data.flag;
                if (data.way == 0) {
                    layer.confirm('上班打卡为9点前，确定打卡？', {
                        icon: 3,
                        title: '提示',
                        offset: '100px'
                    }, function (index) {
                        $.ajax({
                            url: context + '/attendance/signIn',
                            type: 'POST',
                            async:false,
                            data:{"way":way,"flag":flag},
                            success: function (res) {
                                if (res >= 0) {
                                    layer.msg("成功打卡", {
                                        icon: 1,
                                        time: 1000,
                                        offset: '100px'
                                    });
                                }
                                // setTimeout(()=>layer.close(index),1000);
                                // reqDatas("/attendance/selectClockInInfo", res.data);
                                table.reload('table');
                            },
                            error: function () {
                                layer.alert("请求信息发生异常", {icon: 2, title: '提示'});
                            }
                        });
                    });
                }else if (data.way == 1) {
                    layer.confirm('下班打卡为18点后，确定打卡？', {
                        icon: 3,
                        title: '提示',
                        offset: '100px'
                    }, function (index) {
                        console.log(11);
                        $.ajax({
                            url: context + '/attendance/signIn',
                            type: 'POST',
                            async:false,
                            // dataType: 'json',
                            data:{"way":way,"flag":flag},
                            success: function (res) {
                                console.log("success");
                                if (res >= 0) {
                                    layer.msg("成功打卡", {
                                        icon: 1,
                                        time: 1000,
                                        offset: '100px'
                                    });
                                }
                                // setTimeout(()=>layer.close(index),1000);
                                // reqDatas("/attendance/selectClockInInfo", res.data);
                                table.reload('table');
                            },
                            error: function () {
                                layer.alert("请求信息发生异常", {icon: 2, title: '提示'});
                            }

                        });
                    });
                }
                break;
        }
    });




    //监听考勤页面方式搜索下拉框
    form.on('select(kqWay)', function(data){
        var kqWay = data.value;
        // console.log(way); //得到被选中的值
        //赋值到一个input
        $("#kqWay").attr('value', kqWay);
        // console.log($("#kqWay").val());

        // console.log($("#startDate").val());
        var startDate = $("#startDate").val();

        // console.log($("#endDate").val());
        var endDate = $("#endDate").val();
        var datas = {"kqWay": kqWay,"startDate":startDate,"endDate":endDate};
        reqDataFromkaoqin("/attendance/selectAttendInfo",datas,kqWay,startDate,endDate);

    });


    //监听时间范围选择
    var reqDate = laydate.render({
        elem: '#dateScope'
        ,range: true
        ,done: function(value, date, endDate){
            var startDate = date.year + "-" + date.month + "-" + date.date;
            var endDate = endDate.year + "-" + endDate.month + "-" + endDate.date;
            console.log(startDate);
            console.log(endDate);
            $("#startDate").attr('value', startDate);
            $("#endDate").attr('value', endDate);
            var kqWay = $("#kqWay").val();
            var datas = {"kqWay": kqWay,"startDate":startDate,"endDate":endDate};
            reqDataFromkaoqin("/attendance/selectAttendInfo",datas);
        }
    });
});