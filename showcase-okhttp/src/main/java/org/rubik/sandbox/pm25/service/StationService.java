package org.rubik.sandbox.pm25.service;

import static org.rubik.sandbox.pm25.service.StationConstants.*;

import org.rubik.sandbox.pm25.data.StationResponse;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class StationService extends HystrixCommand<StationResponse> {

	private String city;

	protected StationService(String city) {
		super(
				Setter.withGroupKey(
						HystrixCommandGroupKey.Factory.asKey("StationGroup"))
						.andCommandKey(HystrixCommandKey.Factory.asKey("Station"))
						.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("StationThreadPool"))
						.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
										.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
						.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
										.withCoreSize(10)));
//		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("StationGroup"))
//						.andCommandKey(HystrixCommandKey.Factory.asKey("Station")));
		this.city = city;
	}

	@Override
	protected StationResponse run() throws Exception {
		StationApi stationApi = REST_ADAPTER.create(StationApi.class);
		return stationApi.queryStationNames(TOKEN, city);
	}

	@Override
	protected StationResponse getFallback() {
		return null;
	}

}
