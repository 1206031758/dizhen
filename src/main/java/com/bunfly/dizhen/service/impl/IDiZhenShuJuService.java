package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.model.DiZhenShuJu;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * 地震数据的service层
 */
public interface IDiZhenShuJuService {

    List<DiZhenShuJu> getZhouData(String monday,String sunday);//获取一周的地震数据（周图）
    List<DiZhenShuJu> getYueData(String month);//获取一个月的地震数据（月图）
    List<DiZhenShuJu> getDatas(String month1,String month2);//获取month1到month2两个月份之间的数据，包括month2
    Integer getCiShuByTimeAndJiBie(String jibie,String time);//由传过来的地震级别和时间（2019-08）确定发震次数
    List<DiZhenShuJu> getDatasByTimeAndJiBie(String jibie,String time);//由传过来的地震级别和时间(2019-06)取出地震数据
    List<DiZhenShuJu> getDatasByFormChaXun(String dizhentime1,String dizhentime2,Float weidu1,Float weidu2,Float jingdu1,Float jingdu2,Integer shendu1,Integer shendu2,Float zhenji1,Float zhenji2);//由form表单提交的条件来查询地震数据
    List<DiZhenShuJu>  getDatasByATime(Integer time);//根据传进来的时间，如24小时内、30天内、1年内查询出地震数据
    List<DiZhenShuJu> chaXunByAZhenJi(Integer zhenji);//根据传进来的震级，如6、5、4  ，查询出一年以内6、5、4级以上地震的数据
    DiZhenShuJu getShuJuById(Integer dizhenshujuid);//由传过来的地震数据id获取该地震数据
    List<DiZhenShuJu> getfive();//查询出5级以上的地震
}
