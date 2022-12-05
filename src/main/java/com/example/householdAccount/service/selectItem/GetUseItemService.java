package com.example.householdAccount.service.selectItem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.SelectItemImpl;
import com.example.householdAccount.postgresMapperDto.UseItemMapperDto;
import com.example.householdAccount.requestDto.user.UserIdReqDto;
import com.example.householdAccount.responseDto.selectItem.GetUseItemResDto;
import com.example.householdAccount.responseDto.selectItem.GetUseItemResDto.Result.UseItemList;

@Service
public class GetUseItemService {
	
	@Autowired
	SelectItemImpl selectItemImpl;
	
	@Autowired
	GetUseItemResDto resDto = new GetUseItemResDto();
	
	public GetUseItemResDto getUseItem(UserIdReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		List<UseItemMapperDto> useItem = selectItemImpl.getUseItem(reqDto.getUserId());
		if(useItem == null || useItem.isEmpty()) { 
			setErrorReqInfo(HouseholdAccountConstant.NOT_USER_USE_ITEM_ERROR);
			return resDto;
		}
		setSuccessReqInfo(useItem);
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
		GetUseItemResDto errorResDto = new GetUseItemResDto();
		errorResDto.getResult().setErrorMessage(message);;
		errorResDto.getResult().setReturnCd(HouseholdAccountConstant.PARAM_ERROR_CODE);
		resDto = errorResDto;
	}
	
	/**
	 * 成功レスポンス詰め込み処理
	 * @param req(リクエスト内容)
	 * @param res(レスポンス内容)
	 */
	private void setSuccessReqInfo(List<UseItemMapperDto> useItem) {
		GetUseItemResDto successResDto = new GetUseItemResDto();
		List<UseItemList> itemList = new ArrayList<UseItemList>();
		for(UseItemMapperDto item : useItem) {
			UseItemList use = successResDto.getResult().new UseItemList();
			use.setItemId(item.getItem_id());
			use.setItemName(item.getItem_name());
			itemList.add(use);
		}
		successResDto.getResult().setUseItemList(itemList);
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		resDto = successResDto;
	}
	
	

}
