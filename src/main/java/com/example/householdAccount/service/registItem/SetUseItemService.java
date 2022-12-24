package com.example.householdAccount.service.registItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.RegistItemImpl;
import com.example.householdAccount.impl.SelectItemImpl;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.requestDto.user.SetUseItemReqDto;
import com.example.householdAccount.requestDto.user.SetUseItemReqDto.ItemNameList;
import com.example.householdAccount.responseDto.user.CommonResDto;
import com.example.householdAccount.util.CommonUtil;

@Service
public class SetUseItemService {
	
	@Autowired
	CommonResDto resDto = new CommonResDto();
	
	@Autowired
	RegistItemImpl registItemImpl;
	
	@Autowired
	SelectItemImpl selectItemImpl;
	
	@Autowired
	UserImpl userImpl;
	
	CommonUtil commonUtil = new CommonUtil();
	
	public CommonResDto setUseItem(SetUseItemReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		
		try {
			//現状のID最大値を取得
			String maxId = selectItemImpl.getMaxIdUseItem();
			Integer useItemId = commonUtil.getStartId(maxId);
			for(ItemNameList useItem : reqDto.getItemNameList()) {
				registItemImpl.setUseItem(reqDto.getUserId(), useItemId.toString(), useItem.getItemName());
				useItemId ++;
			}
			setSuccessReqInfo();
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.NOT_REGIST_USE_ITEM_ERROR);
			return resDto;
		}
		
		return resDto;
		
	}
	
	
	/**
	 * リクエストパラメータバリデーションチェック
	 * @param reqDto
	 * @return エラー：true エラーなし：false
	 */
	private boolean validationCheck(SetUseItemReqDto reqDto) {
		boolean flg = false;
		
		if(reqDto.getUserId() == null || reqDto.getUserId() == "") {
			setErrorReqInfo(HouseholdAccountConstant.USER_ID_PARAM_ERROR);
			flg = true;
		}
		
		if(reqDto.getItemNameList() == null && reqDto.getItemNameList().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.USE_ITEM_PARAM_ERROR);
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
		CommonResDto errorResDto = new CommonResDto();
		errorResDto.getResult().setErrorMessage(message);;
		errorResDto.getResult().setReturnCd(HouseholdAccountConstant.PARAM_ERROR_CODE);
		resDto = errorResDto;
	}
	
	/**
	 * 成功レスポンス詰め込み処理
	 * @param req(リクエスト内容)
	 * @param res(レスポンス内容)
	 */
	private void setSuccessReqInfo() {
		CommonResDto successResDto = new CommonResDto();
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		resDto = successResDto;
	}
}
