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
	
	@Select("SELECT * from t_location where user_id = #{user_id} and delete_flg = false")
	List<LocationItemMapperDto> getLocationItem(@Param("user_id") String user_Id);

}
