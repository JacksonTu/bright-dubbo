package com.tml.server.notice;

import com.tml.api.base.enterprise.entity.Enterprise;
import com.tml.api.base.enterprise.service.IEnterpriseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 *
 * @author JacksonTu
 * @date 2020/4/8 17:25
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ServerNoticeApplicationTests {

    @DubboReference
    private IEnterpriseService enterpriseService;

    @Test
    public void testEnterprise(){
        List<Enterprise> list=enterpriseService.list();
        if(list!=null && list.size()>0){
            list.forEach(enterprise -> {
                System.out.println(enterprise.toString());
            });
        }
    }





}
