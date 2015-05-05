package org.rubik.sandbox.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class RedisApp {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:app-redis.xml"});
		RedisCounter counter = (RedisCounter) ac.getBean("redisCounter");
		counter.multiThreadAndSynchronized();
	}
}
