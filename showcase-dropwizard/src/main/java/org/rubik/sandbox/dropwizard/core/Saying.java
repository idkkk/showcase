package org.rubik.sandbox.dropwizard.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Saying {
	private int id;
	
	private String content;

	public Saying(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty
	public String getContent() {
		return content;
	}

	@JsonProperty
	public void setContent(String content) {
		this.content = content;
	}
}
