package com.bunfly.dizhen.model;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 权限
 */
public class Menu {

    private Integer menuid;//权限id
    private String menuname;//权限的名字
    private String menulink;//权限的url
    private String menusecuryname;//权限的级别
    private Integer menupid;//该权限的父类id

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getMenulink() {
        return menulink;
    }

    public void setMenulink(String menulink) {
        this.menulink = menulink;
    }

    public String getMenusecuryname() {
        return menusecuryname;
    }

    public void setMenusecuryname(String menusecuryname) {
        this.menusecuryname = menusecuryname;
    }

    public Integer getMenupid() {
        return menupid;
    }

    public void setMenupid(Integer menupid) {
        this.menupid = menupid;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuid=" + menuid +
                ", menuname='" + menuname + '\'' +
                ", menulink='" + menulink + '\'' +
                ", menusecuryname='" + menusecuryname + '\'' +
                ", menupid=" + menupid +
                '}';
    }
}
