package com.snowcattle.game.thread.factory;

import com.snowcattle.game.common.enums.BlockingQueueType;
import com.snowcattle.game.thread.policy.AbortPolicy;
import com.snowcattle.game.thread.policy.BlockingPolicy;
import com.snowcattle.game.thread.policy.CallerRunsPolicy;
import com.snowcattle.game.thread.policy.DiscardOldestPolicy;
import com.snowcattle.game.thread.policy.DiscardPolicy;
import com.snowcattle.game.thread.policy.RejectedPolicyType;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by jiangwenping on 2017/6/5. 游戏线程池辅助工厂
 */
public class GameThreadPoolHelpFactory {

	public RejectedExecutionHandler createPolicy(RejectedPolicyType rejectedPolicyType) {

		switch (rejectedPolicyType) {
			case BLOCKING_POLICY:
				return new BlockingPolicy();
			case CALLER_RUNS_POLICY:
				return new CallerRunsPolicy();
			case ABORT_POLICY:
				return new AbortPolicy();
			case DISCARD_POLICY:
				return new DiscardPolicy();
			case DISCARD_OLDEST_POLICY:
				return new DiscardOldestPolicy();
		}

		return null;
	}

	public RejectedExecutionHandler createPolicy(RejectedPolicyType rejectedPolicyType, String threadName) {

		switch (rejectedPolicyType) {
			case BLOCKING_POLICY:
				return new BlockingPolicy(threadName);
			case CALLER_RUNS_POLICY:
				return new CallerRunsPolicy(threadName);
			case ABORT_POLICY:
				return new AbortPolicy(threadName);
			case DISCARD_POLICY:
				return new DiscardPolicy(threadName);
			case DISCARD_OLDEST_POLICY:
				return new DiscardOldestPolicy(threadName);
		}

		return null;
	}

	public BlockingQueue<Runnable> createBlockingQueue(BlockingQueueType blockingQueueType, int queues) {

		switch (blockingQueueType) {
			case LINKED_BLOCKING_QUEUE:
				return new LinkedBlockingQueue<Runnable>();
			case ARRAY_BLOCKING_QUEUE:
				return new ArrayBlockingQueue<Runnable>(queues);
			case SYNCHRONOUS_QUEUE:
				return new SynchronousQueue<Runnable>();
		}

		return null;
	}
}

