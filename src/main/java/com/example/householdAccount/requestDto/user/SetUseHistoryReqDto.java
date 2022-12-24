package com.example.householdAccount.requestDto.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetUseHistoryReqDto {
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("useItemId")
	private String useItemId;
	
	@JsonProperty("useLocationId")
	private String useLocationId;
	
	@JsonProperty("useDate")
	private String useDate;
	
	@JsonProperty("useInfoList")
	private List<UseInfoList> useInfoList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public List<UseInfoList> getUseInfoList() {
		return useInfoList;
	}
	public void setUseInfoList(List<UseInfoList> useInfoList) {
		this.useInfoList = useInfoList;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}



	public static class UseInfoList{
		@JsonProperty("productName")
		private String productName;
		
		@JsonProperty("money")
		private int money;

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public int getMoney() {
			return money;
		}

		public void setMoney(int money) {
			this.money = money;
		}
		
	}

}
