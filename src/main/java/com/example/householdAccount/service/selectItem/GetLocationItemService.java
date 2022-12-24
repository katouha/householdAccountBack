package com.example.householdAccount.service.selectItem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.SelectItemImpl;
import com.example.householdAccount.postgresMapperDto.LocationItemMapperDto;
import com.example.householdAccount.requestDto.user.UserIdReqDto;
import com.example.householdAccount.responseDto.selectItem.GetLocationItemResDto;
import com.example.householdAccount.responseDto.selectItem.GetLocationItemResDto.Result.LocationItemList;

@Service
public class GetLocationItemService {
	
	@Autowired
	SelectItemImpl selectItemImpl;
	
	@Autowired
	GetLocationItemResDto resDto = new GetLocationItemResDto();
	
	public GetLocationItemResDto getLocationItem(UserIdReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		try {
			List<LocationItemMapperDto> locationItem = selectItemImpl.getLocationItem(reqDto.getUserId());
			if(locationItem == null || locationItem.isEmpty()) { 
				setErrorReqInfo(HouseholdAccountConstant.NOT_USER_LOCATION_ITEM_ERROR);
				return resDto;
			}
			setSuccessReqInfo(locationItem);
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.NOT_USER_LOCATION_ITEM_ERROR);
			return resDto;
		}
		return resDto;
	}
	
	
	
	/**
	 * リクエストパラメータバリデーションチェック
	 * @param reqDto
	 * @return エラー：true エラーなし：false
	 */
	private boolean validationCheck(UserIdReqDto reqDto) {
		boolean flg = false;
		
		if(reqDto.getUserId() == null || reqDto.getUserId() == "") {
			setErrorReqInfo(HouseholdAccountConstant.USER_ID_PARAM_ERROR);
			flg = true;
		}
		
		return flg;
	}
	
	/**
	 * エラーレスポンス詰め込み処理
	 * @param message(エラーメッセージ文言)
	 */
	private void setErrorReqInfo(String message) {
		GetLocationItemResDto errorResDto = new GetLocationItemResDto();
		errorResDto.getResult().setErrorMessage(message);;
		errorResDto.getResult().setReturnCd(HouseholdAccountConstant.PARAM_ERROR_CODE);
		resDto = errorResDto;
	}
	
	/**
	 * 成功レスポンス詰め込み処理
	 * @param req(リクエスト内容)
	 * @param res(レスポンス内容)
	 */
	private void setSuccessReqInfo(List<LocationItemMapperDto> locationItem) {
		GetLocationItemResDto successResDto = new GetLocationItemResDto();
		List<LocationItemList> itemList = new ArrayList<LocationItemList>();
		for(LocationItemMapperDto item : locationItem) {
			LocationItemList location = successResDto.getResult().new LocationItemList();
			location.setLocationId(item.getLocation_id());
			location.setLocationName(item.getLocation_name());
			itemList.add(location);
		}
		successResDto.getResult().setLocationItemList(itemList);
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		resDto = successResDto;
	}

}
