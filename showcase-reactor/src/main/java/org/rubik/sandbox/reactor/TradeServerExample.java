package org.rubik.sandbox.reactor;

import org.rubik.sandbox.reactor.domain.Trade;
import org.rubik.sandbox.reactor.domain.TradeServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.core.dispatch.RingBufferDispatcher;

import java.util.concurrent.CountDownLatch;

import static reactor.bus.selector.Selectors.$;

public class TradeServerExample {

	private static final Logger LOG         = LoggerFactory.getLogger(TradeServerExample.class);
	private static       int    totalTrades = 10000000;

	private static CountDownLatch latch;
	private static long           startTime;

	public static void mainx(String[] args) throws InterruptedException {
		String topic = "trade.execute";
		final TradeServer server = new TradeServer();
		EventBus eventBus = EventBus.create(new RingBufferDispatcher("TRADE"));

		startTimer();

		eventBus.<Event<Trade>>on($(topic), event -> {
			server.execute(event.getData());
			latch.countDown();
		});

		for (int i = 0; i < totalTrades; i++) {
			Trade trade = server.nextTrade();
			eventBus.notify(topic, Event.wrap(trade));
		}

		endTimer();

		server.stop();
	}

	private static void startTimer() {
		LOG.info("开始对{}笔交易进行吞吐量测试...", totalTrades);
		latch = new CountDownLatch(totalTrades);
		startTime = System.currentTimeMillis();
	}

	private static void endTimer() throws InterruptedException {
//		latch.await(30, TimeUnit.SECONDS);
		latch.await();
		long endTime = System.currentTimeMillis();
		double elapsed = endTime - startTime;
		double throughput = totalTrades / (elapsed / 1000);

		LOG.info("TPS:{}, 耗时:{}ms", (int)throughput, (int)elapsed);
	}

}
