package com.example.householdAccount.responseDto.selectItem;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.householdAccount.responseDto.ResponseBase;

@Component
public class GetUseItemResDto {
	private Result result;
	
	public GetUseItemResDto() {
		this.result = new Result();
	}
	
	public class Result extends ResponseBase{
		List<UseItemList> useItemList;

		
		
		public List<UseItemList> getUseItemList() {
			return useItemList;
		}


		public void setUseItemList(List<UseItemList> useItemList) {
			this.useItemList = useItemList;
		}


		public class UseItemList{
			
			private String itemId;
			
			private String itemName;

			public String getItemId() {
				return itemId;
			}

			public void setItemId(String itemId) {
				this.itemId = itemId;
			}

			public String getItemName() {
				return itemName;
			}

			public void setItemName(String itemName) {
				this.itemName = itemName;
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
