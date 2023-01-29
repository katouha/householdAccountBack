package com.example.householdAccount.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.example.householdAccount.postgresMapperDto.UseHistoryMapperDto;

@Mapper
public interface UseHistoryImpl {
	@Insert("INSERT INTO t_use_history (user_id, use_date,item_id,location_id,product_name,money, delete_flg) VALUES (#{user_id}, #{use_date},#{item_id},#{location_id},#{product_name}, #{money}, false)")
	void setUseHistory(@Param("user_id") String user_Id,@Param("use_date") Timestamp use_date,@Param("item_id")String item_id, @Param("location_id")String location_id,@Param("product_name")String product_name,@Param("money")int money);
	
	//動的条件 ただし同一カラムに複数変数pramを付けられないため日付はserviceにてfilterする
	@SelectProvider(type = TodoSQLProvider.class, method = "select")
	List<UseHistoryMapperDto> getUseHistorySearch(@Param("user_id") String user_id, 
			  @Param("use_date") String use_date, 
			  @Param("item_id") String item_id,
			  @Param("location_id") String location_id,
			  @Param("product_name") String product_name);
	
	public class TodoSQLProvider {
		  public String select(@Param("user_id") String user_id, 
				  @Param("use_date") String use_date, 
				  @Param("item_id") String item_id,
				  @Param("location_id") String location_id,
				  @Param("product_name") String product_name) {
		    return new SQL() {
		      {
		        SELECT("*");
		        FROM("t_use_history");
		        if (user_id != null) {
		          WHERE("user_id = #{user_id}");
		        }

		        if (use_date != null) {
		          WHERE("to_char(use_date, 'YYYY/mm') = #{use_date}");
		        }
		        if (item_id != null) {
		          WHERE("item_id = #{item_id}");
		        }
		        if (location_id != null) {
		          WHERE("location_id = #{location_id}");
		        }
		        if (product_name != null) {
		          WHERE("product_name = #{product_name}");
		        }
		      }
		    }.toString();
		  }
		}
}
