package org.rubik.sandbox.jdk8;

import java.util.function.Function;

public abstract class LambdaUtils {

	public static Long calc(Long number, Function<Long, Long> x, Function<Long, Long> y) {
		return x.andThen(y).apply(number);
	}

	public static <T, R> R elapsedTime(T t, Function<T, R> block) {
		long before = System.currentTimeMillis();
		R result = block.apply(t);
		long after = System.currentTimeMillis();
		System.out.println(String.format("Elapsed time: %dms", after - before));
		return result;
	}

}
