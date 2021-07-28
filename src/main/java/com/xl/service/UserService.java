package com.xl.service;

import com.xl.domain.User;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-16 13:34
 */
public interface UserService {
    //登录
    User login(String loginname, String password);
    //用户查询
    List<User> selectAllUser();
    //根据id编辑用户信息
    void updateUser(User user);
    //根据id查询
    User getUserById(String id);
    //插入数据
    void insertUser(User user);
    //删除
    void deleteUsers(String[] ids);
    //用户模糊查询
    List<User> selectUserLike(User user);
}
