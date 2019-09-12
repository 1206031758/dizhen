package com.bunfly.dizhen.dao;

import com.bunfly.dizhen.model.DiZhenShuJu;


import java.util.List;

/**
 * 专题类的dao层
 */
public interface IZhuanTiDao {
    List<DiZhenShuJu> getAllZhuanTi();//从数据库取出按时间排序的前10个专题
    DiZhenShuJu getZhuanTiById(Integer zhuantiid);//根据专题Id取出该专题信息
}
