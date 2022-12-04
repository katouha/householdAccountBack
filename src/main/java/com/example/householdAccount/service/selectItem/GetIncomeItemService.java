package com.example.householdAccount.service.selectItem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.SelectItemImpl;
import com.example.householdAccount.postgresMapperDto.IncomeItemMappperDto;
import com.example.householdAccount.requestDto.user.UserIdReqDto;
import com.example.householdAccount.responseDto.selectItem.GetIncomeItemResDto;
import com.example.householdAccount.responseDto.selectItem.GetIncomeItemResDto.Result.IncomeItemList;

@Service
public class GetIncomeItemService {
	
	@Autowired
	GetIncomeItemResDto resDto = new GetIncomeItemResDto();
	
	@Autowired
	SelectItemImpl selectItemImpl;
	
	public GetIncomeItemResDto getIncomeItem(UserIdReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		List<IncomeItemMappperDto> incomeItem = selectItemImpl.getIncomeItem(reqDto.getUserId());
		if(incomeItem == null || incomeItem.isEmpty()) { 
			setErrorReqInfo(HouseholdAccountConstant.NOT_USER_INCOME_ITEM_ERROR);
			return resDto;
		}
		setSuccessReqInfo(incomeItem);
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
		GetIncomeItemResDto errorResDto = new GetIncomeItemResDto();
		errorResDto.getResult().setErrorMessage(message);;
		errorResDto.getResult().setReturnCd(HouseholdAccountConstant.PARAM_ERROR_CODE);
		resDto = errorResDto;
	}
	
	/**
	 * 成功レスポンス詰め込み処理
	 * @param req(リクエスト内容)
	 * @param res(レスポンス内容)
	 */
	private void setSuccessReqInfo(List<IncomeItemMappperDto> imcomeItem) {
		GetIncomeItemResDto successResDto = new GetIncomeItemResDto();
		List<IncomeItemList> itemList = new ArrayList<IncomeItemList>();
		for(IncomeItemMappperDto item : imcomeItem) {
			IncomeItemList income = successResDto.getResult().new IncomeItemList();
			income.setItemId(item.getIncome_item_id());
			income.setItemName(item.getIncome_item_name());
			itemList.add(income);
		}
		successResDto.getResult().setIncomeList(itemList);
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		resDto = successResDto;
	}

}
