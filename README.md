# AWS RDS PostgreSQL インスタンスへの接続手順
このプロジェクトでは、AWS RDS上のPostgreSQLデータベースに接続して使用します。  
以下の手順で、RDSインスタンスにアクセスできるように設定できます。
  
## 1. セキュリティグループの設定（アクセス許可）  
RDSインスタンスへのアクセスは、セキュリティグループで管理されているため、アクセスを許可するメンバーのグローバルIPアドレスをセキュリティグループに追加する必要があります。  

手順:  
1.自分のグローバルIPアドレスを確認  
2.RDS管理者(前田)にIPアドレスを伝える  
3.管理者がセキュリティグループにIPアドレスを追加  

## 2. PostgreSQL クライアントツールでの接続(PgAdmin)  

(1) サーバーを追加  
左のツリーの「Servers」を右クリック → 「登録」 → 「サーバ...」 を選択  
「General」タブ  
名前: AWS RDS BOOK NEST（任意）  

(2) 接続情報を入力  
「接続」タブ を開き、以下のRDS情報を設定  
  
### RDSインスタンスの設定情報 
※envファイルを共有して貰うことでXXXの情報が分かる。  

ホスト名/アドレス: XXX  
ポート: `5432`  
管理者用データベース: `XXX`  
ユーザ名:	`XXX`   
パスワード:	`XXX`  

(2)「パラメータ」タブ を開く
SSL モード: require（厳密なSSLチェック）  
「保存」ボタンを押して接続  

接続成功すれば、左のツリーにRDSのサーバーが表示される  

## 3. application.propertiesにDB接続情報を設定  
1.envファイルをルートディレクトリに配置  
2.application.propertiesに以下を設定  

```
spring.application.name=booknest-backend

# RDSの接続設定  
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}  
spring.datasource.username=${DB_USERNAME}  
spring.datasource.password=${DB_PASSWORD}  
spring.datasource.driver-class-name=org.postgresql.Driver  
# Hibernateのダイアレクトを明示的に指定  
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect  
# 他の設定（必要に応じて追加）  
# update にすると、アプリケーションの起動時に必要なテーブルが自動で作成される（開発環境でのみ使用推奨）  
spring.jpa.hibernate.ddl-auto=update  
# SQLをコンソールに出力したい場合、true に設定  
spring.jpa.show-sql=true  
```
  
## 4. 接続確認  
mainメソッドに下記コードをコピペし、アプリケーションを起動。  
接続が成功すると、コンソールに接続確認メッセージが表示されます。  
```
@SpringBootApplication
public class BooknestBackendApplication {

	public static void main(String[] args) {

	SpringApplication.run(BooknestBackendApplication.class, args);

	// .env ファイルを読み込む
        Dotenv dotenv = Dotenv.load();
        
        // .envから値を取得
        String url = "jdbc:postgresql://" + dotenv.get("DB_HOST") + ":" + dotenv.get("DB_PORT") + "/" + dotenv.get("DB_NAME");
        String user = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        // 接続確認
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("データベースに接続成功！");
            }
        } catch (SQLException e) {
            System.out.println("接続失敗: " + e.getMessage());
        }
	}
}
```
