package com.tml.api.base.enterprise.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.api.base.enterprise.dto.EnterpriseDepartmentDto;
import com.tml.api.base.enterprise.entity.EnterpriseDepartment;
import com.tml.api.base.enterprise.vo.EnterpriseDepartmentVo;

import java.util.List;
import java.util.Map;

/**
 * 企业部门表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:36:02
 */
public interface IEnterpriseDepartmentService extends IService<EnterpriseDepartment> {

    /**
     * 树表
     *
     * @param enterpriseDepartmentDto
     * @return
     */
    List<EnterpriseDepartmentVo> selectTreeGrid(EnterpriseDepartmentDto enterpriseDepartmentDto);

    /**
     * 自定义查询
     *
     * @param params
     * @return
     */
    List<EnterpriseDepartment> selectEnterpriseDepartmentList(Map<String, Object> params);
}

