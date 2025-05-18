package com.booknest.auth.controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booknest.auth.service.UserService;
import com.booknest.common.util.JwtUtil;

/**
 * AuthControllerクラス
 * 
 * ログイン・ユーザーの新規作成を行う。
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	/**
	 * ユーザーサービス
	 */
	private final UserService userService;

	/**
	 * JWT生成共通部品
	 */
	private final JwtUtil jwtUtil;

	public AuthController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	/**
	 * ユーザー新規登録
	 * 
	 * @param request リクエスト
	 * @return レスポンス
	 */
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
		try {
			String firstName = request.get("firstName");
			String lastName = request.get("lastName");
			String password = request.get("password");
			String email = request.get("email");
			// ユーザー新規登録処理
			Long userId = userService.insertUser(firstName, lastName, password, email);
			// JWT生成
			String token = jwtUtil.generateToken(String.valueOf(userId));
			return ResponseEntity.ok(Map.of(
					"message", "登録が完了しました",
					"token", token));
		} catch (ExceptionInInitializerError e) {
			// 登録失敗
			return ResponseEntity.status(400).body(Map.of("message", "登録に失敗しました"));
		}

	}

	/**
	 * ユーザーログイン
	 * 
	 * @param request リクエスト
	 * @return レスポンス
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		String password = request.get("password");
		Long userId = userService.authenticateUser(email, password);

		if (Objects.nonNull(userId)) {

			// パスワードが正しければ、JWTを返却する
			String token = jwtUtil.generateToken(String.valueOf(userId));
			return ResponseEntity.ok(Map.of("token", token));
		}
		return ResponseEntity.status(401).body("Invalid credentials");
	}
}
