package com.bunfly.dizhen.dao;

import com.bunfly.dizhen.model.Menu;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {
    //登录验证
    User  login(User u);
    //根据用户id获取该用户的所有权限
    List<Menu> getAllMenusById(Integer userid);//获取该用户的所有权限
    List<User> queryPage(PageModel<User> pageModel);//分页的数据量
    Integer queryCount();//总共有多少用户
    void addUser(User u);//往t_user添加一个用户
    void addmiddle(@Param("userid")Integer userid, @Param("roleid")Integer roleid);//往user_role表中添加数据
    void deleteUser(Integer userid);//在t_user删除一个用户
    void deletemiddle(Integer userid);//在user_role表中删除数据
    User queryUser(Integer userid);//根据用户id查看该用户的原始信息（包含职位）
    void updateUser(User u);//根据id在t_user修改用户信息
}
