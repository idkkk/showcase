package org.rubik.sandbox.cctv.data;

import org.rubik.sandbox.cctv.data.pojo.VersionResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface VersionService {
	@GET("/file/{area}/version")
	public VersionResponse getVersion(@Path("area") String area, @Query("appKey") String appKey, @Query("areaId") String areaId, @Query("rand") String randomNumber, @Query("signature") String sign, @Query("timestamp") String requestTime);
	
	@GET("/dict/cities")
	public VersionResponse getCities();
}