package com.bunfly.dizhen.controller;

import com.bunfly.dizhen.model.Menu;
import com.bunfly.dizhen.model.PageModel;
import com.bunfly.dizhen.model.Role;
import com.bunfly.dizhen.model.User;
import com.bunfly.dizhen.service.impl.IRoleService;
import com.bunfly.dizhen.service.impl.IUserService;
import com.bunfly.dizhen.util.NumberFormatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    /**
     *注入userservice
     */
    @Resource
    IUserService  userService;
    @Resource
    private HttpSession session;
    @Resource
    IRoleService roleservice;

    //登录验证
    @RequestMapping("/user/login")
    public String login(String loginname,String password,Model model){
        User u=new User();
        u.setLoginname(loginname);
        u.setPassword(password);
        u=userService.login(u);
        if (u!=null) {
            Integer userid=u.getUserid();//获取userid
            List<Menu> menus = userService.getAllMenusById(userid);//根据userid获取该用户的所有权限
            session.setAttribute("menus",menus);
            session.setAttribute("user",u);//将用户存在session中
            return "redirect:/user/firstmenus";
        }else{
            return "/error.jsp";
        }
    }

    //退出登录，注销session
    @RequestMapping("/user/tuichu")
    public String tuichu(){
        session.removeAttribute("user");
        return "redirect:/jsp/houtai/houTaiLoginPage.jsp";
    }

    //获取一级菜单
    @RequestMapping("/user/firstmenus")
    public String firstmenus(Model model){
        List<Menu> menus = (List<Menu>) session.getAttribute("menus");//获取该用户的所用权限
        //获取该用户的所有一级菜单
        List<Menu> firstMenus=new ArrayList<Menu>();
        for (Menu menu : menus) {
            if(menu.getMenupid()==0){
                firstMenus.add(menu);
            }
        }
        model.addAttribute("firstMenus",firstMenus);
        return "/jsp/houtai/houTaiHomePage.jsp";
    }

    //根据一级权限的menuid获取二级权限
    @RequestMapping("/user/getSecondMenu")
    @ResponseBody
    public List<Menu> getSecondMenu(Integer menuid){
        List<Menu> menus=(List<Menu>) session.getAttribute("menus");
        //获取该用户的所有一级菜单
        List<Menu> firstMenus=new ArrayList<Menu>();
        for (Menu menu : menus) {
            if(menu.getMenupid()==0){
                firstMenus.add(menu);
            }
        }
        //根据传进来的一级菜单的menuid得到二级菜单
        List<Menu> secondMenus=new ArrayList<Menu>();
        for (Menu menu : menus) {
            for (Menu m : firstMenus) {
                if(menu.getMenupid()==m.getMenuid() && menu.getMenupid()==menuid){
                    secondMenus.add(menu);
                }
            }
        }
        return secondMenus;
    }

    //获取所有的用户信息，分页展示
    @RequestMapping("/user/showUser")
    @ResponseBody
    public PageModel getAllUser(String pageSize,String currentPage){
        int pageSize1 = NumberFormatUtil.format(pageSize, 4);//每页的数据量
        int currentPage1 = NumberFormatUtil.format(currentPage, 1);//当前页码
        PageModel<User> pageModel = new PageModel<User>();
        pageModel.setCurrentPage(currentPage1);
        pageModel.setPageSize(pageSize1);
        pageModel =userService.queryPage(pageModel);
        return pageModel;
    }

    //页面点击添加用户，跳转到添加用户的页面
    @RequestMapping("/user/addyemian")
    public String addyemian(Model model){
        List<Role> role = roleservice.getALLRoleNameAndId();
        model.addAttribute("role",role);
        return "/jsp/houtai/addUser.jsp";
    }

    //添加用户页面点击添加，跳转到该处，在t_user表里添加数据
    @RequestMapping("/user/addUser")
    public String addUser(String username,String loginname,String password,String usersex,Integer[] roles,Model model){
    User u=new User();
    u.setPassword(password);
    u.setLoginname(loginname);
    u.setUsername(username);
    u.setUsersex(usersex);
        List<Role> l=new ArrayList<Role>();
        for(Integer roleid:roles){
            Role r=new Role();
            r.setRoleid(roleid);
            l.add(r);
        }
        u.setRoles(l);
        userService.addUser(u);
        model.addAttribute("usermanage", "usermanage");
        return "/user/firstmenus";
    }

    //页面点击删除用户，将该用户的id传过来，在数据库中删除该用户
    @RequestMapping("/user/deleteUser")
    public  String deleteUser(Integer userid,Model model){
        userService.deleteUser(userid);
        model.addAttribute("showuser", "showuser");
        return "/user/firstmenus";
    }

    //页面点击修改用户，跳到该处，将userid传过来，去数据库查询出该user的信息，跳转到用户修改页面
    @RequestMapping("/user/queryUser")
    public String queryUser(Integer userid,Model model){
        User user = userService.queryUser(userid);
        model.addAttribute("user",user);
        List<Role> roles = user.getRoles();//获取该用户的角色信息
        List<Role> roleidandname = roleservice.getALLRoleNameAndId();//获取所有的角色名和id
        model.addAttribute("roles",roles);
        model.addAttribute("roleidandname",roleidandname);
        return "/jsp/houtai/updateUser.jsp";
    }

    //修改用户页面点击修改，将修改的信息传过来，在数据库修改用户
    @RequestMapping("/user/updateUser")
    public  String updateUser(Integer userid,String username,String loginname,String password,String usersex,Integer[] roles,Model model){
        User u=new User();
        u.setUserid(userid);
        u.setUsername(username);
        u.setLoginname(loginname);
        u.setPassword(password);
        u.setUsersex(usersex);
        List<Role> l=new ArrayList<Role>();
        for(Integer roleid:roles){
            Role r=new Role();
            r.setRoleid(roleid);
            l.add(r);
        }
        u.setRoles(l);
        userService.updateUser(u);
        model.addAttribute("showuser", "showuser");
        return "/user/firstmenus";
    }

}
