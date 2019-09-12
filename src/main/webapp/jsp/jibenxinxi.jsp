<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/19
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>地震基本信息</title>
</head>
<style>
    /*div{border: red 1px solid}*/
    #dizhenname{
        text-align: center;
        height: 60px;
        width: 100%;
        float: left;
    }
    #city{
        height: 50px;
        width: 100%;
        float: left;
    }
    #desandmap{
        height: 500px;
        width: 100%;
        float: left;
    }
    #des{
        height: 99%;
        width: 49%;
        float: left;
    }
    #map {width: 49%;height: 99%;overflow: hidden;margin:0;font-family:"微软雅黑";float: right}
</style>
<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=CgaG4OHgurk4S58DGu6PBwpbCtmTvGZw"></script>
<body>
<div><jsp:include page="/jsp/common/header.jsp"></jsp:include></div>
<%--地震名字--%>
<div id="dizhenname">
    <h3>${dizhenname}</h3>
</div>
<%--基本信息、周边城市--%>
<div id="city">
    <a href="${pageContext.request.contextPath }/dizhenshuju/jibenxinxi?dizhenshujuid=${datas.dizhenshujuid}">基本信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath }/dizhenshuju/zhoubiancity?dizhenshujuid=${datas.dizhenshujuid}">周边城市</a>
</div>
<%--描述和图片--%>
<div id="desandmap">
<%--    描述--%>
    <div id="des">
        发震时刻：${datas.dizhentime}<br>
        纬度：${datas.weidu}°<br>
        经度：${datas.jingdu}°<br>
        深度：${datas.shendu}千米<br>
        震级：${datas.zhenji}<br>
        参考位置：${datas.weizhi}<br>
    </div>
<%--    地图--%>
    <div id="map"></div>
</div>


<%--</script>--%>
<script type="text/javascript">
    // 百度地图API功能
    var jingdu='${datas.jingdu}';
    var weidu='${datas.weidu}';
    var map = new BMap.Map("map");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(jingdu, weidu),9);  // 初始化地图,设置中心点坐标和地图级别
    //添加地图类型控件
    map.addControl(new BMap.MapTypeControl({
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放


    <%--var weizhi='${datas.weizhi}';--%>
    var zhenji='${datas.zhenji}';
    var weizhi='${datas.weizhi}';
    var dizhentime='${datas.dizhentime}';
    var shendu='${datas.shendu}';
    var point=new BMap.Point(jingdu,weidu);
    //设置发生地震位置的标注
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
        marker.addEventListener("click",getMapAttr);//为标注添加点击事件
        function getMapAttr() {
            var p = marker.getPosition();       //获取marker的位置
            alert("发震时刻："+dizhentime+"\n"+"经度：" + p.lng + "\n" +"纬度："+ p.lat+"\n"+"深度："+shendu+"\n"+"震级："+zhenji+"\n"+"位置："+weizhi);
        }


</script>
</body>
</html>
