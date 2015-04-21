package org.rubik.sandbox.okhttp;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.base.Throwables;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * 
 * OkHttp依赖： okhttp, okio
 * 
 * @author xiajinxin
 *
 */
public class HttpRequestApp {
	private static final int NUMBER_OF_POOL = 10;   // 线程数
	private static final int NUMBER_OF_WORKS = 10000;  // 总任务数
//	private static final String TARGET_URL = "http://ifconfig.me/all.json";
//	private static final String TARGET_URL = "http://cctv-api.10020.net/dict/cities";
	private static final String TARGET_URL = "http://cctv-admin.10020.net";

	private static OkHttpClient client = new OkHttpClient();
//	static {
//		client.setConnectTimeout(30, TimeUnit.SECONDS); // 30秒超时设置
//	}
	
	public static void main(String[] args) {
		doGet();
		
//		doPost();
	}

	private static void doGet() {
		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_POOL);
		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
			executorService.execute(new GetWorker(client, TARGET_URL));
		}
		executorService.shutdown();
	}

	private static void doPost() {
		String url = "http://www.cribn.com/register/user/submit";
		RequestBody formBody = new FormEncodingBuilder()
								.add("username", "BeiJing")
								.add("password", "123456")
								.add("password2", "123456")
								.add("smsCode", "332500")
								.add("nick", "kkk")
								.add("agree", "1").build();
		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_POOL);
		for (int i = 0; i < NUMBER_OF_WORKS; i++) {
			executorService.execute(new PostWorker(client, url, formBody));
		}
		executorService.shutdown();
	}
}

class GetWorker implements Runnable {
	private String url;
	private OkHttpClient client;

	public GetWorker(OkHttpClient client, String targetUrl) {
		this.client = client;
		this.url = targetUrl;
	}

	@Override
	public void run() {
		Request request = new Request.Builder().url(url).build();
		client.newCall(request).enqueue(new Callback() {
			public void onFailure(Request request, IOException exception) {
				Throwables.getRootCause(exception);
			}

			public void onResponse(Response response) throws IOException {
		        System.out.println(response.body().string());
			}
		});
	}
}

class PostWorker implements Runnable {
	private OkHttpClient client;
	private String url;
	private RequestBody requestBody;

	public PostWorker(OkHttpClient client, String targetUrl, RequestBody formBody) {
		this.client = client;
		this.url = targetUrl;
		this.requestBody = formBody;
	}
	
	@Override
	public void run() {
		Request request = new Request.Builder().url(url).post(requestBody).build();
		try {
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}
}