package com.bunfly.dizhen.model;

/**
 * 直播类
 */
public class ZhiBo {

    private Integer zhiboid;//直播id
    private String zhibotitle;//直播标题
    private String zhibolaiyuan;//直播的来源
    private String  zhibotime;//直播的发布时间
    private String zhibocontent;//直播内容
    private Integer userid;//直播的上传人
    private  Integer  zhuantiid;//该直播所属的专题id
    private String zhuantiname;//该直播所属专题的名字

    public Integer getZhiboid() {
        return zhiboid;
    }

    public void setZhiboid(Integer zhiboid) {
        this.zhiboid = zhiboid;
    }

    public String getZhibotitle() {
        return zhibotitle;
    }

    public void setZhibotitle(String zhibotitle) {
        this.zhibotitle = zhibotitle;
    }

    public String getZhibolaiyuan() {
        return zhibolaiyuan;
    }

    public void setZhibolaiyuan(String zhibolaiyuan) {
        this.zhibolaiyuan = zhibolaiyuan;
    }

    public String getZhibotime() {
        return zhibotime;
    }

    public void setZhibotime(String zhibotime) {
        this.zhibotime = zhibotime;
    }

    public String getZhibocontent() {
        return zhibocontent;
    }

    public void setZhibocontent(String zhibocontent) {
        this.zhibocontent = zhibocontent;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getZhuantiid() {
        return zhuantiid;
    }

    public void setZhuantiid(Integer zhuantiid) {
        this.zhuantiid = zhuantiid;
    }

    public String getZhuantiname() {
        return zhuantiname;
    }

    public void setZhuantiname(String zhuantiname) {
        this.zhuantiname = zhuantiname;
    }

    @Override
    public String toString() {
        return "ZhiBo{" +
                "zhiboid=" + zhiboid +
                ", zhibotitle='" + zhibotitle + '\'' +
                ", zhibolaiyuan='" + zhibolaiyuan + '\'' +
                ", zhibotime='" + zhibotime + '\'' +
                ", zhibocontent='" + zhibocontent + '\'' +
                ", userid=" + userid +
                ", zhuantiid=" + zhuantiid +
                ", zhuantiname='" + zhuantiname + '\'' +
                '}';
    }
}
