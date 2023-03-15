package com.kinoz.controller;

import com.kinoz.domain.pojo.LoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kinoz
 * @Date 2023/3/8 16:21e
 * @apiNote
 */
@RestController
public class helloController {

    @RequestMapping("/hello")
    //@PreAuthorize("hasAuthority('system:test:list')")
    //用于测试权限不足的handler使用
    //@PreAuthorize("hasAuthority('system:programer:list')")
    //使用自定义认证权限
    @PreAuthorize("@cusAuthorize.hasAuthority_cus('system:test:list')")
    public String hello(){
        return "hello";
    }


}
