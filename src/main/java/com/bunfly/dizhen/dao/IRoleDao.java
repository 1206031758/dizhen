package com.bunfly.dizhen.dao;

import com.bunfly.dizhen.model.Role;

import java.util.List;

/**
 * 角色的dao层
 */
public interface IRoleDao {

    List<Role> queryRole(Integer userid);//根据用户id查询用户对应的角色
    List<Role> getALLRoleNameAndId();//获取所有角色的名字和id
}
