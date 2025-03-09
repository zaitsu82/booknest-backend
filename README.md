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
ホスト名/アドレス: ~~~
ポート: `5432`  
管理者用データベース: `db_name_book_nest`  
ユーザ名:	`book_nest_admin`   
パスワード:	`bookNestPass1739`  

(2)「パラメータ」タブ を開く
SSL モード: require（厳密なSSLチェック）  
「保存」ボタンを押して接続  

接続成功すれば、左のツリーにRDSのサーバーが表示される  

## 3. application.propertiesにDB接続情報を設定  
![image](https://github.com/user-attachments/assets/f1537478-b317-4e16-b0f5-f3ea38353e33)

  
## 4. 接続確認  
mainメソッドに下記コードをコピペし、アプリケーションを起動。  
接続が成功すると、コンソールに接続確認メッセージが表示されます。  
```
@SpringBootApplication
public class BooknestBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooknestBackendApplication.class, args);  
		
        String url = "~~~";  
        String user = "book_nest_admin";  
        String password = "bookNestPass1739";  

        try (Connection connection = DriverManager.getConnection(url, user, password)) {  
            System.out.println("コネクション接続成功!");  
        } catch (SQLException e) {  
            System.out.println(コネクション接続失敗: " + e.getMessage());  
        }
	}
}
```
