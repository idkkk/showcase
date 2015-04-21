package org.rubik.sandbox.cctv.data;

import org.rubik.sandbox.cctv.data.pojo.VersionResponse;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Subscriber;

public class ApiManager {

//	private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(Date.class, new DateTypeAdapter()).create();

	private static final String BASE_URL = "http://test.my-wifi.com.cn";    // http://cctv-api.my-wifi.com.cn
	private static final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(BASE_URL).build();
	private static final VersionService versionService = restAdapter.create(VersionService.class);

	public static Observable<VersionResponse> getPlayVersion(final String areaId) {
		return Observable.create(new Observable.OnSubscribe<VersionResponse>() {
			@Override
			public void call(Subscriber<? super VersionResponse> subscriber) {
				try {
//					subscriber.onNext(versionService.getVersion(areaId, "_DOhG7jB6ps.", areaId, "Ti1G", "B4G_QhbgnXClYMIX345UZwehL1WeJptrrWt9qCYVk21699xf15DT2EHwjbq0VxGrBvTGUA..", "1423562449097"));
					subscriber.onNext(versionService.getCities());
					subscriber.onCompleted();
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}
        });
	}
}
