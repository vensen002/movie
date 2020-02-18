<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layuiAdmin 管理员 iframe 框</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${ctx}/static/layuiadmin/layui/css/layui.css" media="all">
</head>
<body>

  <div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
      <label class="layui-form-label"></label>
      <div class="layui-input-inline">
        <input type="text" name="id" lay-verify="required" placeholder="请输入账户名" autocomplete="off" class="layui-input layui-hide" value="${account.id}">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">账户名</label>
      <div class="layui-input-inline">
        <input type="text" name="account" lay-verify="required" placeholder="请输入账户名" autocomplete="off" class="layui-input" value="${account.account}" readonly>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">真实姓名</label>
      <div class="layui-input-inline">
        <input type="text" name="realName" lay-verify="required" placeholder="请输入真实姓名" autocomplete="off" class="layui-input" value="${account.realName}">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">手机</label>
      <div class="layui-input-inline">
        <input type="text" name="tel" lay-verify="phone" placeholder="请输入号码" autocomplete="off" class="layui-input" value="${account.tel}">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">审核状态</label>
      <div class="layui-input-inline">
        <input type="checkbox" lay-filter="switch" name="switch" lay-skin="switch" lay-text="通过|待审核">
      </div>
    </div>
    <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
  </div>
  <script src="${ctx}/static/foreground/js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript">
    jQuery.ctx = "${pageContext.request.contextPath}";
  </script>
  <script src="${ctx}/static/layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
    base: $.ctx + '/static/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form'], function(){
    var $ = layui.$
    ,form = layui.form ;


  })
  </script>
</body>
</html>