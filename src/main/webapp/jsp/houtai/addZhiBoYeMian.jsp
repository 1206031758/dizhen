<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/23
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>直播信息添加页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/zhibo/addzhibo">
    <input type="hidden" value="${user.userid}" name="userid">
    所属专题：<select name="zhuantiid">
                        <c:forEach items="${zhuantinames}" var="zhuantiname">
                            <option value=${zhuantiname.dizhenshujuid}>${zhuantiname.dizhentime}</option>
                        </c:forEach>
                    </select>
    <br><br>
    直播标题：<input type="text" name="zhibotitle"><br><br>
    直播来源：<input type="text" name="zhibolaiyuan"><br><br>
    直播内容：<textarea cols="60" rows="9" name="zhibocontent"></textarea>
    <button>添加</button>
</form>
</body>
</html>
