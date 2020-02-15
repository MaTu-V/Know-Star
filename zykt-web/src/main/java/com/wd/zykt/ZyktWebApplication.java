package com.wd.zykt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.UUID;

@SpringBootApplication
@MapperScan("com.wd.zykt.mapper")
@EnableCaching
@EnableAsync
public class ZyktWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZyktWebApplication.class, args);
    }

}
