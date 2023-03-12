package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.User;
import com.kinoz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/user/logout")
    public ResponseResult logout(){

        //登录
        return loginService.logout();

    }
}
