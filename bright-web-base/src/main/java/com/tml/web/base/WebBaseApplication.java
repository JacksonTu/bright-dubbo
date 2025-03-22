package com.tml.web.base;

import com.bluemiaomiao.annotation.EnableFastdfsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author JacksonTu
 *  Application
 * @date 2017年9月5日下午8:55:08
 */
@SpringBootApplication
@EnableFastdfsClient
public class WebBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBaseApplication.class, args);
    }
}
