package org.rubik.sandbox.cctv;

import org.rubik.sandbox.cctv.data.ApiManager;

import rx.Observable;

public class ClientApp {

	public static void main(String[] args) {
//		List<String> cities = Arrays.asList("Beijing", "Shanghai", "Guangzhou", "Shenzhen", "Jiujiang", "Lijiang", "Tailand", "Fushun", "Xianggang");
//		Observable.from(cities).subscribe(city -> System.out.println(city));

		String[] cities = new String[1];
		for (int i = 0; i < cities.length; i++) {
			cities[i] = "1";
		}
		Observable.from(cities).flatMap(areaId -> ApiManager.getPlayVersion(areaId)).subscribe(serverInfo -> System.out.println(serverInfo));
	}
}