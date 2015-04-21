package org.rubik.sandbox.pm25.service;

import java.util.Date;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

import com.google.common.base.Throwables;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

public class StationConstants {

	private static final String END_POINT = "http://www.pm25.in/api";

	private static Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
	public static RestAdapter REST_ADAPTER = new RestAdapter.Builder().setEndpoint(END_POINT).setConverter(new GsonConverter(gson)).setErrorHandler(new MyErrorHandler()).build();

//	public static RestAdapter REST_ADAPTER = new RestAdapter.Builder().setEndpoint(BASE_URL).setConverter(new JacksonConverter()).setLogLevel(LogLevel.FULL).build();

	public static final String TOKEN = "5j1znBVAsnSf5xQyNQyq";

	interface Api {
		String STATION_NAMES = "/querys/station_names.json";
	}


}

class MyErrorHandler implements ErrorHandler {
	@Override
	public Throwable handleError(RetrofitError cause) {
		Response r = cause.getResponse();
	    if (r != null && r.getStatus() == 401) {
	      return Throwables.propagate(cause);
	    }
	    return cause;
	}
}