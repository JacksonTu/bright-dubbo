package com.tml.api.base.system.service;


import com.tml.api.base.system.entity.SysRole;
import com.tml.api.base.system.vo.RoleVo;
import com.tml.common.core.base.service.IBaseService;

import java.util.List;
import java.util.Map;


/**
 * 角色表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface ISysRoleService extends IBaseService<SysRole> {

    /**
     * 多表信息查询
     *
     * @param param
     * @return
     */
    List<SysRole> selectSysRoleList(Map<String, Object> param);

    RoleVo selectByUserId(Long userId);

    RoleVo selectByRoleId(Long roleId);

    void saveByVo(SysRole role);

    void updateByVo(SysRole role);

    void deleteBatch(Long[] roleIds);
}

