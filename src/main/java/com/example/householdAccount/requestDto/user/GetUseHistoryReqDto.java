package com.example.householdAccount.requestDto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUseHistoryReqDto {
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("yearMonth")
	private String yearMonth;
	
	@JsonProperty("day")
	private String day;
	
	@JsonProperty("useItemId")
	private String useItemId;
	
	@JsonProperty("useLocationId")
	private String useLocationId;
	
	@JsonProperty("productName")
	private String productName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getUseItemId() {
		return useItemId;
	}

	public void setUseItemId(String useItemId) {
		this.useItemId = useItemId;
	}

	public String getUseLocationId() {
		return useLocationId;
	}

	public void setUseLocationId(String useLocationId) {
		this.useLocationId = useLocationId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
