package com.kinoz;

import com.kinoz.domain.pojo.User;
import com.kinoz.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @author kinoz
 * @Date 2023/3/8 16:23
 * @apiNote
 */
@SpringBootTest
public class MapperTest {
    @Autowired
    SysUserMapper mapper;

    @Test
    public void TestA(){
        List<User> sysUsers = mapper.selectList(null);
        for (User sysUser : sysUsers) {
            System.out.println(sysUser);
        }
    }

    @Test
    public void TestB(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //对密码进行加密的方法
        String encode = encoder.encode("12345");
        System.out.println("加密后结果"+encode);

        //$2a$10$kf./kPwHBFaoFRlK54L6EO6ZWKCzLWrumKuNcSUmxVQefVqOdt60K
        //使用名文进行校验
        boolean matches = encoder.matches("12345", "$2a$10$kf./kPwHBFaoFRlK54L6EO6ZWKCzLWrumKuNcSUmxVQefVqOdt60K");
        System.out.println(matches);
    }
}
