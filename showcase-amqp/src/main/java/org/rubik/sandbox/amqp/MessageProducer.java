package org.rubik.sandbox.amqp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProducer {
	private static final int NUMBER_OF_POOL = 8;   // 线程数
	private static final int NUMBER_OF_WORKS = 8;  // 总任务数

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

	public static void main(String[] args) {
		multiThreadAndSynchronized();
	}

	// 多线程同步执行
	public static void multiThreadAndSynchronized() {
		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_POOL);
		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
			executorService.execute(new ProducerWorker());
		}
		executorService.shutdown();
	}

	//同步工作线程
	static class ProducerWorker implements Runnable {
		@Override
		public void run() {
			LOGGER.debug("{} Run...", Thread.currentThread().getName());
		}
	}
}
