package com.booknest.auth.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ユーザーエンティティクラス
 */
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserEntity {

	/**
	 * ユーザーID
	 */
	private Long userId;

	/**
	 * email
	 */
	private String email;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * ユーザー名
	 */
	private String username;

	/**
	 * 登録日時
	 */
	private final LocalDateTime createdAt = LocalDateTime.now();

	/**
	 * 更新日時
	 */
	private LocalDateTime updatedAt;
}
