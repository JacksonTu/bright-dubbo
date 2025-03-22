package com.tml.server.base.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tml.api.base.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户角色表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 根据用户ID查找用户角色ID集合
     *
     * @param userId
     * @return
     */
    @Select("select t.role_id from t_sys_user_role t where t.user_id=#{userId}")
    @ResultType(Long.class)
    List<Long> selectRoleIdListByUserId(@Param("userId") Long userId);

    /**
     * 根据用户批量删除
     *
     * @param userIds
     */
    void deleteBatchByUserIds(Long[] userIds);

    /**
     * 根据角色批量删除
     *
     * @param roleIds
     */
    void deleteBatchByRoleIds(Long[] roleIds);


}
