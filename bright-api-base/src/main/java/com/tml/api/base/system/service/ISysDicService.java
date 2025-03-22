package com.tml.api.base.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.api.base.system.entity.SysDic;
import com.tml.api.base.system.vo.DicVo;

import java.util.List;
import java.util.Map;

/**
 * 数据字典表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface ISysDicService extends IService<SysDic> {

    /**
     * 获取变量树表
     *
     * @param par
     * @return
     */
    List<DicVo> selectTreeGrid(Map<String, Object> par);

    /**
     * 自定义查询
     *
     * @param params
     * @return
     */
    List<SysDic> selectDicList(Map<String, Object> params);

    /**
     * 根据父ID获取变量
     *
     * @param par
     * @return
     */
    List<Map<String, Object>> selectTreeByParentId(Map<String, Object> par);


}

