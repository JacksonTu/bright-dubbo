package com.tml.server.base.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tml.api.base.system.entity.SysResource;

import java.util.List;
import java.util.Map;

/**
 * 资源表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResource> selectResourceList(Map<String, Object> params);

}
