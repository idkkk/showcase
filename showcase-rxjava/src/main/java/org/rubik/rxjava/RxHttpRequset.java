package org.rubik.rxjava;

import java.io.IOException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.HttpAsyncMethods;

import rx.apache.http.ObservableHttp;


public class RxHttpRequset {

	public static void main(String[] args) throws IOException {
		final RequestConfig requestConfig = RequestConfig.custom()
		        .setSocketTimeout(3000)
		        .setConnectTimeout(500).build();
		final CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom()
		        .setDefaultRequestConfig(requestConfig)
		        .setMaxConnPerRoute(20)
		        .setMaxConnTotal(50)
		        .build();
		httpClient.start();
		
        ObservableHttp.createRequest(HttpAsyncMethods.createGet("http://mywifi.10020.cn"), httpClient)
			        .toObservable()
			        .flatMap(response -> response.getContent().map(bb -> new String(bb)))
			        .toBlocking()
			        .forEach(resp -> System.out.println(resp));
        httpClient.close();
	}

	
//	private static void byHttpAsync() {
//		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
//		Future<Response> f = asyncHttpClient.prepareGet("http://passport.lefeng.com/inc/valide_user.jsp").execute();
//		asyncHttpClient.prepareGet("http://passport.lefeng.com/inc/valide_user.jsp").execute(new AsyncCompletionHandler<Response>(){
//		    public Response onCompleted(Response response) throws Exception {
//		    	System.out.println(response.getResponseBody());
//		    	// TODO: 赋值
//		        return response;
//		    }
//
//		    public void onThrowable(Throwable t) {
//		        // TODO: log
//		    }
//		});
//
//		System.out.println("1234567890");
//		System.out.println(f.get().getResponseBody());
//		System.out.println("111111111111111111");
//	}
}
