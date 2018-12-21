package com.snowcattle.game.thread.policy;

import com.snowcattle.game.common.constants.Loggers;

import org.slf4j.Logger;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by jwp on 2017/3/8.
 */
public class DiscardPolicy extends ThreadPoolExecutor.DiscardPolicy {
    private static final Logger logger = Loggers.threadLogger;

    private final String threadName;

    public DiscardPolicy() {
        this(null);
    }

    public DiscardPolicy(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        if (threadName != null) {
            logger.error("hread pool [{}] is exhausted, executor={}", threadName, executor.toString());
        }

        super.rejectedExecution(runnable, executor);
    }
}