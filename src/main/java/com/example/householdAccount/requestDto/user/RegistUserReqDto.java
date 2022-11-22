package com.example.householdAccount.requestDto.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistUserReqDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("mailAddress")
	private String mailAddress;
	
	@JsonProperty("roleId")
	private String roleId;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	

	

}
