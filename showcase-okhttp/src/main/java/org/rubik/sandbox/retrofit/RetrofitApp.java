package org.rubik.sandbox.retrofit;

import retrofit.RestAdapter;

public class RetrofitApp {
	private static final String TARGET_URL = "http://localhost:9990";

	public static void main(String[] args) {
		getServerInfo(TARGET_URL);
	}

	public static void getServerInfo(final String url) {
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
		ServerInfoService serverInfoService = restAdapter.create(ServerInfoService.class);
		ServerInfo serverInfo = serverInfoService.getServerInfo();
		System.out.println(serverInfo);
	}
}
