package org.rubik.sandbox.cctv.data.pojo;

import java.util.Arrays;

import com.google.common.base.Objects;

public class VersionResult {

	private VersionMedia[] mediaList;
	private int playId;
	private String date;
	private long version;

	public VersionMedia[] getMediaList() {
		return mediaList;
	}

	public void setMediaList(VersionMedia[] mediaList) {
		this.mediaList = mediaList;
	}

	public int getPlayId() {
		return playId;
	}

	public void setPlayId(int playId) {
		this.playId = playId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.omitNullValues()
				.add("playId", playId)
				.add("date", date)
				.add("version", version)
				.add("mediaList", Arrays.toString(mediaList))
				.toString();
	}
}
