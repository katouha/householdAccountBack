package com.example.householdAccount.requestDto.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserReqDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("nowPassword")
	private String nowPassword;
	
	@JsonProperty("newPassword")
	private String newPassword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNowPassword() {
		return nowPassword;
	}

	public void setNowPassword(String nowPassword) {
		this.nowPassword = nowPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
