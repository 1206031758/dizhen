package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.dao.IChinaDao;
import com.bunfly.dizhen.model.China;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 全国县市经纬度的service实现层
 */
@Service
@Transactional
public class ChinaServiceImpl  implements  IChinaService{

    @Resource
    IChinaDao  chinadao;

    //取出各个县市的经纬度
    @Override
    public List<China> getAllChinaDatas() {
        return chinadao.getAllChinaDatas();
    }
}
