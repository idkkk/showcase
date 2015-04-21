package org.rubik.sandbox.pm25.data;

import java.util.Arrays;

import com.google.common.base.Objects;

public class StationResponse {
	private String city;
	private Station[] stations;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Station[] getStations() {
		return stations;
	}

	public void setStations(Station[] stations) {
		this.stations = stations;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.omitNullValues()
				.add("city", city)
				.add("stations", Arrays.toString(stations))
				.toString();
	}
}
