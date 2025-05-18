package com.booknest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.booknest.auth.entity.UserEntity;

@Mapper
public interface UserMapper {

	// ユーザーログイン
	UserEntity findByEmail(@Param("email") String email);

	// ユーザー新規登録
	void insertUser(UserEntity user);
}
