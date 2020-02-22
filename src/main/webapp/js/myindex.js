//jquery
//请求session
// $(function(){
//     $.ajax({
//                 url: "../RequiredSession",
//                 type: "POST",
//                 dataType: "json",
//                 async: false,
//                 success:function (res) {
//                     if (res.code == 0){
//                         console.log(res);
//                         $("#black_box").hide();
//                         $("#emp").html(res.data.employee.name);
//                     }else {
//                         alert("请先登录");
//                         location.href = "login.html";
//                     }
//                 }
//             })
// })

//layui 组件
context = '/oa';
layui.use(['layer', 'form','element'], function() {
    var $ = layui.$;
    var element = layui.element;
    var layer = layui.layer;


    //请求主页判断session
    $.ajax({
        url: context + "/RequiredSession",
        type: "POST",
        dataType: "json",
        async: false,
        success: function (res) {
            if (res.code == 0) {
                $("#black_box").hide();
                // $("#emp").html(res.data[0].name);
            } else {
                // alert("请先登录");
                location.href = "login.html";
            }
        },
        error: function () {
            // alert("访问异常");
            location.href = "login.html";
        }
    });


    $.ajax({
        url: context + "/emp/selectEmpById",
        type: "POST",
        dataType: "json",
        async: false,
        success: function (res) {
            if (res.code == 0) {
                $("#emp").html(res.data.name);
            }
        },
        error: function () {
            console.log("访问异常");
        }
    });

    //退出
    $("#logout").on('click',function(e){
        e.preventDefault();
        layer.confirm('确定退出吗？', {icon: 3, title:'提示'},function(index){
            //do something
            // layer.close(index);
            $.ajax({
                url: "../emp/logout",
                type: "POST",
                success: function (res) {
                    if (res == 1) {
                        location.href="./login.html";
                    }
                }
            })

        });
    });
    

       /* 模拟单页跳转*/
        $("dd>a, .index").click(function (e) {
            e.preventDefault();
            $("#iframeMain").attr("src",$(this).attr("href"));
        });

});
// layui.config({
//     base: '../js/' //你存放新模块的目录，注意，不是layui的模块目录
// }).use('index'); //加载入口

//模拟单页跳转
/*
$(document).ready(function(){
    $("dd>a, .index").click(function (e) {
        e.preventDefault();
        $("#iframeMain").attr("src",$(this).attr("href"));
    });
});
*/







