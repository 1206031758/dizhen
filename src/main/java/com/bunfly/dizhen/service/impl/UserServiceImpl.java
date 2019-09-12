package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.dao.IUserDao;
import com.bunfly.dizhen.model.Menu;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.Role;
import com.bunfly.dizhen.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    /**
     *
     * 注入userdao
     */
    @Resource
    IUserDao  userdao;
    //登录验证
    @Override
    public User login(User u) {
        return userdao.login(u);
    }
    //根据用户id获取该用户的所有权限
    @Override
    public List<Menu> getAllMenusById(Integer userid) {
        return userdao.getAllMenusById(userid);
    }
//取出所有用户，分页展示
    @Override
    public PageModel<User> queryPage(PageModel<User> pageModel) {
        List<User> result = userdao.queryPage(pageModel);
        int count=userdao.queryCount();
        pageModel.setResult(result);
        pageModel.setTotalCount(count);
        return pageModel;
    }
    //在t_user中添加user，在user_roler中添加
    @Override
    public void addUser(User u) {
        userdao.addUser(u);
        List<Role> roles = u.getRoles();
        for (Role role : roles) {
            userdao.addmiddle(u.getUserid(), role.getRoleid());
        }
    }
    //根据id删除t_user中数据，然后根据userid删除user_role数据
    @Override
    public void deleteUser(Integer id) {
        userdao.deleteUser(id);
        userdao.deletemiddle(id);

    }
    //根据用户id查看该用户的原始信息（包含职位）
    @Override
    public User queryUser(Integer userid) {
        return userdao.queryUser(userid);
    }
    //根据id修改t_user，然后根据id,循环修改user_role
    @Override
    public void updateUser(User u) {
        userdao.updateUser(u);
        userdao.deletemiddle(u.getUserid());
        List<Role> roles=u.getRoles();
        for (Role role : roles) {
            userdao.addmiddle(u.getUserid(), role.getRoleid());
        }
    }
}
