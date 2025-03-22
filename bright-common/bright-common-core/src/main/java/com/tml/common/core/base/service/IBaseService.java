package com.tml.common.core.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.common.core.base.dto.CommonDTO;
import com.tml.common.core.vo.PageVo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author JacksonTu
 * @date 2019/11/7 14:34
 */
public interface IBaseService<T> extends IService<T> {

    /**
     * 自定义分页
     *
     * @param commonDto
     * @return
     */
    PageVo pageList(CommonDTO commonDto);

    /**
     * 自定义查询
     *
     * @param commonDto
     * @return
     */
    List<Map<String, Object>> selectMapList(CommonDTO commonDto);
}
