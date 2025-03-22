package com.tml.api.base.system.service;

import com.tml.api.base.system.entity.SysUser;
import com.tml.common.core.base.service.IBaseService;
import com.tml.common.core.vo.LoginUserVo;

import java.util.List;
import java.util.Set;

/**
 * 用户表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface ISysUserService extends IBaseService<SysUser> {

    /**
     * 根据用户登录名查询用户信息
     *
     * @param loginName
     * @return
     */
    SysUser selectByLoginName(String loginName);

    /**
     * 根据用户登录名查询登录用户信息
     *
     * @param loginName
     * @return
     */
    LoginUserVo selectLoginUserVoByLoginName(String loginName);

    LoginUserVo selectLoginUserVoByLoginId(Long loginId);

    void saveByVo(SysUser user);

    void updateByVo(SysUser user);

    /**
     * 根据用户批量删除
     *
     * @param userIds
     */
    void deleteBatch(Long[] userIds);

    /**
     * 获取用户权限列表
     */
    Set<String> selectUserPermissions(long userId);

    /**
     * 获取用户角色名称
     */
    Set<String> selectUserRoles(long userId);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId
     * @return
     */
    List<Long> selectResourceIdListByUserId(Long userId);
}

