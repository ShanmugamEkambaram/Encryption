package com.xerago.vault.dto;

public class ValutResponseDTO {

	public String request_id;
	public String lease_id;
	public boolean renewable;
	public Integer lease_duration;
	public Data data;
	public String wrap_info;
	public String warnings;
	public String auth;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getLease_id() {
		return lease_id;
	}

	public void setLease_id(String lease_id) {
		this.lease_id = lease_id;
	}

	public boolean isRenewable() {
		return renewable;
	}

	public void setRenewable(boolean renewable) {
		this.renewable = renewable;
	}

	public Integer getLease_duration() {
		return lease_duration;
	}

	public void setLease_duration(Integer lease_duration) {
		this.lease_duration = lease_duration;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getWrap_info() {
		return wrap_info;
	}

	public void setWrap_info(String wrap_info) {
		this.wrap_info = wrap_info;
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

}
