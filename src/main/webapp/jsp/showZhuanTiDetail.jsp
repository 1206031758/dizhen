<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/9
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>专题详情页面</title>
</head>
<style>
    div{border: red 1px solid}
    #leftdiv{
        width: 80%;
        float: left;
    }
    #rightdiv{
        width: 18%;
        height: 500px;
        float: right;
    }
    #flashdiv{
        width: 99%;
        height: 30px;
        float: left;
    }
    #zhibodiv{
        width: 99%;
        height: 100%;
        float: left;
    }
    #canshu{
        height: 50%;
        width: 99%;
        float: left;
    }
    #beijing{
        height: 30%;
        width: 99%;
        float: left;
    }
    #lianjie{
        height: 10%;
        width: 99%;
        float: left;
    }
</style>
<body>
<div><jsp:include page="/jsp/common/header.jsp"></jsp:include></div>
<!--左边 -->
<div id="leftdiv">
    <!--刷新 -->
    <div id="flashdiv">
        <a href="${pageContext.request.contextPath }/zhibo/showZhuanTiDetail?zhuantiid=${zhuantiid}"><button>点击刷新</button></a>
    </div>
    <!--直播模块 -->
    <div id="zhibodiv">
        <c:forEach items="${zhibos}" var="zhibo">
            <div>
                <h3>${zhibo.zhibotitle}</h3>
                ${zhibo.zhibolaiyuan}(${zhibo.zhibotime})
                <br>
                ${zhibo.zhibocontent}
            </div>
        </c:forEach>
    </div>
</div>
<!--右边 -->
<div id="rightdiv">
<%--    地震参数--%>
    <div id="canshu">
        <h3>地震参数</h3>
        时间：${dizhenshuju.dizhentime}<br>
        纬度：${dizhenshuju.weidu}<br>
        经度：${dizhenshuju.jingdu}<br>
        深度：${dizhenshuju.shendu}<br>
        震级：${dizhenshuju.zhenji}<br>
        震中位置：${dizhenshuju.weizhi}
    </div>
<%--    震区背景--%>
    <div id="beijing">
        <h3>震区背景</h3>
        ${dizhenshuju.weizhi}
    </div>
<%--    相关链接--%>
    <div id="lianjie">
        <h3>相关链接</h3>
        <a href="#">中国地震科普网</a>
    </div>
</div>
</body>
</html>
