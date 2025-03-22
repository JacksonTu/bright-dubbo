package com.tml.common.redis.init;

import com.tml.common.redis.annotation.DelayQueueListener;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * @author 1Clintel Team
 * @version 1.0.0
 * 初始化延迟队列消费者
 * @date 2023/6/21 17:32
 */
@Slf4j
@Component
public class DelayQueueRunner implements CommandLineRunner {

    @Resource
    RedissonClient redisson;
    @Resource
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {

        Map<String, Method> methodMap = new ConcurrentHashMap<>(16);
        Map<String, Object> instanceMap = new ConcurrentHashMap<>(16);

        // 扫描带有@DelayQueueConsumer注解的bean和方法
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = context.getBean(beanName);
            Class<?> clazz = bean.getClass();
            if (clazz.isAnnotationPresent(Service.class) || clazz.isAnnotationPresent(Component.class)) {
                Method[] declaredMethods = clazz.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    if (method.isAnnotationPresent(DelayQueueListener.class)) {
                        DelayQueueListener annotation = method.getAnnotation(DelayQueueListener.class);
                        String name = annotation.name();
                        methodMap.put(name, method);
                        instanceMap.put(name, bean);
                    }
                }
            }
        }

        // 注册为延迟队列的消费者
        for (String name : methodMap.keySet()) {
            RBlockingQueue<Object> blockingQueue = redisson.getBlockingQueue(name);
            //服务重启后，无offer，take不到信息。
            RDelayedQueue<Object> delayedQueue = redisson.getDelayedQueue(blockingQueue);
            blockingQueue.subscribeOnElements(new Consumer<Object>() {
                @Override
                public void accept(Object o) {
                    log.info("监听队列线程，监听队列名称：{},内容:{}", name, o);
                    Method method = methodMap.get(name);
                    Object instance = instanceMap.get(name);
                    try {
                        method.invoke(instance, o);
                    } catch (Exception e) {
                        log.info("监听队列线程错误,", e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
}
