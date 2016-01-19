package org.rubik.sandbox.reactor.util;

public abstract class ThreadUtils {

	public static void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			//TODO:
		}
	}
}
