<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/22
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台登录页面</title>
</head>
<style>
    #f{
        margin: 60px 20px;
    }
    #login{
        border:1px chartreuse solid;
        height:200px;
        width:300px;
        margin: 50px 600px;
        line-height: 30px;
        text-align: center;
        float: left;
    }
</style>
<body>
<div><jsp:include page="/jsp/common/header.jsp"></jsp:include></div>
<%--后台的登录框--%>
<div id="login">
    <form action="${pageContext.request.contextPath }/user/login" method='post' id="f">
        用户名：<input type="text"  name="loginname">
        <br>
        密&nbsp;码：<input type="password" name="password">
        <br>
        <input type='submit'  value="登录">&nbsp;&nbsp;&nbsp;
        <input type='reset'  value="重置">
    </form>
</div>
</body>
</html>
