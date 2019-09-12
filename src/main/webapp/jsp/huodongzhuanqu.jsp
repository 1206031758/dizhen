<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/21
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>钻取页面</title>
</head>
<style>
    #save{
        height: 30px;
        width: 100%;
        float: left;
    }
    #info{
        height: 500px;
        width: 100%;
        float: left;
    }
</style>
<body>
<%--引入jquery--%>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery-3.4.1.min.js"></script>
<%--引入tableExport插件，将表格导出为excel--%>
<script type="text/javascript" src="${pageContext.request.contextPath }/tableExport.js"></script>

<div><jsp:include page="/jsp/common/header.jsp"></jsp:include></div>
<%--保存到本地--%>
<div id="save">
    查询结果：时间:${dizhentime},${jibie}地震信息
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button id="button">保存到本地</button>
</div>
<%--展示地震信息--%>
<div id="info">
    <table border="1px" cellspacing="0" id="table">
        <tr>
            <th>震级(M)</th>
            <th>发震时刻(UTC+8)</th>
            <th>纬度(°)</th>
            <th>经度(°)</th>
            <th>深度(千米)</th>
            <th>参考位置</th>
        </tr>
        <c:forEach items="${datas}" var="datas">
            <tr>
                <td>${datas.zhenji}</td>
                <td>${datas.dizhentime}</td>
                <td>${datas.weidu}</td>
                <td>${datas.jingdu}</td>
                <td>${datas.shendu}</td>
                <td>${datas.weizhi}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
        <script type="text/javascript">
            $("#button").click(function(){$("#table").tableExport({type :"excel",fileName :"地震活动钻取表格"});});
        </script>
</html>
