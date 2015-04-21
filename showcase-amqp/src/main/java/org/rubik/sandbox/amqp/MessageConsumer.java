package org.rubik.sandbox.amqp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.rubik.sandbox.amqp.config.AmqpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MessageConsumer {
	private static final int NUMBER_OF_POOL = 2;   // 线程数
	private static final int NUMBER_OF_WORKS = 2;  // 总任务数

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);
	private static final String QUEUE_NAME = "app.logback.demo";

	public static void main(String[] args) {
		appByJavaConfig();
	}

	private static void appByJavaConfig() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AmqpConfiguration.class);
		AmqpTemplate template = ac.getBean(AmqpTemplate.class);

		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_POOL);
		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
			executorService.execute(new ConsumerWorker(template));
		}
		executorService.shutdown();
	}
	
	//同步工作线程
	static class ConsumerWorker implements Runnable {
		private AmqpTemplate amqpTemplate;
		
		public ConsumerWorker(AmqpTemplate amqpTemplate) {
			this.amqpTemplate = amqpTemplate;
		}
		
		@Override
		public void run() {
			String message = (String) amqpTemplate.receiveAndConvert(QUEUE_NAME);
			System.out.println(message);
		}
	}
}
