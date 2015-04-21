package org.rubik.sandbox.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Hystrix依赖：
 * 	1）hystrix-core
 * 	2）rxjava
 * 	3）slf4j-api
 * 	4）archaius-core，commons-configuartion
 * 	5）commons-lang, commons-logging
 * 
 * @author xiajinxin
 *
 */
public class CommandHelloWorld extends HystrixCommand<String> {

	private final String name;
	
	protected CommandHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected String run() {
		return "Hello " + name + "!";
	}
}
