package com.booknest.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.booknest.auth.entity.UserEntity;

@Mapper
public interface UserMapper {

	@Select("SELECT "
			+ "* "
			+ "FROM users"
			+ " WHERE "
			+ "id = #{id}")
	Optional<UserEntity> findById(@Param("id") Long id);

	@Select("SELECT "
			+ "* "
			+ "FROM users "
			+ "WHERE "
			+ "username = #{username}")
	Optional<UserEntity> findByUsername(@Param("username") String username);

	@Insert("INSERT "
			+ "INTO users "
			+ "(username, "
			+ "password) "
			+ "VALUES "
			+ "(#{username}, "
			+ "#{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insertUser(UserEntity user);

	@Update("UPDATE "
			+ "users "
			+ "SET "
			+ "password = #{password} "
			+ "WHERE "
			+ "username = #{username}")
	void updatePassword(@Param("username") String username, @Param("password") String password);

	@Delete("DELETE "
			+ "FROM users "
			+ "WHERE "
			+ "id = #{id}")
	void deleteById(@Param("id") Long id);
}
