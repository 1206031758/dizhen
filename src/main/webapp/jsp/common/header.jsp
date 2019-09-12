<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/17
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    /*div{border: red 1px solid}*/
    #tophead{height: 80px;
        width:100%;
        float: left;
        background-color: palegreen;
    }
    #buttomhead{height: 35px;
                width:100%;
                float: left;
            background-color: greenyellow;
                text-align: center;
                padding-top: 10px;
    }
    #toplogo{width: 20%;float: left;margin-top: 17px;margin-left: 50px;}
    #topword{width: 62%;float: right;}
    /*a标签去掉下划线*/
    a{text-decoration:none;}
</style>
<body>
<%--上层div--%>
<div id="tophead">
    <div id="toplogo">
        <a href="${pageContext.request.contextPath }/homePage.jsp"><img src="${pageContext.request.contextPath }/logo.png"/></a>
    </div>
    <div id="topword">
        <font size="10"><b>中国地震台网</b></font>
    </div>
</div>
<%--下层div--%>
<div style="position: relative;" id="buttomhead">
    <span style="position: absolute;left: 10%;"><a href="${pageContext.request.contextPath }/homePage.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span style="position: absolute;left: 30%;"> <a href="${pageContext.request.contextPath }/dizhenshuju/zhuanti">地震专题</a>&nbsp;&nbsp;&nbsp;</span>
    <span style="position: absolute;left: 50%;">   <a href="${pageContext.request.contextPath }/jsp/historychaxun.jsp">历史查询</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span style="position: absolute;left: 70%;">  <a href="${pageContext.request.contextPath }/jsp/huodong.jsp">地震活动</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span style="position: absolute;left: 90%;">  <a href="${pageContext.request.contextPath }/jsp/houtai/houTaiLoginPage.jsp">后台管理</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
</div>
</body>
</html>
