## JDBC調査結果
### JDBCインストール方法
　https://dev.mysql.com/downloads/connector/j/  
　上記URLよりSelect Platform：Platform Independentを選択して  
　JDBCドライバ(mysql-connector-java-x.x.xx.zip )をダウンロードし解凍する。  

### eclipseでの使用方法
　プロジェクトを右クリックし、「ビルド・パス」より「外部アーカイブの追加」を実行する。  
　mysql-connector-java-x.x.xx.jarを選択し、開くを押下する。  
　「参照ライブラリー」内にJARファイルが追加されていることを確認する。  
　
### JDBCの使い方
1. 必要情報を定数定義しておく  
```
// 例
private static final String DB_PATH = "データベースURL";
private static final String DB_USER_NAME = "ユーザー名";
private static final String DB_PASSWORD = "パスワード";
private static final String DB_INSERT_SQL = "インサート用SQL文";
```

2. データベースの接続
・まずはデータベースに接続し、Connectionクラスのオブジェクトを取得する。
```
Connection connection = DriverManager.getConnection(DBのPath, ユーザー名, パスワード);
```

3. SQL発行の準備
DB接続処理にPreparedStatementクラスを使用する方が安全で且つ処理も速いみたい。  
・PreparedStatementクラスを使用するメリット  
  ・文字列連結でSQL文を作成しない為、SQLインジェクション対策になる。
  ・SQLがDBにキャッシュされるので同じSQL文を発行する場合処理速度が速い。  
  
■ PreparedStatementクラスを使用する場合としない場合の実装方法。  
  ・PreparedStatementクラスを使用する場合
  ```
  PreparedStatement statement = connection.prepareStatement(SQL文字列);
  // 値の設定。下記は1番目の?の値が1のデータという意味になる。
  statement.setInt(1, 1);
  ResultSet resultSet = statement.executeQuery();
  ```
  
  ・PreparedStatementクラスを使用しない場合
  ```
  Statement statement = connection.createStatement();
  // 値はSQL文字列に一緒に記載する。
  ResultSet resultSet = statement.executeQuery(SQL文字列);
  ```
  
4. 例外処理
・DB接続中にエラーが発生した場合の例外処理を書く。
 finally（絶対に実行したい処理記載箇所）を使用し、成功時でも失敗時でも後片付けを行うようにしておく。
  ```
  Connection connection = null;  
  Statement statement = null;  
  ResultSet resultSet = null;  
  try {  
    // DB接続及び実行処理  
  } catch (SQLException e) {  
    e.printStackTrace();
  } finally {
    try {  
      if (resultSet != null) resultSet.close();  
      if (statement != null) statement.close();  
      if (connection != null) connection.close();  
    } catch (SQLException e) {  
      e.printStackTrace();
    }  
  }
  ```

