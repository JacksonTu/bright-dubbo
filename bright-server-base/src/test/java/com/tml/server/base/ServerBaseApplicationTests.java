package com.tml.server.base;


import com.google.common.collect.Maps;
import com.tml.server.base.generator.utils.GeneratorUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 *
 * @author JacksonTu
 * @date 2020/4/8 17:25
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ServerBaseApplicationTests {

    @Resource
    private GeneratorUtil generatorUtil;

    @Test
    public void testGenerator() {
        List<String> tableNames = Lists.newArrayList();
        tableNames.add("t_sys_user");
        generatorUtil.generatorCode(tableNames);
        log.info("测试");
    }


}
