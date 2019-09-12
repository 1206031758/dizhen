package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.dao.IZhiBoDao;
import com.bunfly.dizhen.model.News;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.ZhiBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 直播的实现service
 */
@Service
@Transactional
public class ZhiBoServiceImpl implements IZhiBoService{

    @Resource
    IZhiBoDao zhibodao;

    //在数据库添加直播信息
    @Override
    public void addZhiBo(ZhiBo zhibo) {
        zhibodao.addZhiBo(zhibo);
    }
    //根据直播id在数据库删除该直播信息
    @Override
    public void deleteZhiBo(Integer zhiboid) {
        zhibodao.deleteZhiBo(zhiboid);
    }
    //根据userid取出分页数据
    @Override
    public PageModel<ZhiBo> queryPage(PageModel<ZhiBo> pageModel, Integer userid) {
        List<ZhiBo> result = zhibodao.queryPage(pageModel, userid);
        int count = zhibodao.queryCount(userid);
        pageModel.setResult(result);
        pageModel.setTotalCount(count);
        return pageModel;
    }
    //根据专题id取出取出该专题的直播信息
    @Override
    public List<ZhiBo> getZhiBoByZid(Integer zhuantiid) {
        return zhibodao.getZhiBoByZid(zhuantiid);
    }
}
