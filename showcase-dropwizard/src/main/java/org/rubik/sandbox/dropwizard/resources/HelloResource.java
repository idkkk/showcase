package org.rubik.sandbox.dropwizard.resources;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.rubik.sandbox.dropwizard.core.Saying;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

	private final AtomicInteger counter = new AtomicInteger();
	private String template;
	private String defaultName;

	public HelloResource(String template, String defaultName) {
		super();
		this.template = template;
		this.defaultName = defaultName;
	}

	@GET
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		String value = String.format(template, name.or(defaultName));
		return new Saying(counter.getAndIncrement(), value);
	}
	
}
