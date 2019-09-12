package com.bunfly.dizhen.dao;

import com.bunfly.dizhen.model.Menu;

import java.util.List;

/**
 * 权限的dao层
 */
public interface IMenuDao {

    List<Menu> queryMenu(Integer roleid);//根据角色id查找相应的权限
}
