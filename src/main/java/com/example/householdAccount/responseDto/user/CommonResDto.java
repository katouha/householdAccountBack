package com.example.householdAccount.responseDto.user;

import org.springframework.stereotype.Component;

import com.example.householdAccount.responseDto.ResponseBase;

@Component
public class CommonResDto {
	
	private Result result;
	
	public CommonResDto() {
		this.result = new Result();
	}
	
	public class Result extends ResponseBase{
		
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
