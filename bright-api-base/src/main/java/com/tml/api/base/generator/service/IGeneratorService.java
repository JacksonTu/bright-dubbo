package com.tml.api.base.generator.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.api.base.generator.entity.TableEntity;
import com.tml.common.core.vo.PageVo;

import java.util.List;
import java.util.Map;

/**
 * @author JacksonTu
 * @version 1.0
 *
 * @date 2020/11/22 11:17
 */
public interface IGeneratorService extends IService<TableEntity> {

    PageVo<Map<String, Object>> pageList(com.tml.api.base.generator.dto.TableDTO tableDto);

    /**
     * 根据表名查询
     *
     * @param tableName
     * @return
     */
    Map<String, String> findByTableName(String tableName);

    /**
     * 根据表名查询
     *
     * @param tableName
     * @return
     */
    List<Map<String, String>> listByTableName(String tableName);

    /**
     * 生成代码
     *
     * @param tableNames
     * @return
     */
    byte[] generatorCode(String[] tableNames);
}
