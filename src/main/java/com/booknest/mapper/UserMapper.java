package com.booknest.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.booknest.auth.entity.UserEntity;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM auth.users WHERE email = #{email}")
	Optional<UserEntity> findByEmail(@Param("email") String email);

	@Insert("INSERT INTO auth.users (email, password, first_name, last_name, created_at) VALUES (#{email}, #{password}, #{firstName}, #{lastName}, NOW())")
	void insertUser(UserEntity user);
}
