package org.rubik.cloud.server.service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.rubik.cloud.server.domain.Order;
import org.rubik.cloud.server.domain.Trade;
import org.rubik.cloud.server.domain.Type;
import org.rubik.cloud.server.util.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * 逻辑处理类.
 * 
 * @author xiajinxin
 *
 */
@Service
public class ReactorService {
	@Autowired
	private EventBus eventBus;

	@Autowired
	private AsyncTaskExecutor asyncTaskExecutor;
	
	// ======================================================  public method
	public void run() {
		eventBus.notify("test.topic", Event.wrap("Hello World!"));
	}

	// sync
	public Order findAll() {
		return generateOrder();
	}

	// async (jdk8)
	public CompletableFuture<Order> findAllJavaAsync() {
		return CompletableFuture.supplyAsync(() -> {
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
