package com.kinoz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kinoz
 * @Date 2023/3/8 16:16
 * @apiNote
 */
@SpringBootApplication
@MapperScan("com.kinoz.mapper")
public class tokenStart {
    public static void main(String[] args) {
        SpringApplication.run(tokenStart.class);
    }
}
