package com.tml.server.base.job.util;

import com.tml.api.base.job.entity.ScheduleJobEntity;
import com.tml.api.base.job.entity.ScheduleJobLogEntity;
import com.tml.api.base.job.service.IScheduleJobLogService;
import com.tml.common.core.utils.SpringUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.Future;


/**
 *  定时任务
 * @author JacksonTu
 * @date 2019/1/18 15:59
 **/
@Slf4j
public class ScheduleJob extends QuartzJobBean {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        dataMap.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        });
        //获取spring bean
        ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap().get(ScheduleJobEntity.JOB_PARAM_KEY);
        IScheduleJobLogService scheduleJobLogService = SpringUtil.getBean(IScheduleJobLogService.class);

        //数据库保存执行记录
        ScheduleJobLogEntity jobLog = new ScheduleJobLogEntity();
        jobLog.setJobId(scheduleJob.getJobId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            log.info("任务准备执行，任务ID：{}", scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = threadPoolTaskExecutor.submit(task);
            future.get();
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes((int) times);
            //任务状态    0：成功    1：失败
            jobLog.setStatus(0);
            log.info("任务执行完毕，任务ID：{}, 总共耗时：{} 毫秒", scheduleJob.getJobId(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：{}, message: {}" + scheduleJob.getJobId(), e.getMessage());
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes((int) times);
            //任务状态    0：成功    1：失败
            jobLog.setStatus(1);
            jobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.save(jobLog);
        }
    }
}
