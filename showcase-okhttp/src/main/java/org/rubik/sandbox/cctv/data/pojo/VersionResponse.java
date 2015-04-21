package org.rubik.sandbox.cctv.data.pojo;

import com.google.common.base.Objects;

public class VersionResponse {

	private int code;
	private boolean success;
	private String message;
	private VersionResult result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public VersionResult getResult() {
		return result;
	}

	public void setResult(VersionResult result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.omitNullValues()
				.add("code", code)
				.add("succcess", success)
				.add("message", message)
				.add("result", result)
				.toString();
	}
}
