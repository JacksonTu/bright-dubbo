package com.tml.server.base.generator.utils;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDatasourceAopProperties;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.config.configcenter.DynamicConfigurationFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * 代码生成器工具类
 *
 * @author JacksonTu
 * @email tuminglong@126.com
 * @date 2018/12/21 14:17
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class GeneratorUtil {

     private final DynamicDataSourceProperties dynamicDataSourceProperties;

    /**
     * 生成代码
     */
    public void generatorCode(List<String> tableNames) {

        String password=dynamicDataSourceProperties.getDatasource().get("master").getPassword();
        String username=dynamicDataSourceProperties.getDatasource().get("master").getUsername();
        String url=dynamicDataSourceProperties.getDatasource().get("master").getUrl();
        log.info("url:{},username:{},password:{}",url,username,password);

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder
                        .author("JacksonTu")
                        .outputDir("D://opt//generator")
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder
                        .parent("com.tml.server.base")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                        .controller("controller")
                )
                .strategyConfig(builder -> builder
                        .addInclude(tableNames)
                        .addTablePrefix("t_") // 增加过滤表前缀
                        .entityBuilder()
                        .enableLombok()
                        .javaTemplate("/templates/dubbo/Entity.java.vm") // 设置实体类模板
                        .serviceBuilder()
                        .serviceTemplate("/templates/dubbo/Service.java.vm") // 设置 Service 模板
                        .serviceImplTemplate("/templates/dubbo/ServiceImpl.java.vm") // 设置 ServiceImpl 模板
                        .mapperBuilder()
                        .mapperTemplate("/templates/dubbo/Mapper.java.vm") // 设置 Mapper 模板
                        .mapperXmlTemplate("/templates/dubbo/Mapper.xml.vm") // 设置 Mapper XML 模板
                        .controllerBuilder()
                        .template("/templates/dubbo/Controller.java.vm") // 设置 Controller 模板
                )
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
