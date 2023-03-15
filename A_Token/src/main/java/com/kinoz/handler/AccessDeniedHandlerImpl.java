package com.kinoz.handler;

import com.alibaba.fastjson.JSON;
import com.kinoz.domain.ResponseResult;
import com.kinoz.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author LiuMiss
 * @Description
 * @Date 2023/3/15
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        var result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(),"权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
