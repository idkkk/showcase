package org.rubik.sandbox.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
//@EnableReactor
public class WebsocketServer {

	public static void main(String... args) {
		SpringApplication.run(WebsocketServer.class, args);
	}

}
