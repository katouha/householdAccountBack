package com.example.householdAccount.requestDto.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetUseItemReqDto {
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("itemNameList")
	private List<ItemNameList> itemNameList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<ItemNameList> getItemNameList() {
		return itemNameList;
	}
	public void setItemNameList(List<ItemNameList> itemNameList) {
		this.itemNameList = itemNameList;
	}



	public static class ItemNameList{
		@JsonProperty("itemName")
		private String itemName;

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		
	}
}
