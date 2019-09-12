package com.bunfly.dizhen.util;

/**
 * 页面点击地震活动，会首先运行ajax方法，这个类存储的是当前的年月和六个月前的年月
 * 例如：2019-01    2019-06
 */
public class SixTime {
    private String  beforetime;  //六个月前的年月
    private String  nowtime;//现在的年月

    public String getBeforetime() {
        return beforetime;
    }

    public void setBeforetime(String beforetime) {
        this.beforetime = beforetime;
    }

    public String getNowtime() {
        return nowtime;
    }

    public void setNowtime(String nowtime) {
        this.nowtime = nowtime;
    }

    @Override
    public String toString() {
        return "SixTime{" +
                "beforetime='" + beforetime + '\'' +
                ", nowtime='" + nowtime + '\'' +
                '}';
    }
}
