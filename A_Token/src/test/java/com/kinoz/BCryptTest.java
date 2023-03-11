package com.kinoz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class BCryptTest {
    @Autowired
    BCryptPasswordEncoder bpe;

    @Test
    public void passwordTest(){

        //对密码进行加密的方法
        String encode = bpe.encode("12345");
        System.out.println("加密后结果"+encode);

        //$2a$10$kf./kPwHBFaoFRlK54L6EO6ZWKCzLWrumKuNcSUmxVQefVqOdt60K
        //使用名文进行校验
        boolean matches = bpe.matches("12345", "$2a$10$kf./kPwHBFaoFRlK54L6EO6ZWKCzLWrumKuNcSUmxVQefVqOdt60K");
        System.out.println(matches);
    }
}
