package com.tml.server.base.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tml.api.base.system.entity.SysDic;
import com.tml.api.base.system.vo.DicVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author JacksonTu
 * @date 2018-04-26
 */
public interface SysDicMapper extends BaseMapper<SysDic> {

    /**
     * 获取变量树表
     *
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