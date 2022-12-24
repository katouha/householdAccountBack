package com.example.householdAccount.postgresMapperDto;

import java.sql.Timestamp;

public class UseHistoryMapperDto {
	private int process_no;
	private String user_id;
	private Timestamp use_date;
	private String item_id;
	private String location_id;
	private String product_name;
	private int money;
	private boolean deleteFlg;
	private Timestamp insert_date;
	private Timestamp update_date;
	
	public int getProcess_no() {
		return process_no;
	}
	public void setProcess_no(int process_no) {
		this.process_no = process_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Timestamp getUse_date() {
		return use_date;
	}
	public void setUse_date(Timestamp use_date) {
		this.use_date = use_date;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
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
