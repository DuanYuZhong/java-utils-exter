package com.duanyu.utils;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import com.duanyu.utils.exception.BusinessException;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Google的EventBus的简易封装
 */
public class EventHandler { 
    private EventHandler() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHandler.class);
    /** 带键值 */
    private static final Map<String, EventBus> FACTORY = new ConcurrentHashMap<>();

    private static final Map<String, AsyncEventBus> ASYNC_FACTORY = new ConcurrentHashMap<>();

    /** 异步执行 */
    private static final AsyncEventBus ASYNC_EVENT_BUS;

    /** 同步执行 */
    private static final EventBus DIRECT_EVENT = new EventBus();

    private static final ScheduledExecutorService executorService;

    static {
        int threadCount = Runtime.getRuntime().availableProcessors() * 2;
        executorService = new ScheduledThreadPoolExecutor(threadCount,
            new BasicThreadFactory.Builder().namingPattern("event-schedule-pool-%d").daemon(true).build());
        ASYNC_EVENT_BUS = new AsyncEventBus(executorService);
        EventHandler.register(new DeadlineEventListener());
    }

    public static void register(Object object) {
        DIRECT_EVENT.register(object);
    }

    public static void registerAsync(Object object) {
        ASYNC_EVENT_BUS.register(object);
    }

    public static void register(String routingKey, Object object) {
        EventBus eventBus = FACTORY.getOrDefault(routingKey, new EventBus());
        FACTORY.put(routingKey, eventBus);
        eventBus.register(object);
    }

    public static void registerAsync(String routingKey, Object object) {
        AsyncEventBus asyncEventBus = ASYNC_FACTORY.getOrDefault(routingKey, new AsyncEventBus(executorService));
        ASYNC_FACTORY.put(routingKey, asyncEventBus);
        asyncEventBus.register(object);
    }

    public static void post(Object object) {
        DIRECT_EVENT.post(object);
    }

    public static void postAsync(Object object) {
        ASYNC_EVENT_BUS.post(object);
    }

    public static void postAsync(String key, Object object) {
        AsyncEventBus asyncEventBus = ASYNC_FACTORY.get(key);
        checkEventBus(asyncEventBus);
        asyncEventBus.post(object);
    }

    public static void post(String routingKey, Object object) {
        EventBus eventBus = FACTORY.get(routingKey);
        checkEventBus(eventBus);
        eventBus.post(object);
    }

    private static void checkEventBus(EventBus eventBus) {
        if (Objects.isNull(eventBus)) {
            LOGGER.warn("routingKey invalid");
            throw new BusinessException("路由地址不正确");
        }
    }

    private static class DeadlineEventListener {

        @Subscribe
        public void listener(DeadEvent deadEvent) {
            LOGGER.warn("-----------> 使用EventBus时，没有被处理, {}", deadEvent.toString());
        }
    }
}
