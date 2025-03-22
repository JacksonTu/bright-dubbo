package com.tml.common.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

/**
 * 异步和定时任务线程池配置
 * @author JacksonTu
 * @version 1.0
 * @date 2020/11/9 21:22
 */
@Slf4j
@Configuration
@EnableScheduling
@EnableAsync
public class ThreadPoolConfigure implements AsyncConfigurer, SchedulingConfigurer {

    /**
     * 配置线程池
     * 对于CPU密集型任务，最大线程数是CPU线程数+1。
     * 对于IO密集型任务，尽量多配点，可以是CPU线程数*2，或者CPU线程数/(1-阻塞系数)。
     * maxPoolSize=(int) (Runtime.getRuntime().availableProcessors()/(1-0.9))
     *
     * @return ExecutorService
     */
    @Bean
    public ExecutorService getThreadPool() {
        return new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                (int) (Runtime.getRuntime().availableProcessors() / (1 - 0.9)),
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(Runtime.getRuntime().availableProcessors()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 异步任务
     * 对于CPU密集型任务，最大线程数是CPU线程数+1。
     * 对于IO密集型任务，尽量多配点，可以是CPU线程数*2，或者CPU线程数/(1-阻塞系数)。
     * maxPoolSize=(int) (Runtime.getRuntime().availableProcessors()/(1-0.9))
     *
     * @return Executor
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        asyncTaskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        // 设置最大线程数
        asyncTaskExecutor.setMaxPoolSize((int) (Runtime.getRuntime().availableProcessors() / (1 - 0.9)));
        // 设置队列容量
        asyncTaskExecutor.setQueueCapacity(Runtime.getRuntime().availableProcessors());
        // 设置线程活跃时间（秒）
        asyncTaskExecutor.setKeepAliveSeconds(60);
        asyncTaskExecutor.setAwaitTerminationSeconds(60);
        // 设置默认线程名称
        asyncTaskExecutor.setThreadNamePrefix("asyncExecutor-");
        // 设置拒绝策略为使用当前线程执行
        asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        asyncTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }


    /**
     * 定时任务
     *
     * @return ThreadPoolTaskScheduler
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors());
        taskScheduler.setThreadNamePrefix("taskScheduler-");
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        taskScheduler.setAwaitTerminationSeconds(60);
        return taskScheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler());
    }
}
