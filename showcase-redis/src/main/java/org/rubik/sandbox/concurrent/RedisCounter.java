package org.rubik.sandbox.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

@Component
public class RedisCounter {

	@Autowired
	private TaskExecutor taskExecutor;

//	@Autowired
//	private RedisTemplate redisTemplate;
	
	@Autowired
	private RedisAtomicLong counter;

	// 多线程同步执行
	public void multiThreadAndSynchronized() {
//		for (int i = 0; i < 1000; i++) {
//			taskExecutor.execute(new RedisWorker(redisTemplate));
//		}

		for (int i = 0; i < 1000; i++) {
			taskExecutor.execute(new RedisWorker(counter));
		}
	}

//	// 多线程异步执行，但获取结果会导致阻塞
//	public static void multiThreadAndAsynchronized() {
//		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_POOL);
//		List<Future<String>> result = Lists.newArrayList();
//		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
//			Future<String> response = executorService.submit(new AnsyncWorker());
//			result.add(response);
//		}
//
//		// block
//		for (Future<String> future : result) {
//			try {
//				System.out.println(future.get());
//			} catch (InterruptedException | ExecutionException e) {
//				Throwables.propagate(e);
//			}
//		}
//		executorService.shutdown();
//	}
//
//	// 多线程异步执行
//	public static void multiThreadAndAllAsynchronized() {
//		ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(NUMBER_OF_POOL));
//		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
//			ListenableFuture<String> listenableFuture = listeningExecutorService.submit(new AnsyncWorker());
//			Futures.addCallback(listenableFuture, new FutureCallback<String>() {
//				@Override
//				public void onSuccess(String result) {
//					System.out.println(result);
//				}
//				
//				@Override
//				public void onFailure(Throwable t) {
//					System.out.println(Throwables.getRootCause(t));
//				}
//			});
//		}
//
//		listeningExecutorService.shutdown();
//	}
//
//	// 调度多线程异步执行
//	public static void multiThreadScheduleAndAllAsynchronized() {
//		ListeningScheduledExecutorService listeningScheduledExecutorService = MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(NUMBER_OF_POOL));
//		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
//			// 延迟100毫秒启动调度
//			ListenableScheduledFuture<String> listenableFuture = listeningScheduledExecutorService.schedule(new AnsyncWorker(), 100, TimeUnit.MILLISECONDS);
//			Futures.addCallback(listenableFuture, new FutureCallback<String>() {
//				@Override
//				public void onSuccess(String result) {
//					System.out.println(result);
//				}
//
//				@Override
//				public void onFailure(Throwable t) {
//					System.out.println(Throwables.getRootCause(t));
//				}
//			});
//		}
//
//		listeningScheduledExecutorService.shutdown();
//	}
//
//	// 调度多线程同步执行
//	public static void multiThreadScheduleAndSynchronized() {
//		ListeningScheduledExecutorService listeningScheduledExecutorService = MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(NUMBER_OF_POOL));
//		// 延迟100毫秒启动调度，每个线程执行间隔为10毫秒
//		listeningScheduledExecutorService.scheduleAtFixedRate(new Worker(), 100, 100, TimeUnit.MILLISECONDS);
//
//		try {
//			Thread.sleep(1200);
//		} catch (InterruptedException e) {
//			Throwables.getRootCause(e);
//		}
//		listeningScheduledExecutorService.shutdown();
//	}
}
