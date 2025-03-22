package com.tml.server.base.enterprise.mapper;

import com.tml.api.base.enterprise.entity.EnterpriseJob;
import com.tml.common.core.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 企业职务配置表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:36:02
 */
public interface EnterpriseJobMapper extends SuperMapper<EnterpriseJob> {

    /**
     * 自定义查询
     *
     * @param params
     * @return
     */
    List<EnterpriseJob> selectEnterpriseJobList(@Param("ew") Map<String, Object> params);
}
