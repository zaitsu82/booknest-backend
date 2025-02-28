package com.booknest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.booknest.auth.UserEntity;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM users WHERE username = #{username}")
	UserEntity findByUsername(@Param("username") String username);
}
