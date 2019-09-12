package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.dao.IZhuanTiDao;
import com.bunfly.dizhen.model.DiZhenShuJu;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专题的service实现层
 */
@Service
@Transactional
public class ZhuanTiServiceImpl implements IZhuanTiService{

    @Resource
    IZhuanTiDao  zhuantidao;

    //从数据库取出按时间排序的前10个专题
    @Override
    public List<DiZhenShuJu> getAllZhuanTi() {
        return zhuantidao.getAllZhuanTi();
    }

    //根据专题Id取出该专题信息
    @Override
    public DiZhenShuJu getZhuanTiById(Integer zhuantiid) {
        return zhuantidao.getZhuanTiById(zhuantiid);
    }
}
