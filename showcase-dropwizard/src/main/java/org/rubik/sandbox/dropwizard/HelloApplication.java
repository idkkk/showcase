package org.rubik.sandbox.dropwizard;

import org.rubik.sandbox.dropwizard.config.HelloConfiguration;
import org.rubik.sandbox.dropwizard.health.TemplateHealthCheck;
import org.rubik.sandbox.dropwizard.resources.HelloResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class HelloApplication extends Application<HelloConfiguration> {

	public static void main(String[] args) throws Exception {
		new HelloApplication().run(args);
	}

	@Override
	public void run(HelloConfiguration configuration, Environment environment) throws Exception {
		HelloResource resource = new HelloResource(configuration.getTemplate(), configuration.getDefaultName());
		TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}
}
