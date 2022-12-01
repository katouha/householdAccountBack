package com.example.householdAccount.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.requestDto.user.UpdateUserReqDto;
import com.example.householdAccount.responseDto.user.CommonResDto;

@Service
public class UpdateUserService {

	@Autowired
	UserImpl userImpl;
	
	@Autowired
	CommonResDto resDto = new CommonResDto();
	
	public CommonResDto updateUser(UpdateUserReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		try {
			userImpl.updateUserInfo(reqDto.getUserId(), reqDto.getUserName(),reqDto.getMailaddress(),reqDto.getGenderType());
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
		if(reqDto.getUserName().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.USER_NAME_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getMailaddress().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.MAIL_ADDRESS_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getGenderType().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.GENDER_TYPE_PARAM_ERROR);
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
	private void setSuccessReqInfo(UpdateUserReqDto req) {
		CommonResDto successResDto = new CommonResDto();
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		successResDto.getResult().setErrorMessage("");
		resDto = successResDto;
	}
}
