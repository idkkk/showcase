package org.rubik.sandbox.pm25;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.rubik.sandbox.pm25.service.ApiManager;

import rx.Observable;

import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

public class PM25App {

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			sync();
			
			async();
			
			asyncAndZip();
			
			asyncAndMerge();
		}
	}

	public static void sync() throws InterruptedException {
		Stopwatch sw = Stopwatch.createUnstarted();
		List<String> cities = Lists.newArrayList("北京", "武汉");
		sw.start();
		Observable.from(cities).map(city -> ApiManager.getStations(city)).subscribe(station -> System.out.println("* " + station));
		sw.stop();
		System.out.printf("同步总耗时: %s毫秒.", sw.elapsed(TimeUnit.MILLISECONDS));
		System.out.println("***************************************");
		Thread.sleep(1000);
	}
	
	public static void async() throws InterruptedException {
		Stopwatch sw = Stopwatch.createUnstarted();
		List<String> cities = Lists.newArrayList("上海", "广州", "九江", "深圳", "丽江", "南京", "大连", "沈阳", "黄山", "三亚");
		sw.start();
		Observable.from(cities).flatMap(city -> ApiManager.getStationNames(city)).delay(1, TimeUnit.SECONDS).subscribe(station -> System.out.println("= " + station));
		sw.stop();
		System.out.printf("异步总耗时: %s毫秒.", sw.elapsed(TimeUnit.MILLISECONDS));
		System.out.println("=======================================");
		Thread.sleep(1000);
	}
	
	public static void asyncAndZip() throws InterruptedException {
		Stopwatch sw = Stopwatch.createUnstarted();
		List<String> cities = Lists.newArrayList("上海", "广州", "九江", "深圳", "丽江", "南京", "大连", "沈阳", "黄山", "三亚");
		sw.start();
		Observable.zip(Observable.from(cities).take(5).flatMap(city -> ApiManager.getStationNames(city)), 
																Observable.from(cities).takeLast(5).flatMap(city -> ApiManager.getStationNames(city)), 
																(cityA, cityB) -> cityA.toString() + " ******** " + cityB.toString())
																.delay(1, TimeUnit.SECONDS)
																.subscribe(city -> System.out.println("< " + city), throwable -> Throwables.propagate(throwable));
		
		sw.stop();
		System.out.printf("zip异步总耗时: %s毫秒.", sw.elapsed(TimeUnit.MILLISECONDS));
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		Thread.sleep(1000);
	}

	public static void asyncAndMerge() throws InterruptedException {
		Stopwatch sw = Stopwatch.createUnstarted();
		List<String> cities = Lists.newArrayList("上海", "广州", "九江", "深圳", "丽江", "南京", "大连", "沈阳", "黄山", "三亚");
		sw.start();
		Observable.merge(Observable.from(cities).take(5).flatMap(city -> ApiManager.getStationNames(city)), 
																Observable.from(cities).takeLast(5).flatMap(city -> ApiManager.getStationNames(city)))
																.delay(1, TimeUnit.SECONDS)
																.subscribe(city -> System.out.println("> " + city));
		
		sw.stop();
		System.out.printf("merge异步总耗时: %s毫秒.", sw.elapsed(TimeUnit.MILLISECONDS));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Thread.sleep(1000);
	}
}
