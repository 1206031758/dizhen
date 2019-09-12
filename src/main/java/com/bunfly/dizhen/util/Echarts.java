package com.bunfly.dizhen.util;

import java.util.ArrayList;
import java.util.List;

/**
 * echarts的工具类
 */
public class Echarts {
    private List<String> category = new ArrayList<String>();//横坐标
    private List<Series> ser=new ArrayList<>();//纵坐标
    private String titlename;//折线图的title

    public String getTitlename() {
        return titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<Series> getSer() {
        return ser;
    }

    public void setSer(List<Series> ser) {
        this.ser = ser;
    }

    @Override
    public String toString() {
        return "Echarts{" +
                "category=" + category +
                ", ser=" + ser +
                ", titlename='" + titlename + '\'' +
                '}';
    }
}
