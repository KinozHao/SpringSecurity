package com.kinoz.service.impl;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.LoginUser;
import com.kinoz.domain.pojo.User;
import com.kinoz.service.LoginService;
import com.kinoz.utils.JwtUtil;
import com.kinoz.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //Authentication authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = manager.authenticate(authenticationToken);
        //认证失败给出提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //认证通过的话，使用userid生成一个jwt对象 jwt存入到ResponseResult进行返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getSysUser().getId();
        String jwt = JwtUtil.createJWT(id.toString());
        var map = new HashMap<String,String>();
        map.put("token",jwt);

        //把完整的用户信息存入redis id作为key
        //todo redis没学先不存了
        //redisCache.setCacheObject("login:"+id,loginUser);
        return new ResponseResult(200,"login success",map);
    }
}
