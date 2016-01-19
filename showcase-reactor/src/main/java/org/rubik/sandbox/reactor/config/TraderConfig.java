package org.rubik.sandbox.reactor.config;

import static reactor.bus.selector.Selectors.$;

import org.rubik.sandbox.reactor.domain.Trade;
import org.rubik.sandbox.reactor.domain.TradeServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.core.dispatch.RingBufferDispatcher;

//@Configuration
public class TraderConfig {

	@Bean
	public TradeServer tradeServer() {
		return new TradeServer();
	}

	@Bean
	public EventBus reactor(Environment env, TradeServer tradeServer) {
		Logger log = LoggerFactory.getLogger("trade.server");
		EventBus eventBus = EventBus.create(env, new RingBufferDispatcher("TRADE"));

		// Wire an event handler to execute trades
		eventBus.on($("trade.execute"), (Event<Trade> e) -> {
			tradeServer.execute(e.getData());
			log.info("Executed trade: {}", e.getData());
		});

		return eventBus;
	}
}
