package com.example.householdAccount.service.useHistory;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.UseHistoryImpl;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.requestDto.user.SetUseHistoryReqDto;
import com.example.householdAccount.requestDto.user.SetUseHistoryReqDto.UseInfoList;
import com.example.householdAccount.responseDto.user.CommonResDto;
import com.example.householdAccount.util.CommonUtil;

@Service
public class SetUseHistoryService {
	
	@Autowired
	CommonResDto resDto = new CommonResDto();
	
	@Autowired
	UserImpl userImpl;
	
	@Autowired
	UseHistoryImpl useHistoryImpl;

	CommonUtil commonUtil = new CommonUtil();
	
	public CommonResDto setUseHistory(SetUseHistoryReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		try {
			Timestamp useDate = commonUtil.strDateForTimestamp(reqDto.getUseDate());
			for(UseInfoList useInfo : reqDto.getUseInfoList()) {
				useHistoryImpl.setUseHistory(reqDto.getUserId(), useDate, reqDto.getUseItemId(), reqDto.getUseLocationId(), useInfo.getProductName(), useInfo.getMoney());
			}
			setSuccessReqInfo();
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.NOT_REGIST_USE_HISTORY_ERROR);
			return resDto;
		}
		
		return resDto;
		
	}
	
	/**
	 * リクエストパラメータバリデーションチェック
	 * @param reqDto
	 * @return エラー：true エラーなし：false
	 */
	private boolean validationCheck(SetUseHistoryReqDto reqDto) {
		boolean flg = false;
		
		if(reqDto.getUserId() == null || reqDto.getUserId() == "") {
			setErrorReqInfo(HouseholdAccountConstant.USER_ID_PARAM_ERROR);
			flg = true;
		}
		
		if(reqDto.getUseItemId() == null) {
			setErrorReqInfo(HouseholdAccountConstant.USE_ITEM_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getUseLocationId() == null) {
			setErrorReqInfo(HouseholdAccountConstant.LOCATION_ITEM_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getUseDate() == null) {
			setErrorReqInfo(HouseholdAccountConstant.USE_DATE_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getUseInfoList() == null || reqDto.getUseInfoList().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.USE_INFO_PARAM_ERROR);
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
