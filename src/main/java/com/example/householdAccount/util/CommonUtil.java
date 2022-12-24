package com.example.householdAccount.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	/**
	 * yyyy-MM-dd型文字列をTimestamp型に変換
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public Timestamp strDateForTimestamp(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(strDate);
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}
	
	/**
	 * IDの値によってDB登録NOを生成
	 * @param maxId
	 * @return
	 */
	public int getStartId(String maxId) {
		if(maxId == null || Integer.parseInt(maxId) < 100) {	//登録がない場合や100以下の場合は100から登録
			return 100;
		}else {
			return Integer.parseInt(maxId) + 1;				//100番以上の登録がある場合はそのID+1を登録
		}
	}

}
