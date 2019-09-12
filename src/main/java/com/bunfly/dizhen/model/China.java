package com.bunfly.dizhen.model;

/**
 * 存放中国各个县市的经纬度
 */
public class China {

    private  String  provice; //省
    private  String shi;//市
    private String xian;//县
    private Double jingdu;//经度
    private  Double weidu;//纬度
    private  Double distance;//该点距离中心点的距离

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public Double getJingdu() {
        return jingdu;
    }

    public void setJingdu(Double jingdu) {
        this.jingdu = jingdu;
    }

    public Double getWeidu() {
        return weidu;
    }

    public void setWeidu(Double weidu) {
        this.weidu = weidu;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "China{" +
                "provice='" + provice + '\'' +
                ", shi='" + shi + '\'' +
                ", xian='" + xian + '\'' +
                ", jingdu=" + jingdu +
                ", weidu=" + weidu +
                ", distance=" + distance +
                '}';
    }
}
