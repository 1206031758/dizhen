<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/20
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>地震活动</title>
</head>
<style>
    /*div{border: red 1px solid}*/
    #title{
        height: 60px;
        width: 100%;
        float: left;
        text-align: center;
    }
    #zhouandyue{
        height: 500px;
        width: 100%;
        float: left;
    }

    #sixmonth{
        height: 500px;
        width: 100%;
        float: left;
    }
    #chaxunsixmonth{
        height: 30px;
        width: 100%;
        float: left;
        text-align: center;
    }
    #zhou {width: 49%;height: 99%;overflow: hidden;margin:0;font-family:"微软雅黑";float: left}
    #yue {width: 49%;height: 99%;overflow: hidden;margin:0;font-family:"微软雅黑";float: right}
    #zhoutitle{width: 100%;height: 4%;float: left;overflow: hidden;margin:0;font-family:"微软雅黑";}
    #yuetitle{width: 100%;height: 4%;float: left;overflow: hidden;margin:0;font-family:"微软雅黑";}
    #zhoutu{width: 100%;height: 95%;overflow: hidden;margin:0;font-family:"微软雅黑";float: left}
    #yuetu{width: 100%;height: 95%;overflow: hidden;margin:0;font-family:"微软雅黑";float: left}
</style>
<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=CgaG4OHgurk4S58DGu6PBwpbCtmTvGZw"></script>
<script type="text/javascript" src="//api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
<%--<script src="/js/echarts.js"></script>--%>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>

<%--页面一打开就调用这两个方法--%>
<body onload="getMapTime();showZhou();showYue();getTime();showZheXian();">
<div><jsp:include page="/jsp/common/header.jsp"></jsp:include></div>
<%--标题--%>
<div id="title"><h3>地震活动</h3></div>
<%--周活动分布图和月活动分布图--%>
<div id="zhouandyue">
<%--    周活动分布图--%>
    <div id="zhou">
        <div id="zhoutitle">
            周活动分布图：
                <input type="date" id="zhoudate">&nbsp;<input type="submit" onclick="showZhou()" value="查询">&nbsp;
            (任一天所在周)
        </div>
        <div id="zhoutu"></div>
    </div>
<%--    月活动分布图--%>
    <div id="yue">
        <div id="yuetitle">月活动分布图：
                <input type="date" id="yuedate" >&nbsp;<input type="submit" onclick="showYue()" value="查询">&nbsp;
            (任一天所在月)
        </div>
        <div id="yuetu"></div>
    </div>
</div>
<%--最近6个月地震统计--%>
<div id="sixmonth">

</div>
<div id="chaxunsixmonth">

        <input type="month" id="chaxundate1" >——
        <input type="month" id="chaxundate2" >
        &nbsp;&nbsp
        <input type="submit" onclick="showZheXian()"  value="查询">

</div>
<script type="text/javascript">

    //周图
    // 百度地图API功能
    var map1 = new BMap.Map("zhoutu");    // 创建Map实例
    var point1 = new BMap.Point(116.404, 39.915);
    map1.centerAndZoom(point1, 1);
    //添加地图类型控件
    map1.addControl(new BMap.MapTypeControl({
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));
    map1.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

    //发生地震的地方的标注（周图）
    // 编写自定义函数,创建标注
    function addMarker1(point,weizhi,zhenji,dizhentime,shendu){
        var marker = new BMap.Marker(point);
        map1.addOverlay(marker);
        marker.addEventListener("click",getMap1Attr);//为标注添加点击事件
        function getMap1Attr() {
            var p = marker.getPosition();       //获取marker的位置
            alert("发震时刻："+dizhentime+"\n"+"经度：" + p.lng + "\n" +"纬度："+ p.lat+"\n"+"深度："+shendu+"\n"+"震级："+zhenji+"\n"+"位置："+weizhi);
        }
    }

    //清除map1覆盖物
    function remove_overlay(){
        map1.clearOverlays();
    }
    //清除map2覆盖物
    function remove_overlay2(){
        map2.clearOverlays();
    }
    //获取当前时间，为地图的查询时间设默认值
    function getMapTime() {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                var nowtime=date.nowtime;
                var zhoudate=document.getElementById("zhoudate");
                var yuedate=document.getElementById("yuedate");
                zhoudate.value=nowtime;
                yuedate.value=nowtime;
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/dizhenshuju/getMapTime",true);
        xhr.send(null);
    }

    function showZhou(){
        var time1=document.getElementById("zhoudate");
        var time=time1.value;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                remove_overlay();//删除覆盖物
                for(var i=0;i<date.length;i++){
                    var jingdu=date[i].jingdu;
                    var weidu=date[i].weidu;
                    var weizhi=date[i].weizhi;//获取地震位置
                    var zhenji=date[i].zhenji;//获取震级
                    var dizhentime=date[i].dizhentime;//获取地震发生时间
                    var shendu = date[i].shendu;//获取地震深度
                    var point=new BMap.Point(jingdu,weidu);
                    addMarker1(point,weizhi,zhenji,dizhentime,shendu);
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/dizhenshuju/showzhou?time="+time,true);
        xhr.send(null);
    }


    // 月图
    var map2 = new BMap.Map("yuetu");    // 创建Map实例
    var point2 = new BMap.Point(116.404, 39.915);
    map2.centerAndZoom(point2, 1);
    //添加地图类型控件
    map2.addControl(new BMap.MapTypeControl({
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));
    map2.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    //发生地震的地方的标注(月图)
    // 编写自定义函数,创建标注
    function addMarker2(point,weizhi,zhenji,dizhentime,shendu){
        var marker = new BMap.Marker(point);
        map2.addOverlay(marker);
        marker.addEventListener("click",getMap1Attr);//为标注添加点击事件,使点击标注，弹出标注所表示地震的详细信息
        function getMap1Attr() {
            var p = marker.getPosition();       //获取marker的位置
            alert("发震时刻："+dizhentime+"\n"+"经度：" + p.lng + "\n" +"纬度："+ p.lat+"\n"+"深度："+shendu+"\n"+"震级："+zhenji+"\n"+"位置："+weizhi);
        }
    }
    function showYue(){
        var time1=document.getElementById("yuedate");
        var time=time1.value;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4&&xhr.status==200){
                var date=xhr.responseText;
                date=eval("("+date+")");
                remove_overlay2();//删除map2的覆盖物
                for(var i=0;i<date.length;i++){
                    var jingdu=date[i].jingdu;
                    var weidu=date[i].weidu;
                    var weizhi=date[i].weizhi;//获取地震位置
                    var zhenji=date[i].zhenji;//获取震级
                    var dizhentime=date[i].dizhentime;//获取地震发生时间
                    var shendu = date[i].shendu;//获取地震深度
                    var point=new BMap.Point(jingdu,weidu);
                    addMarker2(point,weizhi,zhenji,dizhentime,shendu);
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath }/dizhenshuju/showyue?time="+time,true);
        xhr.send(null);
    }
//获取当前时间，并将查询按钮的chaxuntime1和chaxuntime2赋值
function getTime() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState==4&&xhr.status==200){
            var date=xhr.responseText;
            date=eval("("+date+")");
            var chaxundate1=document.getElementById("chaxundate1");
            var chaxundate2=document.getElementById("chaxundate2");
            chaxundate1.value=date.beforetime;
            chaxundate2.value=date.nowtime;
        }
    }
    xhr.open("get","${pageContext.request.contextPath }/dizhenshuju/getTime",true);
    xhr.send(null);
}
    // 定义图表显示区域
    // 基于准备好的dom，初始化echarts实例
    //指定图标的配置和数据
    function zhexiantu(x,y,z) {

        var series=[];
        for(var i=0;i<y.length;i++){
                series.push({
                    name: y[i].yname,
                    smooth: true,
                    type: 'line',
                    data: y[i].datas
            });
        }

    var option = {
        title: {
            text: z,
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c}次'
        },
        // 需从后台获取
        legend: {
            left: 'left',
            data: ['一级', '二级','三级','四级','五级','六级','七级','八级','九级','十级']
        },
        xAxis: {
            type: 'category',
            name: '月份',
            splitLine: {show: false},
            data: x
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        yAxis: {
            type: 'value',
            name: '地震发生次数（次）'
        },
        //从后台获取
        series: series
    };

    //初始化echarts实例
    var myChart = echarts.init(document.getElementById('sixmonth'));

    //使用制定的配置项和数据显示图表
    myChart.setOption(option);

    // 处理点击事件并且跳转到相应的钻取页面
    myChart.on('click', function (params) {
        //观察网页上的控制台打印出params的值有哪些
        // console.log(params);
        window.open('${pageContext.request.contextPath }/dizhenshuju/getDataByTimeAndJiBie?date=' + encodeURIComponent(params.name)+'&jibie='+encodeURIComponent(params.seriesName ));

    });

    }
    //折线图
function showZheXian(){

        var chaxuntime11=document.getElementById("chaxundate1");
        var chaxuntime22=document.getElementById("chaxundate2");
        var chaxuntime1=chaxuntime11.value;
        var chaxuntime2=chaxuntime22.value; //获取点击查询按钮时的日期值
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState==4&&xhr.status==200){
            var date=xhr.responseText;
            date=eval("("+date+")");
            var titlename=date.titlename;
            var category=date.category;//x轴
            var series=date.ser;//y轴
            zhexiantu(category,series,titlename);
        }
    }
    xhr.open("get","${pageContext.request.contextPath }/dizhenshuju/showzhexian?chaxuntime1="+chaxuntime1+"&chaxuntime2="+chaxuntime2,true);
    xhr.send(null);
}

</script>
</body>
</html>
