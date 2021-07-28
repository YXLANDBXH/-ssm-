package com.xl.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xl.domain.User;
import com.xl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author XLong
 * @create 2021-07-16 13:18
 */
@Controller
public class UserController {

    //设置每页显示的条数
    private static final int pageSize = 4;

    @Autowired
    private UserService userService;
    /**
     * 转发到loginForm页面
     * @return
     */
    @RequestMapping(value = "loginform")
    public String toLogin(){
        return "loginForm";
    }

    /**
     * 用户登录
     * @param user
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "userLogin")
    public String login(User user, Model model, HttpSession session){
        User user1 = this.userService.login(user.getLoginname(), user.getPassword());
        if (user1!=null) { //登录成功
            session.setAttribute("user_session",user1);
            return "redirect:main";
        }else { //失败
            // 给出错误提示
            model.addAttribute("message","账号或密码错误");
            return "loginForm";
        }
    }

    /**
     * 分页查询
     * @param model
     * @return
     */
    @RequestMapping(value = "userlist.action")
    public String selectUserList(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = this.userService.selectAllUser();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("pageModel",pageInfo);
        return "user/userlist";
    }

    /**
     * 编辑数据回显
     * @return
     */
    @RequestMapping(value = "viewUser.action")
    public String toViewUser(String id, Model model) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user",user);
        return "user/useredit";
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "useredit.action")
    public String userEdit(User user) {
        this.userService.updateUser(user);
        return "redirect:userlist.action";
    }

    /**
     * 跳转到用户添加页面
     * @return
     */
    @RequestMapping(value = "useradd.action")
    public String toUseradd() {
        return "user/useradd";
    }

    /**
     * 插入用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "useraddsave.action")
    public String userAdd(User user) {
        user.setCreatedate(new Date());
        this.userService.insertUser(user);
        return "redirect:userlist.action";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "userdel.action")
    public String deltUsers(String []ids) {
        this.userService.deleteUsers(ids);
        return "redirect:userlist.action";
    }

    /**
     * 模糊搜索
     * @param model
     * @param user
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "userlistlike.action")
    public String queryUserListLike(Model model,User user,@RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = this.userService.selectUserLike(user);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("pageModel",pageInfo);
        return "user/userlist";
    }

}
