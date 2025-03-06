package com.booknest.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
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
		String username = request.get("username");
		String password = request.get("password");
		userService.insertUser(username, password);
		return ResponseEntity.ok("User registered successfully");
	}

	/**
	 * ユーザーログイン
	 * 
	 * @param request リクエスト
	 * @return レスポンス
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
		String username = request.get("username");
		String password = request.get("password");

		if (userService.authenticateUser(username, password)) {

			// パスワードが正しければ、JWTを返却する
			String token = jwtUtil.generateToken(username);
			return ResponseEntity.ok(Map.of("token", token));
		}
		return ResponseEntity.status(401).body("Invalid credentials");
	}
}
