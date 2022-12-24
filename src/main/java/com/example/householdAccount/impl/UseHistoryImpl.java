package com.example.householdAccount.impl;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UseHistoryImpl {
	@Insert("INSERT INTO t_use_history (user_id, use_date,item_id,location_id,product_name,money, delete_flg) VALUES (#{user_id}, #{use_date},#{item_id},#{location_id},#{product_name}, #{money}, false)")
	void setUseHistory(@Param("user_id") String user_Id,@Param("use_date") Timestamp use_date,@Param("item_id")String item_id, @Param("location_id")String location_id,@Param("product_name")String product_name,@Param("money")int money);
}
