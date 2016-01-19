package org.rubik.cloud.client.web;

import org.rubik.cloud.client.domain.Order;
import org.rubik.cloud.client.service.ReactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class ReactorController {

	@Autowired
	private ReactorService reactorService;

	@RequestMapping(value = "/sync", method = RequestMethod.GET)
	public Order sync() {
		return reactorService.findAll();
	}

	@RequestMapping(value = "/async", method = RequestMethod.GET)
	public DeferredResult<Order> javaAsync() {
		DeferredResult<Order> deferredResult = new DeferredResult<>();
		reactorService.findAllJavaAsync().whenComplete((result, error) -> {
			if (error != null) deferredResult.setErrorResult(error);
			deferredResult.setResult(result);
		});
		return deferredResult;
	}

	@RequestMapping(value = "/reactor", method = RequestMethod.GET)
	public DeferredResult<Order> reactor() {
		reactorService.run();

		DeferredResult<Order> deferredResult = new DeferredResult<>();
		reactorService.findAllJavaAsync().whenComplete((result, error) -> {
			if (error != null) deferredResult.setErrorResult(error);
			deferredResult.setResult(result);
		});
		return deferredResult;
	}
}
