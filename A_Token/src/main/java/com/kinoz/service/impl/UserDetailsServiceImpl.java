package com.kinoz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kinoz.domain.pojo.LoginUser;
import com.kinoz.domain.pojo.User;
import com.kinoz.mapper.SysMenuMapper;
import com.kinoz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author kinoz
 * @Date 2023/3/8 16:43
 * @apiNote 实现查询数据库的用户信息就行登录校验
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper mapper;

    @Resource
    SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        var wq = new LambdaQueryWrapper<User>();
        wq.eq(User::getUserName,username);
        var user = mapper.selectOne(wq);

        //若没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }

        //TODO 查询对应得权限信息
        //测试使用使用静态数据
        //List<String> permission_list = new ArrayList<>(Arrays.asList("test","admin"));
        //替换为数据库
        //把数据封装为UserDetails类型
        List<String> permission_list= sysMenuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user,permission_list);
    }
}
