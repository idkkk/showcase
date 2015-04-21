package org.rubik.sandbox.retrofit;

import retrofit.http.GET;

public interface ServerInfoService {
	@GET("/admin/server_info")
	public ServerInfo getServerInfo();
}