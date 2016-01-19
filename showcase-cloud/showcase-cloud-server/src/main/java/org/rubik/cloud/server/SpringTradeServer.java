package org.rubik.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringTradeServer {

	public static void main(String... args) {
		SpringApplication.run(SpringTradeServer.class, args);
	}

}
