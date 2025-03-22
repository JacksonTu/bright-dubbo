package com.tml.api.base.system.service;

import com.tml.api.base.system.entity.SysFile;
import com.tml.common.core.base.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * 附件表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface ISysFileService extends IBaseService<SysFile> {

    /**
     * 通过tableId和recordId获取相关附件信息
     *
     * @param params
     * @return
     */
    List<SysFile> selectFileListByTableIdAndRecordId(Map<String, Object> params);

    /**
     * 删除文件
     *
     * @param tableId         主表名
     * @param recordId        主表ID
     * @param attachmentGroup 主表附件分组组名
     * @param attachmentName  文件名
     * @param attachmentPath  文件路径
     */
    void deleteFile(String tableId, String recordId, String attachmentGroup, String attachmentName, String attachmentPath);

}

