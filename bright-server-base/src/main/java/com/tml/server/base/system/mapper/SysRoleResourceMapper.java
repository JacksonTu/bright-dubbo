package com.tml.server.base.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tml.api.base.system.entity.SysRoleResource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

;


/**
 * 角色资源表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    /**
     * 根据角色查找菜单ID集合
     *
     * @param roleId
     * @return
     */
    @Select("select t.resource_id from t_sys_role_resource t where t.role_id=#{roleId}")
    @ResultType(Long.class)
    List<Long> selectResourceIdListByRoleId(@Param("roleId") Long roleId);

    void deleteBatch(Long[] roleIds);

    /**
     * 自定义查询
     *
     * @param params
     * @return
     */
    List<SysRoleResource> selectResourceList(@Param("params") Map<String, Object> params);

    /**
     * 查询选中node
     *
     * @param roleId
     * @return
     */
    List<SysRoleResource> selectResourceNodeListByRoleId(@Param("roleId") Long roleId);
}
