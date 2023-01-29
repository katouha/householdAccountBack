package com.example.householdAccount.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
	
	/**
	 * yyyy-MM-dd型文字列をTimestamp型に変換
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public Timestamp strDateForTimestamp(String strDate) throws ParseException {
		Date date = strDateForDate(strDate);
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}
	
	/**
	 * yyyy-MM-dd型文字列をDate型に変換
	 * @param strDate
	 * @return 日付型
	 * @throws ParseException 
	 */
	public Date strDateForDate(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(strDate);
		return date;
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
	
	/**
	 * 文字列型の年月日から検索値を返却
	 * @param yearMonth
	 * @param day
	 * @return 検索初日＋検索最終日
	 * @throws ParseException
	 */
	public SearchDate strDateMargeTimestamp(String yearMonth,String day) throws ParseException {
		SearchDate searchDate = new SearchDate();
		if(day != null) {		//日にちが渡されていれば年月に結合
			yearMonth += day;
			searchDate.setBeforeDate(strDateForTimestamp(yearMonth));
			searchDate.setAfterDate(strMonthLastDateTimestamp(yearMonth));
		}else {					//日にちが渡されていなければ初日
			yearMonth += "01";
			searchDate.setBeforeDate(strDateForTimestamp(yearMonth));
			searchDate.setAfterDate(strMonthLastDateTimestamp(yearMonth));
		}
		
		return searchDate;
	}
	
	/**
	 * 引数で受けた文字列型日付月の最終日をタイムスタンプ型で返却
	 * @param targetDate
	 * @return 月の最終日(Timestamp)
	 * @throws ParseException 
	 */
	public Timestamp strMonthLastDateTimestamp(String targetDate) throws ParseException {
		Date date = strDateForDate(targetDate);
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		//月の最終日をセット
		cl.set(Calendar.DATE, cl.getActualMaximum(Calendar.DATE));
		// 時間
		cl.set(Calendar.HOUR_OF_DAY, 23);
		// 分
		cl.set(Calendar.MINUTE, 59);
		// 秒
		cl.set(Calendar.SECOND, 59);
		// ミリ秒
		cl.set(Calendar.MILLISECOND, 999); 
		date = cl.getTime();
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
		
	}
	
	/**
	 * タイムスタンプ型からyyyy年MM月の文字列型に変換
	 * @param targetDate
	 * @return yyyy年MM月の文字列
	 */
	public String timestampForStringYearMonth(Timestamp targetDate) {
		Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(targetDate.getTime());
        Integer year = cl.get(Calendar.YEAR);
        Integer month = cl.get(Calendar.MONTH) + 1;
        String yearMonth = year.toString() + "年" + month.toString() + "月";
        return yearMonth;
	}
	
	/**
	 * タイムスタンプ型からdd日の文字列型に変換
	 * @param targetDate
	 * @return dd日の文字列
	 */
	public String timestampForStringDay(Timestamp targetDate) {
		Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(targetDate.getTime());
        Integer day = cl.get(Calendar.DATE);
        return day.toString() + "日";
	}
	
	
	public static class SearchDate{
		private Timestamp beforeDate;
		private Timestamp afterDate;
		public Timestamp getBeforeDate() {
			return beforeDate;
		}
		public void setBeforeDate(Timestamp beforeDate) {
			this.beforeDate = beforeDate;
		}
		public Timestamp getAfterDate() {
			return afterDate;
		}
		public void setAfterDate(Timestamp afterDate) {
			this.afterDate = afterDate;
		}
		
	}

}
