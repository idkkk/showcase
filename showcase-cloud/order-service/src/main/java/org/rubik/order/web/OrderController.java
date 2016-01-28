package org.rubik.order.web;

import org.rubik.order.domain.Order;
import org.rubik.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/order")
public class OrderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/sync", method = RequestMethod.GET)
	public Order order() {
		LOGGER.debug("执行order方法...");
		return orderService.findOne();
	}

	@RequestMapping(value = "/async", method = RequestMethod.GET)
	public DeferredResult<Order> javaAsync() {
		LOGGER.debug("执行orderAsync方法...");
		DeferredResult<Order> deferredResult = new DeferredResult<>();
		orderService.findOneAsync().whenComplete((result, error) -> {
			if (error != null) deferredResult.setErrorResult(error);
			deferredResult.setResult(result);
		});
		return deferredResult;
	}
}
