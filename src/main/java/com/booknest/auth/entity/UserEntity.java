package com.booknest.auth.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザーエンティティクラス
 */
@Data
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
	 * 姓
	 */
	private String firstName;

	/**
	 * 名
	 */
	private String lastName;

	/**
	 * 登録日時
	 */
	private LocalDateTime createdAt;

	/**
	 * 更新日時
	 */
	private LocalDateTime updatedAt;
}
