package com.xerago.vault.dto;

import java.util.List;

public class VaultRequestDTO {

	private String token;
	private List<BatchInput> batch_input;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<BatchInput> getBatch_input() {
		return batch_input;
	}

	public void setBatch_input(List<BatchInput> batch_input) {
		this.batch_input = batch_input;
	}

}
