/**
 * Created by Administrator on 2017/10/16.
 */
$(function () {
    $(function () {

        // var table = $('#job_list').dataTable( {
        //     "deferRender": true,
        //     "processing" : true,
        //     "serverSide": true,
        //     ajax:{
        //         url:'/pictures',
        //         type:'post',
        //         data:function (d) {
        //             console.info('ajax data ',d)
        //             var obj = {};
        //             obj.id = $('#id').val();
        //             obj.picturesId = $('#picturesId').val();
        //             obj.url = $('#url').val();
        //             obj.start = d.start;
        //             obj.length = d.length;
        //             return obj;
        //         }
        //     },
        //     "searching": false,
        //     "ordering": false,
        //     columns:[
        //         {data:'id'},
        //         {data:'picturesId'},
        //         {data:'url',visible:true,render:function (data, type, row) {
        //             return '<button class="btn btn-primary btn-xs job_operate" _type="job_delete" type="button">删除</button>'
        //         }},
        //     ],
        //     "language" : {
        //         "sProcessing" : "处理中...",
        //         "sLengthMenu" : "每页 _MENU_ 条记录",
        //         "sZeroRecords" : "没有匹配结果",
        //         "sInfo" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页，_TOTAL_ 条记录 )",
        //         "sInfoEmpty" : "无记录",
        //         "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
        //         "sInfoPostFix" : "",
        //         "sSearch" : "搜索:",
        //         "sUrl" : "",
        //         "sEmptyTable" : "表中数据为空",
        //         "sLoadingRecords" : "载入中...",
        //         "sInfoThousands" : ",",
        //         "oPaginate" : {
        //             "sFirst" : "首页",
        //             "sPrevious" : "上页",
        //             "sNext" : "下页",
        //             "sLast" : "末页"
        //         },
        //         "oAria" : {
        //             "sSortAscending" : ": 以升序排列此列",
        //             "sSortDescending" : ": 以降序排列此列"
        //         }
        //     }
        // } );

        // layui.use('table',function () {
        //     var table = layui.table;
        //
        //     //layui 方法级渲染table
        //     table.render({
        //         elem:'#LAY_table_user',
        //         url:'/pictures1',
        //         cols:[
        //             {checkbox:true,fixed:true},
        //             {field:'id',title:'ID'},
        //             {field:'picturesId',title:'图片Id'},
        //             {field:'url',title:'URL'},
        //         ],
        //         request:{
        //             pageName:'pages'
        //         },
        //         id:'testReload',
        //         page:true,
        //         height:315,
        //         done: function(res, curr, count){
        //             //如果是异步请求数据方式，res即为你接口返回的信息。
        //             //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
        //             console.log(res);
        //
        //             //得到当前页码
        //             console.log(curr);
        //
        //             //得到数据总量
        //             console.log(count);
        //         }
        //     })
        // });


        layui.use('table', function () {
            var table = layui.table;

            //方法级渲染
            table.render({
                elem: '#LAY_table_user'
                , url: '/pictures1'
                , request: {pageName: 'current', limitName: 'size'}
                , response: {statusCode: 200}
                , cols: [[
                    {checkbox: true}
                    , {field: 'id', title: 'ID', width: 360, sort: true}
                    , {field: 'picturesId', title: '图片ID', width: 170}
                    , {field: 'url', title: 'URL', width: 500,templet:'#picturesImg'}
                    , {field: 'op', title: '操作', width: 200, templet: '#op'}
                ]]
                , id: 'testReload'
                , page: true
                ,height: 600
            });

            var $ = layui.$, active = {
                reload: function () {
                    var demoReload = $('#demoReload');
                    table.reload('testReload', {
                        where: {
                            'condition': {
                                id: demoReload.val()
                            }
                        }
                    });
                }
                , getCheckData: function () {
                    var  checkStatus=table.checkStatus('testReload'),
                        data=checkStatus.data;
                    layer.alert(JSON.stringify(data))
                }
                , getCheckLength: function () {
                    var  checkStatus=table.checkStatus('testReload'),
                        data=checkStatus.data;
                    layer.msg("选中了: " + data.length + ' 个')
                },
                isAll: function () {
                    var  checkStatus=table.checkStatus('testReload');
                    layer.msg(checkStatus.isAll?"全选":"未全选")
                }
            };

            table.on('checkbox(user)', function (obj) {
                console.info(obj)
            })

            table.on('tool(user)', function (obj) {
                console.info(obj)
                var data = obj.data;
                if (obj.event === 'detail') {
                    layer.msg('ID:' + data.id + "的查看操作")
                } else if (obj.event === 'del') {
                    layer.confirm('真的删除这一条数据吗', function (index) {
                        obj.del();
                        layer.close(index);
                    })
                } else if (obj.event === 'edit') {
                    layer.alert("编辑行:<br/>" + JSON.stringify(data))
                }
            })


            $('.demoTable .layui-btn').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });


    })
})