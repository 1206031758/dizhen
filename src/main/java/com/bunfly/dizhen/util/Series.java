package com.bunfly.dizhen.util;

import java.util.List;

/**
 * echarts的工具类
 */
public class Series {

    private String yname;   //折线图的y坐标的名字
    private List<Integer> datas; //折线图的y坐标的值

    public String getYname() {
        return yname;
    }

    public void setYname(String yname) {
        this.yname = yname;
    }

    public List<Integer> getDatas() {
        return datas;
    }

    public void setDatas(List<Integer> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Series{" +
                "yname='" + yname + '\'' +
                ", datas=" + datas +
                '}';
    }
}
