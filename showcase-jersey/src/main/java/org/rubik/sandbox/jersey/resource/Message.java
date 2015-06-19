package org.rubik.sandbox.jersey.resource;

import java.util.Date;

import javax.ws.rs.Path;

@Path("/message")
public class Message {

	private String title;
	private String content;
	private Date createDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
