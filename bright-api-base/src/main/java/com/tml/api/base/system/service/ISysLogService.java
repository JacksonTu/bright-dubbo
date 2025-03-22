package com.tml.api.base.system.service;


import com.tml.api.base.system.entity.SysLog;
import com.tml.common.core.base.service.IBaseService;


/**
 * 系统日志表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface ISysLogService extends IBaseService<SysLog> {
    /**
     * 日志添加
     *
     * @param username    用户名
     * @param logContent  内容
     * @param logType     日志类型（0:操作日志，1：登录日志）
     * @param operateType 操作类型(1:添加,2:修改,3:删除)
     */
    void addLog(String username, String logContent, Integer logType, Integer operateType);


}

