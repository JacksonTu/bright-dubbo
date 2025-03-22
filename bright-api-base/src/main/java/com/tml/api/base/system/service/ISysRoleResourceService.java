package com.tml.api.base.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.api.base.system.entity.SysRoleResource;

import java.util.List;
import java.util.Map;


/**
 * 角色资源表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface ISysRoleResourceService extends IService<SysRoleResource> {

    /**
     * 根据角色查找菜单ID集合
     *
     * @param roleId
     * @return
     */
    List<Long> selectResourceIdListByRoleId(Long roleId);

    void saveOrUpdateRoleResource(Long roleId, List<Long> resourceIdList);

    void deleteBatch(Long[] roleIds);

    /**
     * 自定义查询
     *
     * @param params
     * @return
     */
    List<SysRoleResource> selectResourceList(Map<String, Object> params);

    /**
     * 查询选中node
     *
     * @param roleId
     * @return
     */
    List<SysRoleResource> selectResourceNodeListByRoleId(Long roleId);
}

