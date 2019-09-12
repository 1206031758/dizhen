<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/22
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加用户页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/user/addUser">
    姓名：<input type="text" name="username" >   <br><br>
    登录名：<input type="text" name="loginname" onkeyup="checkSuser()" id="suser"><br><br>   <span id="suserMsg"><font color="gray"></font></span><br />
    密码：<input type="password" name="password"><br><br>
    性别：<input type="radio" name="usersex" value="男">男
    <input type="radio" name="usersex" value="女">女
    <br><br>
    所担角色：<c:forEach items="${role}" var="Role">
                    <input type="checkbox" name="roles" value="${Role.roleid}"> ${Role.rolename}
              </c:forEach>
    <br><br>
    <button>添加</button>
</form>
</body>
</html>
