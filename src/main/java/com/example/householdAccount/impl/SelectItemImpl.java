package com.example.householdAccount.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.householdAccount.postgresMapperDto.IncomeItemMappperDto;

@Mapper
public interface SelectItemImpl {
	
	@Select("SELECT * from T_income_item where user_id = #{user_id} and delete_flg = false")
	List<IncomeItemMappperDto> getIncomeItem(@Param("user_id") String user_Id);

}
