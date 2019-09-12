package com.bunfly.dizhen.controller;

import com.bunfly.dizhen.model.News;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.User;
import com.bunfly.dizhen.service.impl.INewsService;
import com.bunfly.dizhen.util.NumberFormatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 行业新闻的controller
 */
@Controller
public class NewsController {

    @Resource
    INewsService   newsservice;  //注入newsservice


    //页面点击添加新闻，跳到该处，该处跳转到新闻添加页面
    @RequestMapping("/news/addNewsYeMian")
    public String addNewsYeMian(){
        return  "/jsp/houtai/addNewsYeMian.jsp";
    }

    //新闻添加页面点击提交，将新闻title和content提交过来，再将当前时间存进news里,将news添加到数据库中
    @RequestMapping("/news/addNews")
    public String addNews(String newstitle, String newscontent,Integer userid ,Model model){
        News news=new News();
        news.setNewstitle(newstitle);
        news.setNewscontent(newscontent);
        news.setUserid(userid);
        //日期的格式
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //将当前日期转化为规定格式的字符串
        String shangchuantime=f.format(new Date());
        news.setShangchuantime(shangchuantime);
        newsservice.addNews(news);
        model.addAttribute("newsmanage","newsmanage");
        return  "/user/firstmenus";
    }

    //页面点击查看上传新闻，跳转到该处，从数据库取出该用户的上传新闻，传回ajax
    @RequestMapping("/news/showNews")
    @ResponseBody
    public PageModel getAllUser(String pageSize, String currentPage,Integer userid){
        int pageSize1 = NumberFormatUtil.format(pageSize, 4);//每页的数据量
        int currentPage1 = NumberFormatUtil.format(currentPage, 1);//当前页码
        PageModel<News> pageModel = new PageModel<News>();
        pageModel.setCurrentPage(currentPage1);
        pageModel.setPageSize(pageSize1);
        pageModel =newsservice.queryPage(pageModel,userid);
        return pageModel;
    }

    //页面点击删除，将该新闻的newsid传过来，在数据库将该条新闻删除
    @RequestMapping("/news/deleteNews")
    public  String deleteNews (Integer newsid,Model model){
        newsservice.deleteNews(newsid);
        model.addAttribute("shownews","shownews");
        return "/user/firstmenus";
    }

    //主页一加载，调用showNews（）方法，ajax请求，这里返回数据给到ajax
    @RequestMapping("/news/showNewsTitle")
    @ResponseBody
    public  List<News> showNewsTitle(){
        List<News> news = newsservice.getNewsByTime();
        return news;
    }

    //主页点击某个新闻，获取到该新闻的id,根据Id获取该新闻的信息，返回到新闻详情页面
    @RequestMapping("/news/showNewsDetail")
    public  String showNewsDetail(Integer newsid,Model model){
        News news = newsservice.getNewsById(newsid);
        model.addAttribute("news",news);
        return  "/jsp/ShowNews.jsp";
    }
}
