package com.tml.common.core.props;

import com.tml.common.core.constant.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * * swagger2 属性配置
 * 必须配置 prefix ，才能有提示
 *
 * @author 1Clintel Team
 * @version 1.0.0
 * @date 2024/5/10 下午3:04
 */
@Data
@ConfigurationProperties(prefix = SwaggerProperties.PREFIX)
public class SwaggerProperties {
    public static final String PREFIX = AppConstant.PROJECT_PREFIX + ".swagger";

    /**
     * 标题
     **/
    private String title = AppConstant.PROJECT_PREFIX + "接口文档系统";
    /**
     * 描述
     **/
    private String description = AppConstant.PROJECT_PREFIX + "接口文档系统";
    /**
     * 版本
     **/
    private String version = AppConstant.APPLICATION_VERSION;
    /**
     * 许可证
     **/
    private String license = "Powered By TuMingLong";
    /**
     * 许可证URL
     **/
    private String licenseUrl = "";
    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl = "";
    /**
     * host信息
     **/
    private String host = "";
    /**
     * 联系人信息
     */
    private Contact contact = new Contact();
    /**
     * 全局统一鉴权配置
     **/
    private Authorization authorization = new Authorization();


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Authorization {
        /**
         * 鉴权策略ID，需要和SecurityReferences ID保持一致
         */
        private String name = "";

        /**
         * 需要开启鉴权URL的正则
         */
        private String authRegex = "^.*$";

        /**
         * 接口匹配地址
         */
        private List<String> tokenUrlList = new ArrayList<>();
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Contact {
        /**
         * 联系人
         **/
        private String name = "";
        /**
         * 联系人url
         **/
        private String url = "";
        /**
         * 联系人email
         **/
        private String email = "";
    }

}
