package com.tml.common.redis.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author 1Clintel Team
 * @version 1.0.0
 * 延时队列模板
 * @date 2023/6/21 19:56
 */
@RequiredArgsConstructor
public class DelayQueueTemplate<T> {

    private final RedissonClient redisson;

    /**
     * 发送延迟消息
     *
     * @param queueName
     * @param message
     * @param delayTime
     * @param timeUnit
     */
    public void sendDelayMessage(String queueName, T message, long delayTime, TimeUnit timeUnit) {
        // 创建阻塞队列
        RBlockingQueue<Object> blockingQueue = redisson.getBlockingQueue(queueName);
        // 创建延迟队列包装器
        RDelayedQueue<Object> delayedQueue = redisson.getDelayedQueue(blockingQueue);
        delayedQueue.offer(message, delayTime, timeUnit);
    }

    /**
     * 移除延迟队列中消息
     *
     * @param queueName
     * @param message
     */
    public void removeDelayMessage(String queueName, T message) {
        // 创建阻塞队列
        RBlockingQueue<Object> blockingQueue = redisson.getBlockingQueue(queueName);
        // 创建延迟队列包装器
        RDelayedQueue<Object> delayedQueue = redisson.getDelayedQueue(blockingQueue);
        // 删除延迟队列中的消息
        delayedQueue.remove(message);
    }
}
