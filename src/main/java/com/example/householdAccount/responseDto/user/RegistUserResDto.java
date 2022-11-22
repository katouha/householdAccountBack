package com.example.householdAccount.responseDto.user;

import org.springframework.stereotype.Component;

import com.example.householdAccount.responseDto.ResponseBase;

@Component
public class RegistUserResDto {
	private Result result;
	
	public RegistUserResDto() {
		this.result = new Result();
	}
	
	public class Result extends ResponseBase{
		private String userId;
		
		private String password;
		
		private String userName;
		
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
		
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
}
