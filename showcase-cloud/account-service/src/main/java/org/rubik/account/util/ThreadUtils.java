package org.rubik.account.util;

public abstract class ThreadUtils {

	public static void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			//TODO:
		}
	}
}
