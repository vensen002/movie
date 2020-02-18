<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layuiAdmin 后台管理员</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${ctx}/static/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/layuiadmin/style/admin.css" media="all">
</head>
<body>

  <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <%-- 头部表单 --%>
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
              <input type="text" name="account" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-block">
              <input type="text" name="realName" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
              <input type="text" name="tel" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-user-back-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>

      <%-- 数据表格区域 --%>
      <div class="layui-card-body">
        <div style="padding-bottom: 10px;">
          <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
          <button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
        </div>

        <table id="LAY-user-back-manage" lay-filter="LAY-user-back-manage"></table>
        <script type="text/html" id="buttonTpl">
          {{#  if(d.looked == true){ }}
            <button class="layui-btn layui-btn-xs">锁定</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">未锁定</button>
          {{#  } }}
        </script>
        <script type="text/html" id="table-useradmin-admin">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          {{#  if(d.role == '超级管理员'){ }}
            <a class="layui-btn layui-btn-disabled layui-btn-xs"><i class="layui-icon layui-icon-delete"></i>删除</a>
          {{#  } else { }}
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
          {{#  } }}
        </script>
      </div>
    </div>
  </div>

  <script src="${ctx}/static/foreground/js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript">
    jQuery.ctx = "${pageContext.request.contextPath}";
  </script>
  <script src="${ctx}/static/layuiadmin/layui/layui.js"></script>
  <script>
    function getFields(input, field) {
      var output = [];
      for (var i=0; i < input.length ; ++i)
        output.push(input[i][field]);
      return output;
    }
  </script>
  <script>
  layui.config({
    base: $.ctx + '/static/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'useradmin', 'table','form'], function(){
    var $ = layui.$
    ,form = layui.form
    ,table = layui.table;

    //监听搜索
    form.on('submit(LAY-user-back-search)', function(data){
      var field = data.field;
      // console.log(field)
      //执行重载
      table.reload('managerList', {
        where: field
      });
    });

    //监听行双击事件
    table.on('rowDouble(LAY-user-back-manage)', function(obj){
      console.log(obj.tr) //得到当前行元素对象
      console.log(obj.data) //得到当前行数据
      //obj.del(); //删除当前行
      //obj.update(fields) //修改当前行数据
      //obj 同上
    });


    //事件
    var active = {
      batchdel: function(){
        var checkStatus = table.checkStatus('managerList')
        ,checkData = checkStatus.data; //得到选中的数据
        // console.log(getFields(checkData,'id'))
        if(checkData.length === 0){
          return layer.msg('请选择数据');
        }

        layer.prompt({
          formType: 1
          ,title: '敏感操作，请验证口令'
        }, function(value, index){
          layer.close(index);

          layer.confirm('确定删除吗？', function(index) {
            var ids = getFields(checkData,'id');
            // console.log(ids)

            //执行 Ajax 后重载
            /*
            admin.req({
              url: 'xxx'
              //,……
            });
            */
            $.ajax({
              url: $.ctx + "/administrators/del" ,
              type: "post",
              data: { ids:ids },
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
          });
        });
      }
      ,add: function(){
        layer.open({
          type: 2
          ,title: '添加管理员'
          ,content: $.ctx + '/administrators/adminform'
          ,area: ['420px', '420px']
          ,btn: ['确定', '取消']
          ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
            ,submitID = 'LAY-user-front-submit'
            ,submit = layero.find('iframe').contents().find('#'+ submitID);

            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
              var field = data.field; //获取提交的字段
              // console.log(field)
              //提交 Ajax 成功后，静态更新表格中的数据
              $.ajax({
                url: $.ctx + "/administrators/create" ,
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
        });
      }
    }
    $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });
  });
  </script>
</body>
</html>

