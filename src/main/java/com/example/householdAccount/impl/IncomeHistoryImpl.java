package com.example.householdAccount.impl;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IncomeHistoryImpl {
	
	@Insert("INSERT INTO t_income_history (user_id, income_item_id,income_date,product_name,income_money, delete_flg) VALUES (#{user_id}, #{income_item_id},#{income_date},#{product_name}, #{income_money}, false)")
	void setIncomeHistory(@Param("user_id") String user_Id,@Param("income_item_id")String income_item_id,@Param("income_date") Timestamp income_date,@Param("product_name")String product_name,@Param("income_money")int income_money);

}
