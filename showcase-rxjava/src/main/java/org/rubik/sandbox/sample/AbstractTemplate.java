package org.rubik.sandbox.sample;

public abstract class AbstractTemplate {
	protected void doCall() {}
	public void run() {
		// before
		doCall();
		// after
	}
}
