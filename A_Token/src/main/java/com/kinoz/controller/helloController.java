package com.kinoz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kinoz
 * @Date 2023/3/8 16:21
 * @apiNote
 */
@RestController
public class helloController {

    @RequestMapping("hello")
    @PreAuthorize("hasAuthority('test')")
    public String hello(){
        return "hello";
    }
}
