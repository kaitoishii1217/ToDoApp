## MySQL調査結果
### インストール方法
　https://dev.mysql.com/downloads/installer/  
　上記URLよりインストーラをダウンロードし、  
　https://webkaru.net/mysql/install-windows/  
　上記URLの手順に沿ってインストールする。  

### 環境設定と動作確認
　PATH変数にC:\Program Files\MySQL\MySQL Server 8.0\binを追加する。（Program filesフォルダに保存している場合）  
　コマンドプロンプトで以下のコマンドを実行する。  
　mysql -u root -p  
　設定したパスワードを入力し、mysqlのプロンプト(＞mysql)が表示されればインストール、環境設定ともに問題なし。  

### テーブル作成方法
　・データベースの作成：create database <データベース名>;  
　・テーブルの作成：create table <テーブル名>(<列名1> <データ型> <制約>,…);  
　※データベース名、テーブル名の制約：半角英数字、アンダーバーのみで、一文字目は半角アルファベットのみ  
　
