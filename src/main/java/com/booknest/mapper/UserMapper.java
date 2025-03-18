package com.booknest.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.booknest.auth.entity.UserEntity;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM auth.users WHERE username = #{username}")
	Optional<UserEntity> findByUsername(@Param("username") String username);

	@Insert("INSERT INTO auth.users (username, password,email) VALUES (#{username}, #{password},#{email})")
	void insertUser(UserEntity user);
}
