package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.dao.IDiZhenShuJuDao;
import com.bunfly.dizhen.model.DiZhenShuJu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地震数据的service实现层
 */
@Service
@Transactional
public class DiZhenShuJuServiceImpl implements IDiZhenShuJuService{

    @Resource
    IDiZhenShuJuDao   dizhenshujudao;

    //获取一周的地震数据（周图
    @Override
    public List<DiZhenShuJu> getZhouData(String monday, String sunday) {
        return dizhenshujudao.getZhouData(monday, sunday);
    }
    //获取一个月的地震数据（月图）
    @Override
    public List<DiZhenShuJu> getYueData(String month) {
        return dizhenshujudao.getYueData(month);
    }
    //获取month1到month2两个月份之间的数据，包括month2
    @Override
    public List<DiZhenShuJu> getDatas(String month1, String month2) {
        return dizhenshujudao.getDatas(month1, month2);
    }

    //由传过来的地震级别和时间（2019-08）确定发震次数
    @Override
    public Integer getCiShuByTimeAndJiBie(String jibie, String time) {
        if(jibie.equals("一级")){jibie="1";};
        if(jibie.equals("二级")){jibie="2";};
        if(jibie.equals("三级")){jibie="3";};
        if(jibie.equals("四级")){jibie="4";};
        if(jibie.equals("五级")){jibie="5";};
        if(jibie.equals("六级")){jibie="6";};
        if(jibie.equals("七级")){jibie="7";};
        if(jibie.equals("八级")){jibie="8";};
        if(jibie.equals("九级")){jibie="9";};
        if(jibie.equals("十级")){jibie="10";};
        return dizhenshujudao.getCiShuByTimeAndJiBie(jibie,time);
    }
    //由传过来的地震级别和时间(2019-06)取出地震数据
    @Override
    public List<DiZhenShuJu> getDatasByTimeAndJiBie(String jibie, String time) {
        if(jibie.equals("一级")){jibie="1";};
        if(jibie.equals("二级")){jibie="2";};
        if(jibie.equals("三级")){jibie="3";};
        if(jibie.equals("四级")){jibie="4";};
        if(jibie.equals("五级")){jibie="5";};
        if(jibie.equals("六级")){jibie="6";};
        if(jibie.equals("七级")){jibie="7";};
        if(jibie.equals("八级")){jibie="8";};
        if(jibie.equals("九级")){jibie="9";};
        if(jibie.equals("十级")){jibie="10";};
        return dizhenshujudao.getDatasByTimeAndJiBie(jibie,time);
    }
    //由form表单提交的条件来查询地震数据
    @Override
    public List<DiZhenShuJu> getDatasByFormChaXun(String dizhentime1,String dizhentime2,Float weidu1,Float weidu2,Float jingdu1,Float jingdu2,Integer shendu1,Integer shendu2,Float zhenji1,Float zhenji2) {
        if(dizhentime1==""){dizhentime1=null;}
        if(dizhentime2==""){dizhentime2=null;}
//        if(weidu1.equals("")){weidu1=null;}
//        if(weidu2.equals("")){weidu2=null;}
//        if(jingdu1.equals("")){jingdu1=null;}
//        if(jingdu2.equals("")){jingdu2=null;}
//        if(shendu1.equals("")){shendu1=null;}
//        if(shendu2.equals("")){shendu2=null;}
//        if(zhenji1.equals("")){zhenji1=null;}
//        if(zhenji2.equals("")){zhenji2=null;}
        System.out.println("这是service的深度1数据："+shendu1);
        System.out.println("这是service的深度2数据："+shendu2);
        return dizhenshujudao.getDatasByFormChaXun(dizhentime1,dizhentime2,weidu1,weidu2,jingdu1,jingdu2,shendu1,shendu2,zhenji1,zhenji2);
    }

    //根据传进来的时间，如24小时内、30天内、1年内查询出地震数据
    @Override
    public List<DiZhenShuJu> getDatasByATime(Integer time) {
        if(time==24){return  dizhenshujudao.get24or48(time);}
        if(time==48){return  dizhenshujudao.get24or48(time);}
        if (time==7){return  dizhenshujudao.get7or30(time);}
        if (time==30){return  dizhenshujudao.get7or30(time);}
        else {return  dizhenshujudao.getOneYear(time);}
    }
    //根据传进来的震级，如6、5、4  ，查询出一年以内6、5、4级以上地震的数据
    @Override
    public List<DiZhenShuJu> chaXunByAZhenJi(Integer zhenji) {
        if(zhenji==6){return dizhenshujudao.sixzhenji();}
        if(zhenji==5){return  dizhenshujudao.fivezhenji();}
        if(zhenji==4){ return  dizhenshujudao.fourzhenji();}
        if(zhenji==3){return  dizhenshujudao.threezhenji();}
        else{return  dizhenshujudao.twozhenji();}
    }
    //由传过来的地震数据id获取该地震数据
    @Override
    public DiZhenShuJu getShuJuById(Integer dizhenshujuid) {
        return dizhenshujudao.getShuJuById(dizhenshujuid);
    }
    //查询出5级以上的地震
    @Override
    public List<DiZhenShuJu> getfive() {
        return dizhenshujudao.getfive();
    }


}
