package com.bunfly.dizhen.service.impl;

import com.bunfly.dizhen.model.Menu;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.User;

import java.util.List;

/**
 * user的service
 */
public interface IUserService {

    //登录验证
    User login(User u);
    //根据用户id获取该用户的所有权限
    List<Menu> getAllMenusById(Integer userid);//获取该用户的所有权限
    PageModel<User> queryPage(PageModel<User> pageModel); //取出分页数据
    void addUser(User u);//在t_user中添加user，在user_roler中添加
    void deleteUser(Integer id);//根据id删除t_user中数据，然后根据userid删除user_role数据
    User queryUser(Integer userid);//根据用户id查看该用户的原始信息（包含职位）
    void updateUser(User u);//根据id修改t_user，然后根据id,循环修改user_role
}
