package com.booknest.common.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * JWT生成共通クラス
 */
@Component
public class JwtUtil {

	/**
	 * 秘密鍵（本番環境では.envに定義)
	 */
	private final String SECRET_KEY = "YourSecretKeyYourSecretKeyYourSecretKey"; // 256bit以上（32文字以上）

	/**
	 * 有効期限（1時間）
	 */
	private final long EXPIRATION_TIME = 1000 * 60 * 60;

	/**
	 * 署名用鍵
	 */
	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	/**
	 * JWTの発行
	 * 
	 * @param username ユーザー名
	 * @return トークン
	 */
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username) // ユーザー名（トークンの持ち主）を設定
				.setIssuedAt(new Date()) // トークンの発行時間を設定
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // トークンの有効時間を設定
				.signWith(key, SignatureAlgorithm.HS256) // 秘密鍵を使用して署名（改ざん防止）
				.compact(); // トークン生成
	}

	/**
	 * JWTからユーザー名を取得
	 * 
	 * @param token トークン
	 * @return ユーザー名
	 */
	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key) // 署名検証のための秘密鍵をセット
				.build()
				.parseClaimsJws(token) // トークンを解析して中身（Claims）を取得
				.getBody()
				.getSubject(); // ユーザー名を取得
	}

	/**
	 * JWT有効性チェック
	 * 
	 * @param token トークン
	 * @return JWT有効性チェック結果
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
					.setSigningKey(key) /// 署名検証のための秘密鍵をセット
					.build()
					.parseClaimsJws(token); // 改ざんチェック
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}
