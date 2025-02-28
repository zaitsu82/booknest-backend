# ベースイメージ (OpenJDK)
FROM openjdk:17-jdk-slim

# 作業ディレクトリを作成
WORKDIR /app

# JAR ファイルをコピー
COPY target/booknest-backend-0.0.1-SNAPSHOT.jar app.jar

# アプリケーションを実行
CMD ["java", "-jar", "app.jar"]
