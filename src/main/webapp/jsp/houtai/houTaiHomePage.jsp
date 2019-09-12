<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/22
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>后台主页</title>
</head>
<style type="text/css">
     div{
       border: red  solid 1px;
   }
    #top{
        background-color:skyblue;
        height: 126px;
    }
    #buttom{
        height:700px;
    }
    #buttom_left{
        background-color:skyblue;
        width: 15%;
        height:650px;
        float: left;
    }
    #buttom_right{
        background-color:lightgrey;
        width: 84%;
        height:650px;
        float: left;
    }

</style>
<script>

    window.onload=function(){
        if('${usermanage}'=="usermanage"){
            usermanage(1);
        }
        if('${showuser}'=="showuser"){
            usermanage(1);
            showUser();
        }
        if('${newsmanage}'=="newsmanage"){
            newsmanage(3);
        }
        if('${shownews}'=="shownews"){
            newsmanage(3);
            showNews();
        }
        if('${zhuantimanage}'=="zhuantimanage"){
            zhuantimanage(2);
        }
        if('${showzhibo}'=="showzhibo"){
            zhuantimanage(2);
            showZhiBo();
        }
    }
    //添加用户
    function addUser(){
        window.location.href='${pageContext.request.contextPath }/user/addyemian';
    }
    //分页展示所有用户
    function showUser(pageSize,currentPage) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                var divtop=document.getElementById("buttom_right_top");
                var divbuttom=document.getElementById("buttom_right_buttom");
                divbuttom.innerHTML="";
                divtop.innerHTML="";
                if(date == "ajaxmsg"){
                    divtop.innerHTML="无访问权限";
                }else{
                    date=eval("("+date+")");
                    var result=date.result;
                    divtop.innerHTML=
                        "<table id='table' border='1px' cellspacing='0'>"+
                        "<tr>"+
                        "<th>用户id</th>"+
                        "<th>用户姓名</th>"+
                        "<th>用户性别</th>"+
                        "<th>用户登录名</th>"+
                        "<th>用户登录密码</th>"+
                        "<th>所担角色</th>"+
                        "<th>操作</th>"+
                        "</tr>"+
                        "<table>";
                    var table=document.getElementById("table");
                    for(var i=0;i<result.length;i++){
                        var tr=document.createElement("tr");
                        var html = "<td>"+result[i].userid+"</td>"+
                            "<td>"+result[i].username+"</td>"+
                            "<td>"+result[i].usersex+"</td>"+
                            "<td>"+result[i].loginname+"</td>"+
                            "<td>"+result[i].password+"</td>"+
                            "<td>";
                        for(var j=0;j<result[i].roles.length;j++){
                            html +=	result[i].roles[j].rolename;
                            html += "  ";
                        }
                        html+="</td>"+
                            "<td>"+
                            "<a href='${pageContext.request.contextPath }/user/queryUser?userid="+result[i].userid+"'>修改</a>"+
                            "&nbsp;&nbsp;"+
                            "<a href='${pageContext.request.contextPath }/user/deleteUser?userid="+result[i].userid+"'>删除</a>"+
                            "</td>";
                        tr.innerHTML=html;
                        table.appendChild(tr);
                    }
                }
                //写页码
                var html="一共有"+date.totalCount+"条数据,每页显示"+
                    "<select onchange='getSize(this.value)'>"+
                    "<option "+(date.pageSize == 4?'selected':'')+"  value='4'>4</option>"+
                    "<option "+(date.pageSize == 10?'selected':'')+"  value='10'>10</option>"+
                    "<option "+(date.pageSize == 15?'selected':'')+"  value'15'>15</option>"+
                    "<option "+(date.pageSize == 20?'selected':'')+"  value='20'>20</option>"+
                    "</select>条,"+
                    " 当前为"+date.currentPage+"/"+date.pageCount+"页,"+
                    "<a onclick='fisrt("+date.pageSize+")' href='#'>首页</a>&nbsp;"+
                    "<a onclick='back("+date.pageSize+","+date.currentPage+")' href='#'>上一页</a>&nbsp;"+
                    "<a onclick='next("+date.pageSize+","+date.currentPage+","+date.pageCount+")' href='#'>下一页</a>&nbsp;"+
                    "<a onclick='last("+date.pageSize+","+date.pageCount+")' href='#'>尾页</a>&nbsp;";
                divbuttom.innerHTML = html;
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/user/showUser?ajaxmsg='ajaxmsg'&pageSize="+pageSize+"&currentPage="+currentPage,true);
        xhr.send(null);
    }

    function getSize(pagesize){
        var currentPage=1;
        var pagesize=pagesize;
        showUser(pagesize,currentPage);
    }

    function fisrt(pageSize){
        var	currentPage=1;
        var pagesize=pageSize;
        showUser(pagesize,currentPage);
    }

    function back(pageSize,currentPage){
        var	currentPage=(currentPage  == 1 ? 1:currentPage-1);
        var pagesize=pageSize;
        showUser(pagesize,currentPage);
    }

    function next(pageSize,currentPage,pageCount){
        var	currentPage=(currentPage  ==pageCount ? pageCount:currentPage+1);
        var pagesize=pageSize;
        showUser(pagesize,currentPage);
    }

    function last(pageSize,pageCount){
        var pagesize=pageSize;
        var currentPage=pageCount;
        showUser(pagesize,currentPage);
    }
    //添加行业新闻
    function addNews(){
        window.location.href='${pageContext.request.contextPath }/news/addNewsYeMian';
    }

    //分页展示所有行业新闻
    function showNews(pageSize,currentPage) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                var divtop=document.getElementById("buttom_right_top");
                var divbuttom=document.getElementById("buttom_right_buttom");
                divbuttom.innerHTML="";
                divtop.innerHTML="";
                if(date == "ajaxmsg"){
                    divtop.innerHTML="无访问权限";
                }else{
                    date=eval("("+date+")");
                    var result=date.result;
                    divtop.innerHTML=
                        "<table id='table' border='1px' cellspacing='0'>"+
                        "<tr>"+
                        "<th>新闻id</th>"+
                        "<th>新闻标题</th>"+
                        "<th>新闻内容</th>"+
                        "<th>用户id</th>"+
                        "<th>上传时间</th>"+
                        "<th>操作</th>"+
                        "</tr>"+
                        "<table>";
                    var table=document.getElementById("table");
                    for(var i=0;i<result.length;i++){
                        var tr=document.createElement("tr");
                        var html = "<td>"+result[i].newsid+"</td>"+
                            "<td>"+result[i].newstitle+"</td>"+
                            "<td>"+result[i].newscontent+"</td>"+
                            "<td>"+result[i].userid+"</td>"+
                            "<td>"+result[i].shangchuantime+"</td>"
                        html+=
                            "<td>"+
                            "<a href='${pageContext.request.contextPath }/news/deleteNews?newsid="+result[i].newsid+"'>删除</a>"+
                            "</td>";
                        tr.innerHTML=html;
                        table.appendChild(tr);
                    }
                }
                //写页码
                var html="一共有"+date.totalCount+"条数据,每页显示"+
                    "<select onchange='getSize2(this.value)'>"+
                    "<option "+(date.pageSize == 4?'selected':'')+"  value='4'>4</option>"+
                    "<option "+(date.pageSize == 10?'selected':'')+"  value='10'>10</option>"+
                    "<option "+(date.pageSize == 15?'selected':'')+"  value'15'>15</option>"+
                    "<option "+(date.pageSize == 20?'selected':'')+"  value='20'>20</option>"+
                    "</select>条,"+
                    " 当前为"+date.currentPage+"/"+date.pageCount+"页,"+
                    "<a onclick='fisrt2("+date.pageSize+")' href='#'>首页</a>&nbsp;"+
                    "<a onclick='back2("+date.pageSize+","+date.currentPage+")' href='#'>上一页</a>&nbsp;"+
                    "<a onclick='next2("+date.pageSize+","+date.currentPage+","+date.pageCount+")' href='#'>下一页</a>&nbsp;"+
                    "<a onclick='last2("+date.pageSize+","+date.pageCount+")' href='#'>尾页</a>&nbsp;";
                divbuttom.innerHTML = html;
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/news/showNews?ajaxmsg='ajaxmsg'&pageSize="+pageSize+"&currentPage="+currentPage+"&userid="+${user.userid},true);
        xhr.send(null);
    }

    function getSize2(pagesize){
        var currentPage=1;
        var pagesize=pagesize;
        showNews(pagesize,currentPage);
    }

    function fisrt2(pageSize){
        var	currentPage=1;
        var pagesize=pageSize;
        showNews(pagesize,currentPage);
    }

    function back2(pageSize,currentPage){
        var	currentPage=(currentPage  == 1 ? 1:currentPage-1);
        var pagesize=pageSize;
        showNews(pagesize,currentPage);
    }

    function next2(pageSize,currentPage,pageCount){
        var	currentPage=(currentPage  ==pageCount ? pageCount:currentPage+1);
        var pagesize=pageSize;
        showNews(pagesize,currentPage);
    }

    function last2(pageSize,pageCount){
        var pagesize=pageSize;
        var currentPage=pageCount;
        showNews(pagesize,currentPage);
    }


    //添加直播信息
    function addZhiBo(){
        window.location.href='${pageContext.request.contextPath }/zhibo/addzhiboyemian';
    }

    //分页展示所有行业新闻
    function showZhiBo(pageSize,currentPage) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                var divtop=document.getElementById("buttom_right_top");
                var divbuttom=document.getElementById("buttom_right_buttom");
                divbuttom.innerHTML="";
                divtop.innerHTML="";
                if(date == "ajaxmsg"){
                    divtop.innerHTML="无访问权限";
                }else{
                    date=eval("("+date+")");
                    var result=date.result;
                    divtop.innerHTML=
                        "<table id='table' border='1px' cellspacing='0'>"+
                        "<tr>"+
                        "<th>直播id</th>"+
                        "<th>直播标题</th>"+
                        "<th>直播来源</th>"+
                        "<th>直播上传时间</th>"+
                        "<th>直播内容</th>"+
                        "<th>用户id</th>"+
                        "<th>所属专题id</th>"+
                        "<th>所属专题名字</th>"+
                        "<th>操作</th>"+
                        "</tr>"+
                        "<table>";
                    var table=document.getElementById("table");
                    for(var i=0;i<result.length;i++){
                        var tr=document.createElement("tr");
                        var html = "<td>"+result[i].zhiboid+"</td>"+
                            "<td>"+result[i].zhibotitle+"</td>"+
                            "<td>"+result[i].zhibolaiyuan+"</td>"+
                            "<td>"+result[i].zhibotime+"</td>"+
                            "<td>"+result[i].zhibocontent+"</td>"+
                        "<td>"+result[i].userid+"</td>"+
                        "<td>"+result[i].zhuantiid+"</td>"+
                        "<td>"+result[i].zhuantiname+"</td>"
                        html+=
                            "<td>"+
                            "<a href='${pageContext.request.contextPath }/zhibo/deleteZhiBo?zhiboid="+result[i].zhiboid+"'>删除</a>"+
                            "</td>";
                        tr.innerHTML=html;
                        table.appendChild(tr);
                    }
                }
                //写页码
                var html="一共有"+date.totalCount+"条数据,每页显示"+
                    "<select onchange='getSize3(this.value)'>"+
                    "<option "+(date.pageSize == 4?'selected':'')+"  value='4'>4</option>"+
                    "<option "+(date.pageSize == 10?'selected':'')+"  value='10'>10</option>"+
                    "<option "+(date.pageSize == 15?'selected':'')+"  value'15'>15</option>"+
                    "<option "+(date.pageSize == 20?'selected':'')+"  value='20'>20</option>"+
                    "</select>条,"+
                    " 当前为"+date.currentPage+"/"+date.pageCount+"页,"+
                    "<a onclick='fisrt3("+date.pageSize+")' href='#'>首页</a>&nbsp;"+
                    "<a onclick='back3("+date.pageSize+","+date.currentPage+")' href='#'>上一页</a>&nbsp;"+
                    "<a onclick='next3("+date.pageSize+","+date.currentPage+","+date.pageCount+")' href='#'>下一页</a>&nbsp;"+
                    "<a onclick='last3("+date.pageSize+","+date.pageCount+")' href='#'>尾页</a>&nbsp;";
                divbuttom.innerHTML = html;
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/zhibo/showZhiBo?ajaxmsg='ajaxmsg'&pageSize="+pageSize+"&currentPage="+currentPage+"&userid="+${user.userid},true);
        xhr.send(null);
    }

    function getSize3(pagesize){
        var currentPage=1;
        var pagesize=pagesize;
        showZhiBo(pagesize,currentPage);
    }

    function fisrt3(pageSize){
        var	currentPage=1;
        var pagesize=pageSize;
        showZhiBo(pagesize,currentPage);
    }

    function back3(pageSize,currentPage){
        var	currentPage=(currentPage  == 1 ? 1:currentPage-1);
        var pagesize=pageSize;
        showZhiBo(pagesize,currentPage);
    }

    function next3(pageSize,currentPage,pageCount){
        var	currentPage=(currentPage  ==pageCount ? pageCount:currentPage+1);
        var pagesize=pageSize;
        showZhiBo(pagesize,currentPage);
    }

    function last3(pageSize,pageCount){
        var pagesize=pageSize;
        var currentPage=pageCount;
        showZhiBo(pagesize,currentPage);
    }


    //点击一级菜单，根据一级菜单的id，在后台获取二级菜单，将二级菜单循环写在页面上，这里展示用户管理的二级菜单
    function usermanage(menuid){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                var divleft=document.getElementById("buttom_left");
                //每次点击一级权限时，将右下的区域清空
                var divtop=document.getElementById("buttom_right_top");
                var divbuttom=document.getElementById("buttom_right_buttom");
                divtop.innerHTML="";
                divbuttom.innerHTML="";

                divleft.innerHTML="";
                for(var i=0;i<date.length;i++){
                    var div=document.createElement("div");
                    div.innerHTML="<a onclick='"+date[i].menulink+"()'>"+date[i].menuname+"</a>";
                    divleft.appendChild(div);
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/user/getSecondMenu?menuid="+menuid,true);
        xhr.send(null);
    }

    //点击一级菜单，根据一级菜单的id，在后台获取二级菜单，将二级菜单循环写在页面上，这里展示行业新闻管理的二级菜单
    function newsmanage(menuid){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                //每次点击一级权限时，将右下的区域清空
                var divtop=document.getElementById("buttom_right_top");
                var divbuttom=document.getElementById("buttom_right_buttom");
                divtop.innerHTML="";
                divbuttom.innerHTML="";

                var divleft=document.getElementById("buttom_left");
                divleft.innerHTML="";
                for(var i=0;i<date.length;i++){
                    var div=document.createElement("div");
                    div.innerHTML="<a onclick='"+date[i].menulink+"()'>"+date[i].menuname+"</a>";
                    divleft.appendChild(div);
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/user/getSecondMenu?menuid="+menuid,true);
        xhr.send(null);
    }


    //点击一级菜单，根据一级菜单的id，在后台获取二级菜单，将二级菜单循环写在页面上，这里展示专题模块管理的二级菜单
    function zhuantimanage(menuid){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                //每次点击一级权限时，将右下的区域清空
                var divtop=document.getElementById("buttom_right_top");
                var divbuttom=document.getElementById("buttom_right_buttom");
                divtop.innerHTML="";
                divbuttom.innerHTML="";

                var divleft=document.getElementById("buttom_left");
                divleft.innerHTML="";
                for(var i=0;i<date.length;i++){
                    var div=document.createElement("div");
                    div.innerHTML="<a onclick='"+date[i].menulink+"()'>"+date[i].menuname+"</a>";
                    divleft.appendChild(div);
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/user/getSecondMenu?menuid="+menuid,true);
        xhr.send(null);
    }

</script>
<body>
<!-- 上层div -->
<div id="top">
    <a href="${pageContext.request.contextPath }/user/tuichu"><button>退出</button></a>
    <div style="background-color: skyblue;">
        <!-- 根据用户，在后台获取到一级权限，onclick的是ajax方法,方法名与权限的link值一样 -->
        <c:forEach items="${firstMenus}" var="firstMenu">
            <b><a  onclick="${firstMenu.menulink}(${firstMenu.menuid})">${firstMenu.menuname}</a></b>
            &nbsp;&nbsp;&nbsp;
        </c:forEach>
    </div>
</div>
<!-- 下层div -->
<div id="buttom">
    <!-- 下层左 -->
    <div id="buttom_left">
    </div>
    <!-- 下层右 -->
    <div id="buttom_right">
        <!-- 上层右上上 -->
        <div id="buttom_right_top_top"></div>
        <!-- 下层右上 -->
        <div id="buttom_right_top"></div>
        <!-- 下层右下 -->
        <div id="buttom_right_buttom">
        </div>
    </div>
</div>
</body>
</html>
