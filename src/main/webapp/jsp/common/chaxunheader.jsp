<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/20
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询的head</title>
</head>
<style>
    /*div{border: red 1px solid}*/
    #chaxun1{
        height: 230px;
        width: 60%;
        float: left;
    }
    #chaxun2{
        height: 230px;
        width: 39%;
        float: right;
    }
    /*a标签去掉下划线*/
    a{text-decoration:none;}
</style>
<body>
<div><jsp:include page="/jsp/common/header.jsp"></jsp:include></div>
<%--自写的查询条件--%>
<div id="chaxun1">
    <form action="${pageContext.request.contextPath }/dizhenshuju/chaxundata" method="post">
        时间：<input type="date" name="dizhentime1">至<input type="date" name="dizhentime2"><br><br>
        纬度：大于<input type="text" name="weidu1">&nbsp;小于<input type="text" name="weidu2">&nbsp;单位：度&nbsp;范围：-90至90<br><br>
        经度：大于<input type="text" name="jingdu1">&nbsp;小于<input type="text" name="jingdu2">&nbsp;单位：度&nbsp;范围：-180至180<br><br>
        深度：大于<input type="text" name="shendu1">&nbsp;小于<input type="text" name="shendu2">&nbsp;单位：千米<br><br>
        震级：大于<input type="text" name="zhenji1">&nbsp;小于<input type="text" name="zhenji2"><br>
        <input type="submit" value="查询">
    </form>
</div>
<%--时间和震级查询--%>
<div id="chaxun2">
    按时间：<a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByATime?time=24">最近24小时内地震</a><br>
            <a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByATime?time=48">最近48小时内地震</a><br>
            <a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByATime?time=7">最近7天内地震</a><br>
            <a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByATime?time=30">最近30天内地震</a><br>
            <a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByATime?time=1">最近一年内地震</a><br><br>
    按震级（一年内）：   <a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByAZhenJi?zhenji=6">6.0级以上地震</a><br>
                        <a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByAZhenJi?zhenji=5">5.0级以上地震</a><br>
                        <a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByAZhenJi?zhenji=4">4.0级以上地震</a><br>
                        <a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByAZhenJi?zhenji=3">3.0级以上地震</a><br>
                        <a href="${pageContext.request.contextPath }/dizhenshuju/chaXunByAZhenJi?zhenji=2">3.0级以下地震</a><br>
</div>
</body>
</html>
