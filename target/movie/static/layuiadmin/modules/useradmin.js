/**

 @Name：layuiAdmin 用户管理 管理员管理 角色管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */


layui.define(['table', 'form','util'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , util = layui.util
        , form = layui.form;


    //用户管理
    table.render({
        elem: '#LAY-user-manage'
        , url: layui.setter.base + 'json/useradmin/webuser.js' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 100, title: 'ID', sort: true}
            , {field: 'username', title: '用户名', minWidth: 100}
            , {field: 'avatar', title: '头像', width: 100, templet: '#imgTpl'}
            , {field: 'phone', title: '手机'}
            , {field: 'email', title: '邮箱'}
            , {field: 'sex', width: 80, title: '性别'}
            , {field: 'ip', title: 'IP'}
            , {field: 'jointime', title: '加入时间', sort: true}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-useradmin-webuser'}
        ]]
        , page: true
        , limit: 30
        , height: 'full-220'
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-user-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.prompt({
                formType: 1
                , title: '敏感操作，请验证口令'
            }, function (value, index) {
                layer.close(index);

                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            });
        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2
                , title: '编辑用户'
                , content: '../../../views/user/user/userform.html'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-user-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        table.reload('LAY-user-front-submit'); //数据刷新
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
            });
        }
    });

    //管理员管理
    table.render({
        elem: '#LAY-user-back-manage'
        // , url: layui.setter.base + 'json/useradmin/mangadmin.js' //模拟接口
        ,url: $.ctx + '/administrators/pages/?v=' + new Date().getTime()  //自定义接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 80, title: '编号', type: 'numbers'}
            , {field: 'account', title: '账号'}
            , {field: 'realName', title: '真实姓名'}
            , {field: 'role', title: '角色'}
            , {field: 'createAt', title: '创建时间', sort: true, templet: function(d) {
                    return util.toDateString(d.createAt);
                }}
            , {field: 'looked', title: '锁定状态', templet: '#buttonTpl', minWidth: 80, align: 'center'}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
        ]]
        , page: true
        , limit: 10
        , id: 'managerList'
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-user-back-manage)', function (obj) {
        var data = obj.data;
        var id = data['id'];
        if (obj.event === 'del') {
            layer.prompt({
                formType: 1
                , title: '敏感操作，请验证口令'
            }, function (value, index) {
                layer.close(index);
                layer.confirm('确定删除此管理员？', function (index) {
                    // console.log(obj)
                    // console.log(obj.data['id'])
                    $.ajax({
                        url: $.ctx + "/administrators/del" ,
                        type: "post",
                        data: { ids:id },
                        traditional:true,
                        dataType: "json",
                        success: function (data) {
                            if (data.code == 1) {
                                parent.layer.msg(data.message, {icon:6, time:2000});
                                table.reload('managerList'); //数据刷新
                                parent.layer.closeAll('iframe');
                            } else {
                                parent.layer.msg(data.message,{icon: 5, time: 2000});
                            }
                        },
                        error: function (x,e,s) {
                            parent.layer.msg('服务器异常，请联系管理员', {icon: 5, time: 2000});
                        }
                    });
                    layer.close(index);
                });
            });
        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2
                , title: '编辑管理员'
                , content: $.ctx + "/administrators/edit/" + id
                , area: ['420px', '420px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-user-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        console.log(field);

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        $.ajax({
                            url: $.ctx + "/administrators/edit" ,
                            type: "post",
                            data: field,
                            dataType: "json",
                            success: function (data) {
                                if (data.code == 1) {
                                    parent.layer.msg(data.message, {icon:6, time:2000});
                                    table.reload('managerList'); //数据刷新
                                    parent.layer.closeAll('iframe');
                                } else {
                                    parent.layer.msg(data.message,{icon: 5, time: 2000});
                                }
                            },
                            error: function (x,e,s) {
                                parent.layer.msg('服务器异常，请联系管理员', {icon: 5, time: 2000});
                            }
                        });
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
            })
        }
    });

    //角色管理
    table.render({
        elem: '#LAY-user-back-role'
        // , url: layui.setter.base + 'json/useradmin/role.js' //模拟接口
        , url: $.ctx + '/role/pages/?v=' + new Date().getTime()  //自定义接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 80, title: 'ID', align: 'center'}
            , {field: 'name', title: '角色名'}
            , {field: 'limits', title: '拥有权限'}
            , {field: 'description', title: '具体描述', templet: function (data) {
                    return data.description.length > 8 ? '<span title="'+ data.description + '">' + data.description.substr(0,8) + '...</span>' : data.description;
                }}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
        ]]
        , id: 'roleList'
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-user-back-role)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除此角色？', function (index) {
                // obj.del();
                $.ajax({
                    url: $.ctx + "/role/del" ,
                    type: "post",
                    data: { ids:data['id'] },
                    traditional:true,
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 1) {
                            parent.layer.msg(data.message, {icon:6, time:2000});
                            table.reload('roleList'); //数据刷新
                            parent.layer.closeAll('iframe');
                        } else {
                            parent.layer.msg(data.message,{icon: 5, time: 2000});
                        }
                    },
                    error: function (x,e,s) {
                        parent.layer.msg('服务器异常，请联系管理员', {icon: 5, time: 2000});
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2
                , title: '编辑角色'
                , content: $.ctx + '/role/edit/' + data['id']
                , area: ['500px', '480px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submit = layero.find('iframe').contents().find("#LAY-user-role-submit");

                    //监听提交
                    iframeWindow.layui.form.on('submit(LAY-user-role-submit)', function (data) {
                        var field = data.field; //获取提交的字段
                        console.log(field)

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        $.ajax({
                            url: $.ctx + "/role/edit" ,
                            type: "post",
                            data: field,
                            dataType: "json",
                            success: function (data) {
                                if (data.code == 1) {
                                    parent.layer.msg(data.message, {icon:6, time:2000});
                                    table.reload('roleList'); //数据刷新
                                    parent.layer.closeAll('iframe');
                                } else {
                                    parent.layer.msg(data.message,{icon: 5, time: 2000});
                                }
                            },
                            error: function (x,e,s) {
                                parent.layer.msg('服务器异常，请联系管理员', {icon: 5, time: 2000});
                            }
                        });
                        table.reload('LAY-user-back-role'); //数据刷新
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
            })
        }
    });

    exports('useradmin', {})
});