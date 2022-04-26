package org.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("org.com.dao")
public class SpringBoot_Redis_SMSProjectDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot_Redis_SMSProjectDemoApplication.class);
    }
}
