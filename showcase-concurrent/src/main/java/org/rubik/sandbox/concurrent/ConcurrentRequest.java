package org.rubik.sandbox.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ConcurrentRequest {
	private static final int NUMBER_OF_POOL = 1000;   // 线程数
	private static final int NUMBER_OF_WORKS = 2000;  // 总任务数
	private static AtomicInteger counter = new AtomicInteger(0);

	public static void main(String[] args) {
		concurrentCounter();

//		singleThreadAndSynchronized();
		
//		multiThreadAndSynchronized();
//		
//		multiThreadAndAsynchronized();
//		
//		multiThreadAndAllAsynchronized();
//		
//		multiThreadScheduleAndAllAsynchronized();
//		
//		multiThreadScheduleAndSynchronized();
	}

	public static void concurrentCounter() {
		for (int i = 0; i < NUMBER_OF_POOL; i++) {
			new Thread(new Counter(counter)).start();
		}

//		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_POOL);
//		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
//			executorService.execute(new Counter(counter));
//		}
//		executorService.shutdown();
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO
		}

		System.out.println(counter.get());
	}

	// 单线程同步执行
	public static void singleThreadAndSynchronized() {
		for (int i = 0; i < NUMBER_OF_POOL; i++) {
			new Thread(new Worker()).start();
//			new Worker().run();
		}
	}
	
	// 多线程同步执行
	public static void multiThreadAndSynchronized() {
		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_POOL);
		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
			executorService.execute(new Worker());
		}
		executorService.shutdown();
	}

	// 多线程异步执行，但获取结果会导致阻塞
	public static void multiThreadAndAsynchronized() {
		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_POOL);
		List<Future<String>> result = Lists.newArrayList();
		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
			Future<String> response = executorService.submit(new AnsyncWorker());
			result.add(response);
		}

		// block
		for (Future<String> future : result) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				Throwables.propagate(e);
			}
		}
		executorService.shutdown();
	}

	// 多线程异步执行
	public static void multiThreadAndAllAsynchronized() {
		ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(NUMBER_OF_POOL));
		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
			ListenableFuture<String> listenableFuture = listeningExecutorService.submit(new AnsyncWorker());
			Futures.addCallback(listenableFuture, new FutureCallback<String>() {
				@Override
				public void onSuccess(String result) {
					System.out.println(result);
				}
				
				@Override
				public void onFailure(Throwable t) {
					System.out.println(Throwables.getRootCause(t));
				}
			});
		}

		listeningExecutorService.shutdown();
	}

	// 调度多线程异步执行
	public static void multiThreadScheduleAndAllAsynchronized() {
		ListeningScheduledExecutorService listeningScheduledExecutorService = MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(NUMBER_OF_POOL));
		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
			// 延迟100毫秒启动调度
			ListenableScheduledFuture<String> listenableFuture = listeningScheduledExecutorService.schedule(new AnsyncWorker(), 100, TimeUnit.MILLISECONDS);
			Futures.addCallback(listenableFuture, new FutureCallback<String>() {
				@Override
				public void onSuccess(String result) {
					System.out.println(result);
				}

				@Override
				public void onFailure(Throwable t) {
					System.out.println(Throwables.getRootCause(t));
				}
			});
		}

		listeningScheduledExecutorService.shutdown();
	}

	// 调度多线程同步执行
	public static void multiThreadScheduleAndSynchronized() {
		ListeningScheduledExecutorService listeningScheduledExecutorService = MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(NUMBER_OF_POOL));
		// 延迟100毫秒启动调度，每个线程执行间隔为10毫秒
		listeningScheduledExecutorService.scheduleAtFixedRate(new Worker(), 100, 100, TimeUnit.MILLISECONDS);

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			Throwables.getRootCause(e);
		}
		listeningScheduledExecutorService.shutdown();
	}
}

// 计数器线程
class Counter implements Runnable {
	private AtomicInteger counter;
	public Counter(AtomicInteger counter) {
		this.counter = counter;
	}
	@Override
	public void run() {
		counter.getAndIncrement();
		System.out.println(Thread.currentThread().getName() + " Run...");
	}
}

// 同步工作线程
class Worker implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Run...");
	}
}

//异步工作线程
class AnsyncWorker implements Callable<String> {
	@Override
	public String call() throws Exception {
		return Thread.currentThread().getName() + " Run...";
	}
}
