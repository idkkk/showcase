package org.rubik.order.service;

import java.util.concurrent.CompletableFuture;

import org.rubik.order.client.AccountClient;
import org.rubik.order.domain.Account;
import org.rubik.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 逻辑处理类.
 * 
 * @author xiajinxin
 *
 */
@Service
public class OrderService {

	@Autowired
	private AccountClient accountClient;

//	@Autowired
//	private AsyncTaskExecutor asyncTaskExecutor;
	
	// ======================================================  public method
	// sync
	public Order findOne() {
		return getOrder();
	}

	// async (jdk8)
	public CompletableFuture<Order> findOneAsync() {
		return CompletableFuture.supplyAsync(() -> {
			return getOrderAsync();
		});
	}

	// ======================================================  private method
	private Order getOrder() {
		Account account = accountClient.getUser();
		Order order = new Order();
		order.setId(999999L);
		order.setAccount(account);
		order.setTimestamp(System.currentTimeMillis());
		return order;
	}

	private Order getOrderAsync() {
		Account account = accountClient.getUserAsync();
		Order order = new Order();
		order.setId(999999L);
		order.setAccount(account);
		order.setTimestamp(System.currentTimeMillis());
		return order;
	}
}
