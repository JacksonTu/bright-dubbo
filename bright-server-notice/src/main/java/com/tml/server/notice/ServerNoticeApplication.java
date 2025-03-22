package com.tml.server.notice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @author JacksonTu
 *  Application
 * @date 2017年9月5日下午8:55:08
 */
@SpringBootApplication
@EnableDubbo
@MapperScan("com.tml.server.notice.mapper")
public class ServerNoticeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ServerNoticeApplication.class, args);
    }

}
