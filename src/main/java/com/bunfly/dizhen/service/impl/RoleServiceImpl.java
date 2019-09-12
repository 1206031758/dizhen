package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.dao.IRoleDao;
import com.bunfly.dizhen.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements  IRoleService{
    @Resource
    IRoleDao  roledao;

    @Override
    public List<Role> getALLRoleNameAndId() {
        return roledao.getALLRoleNameAndId();
    }
}
