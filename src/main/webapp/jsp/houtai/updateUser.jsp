<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/22
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户修改页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/user/updateUser">
    <input type="hidden" value="${user.userid}" name="userid">
    姓名：<input type="text" name="username" value="${user.username}">   <br><br>
    登录名：<input type="text" name="loginname" value="${user.loginname}" onkeyup="checkSuser()" id="suser"><br><br>   <span id="suserMsg"><font color="gray"></font></span><br />
    密码：<input type="password" name="password" value="${user.password}"><br><br>
    性别：<input type="radio" name="usersex" ${user.usersex eq '男' ? 'checked' : '' } value="男">男
    <input type="radio" name="usersex" ${user.usersex eq '女' ? 'checked' : '' } value="女">女
    <br><br>
    所担角色：<c:forEach items="${roleidandname}" var="Role">
    <input type="checkbox" name="roles" <c:forEach items="${roles}" var="role">${role.roleid eq Role.roleid ? 'checked':'' }</c:forEach> value="${Role.roleid}"> ${Role.rolename}
</c:forEach>
    <br><br>
    <button>修改</button>
</form>
</body>
</html>
