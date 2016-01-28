package org.rubik.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;

import reactor.Environment;
import reactor.jarjar.com.lmax.disruptor.YieldingWaitStrategy;
import reactor.jarjar.com.lmax.disruptor.dsl.ProducerType;
import reactor.spring.context.config.EnableReactor;
import reactor.spring.core.task.RingBufferAsyncTaskExecutor;

@Configuration
@EnableReactor
public class ReactorConfig {

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
