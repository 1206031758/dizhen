package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.dao.INewsDao;
import com.bunfly.dizhen.model.News;
import com.bunfly.dizhen.model.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 行业新闻的service实现层
 */
@Service
@Transactional
public class NewsServiceImpl implements INewsService{

    @Resource
    INewsDao   newsdao;

    //添加行业新闻
    @Override
    public void addNews(News news) {
        newsdao.addNews(news);
    }

    //根据userid取出分页数据
    @Override
    public PageModel<News> queryPage(PageModel<News> pageModel,Integer userid) {
        List<News> result = newsdao.queryPage(pageModel, userid);
        int count = newsdao.queryCount(userid);
        pageModel.setResult(result);
        pageModel.setTotalCount(count);
        return pageModel;
    }

    //根据新闻id删除该新闻
    @Override
    public void deleteNews(Integer newsid) {
        newsdao.deleteNews(newsid);
    }

    //按时间顺序取出最新的10条新闻
    @Override
    public List<News> getNewsByTime() {
        return newsdao.getNewsByTime();
    }
    //根据id取出该新闻
    @Override
    public News getNewsById(Integer newsid) {
        return newsdao.getNewsById(newsid);
    }
}
