package com.bunfly.dizhen.model;

/**
 * 地震数据类
 */
public class DiZhenShuJu {
    private Integer dizhenshujuid;   //地震数据Id
    private  Float zhenji;     //震级
    private  String dizhentime;//发震时间
    private  Double weidu;//纬度
    private  Double jingdu;//经度
    private  Integer shendu;//深度
    private  String weizhi;//位置

    public Integer getDizhenshujuid() {
        return dizhenshujuid;
    }

    public void setDizhenshujuid(Integer dizhenshujuid) {
        this.dizhenshujuid = dizhenshujuid;
    }

    public Float getZhenji() {
        return zhenji;
    }

    public void setZhenji(Float zhenji) {
        this.zhenji = zhenji;
    }

    public String getDizhentime() {
        return dizhentime;
    }

    public void setDizhentime(String dizhentime) {
        this.dizhentime = dizhentime;
    }

    public Double getWeidu() {
        return weidu;
    }

    public void setWeidu(Double weidu) {
        this.weidu = weidu;
    }

    public Double getJingdu() {
        return jingdu;
    }

    public void setJingdu(Double jingdu) {
        this.jingdu = jingdu;
    }

    public Integer getShendu() {
        return shendu;
    }

    public void setShendu(Integer shendu) {
        this.shendu = shendu;
    }

    public String getWeizhi() {
        return weizhi;
    }

    public void setWeizhi(String weizhi) {
        this.weizhi = weizhi;
    }

    @Override
    public String toString() {
        return "DiZhenShuJu{" +
                "dizhenshujuid=" + dizhenshujuid +
                ", zhenji=" + zhenji +
                ", dizhentime='" + dizhentime + '\'' +
                ", weidu=" + weidu +
                ", jingdu=" + jingdu +
                ", shendu=" + shendu +
                ", weizhi='" + weizhi + '\'' +
                '}';
    }
}
