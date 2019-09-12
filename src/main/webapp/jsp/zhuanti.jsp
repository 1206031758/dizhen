<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/19
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>专题页面</title>
</head>
<body>
<div><jsp:include page="/jsp/common/header.jsp"></jsp:include></div>
<h3>地震专题</h3>
<table cellspacing="0" border="1px" style="width: 1000px">
    <tr>
        <th>时间</th>
        <th>地震专题名字</th>
    </tr>
    <c:forEach items="${datas}" var="datas">
        <tr>
            <td>${datas.dizhentime}</td>
            <td><a href="${pageContext.request.contextPath }/dizhenshuju/jibenxinxi?dizhenshujuid=${datas.dizhenshujuid}">${datas.weizhi}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
