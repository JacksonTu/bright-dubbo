package com.tml.api.base.job.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.api.base.job.entity.ScheduleJobEntity;
import com.tml.common.core.vo.PageVo;

/**
 *  定时任务
 * @author JacksonTu
 * @date 2019/1/18 15:59
 **/
public interface IScheduleJobService extends IService<ScheduleJobEntity> {

    PageVo queryPage(com.tml.api.base.job.dto.JobDTO jobDto);

    /**
     * 保存定时任务
     */
    void insert(ScheduleJobEntity scheduleJob);

    /**
     * 更新定时任务
     */
    void update(ScheduleJobEntity scheduleJob);

    /**
     * 批量删除定时任务
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     */
    void resume(Long[] jobIds);
}
