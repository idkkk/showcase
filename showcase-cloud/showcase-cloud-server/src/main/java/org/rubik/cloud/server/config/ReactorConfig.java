package org.rubik.cloud.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;

import reactor.Environment;
import reactor.bus.EventBus;
import reactor.core.dispatch.RingBufferDispatcher;
import reactor.jarjar.com.lmax.disruptor.YieldingWaitStrategy;
import reactor.jarjar.com.lmax.disruptor.dsl.ProducerType;
import reactor.spring.context.config.EnableReactor;
import reactor.spring.core.task.RingBufferAsyncTaskExecutor;

@Configuration
@EnableReactor
public class ReactorConfig {

	@Bean
	public EventBus rootReactor(Environment env) {
		return EventBus.create(env, new RingBufferDispatcher("TEST"));
	}

	@Bean
	public AsyncTaskExecutor singleThreadAsyncTaskExecutor(Environment env) {
		RingBufferAsyncTaskExecutor asyncTaskExecutor = new RingBufferAsyncTaskExecutor(env);
		asyncTaskExecutor.setName("ringBufferExecutor");
		asyncTaskExecutor.setBacklog(2048);
		asyncTaskExecutor.setProducerType(ProducerType.SINGLE);
		asyncTaskExecutor.setWaitStrategy(new YieldingWaitStrategy());
		return asyncTaskExecutor;
	}
}
