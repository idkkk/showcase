package org.rubik.sandbox.feign;

import java.util.List;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

//import feign.Feign;
//import feign.jackson.JacksonDecoder;
//import feign.jackson.JacksonEncoder;

/**
 * Feign依赖： feign-core, feign-jackson, dagger, javax.inject, jackson-databind, jackson-annotations, jackson-core
 *
 * @author xiajinxin
 *
 */
public class FeignApp {
	public static void main(String... args) {
		DynamicStringProperty prop = DynamicPropertyFactory.getInstance().getStringProperty("java.version", "JAVA");
		System.out.printf("Java version: %s", prop.get());
		
//		String serverListKey = "";
//		getConfigInstance().setProperty(serverListKey, "localhost:8080,localhost:8081");
//		Github github = Feign.create(LoadBalancingTarget.create(Github.class, "https://api.github.com"), new JacksonModule(), new RibbonModule());
//		Github github = Feign.create(Github.class, "https://api.github.com", new JacksonModule(), new RibbonModule());
//		Github github = Feign.builder()
//						     .encoder(new JacksonEncoder())
//						     .decoder(new JacksonDecoder())
//						     .target(Github.class, "https://api.github.com");

//		List<Contributor> contributors = github.contributors("ReactiveX", "RxJava");
//		for (Contributor contributor : contributors) {
//			System.out.println(contributor.getLogin() + " ("+ contributor.getContributions() + ")");
//		}
	}
}
