package org.rubik.rxjava;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class RxSample {

	public static void main(String[] args) {
		sample1();
		
		sample2();
	}

	// 基本用法
	public static void sample1() {
		// 定义被观察者observable
		Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> subscriber) {
				try {
					subscriber.onNext("Hello, World");
					subscriber.onCompleted();
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}
		});

		// 定义订阅者subscriber
		Subscriber<String> mySubscriber = new Subscriber<String>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(String t) {
				System.out.println(t);
			}
		};

		// 建立关联
		myObservable.subscribe(mySubscriber);

		// 一个被观察者可以有多个订阅者
		myObservable.subscribe(s -> System.out.println(s + " - by Kevin"));
		myObservable.map(s -> s + " - by Xiajinxin").subscribe(s -> System.out.println(s));

		String[] names = new String[]{"a", "b", "c", "d", "e", "f"};
		Observable.from(names).subscribe(s -> System.out.print(s));
		Observable.just("100").subscribe(s -> System.out.println(s));

		// 同一个订阅者实例只能使用一次
//		Observable.from(names).subscribe(mySubscriber);
	}

	// flatMap, doOnNext示例
	public static void sample2() {
		Observable<List<String>> queryResult = Observable.create(new Observable.OnSubscribe<List<String>>() {

			@Override
			public void call(Subscriber<? super List<String>> subscriber) {
				try {
					List<String> result = Arrays.asList("111", "222", "333", "444", "555", "666", "777", "888");
					subscriber.onNext(result);
					subscriber.onCompleted();
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}
		});

		queryResult.flatMap(urls -> Observable.from(urls)).doOnNext(s -> saveDisk(s)).subscribe(url -> System.out.println(url));
	}

	// 内部函数
	private static void saveDisk(String s) {
		//TODO: save disk
	}
}
