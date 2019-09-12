<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/20
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>历史查询</title>
</head>
<style>
    /*div{border: red 1px solid}*/
    #baocun{
        height: 35px;
        width: 100%;
        float: left;
    }
    #jieguo{
        height: 300px;
        width: 100%;
        float: left;
    }
</style>
<body>
<%--引入jquery--%>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery-3.4.1.min.js"></script>
<%--引入tableExport插件，将表格导出为excel--%>
<script type="text/javascript" src="${pageContext.request.contextPath }/tableExport.js"></script>

<div><jsp:include page="/jsp/common/chaxunheader.jsp"></jsp:include></div>
<%--查询结果和保存到本地--%>
<div id="baocun">
    查询结果：&nbsp;<button id="button">保存到本地</button>
</div>
<%--查询的结果展示--%>
<div id="jieguo">
    <table border="1px" cellspacing="0" id="table">
        <tr>
            <th>震级（M）</th>
            <th>发震时刻（UTC+8）</th>
            <th>纬度（°）</th>
            <th>经度（°）</th>
            <th>深度（千米）</th>
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
    $("#button").click(function(){$("#table").tableExport({type :"excel",fileName :"历史查询表格"});});
</script>
</html>
