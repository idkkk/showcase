package org.rubik.sandbox.parseq;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.httpclient.HttpClient;
import com.linkedin.parseq.promise.PromiseException;
import com.linkedin.parseq.trace.TraceUtil;

/**
 * Web爬虫。
 * 
 * @author xiajinxin
 *
 */
public class WebSpider {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSpider.class);

	private static final int numCores = Runtime.getRuntime().availableProcessors();
	private static final ExecutorService taskExecutor = Executors.newFixedThreadPool(numCores);
	private static final ScheduledExecutorService timeScheduler = Executors.newSingleThreadScheduledExecutor();

	private static final Engine engine = new EngineBuilder().setTaskExecutor(taskExecutor).setTimerScheduler(timeScheduler).build();

	private static Task<String> fetchBody(String url) {
		return HttpClient.get(url).task().map(response -> response.getResponseBody());
	}

	public static void main(String[] args) throws PromiseException, IOException, InterruptedException {
		final Task<String> baidu = fetchBody("http://www.baidu.com");
		final Task<String> netease = fetchBody("http://www.163.com");
		final Task<String> tencent = fetchBody("http://www.tencent.com");

		final Task<String> tasks = Task.par(baidu, netease, tencent).map((a, b, c) -> String.format("百度:%s\n网易:%s\n腾讯:%s", a, b, c));
		engine.run(tasks);
		LOGGER.debug(TraceUtil.getJsonTrace(tasks));
	}
}
