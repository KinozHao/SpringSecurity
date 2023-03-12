package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.User;

public interface LoginService {
    ResponseResult login(User sysUser);

    ResponseResult logout();
}
