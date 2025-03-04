# ベースイメージ (Maven + JDK)
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# 作業ディレクトリを作成
WORKDIR /app

# ソースコードをコンテナにコピー
COPY . .

# Maven でビルド (jar ファイルを target/ に生成)
RUN mvn clean package -DskipTests

# 実行用の軽量な JDK イメージ
FROM openjdk:21-jdk-slim

# 作業ディレクトリを作成
WORKDIR /app

# ビルド済みの JAR ファイルをコピー
COPY --from=builder /app/target/booknest-backend-0.0.1-SNAPSHOT.jar app.jar

# アプリケーションを実行
CMD ["java", "-jar", "app.jar"]
