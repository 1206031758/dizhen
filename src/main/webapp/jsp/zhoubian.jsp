<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style type="text/css">
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
<script type="text/javascript" src="//api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
    <title>周边城市</title>
</head>

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
        该地震周边300千米范围内有以下中国境内城市：<br>
        <c:forEach items="${zhoubians}" var="zhoubian">
            城市：${zhoubian.provice}${zhoubian.shi}${zhoubian.xian}(距离震中${zhoubian.distance}千米) <br>
        </c:forEach>
    </div>
    <%--    地图--%>
    <div id="map"></div>
</div>
</body>
</html>
<%--</script>--%>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("map");    // 创建Map实例
    var jingdu='${datas.jingdu}';
    var weidu='${datas.weidu}';
    var mPoint = new BMap.Point(jingdu, weidu);
    map.enableScrollWheelZoom();
    map.centerAndZoom(mPoint,7);  // 初始化地图,设置中心点坐标和地图级别


    //添加地图类型控件
    map.addControl(new BMap.MapTypeControl({
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

    //设置中心点的标注
    var point = new BMap.Point(jingdu, weidu);
    var marker = new BMap.Marker(point);// 创建标注
    map.addOverlay(marker);              // 将标注添加到地图中
    marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    marker.addEventListener("click",getAttr);
    function getAttr(){
        var p = marker.getPosition();       //获取marker的位置
        alert("marker的位置是" + p.lng + "," + p.lat);
    }

  //在中心附近画圆
    var circle = new BMap.Circle(mPoint,300000,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3}); //创建圆
    //增加圆
    map.addOverlay(circle);

 //添加比例尺
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    map.addControl(top_left_control);


    <%--// 编写自定义函数,创建标注--%>
    <%--function addMarker2(point2){--%>
    <%--    var marker2 = new BMap.Marker(point2);--%>
    <%--    map.addOverlay(marker2);--%>
    <%--}--%>

    <%--// 将该中心点附近300km的城市标记--%>
    <%--var zhoubians="${zhoubians}";--%>
    <%--for (var i = 0; i<zhoubians.length; i ++) {--%>
    <%--    var jingdu2=zhoubians[i].jingdu;--%>
    <%--    var weidu2=zhoubians[i].weidu;--%>
    <%--    var point2 = new BMap.Point(jingdu2,weidu2);--%>
    <%--    addMarker2(point2);--%>
    <%--}--%>


    // 编写自定义函数,创建标注
    function addMarker(point,jingdu2,weidu2,title,distance){
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);


        var opts = {
            width : 200,     // 信息窗口宽度
            height: 100,     // 信息窗口高度
            title : title , // 信息窗口标题
            enableMessage:true,//设置允许信息窗发送短息
            message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
        }
        var infoWindow = new BMap.InfoWindow("经度："+jingdu2+"°"+","+"纬度："+weidu2+"°"+","+"距震源距离："+distance+"千米", opts);  // 创建信息窗口对象
        marker.addEventListener("click", function(){
            map.openInfoWindow(infoWindow,point); //开启信息窗口
        });
    }
    // 在地图上添加周边距离小于等于300km的城市
    var zhoubians=eval(${yy});
    for (var i = 0; i < zhoubians.length; i ++) {
        var jingdu2=zhoubians[i].jingdu;
        var weidu2=zhoubians[i].weidu;
        var title=zhoubians[i].xian;
        var distance=zhoubians[i].distance;
        var point = new BMap.Point(jingdu2, weidu2);
        addMarker(point,jingdu2,weidu2,title,distance);
    }

//     // 点击标记会显示标记的信息
// function tishi() {
//     var opts = {
//         width : 200,     // 信息窗口宽度
//         height: 100,     // 信息窗口高度
//         title : "海底捞王府井店" , // 信息窗口标题
//         enableMessage:true,//设置允许信息窗发送短息
//         message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
//     }
//     var infoWindow = new BMap.InfoWindow("地址：北京市东城区王府井大街88号乐天银泰百货八层", opts);  // 创建信息窗口对象
//     marker.addEventListener("click", function(){
//         map.openInfoWindow(infoWindow,point); //开启信息窗口
//     });
// }

</script>

