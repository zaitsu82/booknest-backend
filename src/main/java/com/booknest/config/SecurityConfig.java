package com.booknest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * パスワード暗号化設定
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * JWT認証の有無を設定
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.formLogin(form -> form.disable()) // フォームログインを無効化
				.httpBasic(httpBasic -> httpBasic.disable()) // HTTP Basic 認証を無効化
				.csrf(csrf -> csrf.disable()) // CSRFを無効化（REST API では不要）
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // セッションを完全無効化
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/**").permitAll() // `/auth/**` は認証不要
						.anyRequest().authenticated() // それ以外のリクエストは認証が必要
				);

		return http.build();
	}
}
