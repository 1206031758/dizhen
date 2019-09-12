<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/16
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<style>
    /*div{border: red 1px solid}*/
    #nz{height: 300px;
        width: 100%;
        float: left;
    }
    #news{
        height: 99%;
        width: 49%;
        float: left;
    }
    #zhuanti{
        height: 99%;
        width: 49%;
        float: right;
    }
    #oneyear{
        height: 400px;
        width: 100%;
        float: left;
    }
</style>
<script>
    //页面加载时执行此方法，从后台获取行业新闻数据,展示在页面上
    function showNews(){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                var newsdiv=document.getElementById("news");
                newsdiv.innerHTML="";
                newsdiv.innerHTML="行业新闻"+"<br>"+
                                    "<table id='table' border='1px' cellspacing='0' style='width: 600px'>"+
                                    "<tr>"+
                                        "<th>新闻名</th>"+
                                        "<th>发布时间</th>"+
                                    "</tr>"+
                                    "</table>";
                var table=document.getElementById("table");
                for(var i=0;i<date.length;i++){
                    var tr=document.createElement("tr");
                    var html = "<td>"+"<a href='${pageContext.request.contextPath }/news/showNewsDetail?newsid="+date[i].newsid+"'>"+date[i].newstitle+"</a>"+"</td>"+
                        "<td>"+date[i].shangchuantime+"</td>";
                    tr.innerHTML=html;
                    table.appendChild(tr);
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/news/showNewsTitle",true);
        xhr.send(null);
    }
    //页面加载时执行此方法，从后台获取专题数据,展示在页面上
    function showZhuanTi(){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                var zhuantidiv=document.getElementById("zhuanti");
                zhuantidiv.innerHTML="";

                zhuantidiv.innerHTML="专题报告"+"<br>"+
                    "<table id='table1' border='1px' cellspacing='0' style='width: 600px'>"+
                    "<tr>"+
                    "<th>专题名</th>"+
                    "<th>发布时间</th>"+
                    "</tr>"+
                    "</table>";
                var table1=document.getElementById("table1");
                for(var i=0;i<date.length;i++){

                    var tr1=document.createElement("tr");
                    var html = "<td>"+"<a href='${pageContext.request.contextPath }/zhibo/showZhuanTiDetail?zhuantiid="+date[i].dizhenshujuid+"'>"+date[i].weizhi+"</a>"+"</td>"+
                        "<td>"+date[i].dizhentime+"</td>";
                    tr1.innerHTML=html;
                    table1.appendChild(tr1);
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/zhibo/showZhuantiTitle",true);
        xhr.send(null);
    }
    //页面加载时执行此方法，从后台获取一年的地震数据,分页展示在页面上
    function showOneYear(){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                var oneyeardiv=document.getElementById("oneyear");
                oneyeardiv.innerHTML="";

                oneyeardiv.innerHTML="最近一年地震"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(6)'><font color='blue'>6.0级以上地震</font></a>"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(5)'><font color='blue'>5.0级以上地震</font></a>"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(4)'><font color='blue'>4.0级以上地震</font></a>"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(3)'><font color='blue'>3.0级以上地震</font></a>"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(2)'><font color='blue'>3.0级以下地震</font></a>"+
                                    "<br>"+
                    "<table id='table2' border='1px' cellspacing='0' style='width: 1000px'>"+
                    "<tr>"+
                    "<th>震级（M）</th>"+
                    "<th>发震时刻（UTC+8）</th>"+
                    "<th>纬度（°）</th>"+
                    "<th>经度（°）</th>"+
                    "<th>深度（千米）</th>"+
                    "<th>参考位置</th>"+
                    "</tr>"+
                    "</table>";
                var table2=document.getElementById("table2");
                for(var i=0;i<date.length;i++){
                    var tr2=document.createElement("tr");
                    var html = "<td>"+date[i].zhenji+"</td>"+
                                "<td>"+date[i].dizhentime+"</td>"+
                                "<td>"+date[i].weidu+"</td>"+
                                "<td>"+date[i].jingdu+"</td>"+
                                "<td>"+date[i].shendu+"</td>"+
                                "<td>"+date[i].weizhi+"</td>";
                    tr2.innerHTML=html;
                    table2.appendChild(tr2);
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/dizhenshuju/oneYear",true);
        xhr.send(null);
    }
    //页面点击6级以上地震，将震级传过来，这里在传给controller，返回地震数据
    function chaxun(a){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                var oneyeardiv=document.getElementById("oneyear");
                oneyeardiv.innerHTML="";

                oneyeardiv.innerHTML="最近一年地震"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(6)'><font color='blue'>6.0级以上地震</font></a>"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(5)'><font color='blue'>5.0级以上地震</font></a>"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(4)'><font color='blue'>4.0级以上地震</font></a>"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(3)'><font color='blue'>3.0级以上地震</font></a>"+
                                        "&nbsp;&nbsp;<a onclick='chaxun(2)'><font color='blue'>3.0级以下地震</font></a>"+
                                    "<br>"+
                    "<table id='table3' border='1px' cellspacing='0' style='width: 1000px'>"+
                    "<tr>"+
                    "<th>震级（M）</th>"+
                    "<th>发震时刻（UTC+8）</th>"+
                    "<th>纬度（°）</th>"+
                    "<th>经度（°）</th>"+
                    "<th>深度（千米）</th>"+
                    "<th>参考位置</th>"+
                    "</tr>"+
                    "</table>";
                var table3=document.getElementById("table3");
                for(var i=0;i<date.length;i++){
                    var tr3=document.createElement("tr");
                    var html = "<td>"+date[i].zhenji+"</td>"+
                        "<td>"+date[i].dizhentime+"</td>"+
                        "<td>"+date[i].weidu+"</td>"+
                        "<td>"+date[i].jingdu+"</td>"+
                        "<td>"+date[i].shendu+"</td>"+
                        "<td>"+date[i].weizhi+"</td>";
                    tr3.innerHTML=html;
                    table3.appendChild(tr3);
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/dizhenshuju/chaxun?zhenji="+a,true);
        xhr.send(null);
    }
</script>
<body onload="showZhuanTi();showNews();showOneYear();">
<div><jsp:include page="/jsp/common/header.jsp"></jsp:include></div>
<div id="nz">
<%--行业新闻模块--%>
<div id="news">
    行业新闻
</div>
<%--专题报告模块--%>
<div id="zhuanti">
    专题报告
    <a href="jsp/zhuantixiangxi.jsp">测试</a>
</div>
</div>
<%--最近一年地震模块--%>
<div id="oneyear">
    最近一年地震
</div>
</body>
</html>
