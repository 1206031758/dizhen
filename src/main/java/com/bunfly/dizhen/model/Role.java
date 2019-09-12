package com.bunfly.dizhen.model;

import java.util.List;

/**
 * 角色类
 */
public class Role {
    private Integer roleid;//角色id
    private String rolename;//角色名字
    private String rolememo;//角色的描述
    private List<Menu> menus;   //该角色包含的权限

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRolememo() {
        return rolememo;
    }

    public void setRolememo(String rolememo) {
        this.rolememo = rolememo;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                ", rolememo='" + rolememo + '\'' +
                ", menus=" + menus +
                '}';
    }
}
