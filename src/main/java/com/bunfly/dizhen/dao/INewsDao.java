package com.bunfly.dizhen.dao;

import com.bunfly.dizhen.model.News;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 行业新闻的dao层
 */
public interface INewsDao {

    void addNews(News news);//添加行业新闻
    List<News> queryPage(@Param("pageModel")PageModel<News> pageModel, @Param("userid")Integer userid);//分页的数据量
    Integer queryCount(Integer userid);//总共有多少用户
    void deleteNews(Integer newsid);//根据新闻id删除该新闻
    List<News> getNewsByTime();//按时间顺序取出最新的10条新闻
    News getNewsById(Integer newsid);//根据id取出该新闻
}
