package com.demo.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.controller.result.Code;
import com.demo.exception.BusinessException;
import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.service.UserSerice;
import com.demo.utils.Md5PasswordUtil;
import com.demo.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class UserSericeImpl implements UserSerice {

    //使用Users的缓存空间，key过期时间为1小时
    @CreateCache(area="users",name="token_",expire=3600)
    Cache<String,String> jetCache;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenUtil tokenUtil;

    @Override
    public User register(User user) {

        if (user == null){
            log.error("参数错误"+user);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误");
        }

        if(null == user.getUsername() || "" == user.getUsername()
                || null == user.getPassword() || "" == user.getPassword()){
            throw new BusinessException(Code.BUSINESS_USER_NP_ERR,"用户名或密码不能为空");
        }

        User userByName = findUserByName(user.getUsername());

        if (userByName != null){
            throw new BusinessException(Code.BUSINESS_USER_EXISTS_ERR,"用户名已存在");
        }

        //生成用户盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        //加密后的密码
        String md5Password = Md5PasswordUtil.getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        //是否删除
        user.setDeleted(1);
        //是否禁用
        user.setDisabled(1);
        //创建人
        user.setCreateUser("administrator");
        //创建时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateUser(simpleDateFormat.format(new Date()));

        int insert = userMapper.insert(user);

        if (!(insert>0)){
            throw new BusinessException(Code.BUSINESS_USER_NEW_ERR,"新建用户失败");
        }

        User userByName1 = findUserByName(user.getUsername());

        return userByName1;
    }

    @Override
    public User login(User user) {

        if (user == null){
            log.error("参数错误"+user);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误");
        }

        if(null == user.getUsername() || "" == user.getUsername()
                || null == user.getPassword() || "" == user.getPassword()){
            throw new BusinessException(Code.BUSINESS_USER_NP_ERR,"用户名或密码不能为空");
        }

        User userByName = findUserByName(user.getUsername());

        if (userByName == null){
            throw new BusinessException(Code.BUSINESS_USER_NEXISTS_ERR,"用户不存在");
        }

        if (!userByName.getPassword().equals(
                Md5PasswordUtil.getMd5Password(
                        user.getPassword(),userByName.getSalt()))){
            throw new BusinessException(Code.BUSINESS_USER_NP_ERR,"用户名或密码错误");
        }

        // 先验证用户的账号密码,账号密码验证通过之后，生成Token
        String role = "ROLE_ADMIN";
        //生成token
        String token = tokenUtil.getToken(String.valueOf(userByName.getId()),role);
        userByName.setToken(token);
        jetCache.put(String.valueOf(userByName.getId()),token);

        return userByName;
    }

    @Override
    public User findUserByName(String userName) {

        if (null == userName || "" == userName){
            log.error("参数错误"+userName);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误");
        }

        LambdaQueryWrapper<User> lwq = new LambdaQueryWrapper();

        lwq.eq(userName!=null,User::getUsername,userName);

        User user = userMapper.selectOne(lwq);

        return user;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public IPage<User> findUsersByOptions(Integer currentPage, Integer pageSize, User user) {
        return null;
    }
}
