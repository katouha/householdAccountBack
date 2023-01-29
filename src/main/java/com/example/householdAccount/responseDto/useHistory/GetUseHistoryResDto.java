package com.example.householdAccount.responseDto.useHistory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.householdAccount.responseDto.ResponseBase;

@Component
public class GetUseHistoryResDto {
	
	public GetUseHistoryResDto() {
		this.result = new Result();
	}
	
	private Result result;
	
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	
	public static class Result extends ResponseBase{
		
		private List<YearMonth> yearMonthList;
		
		public List<YearMonth> getYearMonthList() {
			return yearMonthList;
		}

		public void setYearMonthList(List<YearMonth> yearMonthList) {
			this.yearMonthList = yearMonthList;
		}

		public static class YearMonth{
			
			private String yearMonth;
			
			private List<Day> dayList;
			
			
			public String getYearMonth() {
				return yearMonth;
			}
			public void setYearMonth(String yearMonth) {
				this.yearMonth = yearMonth;
			}
			
			public List<Day> getDayList() {
				return dayList;
			}
			public void setDayList(List<Day> dayList) {
				this.dayList = dayList;
			}


			public static class Day{
				
				private String day;
				
				private List<UseItem> useItemList;

				public String getDay() {
					return day;
				}
				public void setDay(String day) {
					this.day = day;
				}

				public List<UseItem> getUseItemList() {
					return useItemList;
				}
				public void setUseItemList(List<UseItem> useItemList) {
					this.useItemList = useItemList;
				}
				
				public static class UseItem{
					
					private String useItemName;
					
					private String useLocationName;
					
					private List<UseInfo> useInfoList;
					
					public String getUseItemName() {
						return useItemName;
					}
					public void setUseItemName(String useItemName) {
						this.useItemName = useItemName;
					}

					public String getUseLocationName() {
						return useLocationName;
					}
					public void setUseLocationName(String useLocationName) {
						this.useLocationName = useLocationName;
					}

					public List<UseInfo> getUseInfoList() {
						return useInfoList;
					}
					public void setUseInfoList(List<UseInfo> useInfoList) {
						this.useInfoList = useInfoList;
					}

					public static class UseInfo{
						
						private String productName;
						
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
				
			}
		}
	}
}
