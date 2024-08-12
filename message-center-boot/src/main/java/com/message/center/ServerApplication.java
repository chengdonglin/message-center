package com.message.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author linchengdong
 * @Date 2024-08-12 14:00
 * @PackageName:com.message.center
 * @ClassName: ServerApplication
 * @Description: TODO
 * @Version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.message.center.infrastructure.**.mapper")
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class,args);
    }
}
