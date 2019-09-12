package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.model.News;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.User;

import java.util.List;

/**
 * 行业新闻的service
 */
public interface INewsService {
    void addNews(News news);//添加行业新闻
    PageModel<News> queryPage(PageModel<News> pageModel,Integer userid); //根据userid取出分页数据
    void deleteNews(Integer newsid);//根据新闻id删除该新闻
    List<News> getNewsByTime();//按时间顺序取出最新的10条新闻
    News getNewsById(Integer newsid);//根据id取出该新闻
}
