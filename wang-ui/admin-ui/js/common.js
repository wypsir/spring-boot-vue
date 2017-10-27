/**
 * Created by Administrator on 2017/10/15.
 */
$(function () {
    $(document).ready(function () {
        //一般直接写在一个js文件中
        layui.use(['layer', 'form','table'], function () {
            var layer = layui.layer
                , form = layui.form
                ,table = layui.table;
            if (window.location.href.indexOf("login") > -1) {
               return;
            }
            layer.msg('Hello World',table);
        });

    });
    $("#logoutBtn").on('click',function () {

        layer.confirm('确认注销登录？',{icon:3,title:'系统提示'},function (index) {
            layer.close(index);
            $.post('/logout',function (res) {
                if (res.code == 200) {
                    layer.open({title:'系统提示',content:'注销成功',icon:1,end:function () {
                        window.location.href = '/';
                    }});
                } else {
                    layer.msg(res.msg||'操作失败');
                }

            });
        })

    })

});