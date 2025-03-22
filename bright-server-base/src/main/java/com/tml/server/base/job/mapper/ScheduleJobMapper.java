package com.tml.server.base.job.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tml.api.base.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 *  定时任务
 * @author JacksonTu
 * @date 2018/12/13 10:44
 */
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
