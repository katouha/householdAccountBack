package com.example.householdAccount.responseDto.user;

import org.springframework.stereotype.Component;

import com.example.householdAccount.responseDto.ResponseBase;

@Component
public class LoginResDto {
	private Result result;
	
	public LoginResDto() {
		this.result = new Result();
	}
	
	public class Result extends ResponseBase{
		private String userId;
		
		private String password;
		
		private String userName;
		
		private String mailAddress;
		
		private String roleId;
		
		private String genderType;
		
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
		public String getGenderType() {
			return genderType;
		}
		public void setGenderType(String genderType) {
			this.genderType = genderType;
		}
		
		
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
}
