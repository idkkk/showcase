package org.rubik.sandbox.reactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import reactor.spring.context.config.EnableReactor;

@Configuration
@EnableAutoConfiguration
@ComponentScan
//@EnableReactor
public class SpringTradeServer {

	public static void main(String... args) {
		SpringApplication.run(SpringTradeServer.class, args);
	}

}
