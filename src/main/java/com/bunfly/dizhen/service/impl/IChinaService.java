package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.model.China;

import java.util.List;

/**
 * 中国各个县市的经纬度的service层
 */
public interface IChinaService {
    List<China> getAllChinaDatas();//取出各个县市的经纬度
}
