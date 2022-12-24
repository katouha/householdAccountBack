package com.example.householdAccount.requestDto.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetLocationItemReqDto {
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("locationItemList")
	private List<LocationItemList> locationItemList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<LocationItemList> getLocationItemList() {
		return locationItemList;
	}
	public void setLocationItemList(List<LocationItemList> locationItemList) {
		this.locationItemList = locationItemList;
	}



	public static class LocationItemList{
		
		@JsonProperty("locationItemName")
		private String locationItemName;

		public String getLocationItemName() {
			return locationItemName;
		}

		public void setLocationItemName(String locationItemName) {
			this.locationItemName = locationItemName;
		}

	}
}
