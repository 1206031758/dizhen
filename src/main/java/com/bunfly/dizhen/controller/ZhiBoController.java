package com.bunfly.dizhen.controller;

import com.bunfly.dizhen.model.DiZhenShuJu;
import com.bunfly.dizhen.model.News;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.ZhiBo;
import com.bunfly.dizhen.service.impl.IDiZhenShuJuService;
import com.bunfly.dizhen.service.impl.IZhiBoService;
import com.bunfly.dizhen.service.impl.IZhuanTiService;
import com.bunfly.dizhen.util.NumberFormatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 直播的controller
 */
@Controller
public class ZhiBoController {

    @Resource
    IZhiBoService  zhiboservice;
    @Resource
    IZhuanTiService zhuantiservice;
    @Resource
    IDiZhenShuJuService dizhenshujuservice;

    //页面点击添加直播信息，跳转到该处，该处从数据库获取所有的专题信息，再跳转到添加直播信息页面
    @RequestMapping("/zhibo/addzhiboyemian")
    public String addzhiboyemian(Model model){
        List<DiZhenShuJu> zhuantis = zhuantiservice.getAllZhuanTi();
        List<DiZhenShuJu> zhuantinames=new ArrayList<>();//创建一个集合来存放所有的经过拼接的专题名字
        for (DiZhenShuJu zhuanti : zhuantis) {
            Integer zhuantiid = zhuanti.getDizhenshujuid();//获取该专题id
            String time = zhuanti.getDizhentime();//地震发生时间
                String time2=time.substring(0,10);//截取发生时间的前10位
               //将time按照指定格式转化，例如将2019-01-02转化为2019年01月02日
            StringBuilder sb=new StringBuilder(time2);
            sb.replace(4,5,"年");
            sb.replace(7,8,"月");
            String time3=""+sb+"日";

            String weizhi = zhuanti.getWeizhi();//地震发生位置
            Float zhenji = zhuanti.getZhenji();//地震震级
                String zhenji2=zhenji+"级";
            String zhuantiname=time3+weizhi+zhenji2+"地震微直播";
            DiZhenShuJu  z=new DiZhenShuJu();
            z.setDizhenshujuid(zhuantiid);
            z.setDizhentime(zhuantiname);
            zhuantinames.add(z);//将该专题名字放入集合中
        }
        model.addAttribute("zhuantinames",zhuantinames);
        return  "/jsp/houtai/addZhiBoYeMian.jsp";
    }
    //直播信息添加页面点击添加，传过来所属专题id、直播标题、直播来源、直播内容、用户id，在这里
    //获取直播的time和专题名字
    @RequestMapping("/zhibo/addzhibo")
    public  String addzhibo(Model model,Integer zhuantiid,String zhibotitle,String zhibolaiyuan,String zhibocontent,Integer userid){
        ZhiBo z=new ZhiBo();
        z.setUserid(userid);
        z.setZhuantiid(zhuantiid);
        z.setZhibotitle(zhibotitle);
        z.setZhibolaiyuan(zhibolaiyuan);
        z.setZhibocontent(zhibocontent);
        //日期的格式
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //将当前日期转化为规定格式的字符串
        String zhibotime=f.format(new Date());
        z.setZhibotime(zhibotime);
        //获取专题名字
        DiZhenShuJu zhuanti = zhuantiservice.getZhuanTiById(zhuantiid);
        String time = zhuanti.getDizhentime();//地震发生时间
            String time2=time.substring(0,10);//截取发生时间的前10位
                //将time按照指定格式转化，例如将2019-01-02转化为2019年01月02日
                StringBuilder sb=new StringBuilder(time2);
                sb.replace(4,5,"年");
                sb.replace(7,8,"月");
                String time3=""+sb+"日";
        String weizhi = zhuanti.getWeizhi();//地震发生位置
        Float zhenji = zhuanti.getZhenji();//地震震级
        String zhenji2=zhenji+"级";
        String zhuantiname=time3+weizhi+zhenji2+"地震微直播";
        z.setZhuantiname(zhuantiname);
        zhiboservice.addZhiBo(z);
        model.addAttribute("zhuantimanage","zhuantimanage");
        return "/user/firstmenus";
    }

    //页面点击查看上传直播，转到该处，从数据库获取该用户发布的直播信息
    @RequestMapping("/zhibo/showZhiBo")
    @ResponseBody
    public  PageModel showNews(String pageSize, String currentPage,Integer userid){
        int pageSize1 = NumberFormatUtil.format(pageSize, 4);//每页的数据量
        int currentPage1 = NumberFormatUtil.format(currentPage, 1);//当前页码
        PageModel<ZhiBo> pageModel = new PageModel<ZhiBo>();
        pageModel.setCurrentPage(currentPage1);
        pageModel.setPageSize(pageSize1);
        pageModel =zhiboservice.queryPage(pageModel,userid);
        return pageModel;
    }

    //页面点击删除直播，将该条直播的id传过来，从数据库删除该直播
    @RequestMapping("/zhibo/deleteZhiBo")
    public String deleteZhiBo(Model model,Integer zhiboid){
        zhiboservice.deleteZhiBo(zhiboid);
        model.addAttribute("showzhibo","showzhibo");
        return "/user/firstmenus";
    }

     //首页加载时，首先调用showzhuanti（）方法,取出数据库所有专题的名字，返回给ajax
    @RequestMapping("/zhibo/showZhuantiTitle")
    @ResponseBody
    public List<DiZhenShuJu> showZhouTiTitle(){
        List<DiZhenShuJu> zhuantis = zhuantiservice.getAllZhuanTi();
        List<DiZhenShuJu> zhuantinames=new ArrayList<>();//创建一个集合来存放所有的经过拼接的专题名字
        for (DiZhenShuJu zhuanti : zhuantis) {
            Integer zhuantiid = zhuanti.getDizhenshujuid();//获取该专题id
            String time = zhuanti.getDizhentime();//地震发生时间
            String time2=time.substring(0,10);//截取发生时间的前10位
            //将time按照指定格式转化，例如将2019-01-02转化为2019年01月02日
            StringBuilder sb=new StringBuilder(time2);
            sb.replace(4,5,"年");
            sb.replace(7,8,"月");
            String time3=""+sb+"日";

            String weizhi = zhuanti.getWeizhi();//地震发生位置
            Float zhenji = zhuanti.getZhenji();//地震震级
            String zhenji2=zhenji+"级";
            String zhuantiname=time3+weizhi+zhenji2+"地震微直播";
            DiZhenShuJu  z=new DiZhenShuJu();
            z.setDizhenshujuid(zhuantiid);
            z.setWeizhi(zhuantiname);
            z.setDizhentime(time);
            zhuantinames.add(z);//将该专题名字放入集合中
        }
        return  zhuantinames;
    }
    //首页点击专题标题，将专题id传过来，根据专题id查询出该专题的直播信息，再将数据保存到专题详情页面
    @RequestMapping("/zhibo/showZhuanTiDetail")
    public  String showZhuanTiDetail(Integer zhuantiid,Model model){
        List<ZhiBo> zhibos = zhiboservice.getZhiBoByZid(zhuantiid);
        model.addAttribute("zhibos",zhibos);
        model.addAttribute("zhuantiid",zhuantiid);
        DiZhenShuJu dizhenshuju = dizhenshujuservice.getShuJuById(zhuantiid);
        model.addAttribute("dizhenshuju",dizhenshuju);
        return  "/jsp/showZhuanTiDetail.jsp";
    }
}
