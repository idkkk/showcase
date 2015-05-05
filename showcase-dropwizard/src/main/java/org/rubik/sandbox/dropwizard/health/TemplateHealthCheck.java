package org.rubik.sandbox.dropwizard.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {
	private String template;

	public TemplateHealthCheck(String template) {
		this.template = template;
	}

	@Override
	protected Result check() throws Exception {
		String saying = String.format(template, "TEST");
		if (saying.contains("TEST")) {
			return Result.healthy();
		} else {
			return Result.unhealthy("template don't include a name");
		}
	}

}
