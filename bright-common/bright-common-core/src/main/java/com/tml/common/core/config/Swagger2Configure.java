package com.tml.common.core.config;

import cn.hutool.core.util.RandomUtil;
import com.tml.common.core.props.SwaggerProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author scott
 */
@EnableConfigurationProperties(SwaggerProperties.class)
public class Swagger2Configure {


    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_HEADER = "Token";
    private static final String TENANT_HEADER = "Tenant-Id";

    private final SwaggerProperties swaggerProperties;

    public Swagger2Configure(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    /**
     * 初始化GlobalOpenApiCustomizer对象
     */
    @Bean
    @ConditionalOnMissingBean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((s, pathItem) -> pathItem.readOperations().forEach(operation ->
                        operation.addSecurityItem(new SecurityRequirement()
                                .addList(AUTHORIZATION_HEADER)
                                .addList(TOKEN_HEADER)
                                .addList(TENANT_HEADER))));
            }
            if (openApi.getTags() != null) {
                openApi.getTags().forEach(tag -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("x-order", RandomUtil.randomInt(0, 100));
                    tag.setExtensions(map);
                });
            }
        };
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components()
                        // 添加安全策略，配置API密钥（Token）和鉴权机制
                        .addSecuritySchemes(TOKEN_HEADER,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .name(TOKEN_HEADER)
                        )
                        // 添加安全策略，配置API密钥（Authorization）和鉴权机制
                        .addSecuritySchemes(AUTHORIZATION_HEADER,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name(AUTHORIZATION_HEADER)
                        )
                        // 添加安全策略，配置租户ID（Tenant-Id）和鉴权机制
                        .addSecuritySchemes(TENANT_HEADER,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name(TENANT_HEADER)
                        )
                )
                // 设置API文档的基本信息，包括标题、描述、联系方式和许可信息
                .info(new Info()
                        .title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .version(swaggerProperties.getVersion())
                        .termsOfService(swaggerProperties.getTermsOfServiceUrl())
                        .contact(new Contact()
                                .name(swaggerProperties.getContact().getName())
                                .url(swaggerProperties.getContact().getUrl())
                                .email(swaggerProperties.getContact().getEmail()))
                        .license(new License()
                                .name(swaggerProperties.getLicense())
                                .url(swaggerProperties.getLicenseUrl())));
    }

}
