package com.example.householdAccount.requestDto.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetIncomeHistoryReqDto {
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("incomeItemId")
	private String incomeItemId;
	
	@JsonProperty("incomeDate")
	private String incomeDate;
	
	@JsonProperty("incomeInfoList")
	private List<IncomeInfoList> incomeInfoList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIncomeItemId() {
		return incomeItemId;
	}
	public void setIncomeItemId(String incomeItemId) {
		this.incomeItemId = incomeItemId;
	}

	public String getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(String incomeDate) {
		this.incomeDate = incomeDate;
	}

	public List<IncomeInfoList> getIncomeInfoList() {
		return incomeInfoList;
	}
	public void setIncomeInfoList(List<IncomeInfoList> incomeInfoList) {
		this.incomeInfoList = incomeInfoList;
	}


	public static class IncomeInfoList{
		@JsonProperty("productName")
		private String productName;
		
		@JsonProperty("incomeMoney")
		private int incomeMoney;

		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}

		public int getIncomeMoney() {
			return incomeMoney;
		}
		public void setIncomeMoney(int incomeMoney) {
			this.incomeMoney = incomeMoney;
		}
		
	}
}
