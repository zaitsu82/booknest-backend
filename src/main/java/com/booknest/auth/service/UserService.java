package com.booknest.auth.service;

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
	 */
	public void insertUser(String firstName, String lastName, String password, String email) {
		// パスワードをハッシュ化
		String hashedPassword = passwordEncoder.encode(password);

		UserEntity userEntity = UserEntity.builder()
				.firstName(firstName)
				.lastName(lastName)
				.password(hashedPassword)
				.email(email)
				.build();
		userMapper.insertUser(userEntity);
	}

	/**
	 * ユーザーログイン
	 * 
	 * @param email メールアドレス
	 * @param rawPassword パスワード
	 * @return
	 */
	public boolean authenticateUser(String email, String rawPassword) {
		return userMapper.findByEmail(email)
				.map(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
				.orElse(false);
	}
}
