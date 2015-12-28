package org.rubik.sandbox.sample;

public class Component {
	private String currency;
	public Component(String currency) {
		this.currency = currency;
	}

	public void setText(String message) {
		System.out.println(String.format("%s汇率：%s", currency, message));
	}
}
