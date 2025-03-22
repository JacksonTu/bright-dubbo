package com.tml.server.base.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tml.api.base.system.entity.SysUserEnterprise;
import com.tml.api.base.system.service.ISysUserEnterpriseService;
import com.tml.server.base.system.mapper.SysUserEnterpriseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 监管用户与企业关联表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Slf4j
@DubboService
@Transactional(rollbackFor = Exception.class)
public class SysUserEnterpriseServiceImpl extends ServiceImpl<SysUserEnterpriseMapper, SysUserEnterprise> implements ISysUserEnterpriseService {

    @Override
    public List<Long> selectEnterpriseIdByUserId(Long userId) {
        return this.baseMapper.selectEnterpriseIdByUserId(userId);
    }

    @Override
    public void saveOrUpdateUserEnterprise(Long userId, List<Long> enterpriseIdList) {
        //先删除企业与用户关系
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        this.removeByMap(params);
        if (enterpriseIdList == null || enterpriseIdList.size() == 0) {
            return;
        }

        //保存用户与企业关系
        List<SysUserEnterprise> list = new ArrayList<>(enterpriseIdList.size());
        for (Long enterpriseId : enterpriseIdList) {
            SysUserEnterprise sysUserEnterprise = new SysUserEnterprise();
            sysUserEnterprise.setUserId(userId);
            sysUserEnterprise.setEnterpriseId(enterpriseId);
            list.add(sysUserEnterprise);
        }
        this.saveBatch(list);
    }

    @Override
    public void deleteBatchByUserIds(Long[] userIds) {
        this.baseMapper.deleteBatchByUserIds(userIds);
    }

    @Override
    public void deleteBatchByEnterpriseIds(Long[] enterpriseIds) {
        this.baseMapper.deleteBatchByEnterpriseIds(enterpriseIds);
    }
}
