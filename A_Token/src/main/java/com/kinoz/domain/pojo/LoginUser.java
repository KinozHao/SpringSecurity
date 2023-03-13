package com.kinoz.domain.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kinoz
 * @Date 2023/3/8 16:54
 * @apiNote
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private User user;

    private List<String> permissions;

    public LoginUser(User user, List<String> permission) {
        this.user = user;
        this.permissions = permission;
    }

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    /**
     * 获取权限信息的
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /**
         * @discripte SimpleGrantedAuthority为GrantedAuthority的实现类
         * @see GrantedAuthority
         * @see org.springframework.security.core.authority.SimpleGrantedAuthority
         */
        //先判断是否为空
        if (authorities !=null){
            return authorities;
        }

        //把permission中的String类型的权限信息封装为SimpleGrantedAuthority对象
        authorities = permissions
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
