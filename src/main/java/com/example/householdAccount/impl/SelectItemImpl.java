package com.example.householdAccount.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.householdAccount.postgresMapperDto.IncomeItemMappperDto;
import com.example.householdAccount.postgresMapperDto.LocationItemMapperDto;
import com.example.householdAccount.postgresMapperDto.UseItemMapperDto;

@Mapper
public interface SelectItemImpl {
	
	@Select("SELECT * from t_income_item where user_id = #{user_id} and delete_flg = false")
	List<IncomeItemMappperDto> getIncomeItem(@Param("user_id") String user_Id);
	
	@Select("SELECT * from t_use_item where user_id = #{user_id} and delete_flg = false")
	List<UseItemMapperDto> getUseItem(@Param("user_id") String user_Id);
	
	@Select("SELECT * from t_use_item where user_id = #{user_id} and item_id = #{item_id} and delete_flg = false")
	UseItemMapperDto getUseItemFind(@Param("user_id") String user_Id, @Param("item_id") String item_id);
	
	@Select("SELECT * from t_location where user_id = #{user_id} and delete_flg = false")
	List<LocationItemMapperDto> getLocationItem(@Param("user_id") String user_Id);
	
	@Select("SELECT * from t_location where user_id = #{user_id} and location_id = #{location_id} and delete_flg = false")
	LocationItemMapperDto getLocationItemFind(@Param("user_id") String user_Id,@Param("location_id") String location_id);
	
	@Select("SELECT MAX(cast(income_item_id as integer)) from t_income_item;")
	String getMaxIdIncomeItem();
	
	@Select("SELECT MAX(cast(item_id as integer)) from t_use_item")
	String getMaxIdUseItem();
	
	@Select("SELECT MAX(cast(location_id as integer)) from t_location;")
	String getMaxIdLocationItem();

}
