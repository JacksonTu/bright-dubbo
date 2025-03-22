package com.tml.web.base.interceptor;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  WebMVC 拦截器配置
 * @author JacksonTu
 * @date 2020/4/8 19:00
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(InterceptorProperties.class)
public class InterceptorWebConfigure implements WebMvcConfigurer {
    @Resource
    private InterceptorProperties interceptorProperties;

    @Resource
    private UploadInterceptor uploadInterceptor;

    public InterceptorWebConfigure() {
        log.debug("-----InterceptorWebConfig init-----");
    }

    /**
     * 注册自定义拦截器，添加拦截路径和排除拦截路径
     * 添加文件上传类型拦截器
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 上传拦截器
        if (interceptorProperties.getUpload().isEnabled()) {
            registry.addInterceptor(uploadInterceptor)
                    .addPathPatterns(interceptorProperties.getUpload().getIncludePaths());
        }
    }
}
