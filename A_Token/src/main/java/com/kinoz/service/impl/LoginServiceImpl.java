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
import org.springframework.security.core.context.SecurityContextHolder;
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
        //Authentication authenticate进行用户认证(跟数据库中的数据进行校验)
        //UsernamePasswordAuthenticationToken是Authentication的实现类
        //对数据进行封装,封装过后AuthenticationManager会去调用我们自己的UserDetailsService里面的逻辑进行匹配数据库
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate =
                manager.authenticate(authenticationToken);

        //认证失败给出异常提示
        //通过debug断点此处 可以发现会返回一个LoginUser类型的数据
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        //认证通过
        //获取之后进行LoginUser强转即可
        var loginUser = (LoginUser) authenticate.getPrincipal();

        //使用userid生成一个jwt对象
        var userId = loginUser.getUser().getId();
        var jwt = JwtUtil.createJWT(userId.toString());

        //jwt存入到ResponseResult进行返回
        var map = new HashMap<String,String>();
        map.put("token",jwt);

        //把完整的用户信息存入redis id作为key
        redisCache.setCacheObject("login:"+userId,loginUser);

        return new ResponseResult(200,"login success",map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        var authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var loginuser = (LoginUser)authentication.getPrincipal();
        Long userid = loginuser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"注销成功");
    }
}
