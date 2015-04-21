package org.rubik.sandbox.retrofit;

import rx.Observable;

public class RxJavaApp {

	public static void main(String[] args) {
//		List<String> cities = Arrays.asList("Beijing", "Shanghai", "Guangzhou", "Shenzhen", "Jiujiang", "Lijiang", "Tailand", "Fushun", "Xianggang");
//		Observable.from(cities).subscribe(city -> System.out.println(city));

		String[] cities = new String[8000];
		for (int i = 0; i < cities.length; i++) {
			cities[i] = "A";
		}
		Observable.from(cities).flatMap(city -> ApiManager.getServerInfo(city)).subscribe(serverInfo -> System.out.println(serverInfo));
	}
}