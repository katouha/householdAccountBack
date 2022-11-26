package com.example.householdAccount.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.mail.MailSend;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.requestDto.user.ReissuePasswordReqDto;
import com.example.householdAccount.responseDto.ResponseBase;

@Service
public class ReissuePasswordService {
	
	@Autowired
	ResponseBase resDto = new ResponseBase();
	
	@Autowired
	UserImpl getRegistUserImpl;
	
	@Autowired
	MailSend mailSend;
	
	public ResponseBase reIssuePassword(ReissuePasswordReqDto reqDto) {
		
		try {
			if(validationCheck(reqDto)) {
				return resDto;
			}
			TLoginUserMapperDto tLoginInfo = getRegistUserImpl.getRegistUser(reqDto.getUserId());
			if(tLoginInfo == null) {
				setErrorReqInfo(HouseholdAccountConstant.NOT_REGIST_USER);
				return resDto;
			}
			
			mailSend.reIssuePasswordMail(tLoginInfo.getMail_address(),tLoginInfo.getUser_name());
			
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.REISSUE_PASSWORD_ERROR);
			return resDto;
		}
		setSuccessReqInfo();
		
		return resDto;
	}
	
	
	/**
	 * リクエストパラメータバリデーションチェック
	 * @param reqDto
	 * @return エラー：true エラーなし：false
	 */
	private boolean validationCheck(ReissuePasswordReqDto reqDto) {
		boolean flg = false;
		if(reqDto.getUserId().isEmpty()) {
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
		ResponseBase errorResDto = new ResponseBase();
		errorResDto.setErrorMessage(message);;
		errorResDto.setReturnCd(HouseholdAccountConstant.PARAM_ERROR_CODE);
		resDto = errorResDto;
	}
	
	/**
	 * 成功レスポンス詰め込み処理
	 * @param req(リクエスト内容)
	 * @param res(レスポンス内容)
	 */
	private void setSuccessReqInfo() {
		ResponseBase successResDto = new ResponseBase();
		successResDto.setErrorMessage("");;
		successResDto.setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		resDto = successResDto;
	}

}
