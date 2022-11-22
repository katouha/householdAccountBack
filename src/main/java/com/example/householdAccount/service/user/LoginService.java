package com.example.householdAccount.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.requestDto.user.LoginReqDto;
import com.example.householdAccount.responseDto.user.LoginResDto;

@Service
public class LoginService {
	
	@Autowired
	UserImpl loginImpl;
	
	@Autowired
	LoginResDto resDto = new LoginResDto();
	
	
	public LoginResDto getLogin(LoginReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		TLoginUserMapperDto tLoginInfo = null;
		try {
			tLoginInfo = loginImpl.getLogin(reqDto.getUserId(), reqDto.getPassword());
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.LOGIN_NOT_MATCH);
			return resDto;
		}
		if(tLoginInfo != null && tLoginInfo.getUser_id() != null) {
			setSuccessReqInfo(reqDto, tLoginInfo);
		}else {
			setErrorReqInfo(HouseholdAccountConstant.LOGIN_NOT_MATCH);
		}

		//jsonレスポンス
		return resDto;
	}
	
	
	/**
	 * リクエストパラメータバリデーションチェック
	 * @param reqDto
	 * @return エラー：true エラーなし：false
	 */
	private boolean validationCheck(LoginReqDto reqDto) {
		boolean flg = false;
		
		if(reqDto.getUserId().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.USER_ID_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getPassword().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.PASSWORD_ERROR);
			flg = true;
		}
		
		return flg;
	}
	
	/**
	 * エラーレスポンス詰め込み処理
	 * @param message(エラーメッセージ文言)
	 */
	private void setErrorReqInfo(String message) {
		LoginResDto errorResDto = new LoginResDto();
		errorResDto.getResult().setErrorMessage(message);;
		errorResDto.getResult().setReturnCd(HouseholdAccountConstant.PARAM_ERROR_CODE);
		resDto = errorResDto;
	}
	
	/**
	 * 成功レスポンス詰め込み処理
	 * @param req(リクエスト内容)
	 * @param res(レスポンス内容)
	 */
	private void setSuccessReqInfo(LoginReqDto req, TLoginUserMapperDto userMapper) {
		LoginResDto successResDto = new LoginResDto();
		successResDto.getResult().setUserId(userMapper.getUser_id());
		successResDto.getResult().setPassword(userMapper.getPassword());
		successResDto.getResult().setUserName(userMapper.getUser_name());
		successResDto.getResult().setMailAddress(userMapper.getMail_address());
		successResDto.getResult().setRoleId(userMapper.getRole_id());
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		resDto = successResDto;
	}
	
	
}
