package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.model.News;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.ZhiBo;

import java.util.List;

/**
 * 直播的service层
 */
public interface IZhiBoService {

    void addZhiBo(ZhiBo zhibo);//在数据库添加直播信息
    void deleteZhiBo(Integer zhiboid);//根据直播id在数据库删除该直播信息
    PageModel<ZhiBo> queryPage(PageModel<ZhiBo> pageModel, Integer userid); //根据userid取出分页数据
    List<ZhiBo> getZhiBoByZid(Integer zhuantiid);//根据专题id取出取出该专题的直播信息
}
