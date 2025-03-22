package com.tml.api.base.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.api.base.system.entity.SysUserEnterprise;

import java.util.List;

/**
 * 监管用户与企业关联表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface ISysUserEnterpriseService extends IService<SysUserEnterprise> {

    List<Long> selectEnterpriseIdByUserId(Long userId);

    /**
     * 保存或修改用户所监管的企业关系
     *
     * @param userId
     * @param enterpriseIdList
     */
    void saveOrUpdateUserEnterprise(Long userId, List<Long> enterpriseIdList);

    /**
     * 根据用户批量删除
     *
     * @param userIds
     */
    void deleteBatchByUserIds(Long[] userIds);

    /**
     * 根据企业批量删除
     *
     * @param enterpriseIds
     */
    void deleteBatchByEnterpriseIds(Long[] enterpriseIds);
}

