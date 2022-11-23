package com.example.householdAccount.requestDto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginReqDto {
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("password")
	private String password;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
