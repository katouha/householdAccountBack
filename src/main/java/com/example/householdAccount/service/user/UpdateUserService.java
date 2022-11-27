package com.example.householdAccount.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.requestDto.user.UpdateUserReqDto;
import com.example.householdAccount.responseDto.user.UpdateUserResDto;

@Service
public class UpdateUserService {

	@Autowired
	UserImpl userImpl;
	
	@Autowired
	UpdateUserResDto resDto = new UpdateUserResDto();
	
	public UpdateUserResDto updateUser(UpdateUserReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		try {
//			userImpl.updateUser(reqDto.getUserId(), reqDto.getNewPassword());
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.UPDATE_USER_ERROR);
			return resDto;
		}
		
		setSuccessReqInfo(reqDto);
		//jsonレスポンス
		return resDto;
	}
	
	
	/**
	 * リクエストパラメータバリデーションチェック
	 * @param reqDto
	 * @return エラー：true エラーなし：false
	 */
	private boolean validationCheck(UpdateUserReqDto reqDto) {
		boolean flg = false;
		if(reqDto.getUserId().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.USER_ID_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getNowPassword().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.NOW_PASSWORD_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getNewPassword().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.NEW_PASSWORD_PARAM_ERROR);
			flg = true;
		}
		TLoginUserMapperDto tLoginInfo = null;
		try {
			tLoginInfo = userImpl.getRegistUser(reqDto.getUserId());
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.REGIST_USER_ERROR);
			flg = true;
		}
		if(tLoginInfo == null) {
			setErrorReqInfo(HouseholdAccountConstant.NOT_REGIST_USER);
			flg = true;
		}else if(!tLoginInfo.getPassword().equals(reqDto.getNowPassword())) {
			setErrorReqInfo(HouseholdAccountConstant.PASSWORD_NOT_EQUALES);
			flg = true;
		}else if(tLoginInfo.getPassword().equals(reqDto.getNewPassword())) {
			setErrorReqInfo(HouseholdAccountConstant.ALREADY_REGIST_PASSWORD);
			flg = true;
		}
		
		
		return flg;
	}
	
	/**
	 * エラーレスポンス詰め込み処理
	 * @param message(エラーメッセージ文言)
	 */
	private void setErrorReqInfo(String message) {
		UpdateUserResDto errorResDto = new UpdateUserResDto();
		errorResDto.getResult().setErrorMessage(message);;
		errorResDto.getResult().setReturnCd(HouseholdAccountConstant.PARAM_ERROR_CODE);
		resDto = errorResDto;
	}
	
	/**
	 * 成功レスポンス詰め込み処理
	 * @param req(リクエスト内容)
	 */
	private void setSuccessReqInfo(UpdateUserReqDto req) {
		UpdateUserResDto successResDto = new UpdateUserResDto();
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		resDto = successResDto;
	}
}
