package org.rubik.sandbox.reactor;

import io.undertow.Undertow;
import io.undertow.util.Headers;

public class UndertowServer {

	public static void main(String... args) {
		Undertow server = Undertow.builder().addHttpListener(8080, "127.0.0.1").setHandler(exchange -> {
				exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
				exchange.getResponseSender().send("Hello World");
		}).build();
		server.start();
	}

}
