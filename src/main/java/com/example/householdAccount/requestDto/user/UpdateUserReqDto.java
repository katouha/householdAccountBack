package com.example.householdAccount.requestDto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserReqDto {
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("mailaddress")
	private String mailaddress;
	
	@JsonProperty("genderType")
	private String genderType;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMailaddress() {
		return mailaddress;
	}

	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}

	public String getGenderType() {
		return genderType;
	}

	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}
	
}
