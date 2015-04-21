package org.rubik.sandbox.pm25.data;

//import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
//import com.google.gson.annotations.SerializedName;

public class Station {

//	@SerializedName("station_name")
//	@JsonProperty("station_name")
	private String stationName;

//	@SerializedName("station_code")
//	@JsonProperty("station_code")
	private String stationCode;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.omitNullValues()
				.add("stationName", stationName)
				.add("stationCode", stationCode)
				.toString();
	}
}
