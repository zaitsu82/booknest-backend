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
	public void insertUser(String username, String password) {
		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		userMapper.insertUser(user);
	}

	/**
	 * ユーザー認証
	 * 
	 * @param username ユーザー名
	 * @param rawPassword パスワード
	 * @return
	 */
	public boolean authenticateUser(String username, String rawPassword) {
		return userMapper.findByUsername(username)
				.map(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
				.orElse(false);
	}
}
