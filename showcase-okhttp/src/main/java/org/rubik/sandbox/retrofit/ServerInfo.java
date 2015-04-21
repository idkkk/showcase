package org.rubik.sandbox.retrofit;

import java.io.Serializable;
import java.util.Arrays;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerInfo implements Serializable {
	private static final long serialVersionUID = 6303743149506150455L;

	@Expose
	private String name;

	@Expose
	private String version;

	@Expose
	private String build;

	@Expose
	@SerializedName("build_revision")
	private String buildRevision;

	@Expose
	@SerializedName("build_branch_name")
	private String buildBranchName;

	@Expose
	@SerializedName("build_last_few_commits")
	private String[] buildLastFewCommits;

	@Expose
	@SerializedName("start_time")
	private String startTime;

	@Expose
	private long uptime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getBuildRevision() {
		return buildRevision;
	}

	public void setBuildRevision(String buildRevision) {
		this.buildRevision = buildRevision;
	}

	public String getBuildBranchName() {
		return buildBranchName;
	}

	public void setBuildBranchName(String buildBranchName) {
		this.buildBranchName = buildBranchName;
	}

	public String[] getBuildLastFewCommits() {
		return buildLastFewCommits;
	}

	public void setBuildLastFewCommits(String[] buildLastFewCommits) {
		this.buildLastFewCommits = buildLastFewCommits;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.omitNullValues()
				.add("name", name)
				.add("version", version)
				.add("build", build)
				.add("buildRevision", buildRevision)
				.add("buildBranchName", buildBranchName)
				.add("buildLastFewCommits", Arrays.toString(buildLastFewCommits))
				.add("startTime", startTime)
				.add("uptime", uptime)
				.toString();
	}
}
