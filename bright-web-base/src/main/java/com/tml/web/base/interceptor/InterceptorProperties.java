package com.tml.web.base.interceptor;

import com.tml.common.core.constant.AppConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

/**
 *  拦截器属性
 * @author JacksonTu
 * @date 2019/11/1 14:18
 */
@Data
@ConfigurationProperties(prefix = InterceptorProperties.PREFIX)
public class InterceptorProperties {

    public static final String PREFIX = AppConstant.PROJECT_PREFIX + ".interceptor";

    /**
     * 上传拦截器
     */
    @NestedConfigurationProperty
    private InterceptorConfig upload = new InterceptorConfig();

    @Data
    public static class InterceptorConfig {

        /**
         * 是否启用
         */
        private boolean enabled;

        /**
         * 排除路径
         */
        private String[] excludePaths;

        /**
         * 包含的路径
         */
        private String[] includePaths;

        /**
         * 允许上传下载的文件后缀集合
         */
        private List<String> allowFileExtensions;

    }
}
