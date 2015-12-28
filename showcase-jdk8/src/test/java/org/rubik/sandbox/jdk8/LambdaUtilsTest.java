package org.rubik.sandbox.jdk8;

import static org.junit.Assert.*;

import org.junit.Test;

public class LambdaUtilsTest {

	@Test
	public void testCalc() {
		assertEquals(400, LambdaUtils.calc(10L, x -> x * x, x -> x * 4).longValue());  // funcA(x) = x * x; funcB(y) = y * 4; funcB(funcA(x))
	}

	@Test
	public void testElapsedTime() {
		assertEquals(100, LambdaUtils.elapsedTime(10, x -> x * x));
	}
}
