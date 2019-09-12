package com.bunfly.dizhen.dao;

import com.bunfly.dizhen.model.DiZhenShuJu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对数据库地震数据进行操作的dao层
 */
public interface IDiZhenShuJuDao {

    List<DiZhenShuJu> getZhouData(@Param("monday") String monday, @Param("sunday") String sunday);//获取一周的地震数据（周图）
    List<DiZhenShuJu> getYueData(@Param("month") String month);//获取一个月的地震数据（月图）
    List<DiZhenShuJu> getDatas(@Param("month1") String month1,@Param("month2") String month2);//获取month1到month2两个月份之间的数据，包括month2
    Integer getCiShuByTimeAndJiBie(@Param("jibie") String jibie,@Param("time") String time);//由传过来的地震级别和时间（2019-08）确定发震次数
    List<DiZhenShuJu> getDatasByTimeAndJiBie(@Param("jibie") String jibie,@Param("time") String time);//由传过来的地震级别和时间(2019-06)取出地震数据
    List<DiZhenShuJu> getDatasByFormChaXun(@Param("dizhentime1") String dizhentime1,@Param("dizhentime2")String dizhentime2,@Param("weidu1")Float weidu1,@Param("weidu2")Float weidu2,@Param("jingdu1")Float jingdu1,@Param("jingdu2")Float jingdu2,@Param("shendu1")Integer shendu1,@Param("shendu2")Integer shendu2,@Param("zhenji1")Float zhenji1,@Param("zhenji2")Float zhenji2);//由form表单提交的条件来查询地震数据
    List<DiZhenShuJu> get24or48(Integer time);//获得24或48小时内的数据
    List<DiZhenShuJu> get7or30(Integer time);//获得7天或30天内的数据
    List<DiZhenShuJu> getOneYear(Integer time);//获得一年内的数据
    List<DiZhenShuJu> sixzhenji();//查询1年内6级以上地震
    List<DiZhenShuJu> fivezhenji();//查询1年内5级以上地震，6级以下
    List<DiZhenShuJu> fourzhenji();//查询1年内4级以上地震，5级以下
    List<DiZhenShuJu> threezhenji();//查询1年内3级以上地震，4级以下
    List<DiZhenShuJu> twozhenji();//查询1年内3级以下地震
    DiZhenShuJu getShuJuById(Integer dizhenshujuid);//由传过来的地震数据id获取该地震数据
    List<DiZhenShuJu> getfive();//查询出5级以上的地震
}