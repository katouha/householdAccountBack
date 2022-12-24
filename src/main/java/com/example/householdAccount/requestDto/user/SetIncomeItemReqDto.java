package com.example.householdAccount.requestDto.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetIncomeItemReqDto {
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("incomeItemList")
	private List<IncomeItemList> incomeItemList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<IncomeItemList> getIncomeItemList() {
		return incomeItemList;
	}
	public void setIncomeItemList(List<IncomeItemList> incomeItemList) {
		this.incomeItemList = incomeItemList;
	}


	public static class IncomeItemList{
		@JsonProperty("incomeItemName")
		private String incomeItemName;

		public String getIncomeItemName() {
			return incomeItemName;
		}

		public void setIncomeItemName(String incomeItemName) {
			this.incomeItemName = incomeItemName;
		}
	}
}
