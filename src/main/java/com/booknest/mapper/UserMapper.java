package com.booknest.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.booknest.auth.entity.UserEntity;

@Mapper
public interface UserMapper {

	Optional<UserEntity> findByEmail(@Param("email") String email);

	void insertUser(UserEntity user);
}
