package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.model.Role;

import java.util.List;

/**
 * 角色的service层
 */
public interface IRoleService {

    List<Role> getALLRoleNameAndId();//获取所有角色的名字和id
}
