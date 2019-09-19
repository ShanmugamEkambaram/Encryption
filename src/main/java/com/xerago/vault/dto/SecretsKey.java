package com.xerago.vault.dto;

public class SecretsKey {
	private String type;
	private String derived;
	private String token;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDerived() {
		return derived;
	}

	public void setDerived(String derived) {
		this.derived = derived;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
