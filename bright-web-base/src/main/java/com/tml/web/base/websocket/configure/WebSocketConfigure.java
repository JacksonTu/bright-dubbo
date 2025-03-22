package com.tml.web.base.websocket.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @author JacksonTu
 *  websocket配置
 * @date 2018/6/28 19:44
 */
@Configuration
public class WebSocketConfigure {
    /**
     * 打成war包,Tomcat运行时需注释掉
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
