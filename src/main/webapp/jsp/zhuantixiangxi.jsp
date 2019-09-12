<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/19
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>专题详细页面</title>
</head>
<style>
    /*div{border: red 1px solid}*/
    #zhibo{
        height: 600px;
        width: 60%;
        float: left;
    }
    #canshu{
        height: 300px;
        width: 30%;
        float: right;
    }
    #dizhencanshu{
        height: 39%;
        width: 100%;
    }
    #zhenqubeijing{
        height: 39%;
        width: 100%;
    }
    #lianjie{
        height: 20%;
        width: 100%;
    }
</style>
<body>
<div><jsp:include page="/jsp/common/header.jsp"></jsp:include></div>
<%--直播模块--%>
<div id="zhibo"></div>
<%--参数--%>
<div id="canshu">
<%--地震参数--%>
    <div id="dizhencanshu"></div>
<%--震区背景--%>
    <div id="zhenqubeijing"></div>
<%--相关链接--%>
    <div id="lianjie"></div>
</div>

</body>
</html>
