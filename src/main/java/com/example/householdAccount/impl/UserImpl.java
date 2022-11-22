package com.example.householdAccount.impl;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.householdAccount.postgresMapperDto.TLoginUserMapperDto;

@Mapper
public interface UserImpl {
	@Insert("INSERT INTO t_login_user (user_id, password,user_name,mail_address, role_id,delete_flg) VALUES (#{user_id}, #{password},#{user_name},#{mail_address}, #{role_id}, false)")
	void registUser(@Param("user_id") String user_Id,@Param("password")String password, @Param("user_name")String user_name,@Param("mail_address")String mail_address,@Param("role_id")String role_id);
	
	@Select("SELECT * from t_login_user where user_id = #{user_id}")
	TLoginUserMapperDto getRegistUser(@Param("user_id") String user_Id);
	
	@Update("UPDATE t_login_user set password = #{password} where user_id=#{user_id}")
	void updateUser(@Param("user_id") String user_Id,@Param("password") String password);
}
