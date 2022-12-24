package com.example.householdAccount.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.requestDto.user.RegistUserReqDto;
import com.example.householdAccount.responseDto.user.CommonResDto;

@Service
public class RegistUserService {
	
	@Autowired
	UserImpl registUserImpl;
	
	@Autowired
	CommonResDto resDto = new CommonResDto();
	
	
	public CommonResDto registUser(RegistUserReqDto reqDto) {
		try {
			//レスポンスオブジェクト定義
			if(validationCheck(reqDto)) {
				return resDto;
			}
			registUserImpl.registUser(reqDto.getUserId(), reqDto.getPassword(), reqDto.getUserName(),reqDto.getMailAddress(),reqDto.getGenderType());
			setSuccessReqInfo(reqDto);
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.REGIST_USER_ERROR);
			return resDto;
		}
		//jsonレスポンス
		return resDto;
	}
	
	
	/**
	 * リクエストパラメータバリデーションチェック
	 * @param reqDto
	 * @return エラー：true エラーなし：false
	 */
	private boolean validationCheck(RegistUserReqDto reqDto) {
		boolean flg = false;
		
		if(reqDto.getUserId().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.USER_ID_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getPassword().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.PASSWORD_ERROR);
			flg = true;
		}
		if(reqDto.getUserName().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.USER_NAME_PARAM_ERROR);
			flg = true;
		}
		TLoginUserMapperDto tLoginInfo = null;
		try {
			tLoginInfo = registUserImpl.getRegistUser(reqDto.getUserId());
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.REGIST_USER_ERROR);
			flg = true;
		}
		if(tLoginInfo != null) {
			setErrorReqInfo(HouseholdAccountConstant.ALREADY_REGIST_USER);
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
	 */
	private void setSuccessReqInfo(RegistUserReqDto req) {
		CommonResDto successResDto = new CommonResDto();
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		resDto = successResDto;
	}
	
	
}
