package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.User;
import com.kinoz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User sysUser){
        //登录
        return loginService.login(sysUser);
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        //登出
        return loginService.logout();

    }
}
