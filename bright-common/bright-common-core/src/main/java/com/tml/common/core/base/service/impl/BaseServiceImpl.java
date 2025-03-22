package com.tml.common.core.base.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tml.common.core.base.dto.CommonDTO;
import com.tml.common.core.base.mapper.SuperMapper;
import com.tml.common.core.base.service.IBaseService;
import com.tml.common.core.vo.PageVo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author JacksonTu
 * @date 2019/11/7 14:35
 */
public abstract class BaseServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {


    @Override
    public PageVo pageList(CommonDTO commonDto) {
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(commonDto.getPage());
        // 设置页大小
        page.setSize(commonDto.getLimit());
        IPage iPage = this.baseMapper.pageList(page, commonDto);
        return new PageVo(iPage);

    }

    @Override
    public List<Map<String, Object>> selectMapList(CommonDTO commonDto) {
        return this.baseMapper.selectMapList(commonDto);
    }
}
