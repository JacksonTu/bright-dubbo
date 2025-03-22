package com.tml.api.base.job.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.api.base.job.entity.ScheduleJobLogEntity;
import com.tml.common.core.vo.PageVo;


/**
 * 定时任务日志
 *
 * @author JacksonTu
 * @date 2019/1/18 15:59
 **/
public interface IScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageVo queryPage(com.tml.api.base.job.dto.JobLogDTO jobLogDto);

}
