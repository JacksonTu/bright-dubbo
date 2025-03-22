package com.tml.server.base.system.mapper;

import com.tml.api.base.system.entity.SysUser;
import com.tml.common.core.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface SysUserMapper extends SuperMapper<SysUser> {

    SysUser selectByLoginName(@Param("loginName") String loginName);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId
     * @return
     */
    List<Long> selectResourceIdListByUserId(@Param("userId") Long userId);

    /**
     * 查询用户的所有权限
     *
     * @param userId
     * @return
     */
    List<String> selectPerms(@Param("userId") Long userId);

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    List<String> selectRoles(@Param("userId") Long userId);
}
