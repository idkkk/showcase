package org.rubik.sandbox.sample;

import static org.rubik.sandbox.sample.RxJavaAsyncHelper.expression;
import static org.rubik.sandbox.sample.RxJavaAsyncHelper.get;
import static org.rubik.sandbox.sample.RxJavaAsyncHelper.mapJson;
import static org.rubik.sandbox.sample.RxJavaAsyncHelper.updateComponent;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import com.google.common.base.Throwables;

public class RxJavaAsync {
	private static final Logger LOGGER = LoggerFactory
	        .getLogger(RxJavaAsync.class);

	private static Component usd = new Component("USD");
	private static Component rmb = new Component("RMB");

	public static void main(String[] args) throws InterruptedException {
//		doAction1();
//		doAction2();
//		doAction3();
//		doAction4();
//		doAction5();
//		Thread.sleep(10000000);
	}

	// 传统方式
	private static void doAction1() {
		new Runnable() {
			public void run() {
				try {
					String responseAsString = Request.Get("http://ifconfig.me/all.json").execute().returnContent().asString();
					System.out.println(responseAsString);
				} catch (IOException e) {
					// TODO
				}
			}
		}.run();
	}
	
	public static void doAction2() {
		Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				try {
					String responseAsString = Request.Get("http://ifconfig.me/all.json").execute().returnContent().asString();
					LOGGER.warn(responseAsString);
					subscriber.onNext(responseAsString);
					subscriber.onCompleted();
				} catch (IOException e) {
					subscriber.onError(e);
				}
			}
		})
		.map(s -> String.format("Hello, RP: %s",s))
		.subscribe(o -> {
			System.out.println(o);
		});
	}

	public static void doAction3() {
		Worker worker = Schedulers.newThread().createWorker();
		worker.schedulePeriodically(new Action0() {
			@Override
			public void call() {
				try {
					String responseAsString = Request.Get("https://blockchain.info/ticker").execute().returnContent().asString();
					LOGGER.warn(responseAsString);
				} catch (IOException e) {
					LOGGER.error(Throwables.getStackTraceAsString(e));
				}
			}
		}, 0, 5000, TimeUnit.MILLISECONDS);
	}

	public static void doAction4() {
		Worker worker = Schedulers.newThread().createWorker();
		worker.schedulePeriodically(new Action0() {
			@Override
			public void call() {
				try {
					String responseAsString = Request.Get("http://ifconfig.me/all.json").execute().returnContent().asString();
					LOGGER.warn(responseAsString);
				} catch (IOException e) {
					LOGGER.error(Throwables.getStackTraceAsString(e));
				}
			}
		}, 0, 2, TimeUnit.SECONDS);
	}

	public static void doAction5() {
		Worker worker = Schedulers.newThread().createWorker();
		worker.schedulePeriodically(new Action0() {
			@Override
			public void call() {
				ConnectableObservable<String> publish = get("https://blockchain.info/ticker").publish();
				publish.map(mapJson()).map(expression("[USD][last]")).subscribe(updateComponent(usd));
				publish.map(mapJson()).map(expression("[HKD][last]")).subscribe(updateComponent(rmb));
				publish.connect();
			}
		}, 0, 2, TimeUnit.SECONDS);
	}
}
