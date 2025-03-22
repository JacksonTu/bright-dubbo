package com.tml.server.base.enterprise.service.impl;


import com.tml.api.base.enterprise.entity.EnterpriseJob;
import com.tml.api.base.enterprise.service.IEnterpriseJobService;
import com.tml.common.core.base.service.impl.BaseServiceImpl;
import com.tml.server.base.enterprise.mapper.EnterpriseJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 企业职务配置表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:36:02
 */
@Slf4j
@DubboService
@Transactional(rollbackFor = Exception.class)
public class EnterpriseJobServiceImpl extends BaseServiceImpl<EnterpriseJobMapper, EnterpriseJob> implements IEnterpriseJobService {

    @Override
    public List<EnterpriseJob> selectEnterpriseJobList(Map<String, Object> par) {

        return this.baseMapper.selectEnterpriseJobList(par);
    }
}
