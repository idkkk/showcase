package org.rubik.sandbox.retrofit;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Subscriber;

public class ApiManager {

//	private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
	private static final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://localhost:9990").build();
	private static final ServerInfoService serverInfoService = restAdapter.create(ServerInfoService.class);

	public static Observable<ServerInfo> getServerInfo(final String city) {
		return Observable.create(new Observable.OnSubscribe<ServerInfo>() {
			@Override
			public void call(Subscriber<? super ServerInfo> subscriber) {
				try {
					subscriber.onNext(serverInfoService.getServerInfo());
					subscriber.onCompleted();
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}
        });
	}
}
