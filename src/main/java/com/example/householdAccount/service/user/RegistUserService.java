package com.example.householdAccount.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.requestDto.user.RegistUserReqDto;
import com.example.householdAccount.responseDto.user.RegistUserResDto;

@Service
public class RegistUserService {
	
	@Autowired
	UserImpl registUserImpl;
	
	@Autowired
	RegistUserResDto resDto = new RegistUserResDto();
	
	
	public RegistUserResDto registUser(RegistUserReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		try {
			registUserImpl.registUser(reqDto.getUserId(), reqDto.getPassword(), reqDto.getUserName(),reqDto.getMailAddress(), reqDto.getRoleId());
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.REGIST_USER_ERROR);
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
		if(reqDto.getRoleId().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.ROLE_ID_PARAM_ERROR);
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
		RegistUserResDto errorResDto = new RegistUserResDto();
		errorResDto.getResult().setErrorMessage(message);;
		errorResDto.getResult().setReturnCd(HouseholdAccountConstant.PARAM_ERROR_CODE);
		resDto = errorResDto;
	}
	
	/**
	 * 成功レスポンス詰め込み処理
	 * @param req(リクエスト内容)
	 */
	private void setSuccessReqInfo(RegistUserReqDto req) {
		RegistUserResDto successResDto = new RegistUserResDto();
		successResDto.getResult().setPassword(req.getPassword());
		successResDto.getResult().setUserId(req.getUserId());
		successResDto.getResult().setUserName(req.getUserName());
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		resDto = successResDto;
	}
	
	
}
