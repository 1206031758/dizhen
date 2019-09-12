package com.bunfly.dizhen.dao;

import com.bunfly.dizhen.model.China;

import java.util.List;

/**
 * 中国各个县市的经纬度的dao层
 */
public interface IChinaDao {

    List<China>  getAllChinaDatas();//取出各个县市的经纬度
}
