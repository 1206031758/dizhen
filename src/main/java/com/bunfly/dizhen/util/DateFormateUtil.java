package com.bunfly.dizhen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 传入一个日期，返回该日期所在周的周一和周天的日期
 */
public class DateFormateUtil {
//获取周一
    public static  String getBegin(Date time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(cal.getTime());
        return imptimeBegin;
    }
  //获取周天
  public static  String getEnd(Date time){
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
      Calendar cal = Calendar.getInstance();
      cal.setTime(time);
      //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
      int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
      if(1 == dayWeek) {
          cal.add(Calendar.DAY_OF_MONTH, -1);
      }
      cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
      int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
      cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
      String imptimeBegin = sdf.format(cal.getTime());
      cal.add(Calendar.DATE, 6);
      String imptimeEnd = sdf.format(cal.getTime());
      return imptimeEnd;
  }

  //获取两个月份之间的所有月份
    public static List<String> getMonthBetween(String start,String end) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(start));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(end));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    //获得两个日期之间相差了多少月
    public  static  Integer getYue(String start,String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(end));
        aft.setTime(sdf.parse(start));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        int jieguo=Math.abs(month + result-1);
        return jieguo;
    }
}
