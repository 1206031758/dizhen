package com.bunfly.dizhen.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.bunfly.dizhen.model.China;
import com.bunfly.dizhen.model.DiZhenShuJu;
import com.bunfly.dizhen.service.impl.IChinaService;
import com.bunfly.dizhen.service.impl.IDiZhenShuJuService;
import com.bunfly.dizhen.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

/**
 * 地震活动的Controller
 */
@Controller
public class DiZhenHuoDongController {

    @Resource
    IDiZhenShuJuService   dizhenshujuservice;
    @Resource
    IChinaService   chinaservice;

    //获取当前时间，为地图的查询时间设默认值
    @RequestMapping("/dizhenshuju/getMapTime")
    @ResponseBody
    public  SixTime  getMapTime(){
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd");//规定格式
        String time=f.format(new Date());
        SixTime t=new SixTime();
        t.setNowtime(time);
        return t;
    }

    //页面点击地震活动，跳转到该处，在该处默认时间为当前时间，
    //1、 获取该日期所在周的周一和周日的数据
    //2、获取该日期所在月的数据
    //3、获取当前月份与前5个月的数据
    //4、跳转到huodong.jsp页面
    @RequestMapping("/dizhenshuju/showzhou")
    @ResponseBody
    public  List<DiZhenShuJu> showzhou(String time) throws JsonProcessingException, ParseException {
        if(time=="") {
            Date time1 = new Date();
            String monday = DateFormateUtil.getBegin(time1);//根据当前时间获得周一的日期
            String sunday = DateFormateUtil.getEnd(time1);//根据当前日期获得周天的日期
            List<DiZhenShuJu> zhoudatas = dizhenshujuservice.getZhouData(monday, sunday);
        return zhoudatas;}
        else{
            DateFormat f=new SimpleDateFormat("yyyy-MM-dd");//规定格式
            Date time2=(Date)f.parseObject(time);
            String monday = DateFormateUtil.getBegin(time2);//根据当前时间获得周一的日期
            String sunday = DateFormateUtil.getEnd(time2);//根据当前日期获得周天的日期
            System.out.println("所在周星期一："+monday);
            System.out.println("所在周星期天："+sunday);
            List<DiZhenShuJu> zhoudatas = dizhenshujuservice.getZhouData(monday, sunday);
            System.out.println("所在周的数据:"+zhoudatas);
            return zhoudatas;
        }
    }

    @RequestMapping("/dizhenshuju/showyue")
    @ResponseBody
    public  List<DiZhenShuJu> showyue(String time) throws JsonProcessingException {
        if(time==""){
        Date time1=new Date();
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd");//规定格式
        String time2 =f.format(time1);//将当前时间按规定格式转化为字符串
        String month=time2.substring(0,7);//截取日期的前7位，例如2019-01-05，截取后为2019-01
        List<DiZhenShuJu> yuedatas = dizhenshujuservice.getYueData(month);
        return yuedatas;}
        else{
            String month=time.substring(0,7);
            List<DiZhenShuJu> yuedatas = dizhenshujuservice.getYueData(month);
            return yuedatas;
        }
    }

    @RequestMapping("/dizhenshuju/showzhexian")
    @ResponseBody
    public Echarts showzhexian(String chaxuntime1,String chaxuntime2) throws ParseException {
        Echarts e=new Echarts();
        System.out.println("查询的时间1："+chaxuntime1);
        System.out.println("查询的时间2："+chaxuntime2);
        if(chaxuntime1==""){
            Date time=new Date();
            DateFormat f=new SimpleDateFormat("yyyy-MM");//规定格式
            Calendar c=Calendar.getInstance();//获取calender对象
            c.add(Calendar.MONTH,-5);
            Date time3 = c.getTime();
            String month1=f.format(time3);
            chaxuntime1=month1;
        }
        if(chaxuntime2==""){
            Date time=new Date();
            DateFormat f=new SimpleDateFormat("yyyy-MM");//规定格式
            String month2=f.format(time);
            chaxuntime2=month2;

            String titlename="最近6个月地震统计";
            e.setTitlename(titlename);
        }

        //根据工具类将从前端传过来的两个日期的中间日期求出
        String start=chaxuntime1;
        String end = chaxuntime2;
        List<String> times = DateFormateUtil.getMonthBetween(start, end);
        e.setCategory(times);//将这些日期作为横坐标

        Integer jieguo = DateFormateUtil.getYue(start, end);//根据日期求出两个日期之间相差的月份
        String titlename=jieguo+"个月地震统计";
        e.setTitlename(titlename);

        //将地震的级别存在该集合中
        List<String> jibies=new ArrayList<>();
        jibies.add("一级");
        jibies.add("二级");
        jibies.add("三级");
        jibies.add("四级");
        jibies.add("五级");
        jibies.add("六级");
        jibies.add("七级");
        jibies.add("八级");
        jibies.add("九级");
        jibies.add("十级");
        List<Series> series=new ArrayList<>();
        for (String jiby : jibies) {
            Series s=new Series();
            List<Integer> datas=new ArrayList<>();
            for (String time : times) {
                Integer cishu = dizhenshujuservice.getCiShuByTimeAndJiBie(jiby, time);
                datas.add(cishu);
            }
            //判断该集合中所有值的和是否为0，为0则不保存该集合
            LongAdder longAdder = new LongAdder();
            datas.parallelStream().forEach(longAdder::add);
            Integer sum = longAdder.intValue();//sum是集合中的所有值的和

            if(sum!=0){
            s.setYname(jiby);
            s.setDatas(datas);
            series.add(s);}
        }
       e.setSer(series);
        System.out.println(e);
        return e;
    }

    @RequestMapping("/dizhenshuju/getTime")
    @ResponseBody
    public SixTime getTime(){
        Date time=new Date();
        DateFormat f=new SimpleDateFormat("yyyy-MM");//规定格式

        Calendar c=Calendar.getInstance();//获取calender对象
        c.add(Calendar.MONTH,-5);
        Date time3 = c.getTime();
        String month1=f.format(time3);
        String month2=f.format(time);
        SixTime chaxun=new SixTime();
        chaxun.setBeforetime(month1);
        chaxun.setNowtime(month2);
        return chaxun;
    }

    //页面点击折线图的一个点，钻取到该点的地震时间和级别，将该值传过来，在数据库查询出数据
    @RequestMapping("/dizhenshuju/getDataByTimeAndJiBie")
    public  String getDataByTimeAndJiBie(String jibie,String date,Model model){
        System.out.println("这是传过来的查询时间："+date);
        List<DiZhenShuJu> datas = dizhenshujuservice.getDatasByTimeAndJiBie(jibie, date);
        model.addAttribute("jibie",jibie);
        model.addAttribute("dizhentime",date);
        model.addAttribute("datas",datas);
        return  "/jsp/huodongzhuanqu.jsp";
    }

    //页面在历史查询页面点击查询，提交form表单，将表单里的信息传过来，从数据库查询出符合条件的数据
    @RequestMapping("/dizhenshuju/chaxundata")
    public  String chaxundata(Model model,String dizhentime1,String dizhentime2,Float weidu1,Float weidu2,Float jingdu1,Float jingdu2,Integer shendu1,Integer shendu2,Float zhenji1,Float zhenji2){
        System.out.println("这是传进来的深度1数据："+shendu1);
        System.out.println("这是传进来的深度2数据："+shendu2);
        List<DiZhenShuJu> datas = dizhenshujuservice.getDatasByFormChaXun(dizhentime1, dizhentime2, weidu1, weidu2, jingdu1, jingdu2, shendu1, shendu2, zhenji1, zhenji2);
        System.out.println("这是根据form表单查询出的地震数据："+datas);
        model.addAttribute("datas",datas);
        return "/jsp/historychaxun.jsp";
    }

    //历史查询页面，点击按时间查询里的链接，将时间传过来，在数据库查询数据，返回值给到历史查询页面
    @RequestMapping("/dizhenshuju/chaXunByATime")
    public  String  chaXunByATime(Integer time,Model model){
        List<DiZhenShuJu> datas = dizhenshujuservice.getDatasByATime(time);
        model.addAttribute("datas",datas);
        return "/jsp/historychaxun.jsp";
    }

    //历史查询页面，点击按震级查询里的链接，将震级传过来，在数据库查询数据，返回值到历史查询页面
    @RequestMapping("/dizhenshuju/chaXunByAZhenJi")
    public  String  chaXunByAZhenJi(Integer zhenji,Model model){
        List<DiZhenShuJu> datas = dizhenshujuservice.chaXunByAZhenJi(zhenji);
        model.addAttribute("datas",datas);
        return "/jsp/historychaxun.jsp";
    }

    //登录首页，自动加载出一年内的地震数据，将数据以json格式返回给首页
    @RequestMapping("/dizhenshuju/oneYear")
    @ResponseBody
    public  List<DiZhenShuJu> oneYear(){
        List<DiZhenShuJu> dizhenshuju = dizhenshujuservice.getDatasByATime(1);
        return dizhenshuju;
    }
    //首页点击6级或5级以上地震时，ajax将震级传过来，这里查询出该震级的数据，返回给ajax
    @RequestMapping("/dizhenshuju/chaxun")
    @ResponseBody
    public  List<DiZhenShuJu> chaxun(Integer zhenji){
        List<DiZhenShuJu> dizhenshuju = dizhenshujuservice.chaXunByAZhenJi(zhenji);
        return dizhenshuju;
    }
    //首页点击地震专题，跳转到该处，从数据库取出所有5.0级以上的地震
    @RequestMapping("/dizhenshuju/zhuanti")
    public  String  zhuanti(Model model){
        List<DiZhenShuJu> dizhenshujus = dizhenshujuservice.getfive();
        List<DiZhenShuJu> datas=new ArrayList<>();//创建一个新的list来存放时间和专题名
        for (DiZhenShuJu diZhenShuJu : dizhenshujus) {
            String time = diZhenShuJu.getDizhentime();
            String weizhi = diZhenShuJu.getWeizhi();
            Float zhenji = diZhenShuJu.getZhenji();
            String zhuantiname=weizhi+zhenji+"级地震";
            DiZhenShuJu d=new DiZhenShuJu();
            Integer id = diZhenShuJu.getDizhenshujuid();
            d.setDizhenshujuid(id);
            d.setDizhentime(time);
            d.setWeizhi(zhuantiname);
            datas.add(d);
        }
        model.addAttribute("datas",datas);
        return "/jsp/zhuanti.jsp";
    }

    //地震专题页面点击专题名，获取地震的id,根据id取出地震数据，再转到基本信息页面
    @RequestMapping("/dizhenshuju/jibenxinxi")
    public  String jibenxinxi(Integer dizhenshujuid,Model model){
        DiZhenShuJu datas = dizhenshujuservice.getShuJuById(dizhenshujuid);
        String weizhi=datas.getWeizhi();
        Float zhenji=datas.getZhenji();
        String dizhenname=weizhi+zhenji+"级地震";
        model.addAttribute("dizhenname",dizhenname);
        model.addAttribute("datas",datas);
        return  "/jsp/jibenxinxi.jsp";
    }

    //地震专题点击周边城市，将地震数据id传过来
    @RequestMapping("/dizhenshuju/zhoubiancity")
    public  String zhoubiancity(Integer dizhenshujuid,Model model) throws JsonProcessingException {
        DiZhenShuJu datas = dizhenshujuservice.getShuJuById(dizhenshujuid);
        String weizhi=datas.getWeizhi();
        Double jingdu = datas.getJingdu();//获取中心点的经度
        Double weidu = datas.getWeidu();//获取中心点的纬度
        Float zhenji=datas.getZhenji();
        String dizhenname=weizhi+zhenji+"级地震";
        model.addAttribute("dizhenname",dizhenname);
        model.addAttribute("datas",datas);

        List<China> chinas = chinaservice.getAllChinaDatas();
        List<China>  zhoubians=new ArrayList<>();//建一个集合来存放距离适合的县市经纬度
        for (China china : chinas) {
            Double jingdu2 = china.getJingdu();//获取经度
            Double weidu2 = china.getWeidu();//获取纬度
            Double distance = DistanceUtil.algorithm(jingdu, weidu, jingdu2, weidu2);
           if(distance<=300){ //如果距离大于等于300千米，则将该china存入zhoubians这个集合中
               china.setDistance(distance);
               zhoubians.add(china);
           }
        }
        ObjectMapper o3 = new ObjectMapper();//将third以对象的方式传给页面（不写的话就会以字符串传给页面）
        String y = o3.writeValueAsString(zhoubians);
        model.addAttribute("zhoubians",zhoubians);
        model.addAttribute("yy",y);//将数据存为json格式传到前端页面
        return  "/jsp/zhoubian.jsp";
    }
}
