package com.jiumai.base.common.core.manager;

import com.jiumai.base.common.core.utils.ThreadsUtils;
import org.springframework.context.annotation.Configuration;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 *
 * @author liuhulu
 */
@Configuration
public class AsyncManager {
    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private static ScheduledExecutorService executor = null;

    public AsyncManager(ScheduledExecutorService scheduledExecutorService) {
        this.executor = scheduledExecutorService;
    }

    /**
     * 单例模式
     */
    private AsyncManager() {
    }

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        ThreadsUtils.shutdownAndAwaitTermination(executor);
    }
}
