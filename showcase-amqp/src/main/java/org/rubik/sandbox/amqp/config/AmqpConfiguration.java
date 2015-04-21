package org.rubik.sandbox.amqp.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {

	public static String LOG_EXCHANGE_NAME = "app.logbak.log";
	public static String LOG_QUEUE_NAME = "app.logback.demo";
	public static String LOG_ALL_INFO_ROUTING_KEY = "#.DEBUG";
	
	@Bean
	public ConnectionFactory getConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("10.211.55.8");
		return connectionFactory;
	}

	@Bean
	public AmqpTemplate getAmqpTemplate() {
		AmqpTemplate template = new RabbitTemplate(getConnectionFactory());
		return template;
	}

	@Bean
	public AmqpAdmin getAmqpAdmin() {
		AmqpAdmin admin = new RabbitAdmin(getConnectionFactory());
		return admin;
	}

	@Bean
	public TopicExchange logExchange() {
		return new TopicExchange(LOG_EXCHANGE_NAME, false, false);
	}

	@Bean
	public Queue queue() {
		Queue queue = new Queue(LOG_QUEUE_NAME);
		return queue;
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(logExchange()).with(LOG_ALL_INFO_ROUTING_KEY);
	}

}
