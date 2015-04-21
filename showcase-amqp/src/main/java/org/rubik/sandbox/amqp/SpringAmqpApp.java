package org.rubik.sandbox.amqp;

import org.rubik.sandbox.amqp.config.AmqpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringAmqpApp {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringAmqpApp.class);
	private static final String QUEUE_NAME = "myqueue";

	public static void main(String[] args) {
//		appByXmlConfig();
//		appByCode();
		
		appByJavaConfig();
	}

	private static void appByJavaConfig() {
		for (int i = 0; i < 100; i++) {
			LOGGER.debug("消息: {}", i);
		}
//		ApplicationContext ac = new AnnotationConfigApplicationContext(AmqpConfiguration.class);
//		AmqpTemplate template = ac.getBean(AmqpTemplate.class);
//		template.convertAndSend(QUEUE_NAME, "Hello, Scala集市！");
//		
//		String message = (String) template.receiveAndConvert(QUEUE_NAME);
//		LOGGER.debug("消息：{}", message);
	}

	private static void appByXmlConfig() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:/app-amqp.xml"});
		AmqpTemplate template = (AmqpTemplate) ac.getBean("amqpTemplate");
		template.convertAndSend(QUEUE_NAME, "紫玉山庄");
	}

	private static void appByCode() {
		ConnectionFactory connectionFactory = new CachingConnectionFactory("10.211.55.8");
		
		AmqpAdmin admin = new RabbitAdmin(connectionFactory);
		admin.declareQueue(new Queue(QUEUE_NAME));
		
		AmqpTemplate template = new RabbitTemplate(connectionFactory);
		template.convertAndSend(QUEUE_NAME, "Hello World");
		
		String message = (String) template.receiveAndConvert(QUEUE_NAME);
		System.out.println(message);
	}
}
