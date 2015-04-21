package org.rubik.sandbox.pm25.service;

import org.rubik.sandbox.pm25.data.StationResponse;
import org.rubik.sandbox.pm25.service.StationConstants.Api;

import retrofit.http.GET;
import retrofit.http.Query;

public interface StationApi {

	@GET(Api.STATION_NAMES)
	public StationResponse queryStationNames(@Query("token") String token, @Query("city") String city);

}
