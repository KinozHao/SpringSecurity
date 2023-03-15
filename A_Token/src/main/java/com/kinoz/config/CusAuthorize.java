package com.kinoz.config;

import com.kinoz.domain.pojo.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author LiuMiss
 * @Description 自定义权限校验
 * @Date 2023/3/15
 **/
@Component
public class CusAuthorize {

    public boolean hasAuthority_cus(String authority){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        Set<String> permissions_set = permissions.stream().distinct().collect(Collectors.toSet());
        return permissions_set.contains(authority);
    }
}
