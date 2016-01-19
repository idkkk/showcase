package org.rubik.sandbox.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class SingleThreadApp {

	public static void main(String[] args) {
		doA();
		doB();
	}

	@SuppressWarnings("unchecked")
	public static void doA() {
		int numCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executorService = Executors.newFixedThreadPool(numCores);
		Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, 2048, executorService);
		disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println(String.format("Sequence:%d", sequence)))
							.then((event, sequence, endOfBatch) -> System.out.println(String.format("Event:%d", event.getValue())));
//		disruptor.handleEventsWithWorkerPool(event -> System.out.println("消费线程1: " + event.getValue()), event -> System.out.println("消费线程2: " + event.getValue()));
		RingBuffer<LongEvent> ringBuffer = disruptor.start();
		long start = System.currentTimeMillis();
		for (long i = 100; i < 105; i++) {
			long seq = ringBuffer.next();
			LongEvent longEvent = ringBuffer.get(seq);
			longEvent.setValue(i);
			ringBuffer.publish(seq);
		}
		System.out.println(String.format("Time: %dms", System.currentTimeMillis() - start));
		disruptor.shutdown();
		executorService.shutdown();
	}

	public static void doB() {
		long start = System.currentTimeMillis();
		for (long i = 0; i < 10000000; i++) {
			i++;
		}
		System.out.println(String.format("Time: %dms", System.currentTimeMillis() - start));
	}
}
