package com.example.householdAccount.service.useHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.SelectItemImpl;
import com.example.householdAccount.impl.UseHistoryImpl;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.postgresMapperDto.LocationItemMapperDto;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.postgresMapperDto.UseHistoryMapperDto;
import com.example.householdAccount.postgresMapperDto.UseItemMapperDto;
import com.example.householdAccount.requestDto.user.GetUseHistoryReqDto;
import com.example.householdAccount.responseDto.useHistory.GetUseHistoryResDto;
import com.example.householdAccount.responseDto.useHistory.GetUseHistoryResDto.Result.YearMonth;
import com.example.householdAccount.responseDto.useHistory.GetUseHistoryResDto.Result.YearMonth.Day;
import com.example.householdAccount.responseDto.useHistory.GetUseHistoryResDto.Result.YearMonth.Day.UseItem;
import com.example.householdAccount.responseDto.useHistory.GetUseHistoryResDto.Result.YearMonth.Day.UseItem.UseInfo;
import com.example.householdAccount.util.CommonUtil;

@Service
public class GetUseHistoryService {
	
	@Autowired
	GetUseHistoryResDto resDto = new GetUseHistoryResDto();
	
	@Autowired
	UserImpl userImpl;

	@Autowired
	UseHistoryImpl useHistoryImpl;
	
	@Autowired
	SelectItemImpl selectItemImpl;
	
	CommonUtil commonUtil = new CommonUtil();
	
	public GetUseHistoryResDto getUseHistory(GetUseHistoryReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		try {
			List<UseHistoryMapperDto> searchDbDate = searchDate(reqDto);
			setSuccessReqInfo(searchDbDate,reqDto.getUserId(),reqDto.getDay());
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.NOT_SEARCH_USE_HISTORY_ERROR);
			return resDto;
		}
		
		return resDto;
		
	}
	
	/**
	 * リクエストパラメータバリデーションチェック
	 * @param reqDto
	 * @return エラー：true エラーなし：false
	 */
	private boolean validationCheck(GetUseHistoryReqDto reqDto) {
		boolean flg = false;
		
		if(reqDto.getUserId() == null || reqDto.getUserId() == "") {
			setErrorReqInfo(HouseholdAccountConstant.USER_ID_PARAM_ERROR);
			flg = true;
		}
		TLoginUserMapperDto userInfo = null;
		try {
			userInfo = userImpl.getRegistUser(reqDto.getUserId());
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.NOT_REGIST_USER);
			flg = true;
		}
		if(userInfo == null) {
			setErrorReqInfo(HouseholdAccountConstant.NOT_REGIST_USER);
			flg = true;
		}
		
		return flg;
	}
	
	/**
	 * エラーレスポンス詰め込み処理
	 * @param message(エラーメッセージ文言)
	 */
	private void setErrorReqInfo(String message) {
		GetUseHistoryResDto errorResDto = new GetUseHistoryResDto();
		errorResDto.getResult().setErrorMessage(message);;
		errorResDto.getResult().setReturnCd(HouseholdAccountConstant.PARAM_ERROR_CODE);
		resDto = errorResDto;
	}
	
	/**
	 * 成功レスポンス詰め込み処理
	 * @param req(リクエスト内容)
	 * @param res(レスポンス内容)
	 */
	private void setSuccessReqInfo(List<UseHistoryMapperDto> searchData,String userId,String day) {
		GetUseHistoryResDto successResDto = new GetUseHistoryResDto();
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		successResDto.getResult().setYearMonthList(convertReqData(searchData,userId,day));
		
		
		resDto = successResDto;
	}
	
	/**
	 * レスポンスの日付リスト生成
	 * @param searchData
	 * @return 日付リスト
	 */
	private List<YearMonth> convertReqData(List<UseHistoryMapperDto> searchData,String userId,String reqDay) {
		List<YearMonth> yearMonthList = new ArrayList<YearMonth>();
		//リクエストに日付があれば絞り込み
		if(reqDay != null) {
			searchData = searchData.stream().filter(dayInfo -> new SimpleDateFormat("dd").format(dayInfo.getUse_date()).equals(reqDay)).collect(Collectors.toList());
		}
		Map<String,List<UseHistoryMapperDto>> yearMonthMap = searchData.stream().collect(Collectors.groupingBy(yearMonth -> new SimpleDateFormat("yyyy年MM月").format(yearMonth.getUse_date())));
		//年月ループ
		for(Entry<String, List<UseHistoryMapperDto>> yearMonth : yearMonthMap.entrySet()) {
			YearMonth yearMonthObj = new YearMonth();
			yearMonthObj.setYearMonth(yearMonth.getKey());
			List<Day> dayList = new ArrayList<Day>();
			Map<String,List<UseHistoryMapperDto>> dateMap = yearMonth.getValue().stream().collect(Collectors.groupingBy(day -> new SimpleDateFormat("dd日(E)").format(day.getUse_date())));
			//日付ループ
			for(Entry<String, List<UseHistoryMapperDto>> date : dateMap.entrySet()) {
				Day dayObj = new Day();
				dayObj.setDay(date.getKey());
				List<UseItem> useItemList = new ArrayList<UseItem>();
				Map<String, List<UseHistoryMapperDto>> itemMap = date.getValue().stream().collect(Collectors.groupingBy(id -> id.getItem_id() + ":"+id.getLocation_id()));
				//idループ
				for(Entry<String, List<UseHistoryMapperDto>> idMap : itemMap.entrySet()) {
					UseItem useItemObj = new UseItem();
					//itemidとlocationidを分類
					String[] id = idMap.getKey().split(":");
					useItemObj.setUseItemName(getUseItemName(userId,id[0]));
					useItemObj.setUseLocationName(getLocationItemName(userId,id[1]));
					List<UseInfo> useInfoList = new ArrayList<UseInfo>();
					//対象データ抽出
					for(UseHistoryMapperDto valueMap : idMap.getValue()) {
						UseInfo useInfoObj = new UseInfo();
						useInfoObj.setMoney(valueMap.getMoney());
						useInfoObj.setProductName(valueMap.getProduct_name());
						useInfoList.add(useInfoObj);
					}
					useItemObj.setUseInfoList(useInfoList);
					useItemList.add(useItemObj);
				}
				dayObj.setUseItemList(useItemList);
				dayList.add(dayObj);
			}
			yearMonthObj.setDayList(dayList);
			yearMonthList.add(yearMonthObj);
		}
		return yearMonthList;
	}
	
	/**
	 * ユーザID、利用項目IDから一意なレコード取得
	 * @param userId
	 * @param itemId
	 * @return
	 */
	private String getUseItemName(String userId, String itemId) {
		UseItemMapperDto useItem = selectItemImpl.getUseItemFind(userId, itemId);
		return useItem.getItem_name();
	}
	
	/**
	 * ユーザID、場所IDから一意なレコード取得
	 * @param userId
	 * @param locationId
	 * @return
	 */
	private String getLocationItemName(String userId,String locationId) {
		LocationItemMapperDto locationItem = selectItemImpl.getLocationItemFind(userId, locationId);
		return locationItem.getLocation_name();	
	}
	
	/**
	 * リクエストを基にDB検索
	 * @param reqDto
	 * @return
	 * @throws ParseException
	 */
	private List<UseHistoryMapperDto> searchDate(GetUseHistoryReqDto reqDto) throws ParseException{
		List<UseHistoryMapperDto> searchDate = null;
		searchDate = useHistoryImpl.getUseHistorySearch(reqDto.getUserId(),
				reqDto.getYearMonth(),
				reqDto.getUseItemId(),
				reqDto.getUseLocationId(),
				reqDto.getProductName());
		
		return searchDate;
	}
	
	public class ItemLocationId{
		private String itemId;
		private String locatonId;
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public String getLocatonId() {
			return locatonId;
		}
		public void setLocatonId(String locatonId) {
			this.locatonId = locatonId;
		}
		
	}

}
