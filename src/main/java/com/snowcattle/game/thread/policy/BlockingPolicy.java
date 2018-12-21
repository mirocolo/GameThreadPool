package com.snowcattle.game.thread.policy;

import com.snowcattle.game.common.constants.Loggers;

import org.slf4j.Logger;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by jwp on 2017/3/8.
 * 阻塞
 */

public class BlockingPolicy implements RejectedExecutionHandler {
    private static final Logger logger = Loggers.threadLogger;

    private String threadName;

    public BlockingPolicy() {
        this(null);
    }

    public BlockingPolicy(String threadName) {
        this.threadName = threadName;
    }

	@Override
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
		if (threadName != null) {
			logger.error("Thread pool [{}] is exhausted, executor={}", threadName, executor.toString());
		}

		if (!executor.isShutdown()) {
			try {
				executor.getQueue().put(runnable);
			} catch (InterruptedException e) {
			}
		}
	}
}