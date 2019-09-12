<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
        #allmap{width:100%;height:500px;}
        p{margin-left:5px; font-size:14px;}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=CgaG4OHgurk4S58DGu6PBwpbCtmTvGZw"></script>
    <title>圆形区域搜索</title>
</head>
<body>
<div id="allmap"></div>
<p>返回北京市地图上圆形覆盖范围内的“餐馆”检索结果，并展示在地图上</p>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");            // 创建Map实例
    var mPoint = new BMap.Point(116.404, 39.915);
    map.enableScrollWheelZoom();
    map.centerAndZoom(mPoint,7);

    var circle = new BMap.Circle(mPoint,300000,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
    map.addOverlay(circle);
    var local =  new BMap.LocalSearch(map, {renderOptions: {map: map, autoViewport: false}});
    local.searchNearby('天津市',mPoint,300000);
</script>
