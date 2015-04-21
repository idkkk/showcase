package org.rubik.sandbox.pm25.service;

import org.rubik.sandbox.pm25.data.StationResponse;

import rx.Observable;

// API gateway
public class ApiManager {

	public static Observable<StationResponse> getStationNames(String city) {
		StationService stationService = new StationService(city);
		return stationService.observe();
	}

	public static StationResponse getStations(String city) {
		StationService stationService = new StationService(city);
		return stationService.execute();
	}
}