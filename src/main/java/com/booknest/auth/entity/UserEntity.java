package com.booknest.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

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
	@Column(name = "user_id")
	private Long userId;

	/**
	 * email
	 */
	@Column(name = "email")
	private String email;

	/**
	 * パスワード
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 姓
	 */
	@Column(name = "first_name")
	private String firstName;

	/**
	 * 名
	 */
	@Column(name = "last_name")
	private String lastName;

	/**
	 * 登録日時
	 */
	@Column(name = "created_at")
	private final LocalDateTime createdAt;

	/**
	 * 更新日時
	 */
	@Column(name = "update_at")
	private LocalDateTime updatedAt;
}
