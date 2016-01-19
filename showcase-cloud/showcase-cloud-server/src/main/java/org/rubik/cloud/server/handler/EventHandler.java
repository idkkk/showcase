package org.rubik.cloud.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.bus.EventBus;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

@Consumer
public class EventHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventHandler.class);

	@Autowired
	private EventBus eventBus;

	@Selector(value = "test.topic")
	public void onMessage(String message) {
		LOGGER.debug("Message :{}", message);
	}
}
