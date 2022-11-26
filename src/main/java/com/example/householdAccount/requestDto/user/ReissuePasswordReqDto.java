package com.example.householdAccount.requestDto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReissuePasswordReqDto {
	@JsonProperty("userId")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
