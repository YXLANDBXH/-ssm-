package com.xl.service;
import com.xl.domain.User;
import com.xl.domain.UserExample;
import com.xl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-16 13:34
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param loginname
     * @param password
     * @return
     */
    @Override
    public User login(String loginname,String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginnameEqualTo(loginname);
        criteria.andPasswordEqualTo(password);

        List<User> userList = this.userMapper.selectByExample(userExample);
        if (userList!=null && userList.size()>0) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 查询用户信息
     * @return
     */
    @Override
    public List<User> selectAllUser() {
        return this.userMapper.selectByExample(null);
    }

    /**
     * 根据id跟新用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        this.userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @Override
    public User getUserById(String id) {
        return this.userMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 插入用户信息
     * @param user
     */
    @Override
    public void insertUser(User user) {
        this.userMapper.insertSelective(user);
    }

    @Override
    public void deleteUsers(String[] ids) {
       for (int i=0;i<ids.length;i++) {
           this.userMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
       }
    }

    @Override
    public List<User> selectUserLike(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (user!=null) {
            if (user.getLoginname()!=null && !user.getLoginname().equals("")) {
                criteria.andLoginnameLike(user.getLoginname());
            }
            if (user.getUsername()!=null && !user.getUsername().equals("")) {
                criteria.andUsernameLike(user.getUsername());
            }
            if (user.getStatus()!=null) {
                criteria.andStatusEqualTo(user.getStatus());
            }
        }
        return this.userMapper.selectByExample(userExample);
    }

}
