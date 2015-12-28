package org.rubik.sandbox.sample;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.ReflectionUtils;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public abstract class RxJavaAsyncHelper {

	private static final Logger LOGGER = LoggerFactory
	        .getLogger(RxJavaAsyncHelper.class);

	private static JsonParser jsonParser = new JacksonJsonParser();

	private static ExpressionParser expressionParser = new SpelExpressionParser();

	private RxJavaAsyncHelper() {

	}

	public static Observable<String> get(final String uri) {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				try {
					String responseAsString = Request.Get(uri).execute().returnContent().asString();
					LOGGER.warn(responseAsString);
					subscriber.onNext(responseAsString);
					subscriber.onCompleted();
				} catch (IOException e) {
					subscriber.onError(e);
				}
			}
		});
	}

	public static Func1<String, Map<String, Object>> mapJson() {
		return json -> jsonParser.parseMap(json);
	}

	public static Func1<Object, Object> expression(String l) {
		Expression expression = expressionParser.parseExpression(l);
		return o -> expression.getValue(o);
	}

	public static Action1<Object> updateComponent(Component component) {
		return o -> {
			Method setText = ReflectionUtils.findMethod(component.getClass(), "setText", String.class);
			if (setText != null) {
				String s = String.valueOf(o);
				ReflectionUtils.invokeMethod(setText, component, s);
			}
		};
	}
}
