package org.rubik.sandbox.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

public class CommandHelloWorldFailure extends HystrixCommand<String> {

	private final String name;
	
	protected CommandHelloWorldFailure(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected String run() {
		throw new HystrixBadRequestException("非法的参数或者非系统的失败！");
//		throw new RuntimeException("this command always fails");
	}

	@Override
	protected String getFallback() {
		return "Hello Failure " + name + "!";
	}
}
