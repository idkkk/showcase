package org.rubik.sandbox.concurrent;

import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public class RedisWorker implements Runnable {

	private RedisAtomicLong counter;

	public RedisWorker(RedisAtomicLong counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		counter.getAndIncrement();
//		counter.getAndDecrement();
	}
	
//	private RedisTemplate redisTemplate;
//
//	public RedisWorker(RedisTemplate redisTemplate) {
//		this.redisTemplate = redisTemplate;
//	}
//
//	@Override
//	public void run() {
//		redisTemplate.execute(new RedisCallback<Long>() {
//			@Override
//			public Long doInRedis(RedisConnection connection) throws DataAccessException {
//				return connection.incr("counter".getBytes());
//			}
//		});
//	}

}
