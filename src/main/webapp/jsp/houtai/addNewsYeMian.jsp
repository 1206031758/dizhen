<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/23
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加行业新闻页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/news/addNews">
    <input type="hidden" value="${user.userid}" name="userid">
    新闻标题：<input type="text" name="newstitle"><br><br>
    新闻内容：<textarea cols="60" rows="10" name="newscontent"></textarea><br><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
