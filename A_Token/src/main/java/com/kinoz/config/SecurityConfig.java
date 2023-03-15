package com.kinoz.config;

import com.kinoz.filter.JwtAuthenticationTokenFilter;
import com.kinoz.handler.AccessDeniedHandlerImpl;
import com.kinoz.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * 用于替换默认的PasswordEncoder
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Resource
    JwtAuthenticationTokenFilter jwt;
    @Resource
    AccessDeniedHandlerImpl access;
    @Resource
    AuthenticationEntryPointImpl entryPoint;

    /**
     * 配置过滤规则
     * 自定义登录接口放行
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                //permitAll方法登录没登录都可以访问
                .antMatchers("/hello").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //添加各种过滤器
        //把我们自定义的filter添加到前面
        http.addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器
        http.exceptionHandling()
            .authenticationEntryPoint(entryPoint)
            .accessDeniedHandler(access);
        //允许跨域
        http.cors();
    }

    /**
     * 使用Authentication的authenticate进行用户认证
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}