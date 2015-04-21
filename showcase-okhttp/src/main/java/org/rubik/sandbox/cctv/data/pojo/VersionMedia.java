package org.rubik.sandbox.cctv.data.pojo;

import com.google.common.base.Objects;

public class VersionMedia {

	private String title;
	private String fileName;
	private String type;
	private long size;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.omitNullValues()
				.add("title", title)
				.add("fileName", fileName)
				.add("type", type)
				.add("size", size)
				.toString();
	}
}
