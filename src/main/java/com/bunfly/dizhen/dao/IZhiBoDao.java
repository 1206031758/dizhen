package com.bunfly.dizhen.dao;

import com.bunfly.dizhen.model.News;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.ZhiBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 直播dao层
 */
public interface IZhiBoDao {

    void addZhiBo(ZhiBo zhibo);//在数据库添加直播信息
    void deleteZhiBo(Integer zhiboid);//根据直播id在数据库删除该直播信息
    List<ZhiBo> queryPage(@Param("pageModel") PageModel<ZhiBo> pageModel, @Param("userid")Integer userid);//分页的数据量
    Integer queryCount(Integer userid);//总共有多少用户
    List<ZhiBo> getZhiBoByZid(Integer zhuantiid);//根据专题id取出取出该专题的直播信息
}
