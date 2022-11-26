package com.example.householdAccount.responseDto.user;

import org.springframework.stereotype.Component;

import com.example.householdAccount.responseDto.ResponseBase;

@Component
public class RegistUserResDto {
	private ResponseBase result;
	
	public RegistUserResDto() {
		this.result = new ResponseBase();
	}

	public ResponseBase getResult() {
		return result;
	}

	public void setResult(ResponseBase result) {
		this.result = result;
	}

	
	
}
