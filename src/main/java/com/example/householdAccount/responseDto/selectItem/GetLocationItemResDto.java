package com.example.householdAccount.responseDto.selectItem;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.householdAccount.responseDto.ResponseBase;

@Component
public class GetLocationItemResDto {

	private Result result;
	
	public GetLocationItemResDto() {
		this.result = new Result();
	}
	
	public class Result extends ResponseBase{
		List<LocationItemList> locationItemList;

		public List<LocationItemList> getLocationItemList() {
			return locationItemList;
		}

		public void setLocationItemList(List<LocationItemList> locationItemList) {
			this.locationItemList = locationItemList;
		}

		public class LocationItemList{
			
			private String locationId;
			
			private String locationName;

			public String getLocationId() {
				return locationId;
			}

			public void setLocationId(String locationId) {
				this.locationId = locationId;
			}

			public String getLocationName() {
				return locationName;
			}

			public void setLocationName(String locationName) {
				this.locationName = locationName;
			}

			
		}
		
	}
	

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
