package com.tml.monitor.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author 1Clintel Team
 * @version 1.0.0
 * @date 2025/3/22 09:14
 */
@Configuration
public class SecuritySecureConfig {

    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    /**
     * 增加 springboot 服务端安全验证 解决客户端注册报401
     * <p>
     * 注释必须登录才能进入使用
     *
     * Bean  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     * return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll())
     * .csrf().disable().build();
     * }
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requestMatcherRegistry) -> requestMatcherRegistry.anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * 客户端注册直接放过 解决服务端增加登录后客户端无法注册一直报401问题
     *
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(adminContextPath + "/instances", adminContextPath + "/actuator/**");
    }
}
