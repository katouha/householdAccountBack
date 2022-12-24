package com.example.householdAccount.impl;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegistItemImpl {
	
	@Insert("INSERT INTO t_income_item (user_id, income_item_id,income_item_name,delete_flg) VALUES (#{user_id}, #{income_item_id}, #{income_item_name}, false)")
	void setIncomeItem(@Param("user_id") String user_Id, @Param("income_item_id") String income_item_id, @Param("income_item_name") String income_item_name);
	
	@Insert("INSERT INTO t_use_item (user_id, item_id,item_name,delete_flg) VALUES (#{user_id}, #{item_id}, #{item_name}, false)")
	void setUseItem(@Param("user_id") String user_Id, @Param("item_id") String item_id, @Param("item_name") String item_name);
	
	@Insert("INSERT INTO t_location (user_id, location_id,location_name,delete_flg) VALUES (#{user_id}, #{location_id}, #{location_name}, false)")
	void setLocationItem(@Param("user_id") String user_Id, @Param("location_id") String location_id, @Param("location_name") String location_name);

}
