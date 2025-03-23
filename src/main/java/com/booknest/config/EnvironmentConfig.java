package com.booknest.config;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;

@Component // @Component をつけることで、Spring Bootが自動的にこのクラスを管理し、アプリ起動時に.envを読み込む
public class EnvironmentConfig {

    @PostConstruct // Beanが初期化された直後に実行されるメソッドを定義するためのアノテーション※処理を一度だけ行いたい時に有効
    public void init() {
        Dotenv dotenv = Dotenv.load(); // .envファイルを読み込む
        System.setProperty("DB_HOST", dotenv.get("DB_HOST"));
        System.setProperty("DB_PORT", dotenv.get("DB_PORT"));
        System.setProperty("DB_NAME", dotenv.get("DB_NAME"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    }
}
