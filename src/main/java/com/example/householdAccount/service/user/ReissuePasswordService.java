package com.example.householdAccount.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdAccount.common.HouseholdAccountConstant;
import com.example.householdAccount.impl.UserImpl;
import com.example.householdAccount.mail.MailSend;
import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;
import com.example.householdAccount.requestDto.user.ReissuePasswordReqDto;
import com.example.householdAccount.responseDto.user.CommonResDto;

@Service
public class ReissuePasswordService {
	
	@Autowired
	CommonResDto resDto = new CommonResDto();
	
	@Autowired
	UserImpl userImpl;
	
	@Autowired
	MailSend mailSend;
	
	public CommonResDto reIssuePassword(ReissuePasswordReqDto reqDto) {
		
		try {
			if(validationCheck(reqDto)) {
				return resDto;
			}
			TLoginUserMapperDto tLoginInfo = userImpl.getRegistUser(reqDto.getUserId());
			if(tLoginInfo == null) {
				setErrorReqInfo(HouseholdAccountConstant.NOT_REGIST_USER);
				return resDto;
			}
			//再発行パスワードを生成
			String reissuePass = createPass();
			//再発行パスワードにDBデータを更新
			userImpl.updateUserPassword(reqDto.getUserId(), reissuePass);
			//メールの送信
			mailSend.reIssuePasswordMail(tLoginInfo.getMail_address(),tLoginInfo.getUser_name(),reissuePass);
			
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
		successResDto.getResult().setErrorMessage("");;
		successResDto.getResult().setReturnCd(HouseholdAccountConstant.API_SUCCESS);
		resDto = successResDto;
	}
	
	
	/**
	 * ランダムパスワードを生成し返却する「
	 * @return
	 */
	private String createPass() {
		String password = null;
	    boolean a = true;
	    while (a) {
	      final List<Boolean> checks = new ArrayList<>();
			  password = pass(); // pass生成
			  checks.add(password.matches(".*[a-z].*"));
			  checks.add(password.matches(".*[A-Z].*"));
			  checks.add(password.matches(".*[1-9].*"));
			  checks.add(password.matches(".*[!#$%].*"));
			  a = !checks.stream().allMatch(e -> e.equals(Boolean.TRUE));
	    }
	    return password;
	}
	
	/**
	 * 特定文字列をランダムに組み合わせてパスワードを生成
	 * @return
	 */
	public static String pass() {
	    final StringBuilder sb = new StringBuilder(
	      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%");
	    final StringBuilder password = new StringBuilder();
	    final Random rand = new Random();
	    IntStream.rangeClosed(0, 11).forEach(i -> {
			  final int num = rand.nextInt(sb.length());
			  password.append(sb.charAt(num));
	    });
	    return password.toString();
	}

}
