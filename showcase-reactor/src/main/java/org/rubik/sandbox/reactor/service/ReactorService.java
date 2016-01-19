package org.rubik.sandbox.reactor.service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.rubik.sandbox.reactor.domain.Order;
import org.rubik.sandbox.reactor.domain.Trade;
import org.rubik.sandbox.reactor.domain.Type;
import org.rubik.sandbox.reactor.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

import reactor.bus.Event;
import reactor.bus.EventBus;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 逻辑处理类.
 * 
 * @author xiajinxin
 *
 */
@Service
public class ReactorService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReactorService.class);

	private static ExecutorService rxJavaExecutorService = Executors.newFixedThreadPool(4);

//	private static ExecutorService jdk8ExecutorService = Executors.newFixedThreadPool(4);

	// ======================================================  public method

	@Autowired
	private EventBus eventBus;

	public void run() {
		eventBus.notify("test.topic", Event.wrap("Hello World!"));
	}

	@Autowired
	private AsyncTaskExecutor asyncTaskExecutor;

	// sync
	public Order findAll() {
		return generateOrder();
	}

	// async (rxjava)
	public Observable<Order> findAllAsync() {
		return Observable.<Order>create(subscriber -> {
//			LOGGER.debug("开始执行长时间的业务处理...");

			subscriber.onNext(generateOrder());

//			LOGGER.debug("结束执行长时间的业务处理...");

			subscriber.onCompleted();
		}).subscribeOn(Schedulers.from(rxJavaExecutorService));
	}

	// async (jdk8)
	public CompletableFuture<Order> findAllJavaAsync() {
		return CompletableFuture.supplyAsync(() -> {
//			LOGGER.debug("开始执行长时间的业务处理...");

//			LOGGER.debug("结束执行长时间的业务处理...");

			return generateOrder();
		}, asyncTaskExecutor);
	}

	// ======================================================  private method
	private Order generateOrder() {
		Random random = new Random();
		ThreadUtils.delay(2000);

		Trade trade = new Trade(random.nextLong());
		trade.setPrice(100000f);
		trade.setQuantity(50000);
		trade.setSymbol("Mac Book");
		trade.setType(Type.BUY);

		Order order = new Order(random.nextLong());
		order.setTrade(trade);
		order.setTimestamp(System.currentTimeMillis());

		return order;
	}
}
