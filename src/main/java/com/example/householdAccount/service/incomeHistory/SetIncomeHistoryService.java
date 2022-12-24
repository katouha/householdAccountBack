package com.example.householdAccount.service.incomeHistory;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.IncomeHistoryImpl;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.requestDto.user.SetIncomeHistoryReqDto;
import com.example.householdAccount.requestDto.user.SetIncomeHistoryReqDto.IncomeInfoList;
import com.example.householdAccount.responseDto.user.CommonResDto;
import com.example.householdAccount.util.CommonUtil;

@Service
public class SetIncomeHistoryService {
	
	@Autowired
	CommonResDto resDto = new CommonResDto();
	
	@Autowired
	UserImpl userImpl;
	
	@Autowired
	IncomeHistoryImpl incomeHistoryImpl;

	CommonUtil commonUtil = new CommonUtil();
	
	public CommonResDto setIncomeHistory(SetIncomeHistoryReqDto reqDto) {
		//レスポンスオブジェクト定義
		if(validationCheck(reqDto)) {
			return resDto;
		}
		try {
			Timestamp incomeDate = commonUtil.strDateForTimestamp(reqDto.getIncomeDate());
			for(IncomeInfoList incomeInfo : reqDto.getIncomeInfoList()) {
				incomeHistoryImpl.setIncomeHistory(reqDto.getUserId(),reqDto.getIncomeItemId(), incomeDate, incomeInfo.getProductName(), incomeInfo.getIncomeMoney());
			}
			setSuccessReqInfo();
		}catch(Exception e) {
			setErrorReqInfo(HouseholdAccountConstant.NOT_REGIST_INCOME_HISTORY_ERROR);
			return resDto;
		}
		
		return resDto;
		
	}
	
	/**
	 * リクエストパラメータバリデーションチェック
	 * @param reqDto
	 * @return エラー：true エラーなし：false
	 */
	private boolean validationCheck(SetIncomeHistoryReqDto reqDto) {
		boolean flg = false;
		
		if(reqDto.getUserId() == null || reqDto.getUserId() == "") {
			setErrorReqInfo(HouseholdAccountConstant.USER_ID_PARAM_ERROR);
			flg = true;
		}
		
		if(reqDto.getIncomeItemId() == null) {
			setErrorReqInfo(HouseholdAccountConstant.INCOME_ITEM_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getIncomeDate() == null) {
			setErrorReqInfo(HouseholdAccountConstant.INCOME_DATE_PARAM_ERROR);
			flg = true;
		}
		if(reqDto.getIncomeInfoList() == null || reqDto.getIncomeInfoList().isEmpty()) {
			setErrorReqInfo(HouseholdAccountConstant.INCOME_INFO_PARAM_ERROR);
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
