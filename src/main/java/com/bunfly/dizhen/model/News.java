package com.bunfly.dizhen.model;

/**
 * 行业新闻类
 */
public class News {

    private Integer newsid;//新闻id
    private String  newstitle;//新闻标题
    private String   newscontent;//新闻内容
    private  Integer userid;//上传用户的id
    private  String  shangchuantime;//新闻上传的时间

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getShangchuantime() {
        return shangchuantime;
    }

    public void setShangchuantime(String shangchuantime) {
        this.shangchuantime = shangchuantime;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsid=" + newsid +
                ", newstitle='" + newstitle + '\'' +
                ", newscontent='" + newscontent + '\'' +
                ", userid=" + userid +
                ", shangchuantime='" + shangchuantime + '\'' +
                '}';
    }
}
