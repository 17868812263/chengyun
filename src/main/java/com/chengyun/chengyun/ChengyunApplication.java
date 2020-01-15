package com.chengyun.chengyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ChengyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChengyunApplication.class, args);
    }

}
