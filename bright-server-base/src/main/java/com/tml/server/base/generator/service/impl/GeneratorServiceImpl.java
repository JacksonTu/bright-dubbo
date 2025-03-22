package com.tml.server.base.generator.service.impl;

import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tml.api.base.generator.entity.TableEntity;
import com.tml.api.base.generator.service.IGeneratorService;
import com.tml.common.core.vo.PageVo;
import com.tml.server.base.generator.mapper.GeneratorMapper;
import com.tml.server.base.generator.utils.GeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author JacksonTu
 * @version 1.0
 *
 * @date 2020/11/22 11:40
 */
@DubboService
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GeneratorServiceImpl extends ServiceImpl<GeneratorMapper, TableEntity> implements IGeneratorService {

    private final GeneratorUtil generatorUtil;

    @Override
    public PageVo<Map<String, Object>> pageList(com.tml.api.base.generator.dto.TableDTO tableDto) {
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(tableDto.getPage());
        // 设置页大小
        page.setSize(tableDto.getLimit());

        return new PageVo<>(this.baseMapper.pageList(page, tableDto));
    }

    @Override
    public Map<String, String> findByTableName(String tableName) {
        return this.baseMapper.findByTableName(tableName);
    }

    @Override
    public List<Map<String, String>> listByTableName(String tableName) {
        return this.baseMapper.listByTableName(tableName);
    }

    @Override
    public boolean generatorCode(String[] tableNames) {

        generatorUtil.generatorCode(Lists.newArrayList(tableNames));

        return true;
    }
}
