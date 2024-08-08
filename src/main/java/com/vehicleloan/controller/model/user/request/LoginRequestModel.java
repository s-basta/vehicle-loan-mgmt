package com.vehicleloan.controller.model.user.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginRequestModel {
	@NotNull(message="email cannot be null")
	private String username;
	
	@NotNull(message="password cannot be null")
	@Size(min=8, max=64, message="Password must be equal or greater than 8 characters and less than 16 characters")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
