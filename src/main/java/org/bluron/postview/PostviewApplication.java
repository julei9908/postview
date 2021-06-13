package org.bluron.postview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan(basePackages={"org.bluron.postview.dao"})
@SpringBootApplication
public class PostviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostviewApplication.class, args);
    }

}
