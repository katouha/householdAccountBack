package com.example.householdAccount.postgresMapperDto;

import java.sql.Timestamp;

public class IncomeItemMappperDto {
	private String user_id;
	private String income_item_id;
	private String income_item_name;
	private boolean deleteFlg;
	private Timestamp insert_date;
	private Timestamp update_date;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIncome_item_id() {
		return income_item_id;
	}
	public void setIncome_item_id(String income_item_id) {
		this.income_item_id = income_item_id;
	}
	public String getIncome_item_name() {
		return income_item_name;
	}
	public void setIncome_item_name(String income_item_name) {
		this.income_item_name = income_item_name;
	}
	public boolean isDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public Timestamp getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Timestamp insert_date) {
		this.insert_date = insert_date;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}
	
	
}
