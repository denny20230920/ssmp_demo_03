package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.pojo.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserSerice {

    //用户注册
    @Transactional
    User register(User user);

    //用户登录
    User login(User user);

    //按用户名查询用户
    User findUserByName(String userName);

    //修改用户信息
    @Transactional
    boolean updateUser(User user);

    //删除用户
    @Transactional
    boolean deleteUser(Long id);

    //按条件查询全部用户
    IPage<User> findUsersByOptions(Integer currentPage,Integer pageSize,User user);

}
