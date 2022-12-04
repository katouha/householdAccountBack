package com.example.householdAccount.responseDto.selectItem;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.householdAccount.responseDto.ResponseBase;


@Component
public class GetIncomeItemResDto {
	
	private Result result;
	
	public GetIncomeItemResDto() {
		this.result = new Result();
	}
	
	public class Result extends ResponseBase{
		List<IncomeItemList> incomeList;

		public List<IncomeItemList> getIncomeList() {
			return incomeList;
		}

		public void setIncomeList(List<IncomeItemList> incomeList) {
			this.incomeList = incomeList;
		}
		
		public class IncomeItemList{
			
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
