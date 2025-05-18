package com.booknest.auth.service;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booknest.auth.entity.UserEntity;
import com.booknest.mapper.UserMapper;

/**
 * UserServiceクラス
 */
@Service
public class UserService {

	/**
	 * ユーザーマッパー
	 */
	private final UserMapper userMapper;

	/**
	 * パスワードエンコーダー
	 */
	private final PasswordEncoder passwordEncoder;

	public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * ユーザー新規登録
	 * 
	 * @param username ユーザー名
	 * @param password パスワード
	 * @return ユーザーID
	 */
	public Long insertUser(String firstName, String lastName, String password, String email) {
		// パスワードをハッシュ化
		String hashedPassword = passwordEncoder.encode(password);

		UserEntity userEntity = UserEntity.builder()
				.firstName(firstName)
				.lastName(lastName)
				.password(hashedPassword)
				.email(email)
				.build();
		userMapper.insertUser(userEntity);
		return userEntity.getUserId();
	}

	/**
	 * ユーザー認証
	 * 
	 * @param email メールアドレス
	 * @param rawPassword パスワード
	 * @return ユーザーID
	 */
	public Long authenticateUser(String email, String rawPassword) {
		UserEntity user = userMapper.findByEmail(email);
		if (Objects.nonNull(user) && passwordEncoder.matches(rawPassword, user.getPassword())) {
			return user.getUserId();
		} else {
			return null;
		}
	}
}
