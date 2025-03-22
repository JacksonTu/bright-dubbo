package com.tml.common.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 1Clintel Team
 * @version 1.0.0
 * 延迟队列消费端注解
 * @date 2023/6/21 17:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DelayQueueListener {
    /**
     * 延迟队列名称
     *
     * @return
     */
    String name();
}
