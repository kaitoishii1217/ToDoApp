import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UpdateTest{
	
	private static final String dr = "com.mysql.cj.jdbc.Driver";
	private static final String path = "jdbc:mysql://localhost/testdb";
	private static final String user ="root";
	private static final String pass ="root";

	
    Connection con = null;
    PreparedStatement st = null;
    PreparedStatement st1 = null;
    PreparedStatement st2 = null;
    PreparedStatement st3 = null;
    String selsql = "SELECT seq,todo,enddate,priority from todolist;";
    String updsql1 = "UPDATE todolist SET todo = ? WHERE seq = ? ;";
    String updsql2 = "UPDATE todolist SET enddate = ? WHERE seq = ? ;";
    String updsql3 = "UPDATE todolist SET priority = ? WHERE seq = ? ;";
    ResultSet rs=null;
    Scanner sc=null;
    
private void selecttodo() {
           try {
        	   Class.forName(dr);
        	   con = DriverManager.getConnection(path, user, pass);
        	   st = con.prepareStatement(selsql);
        	   rs=st.executeQuery();
        	   
        	   while (rs.next()) {
        		   System.out.print(rs.getInt("seq"));
        		   System.out.print("：");
        		   System.out.print(rs.getString("todo"));
        		   System.out.print("　");
        		   System.out.print(rs.getInt("enddate"));
        		   System.out.print("　優先度：");
        		   System.out.print(rs.getString("priority"));
        		   System.out.println();
        	   }
           } catch(Exception e) {
        	   e.printStackTrace();
           }finally {
             try{
            	if(con!=null) {
            	con.close();
            	}
            	if(rs!=null) {
            		rs.close();
            	}
             } catch(Exception e) {
            	e.printStackTrace();
               }
            }
	}

private void updatetodo(){
       try {
    	   Class.forName(dr);
    	   con = DriverManager.getConnection(path, user, pass);
    	   st1 = con.prepareStatement(updsql1);
    	   st2 = con.prepareStatement(updsql2);
    	   st3 = con.prepareStatement(updsql3);
    	   sc = new Scanner(System.in);
    	   System.out.println("更新するToDoの番号を指定してください");
    	   int seq = Integer.parseInt(sc.nextLine());
    	   
    	   System.out.println("更新する項目を選択してください(1:ToDo内容　2:期日　3:優先度)");
    	   int updpj = Integer.parseInt(sc.nextLine());
    	   
    	   switch(updpj) {
    	   case 1:
    		   System.out.println("更新後の内容を入力してください");
        	   String todo = sc.nextLine();
        	   st1.setString(1,todo);
        	   st1.setInt(2,seq);
        	   st1.executeUpdate();
        	   break;
    	   case 2:
    		   System.out.println("更新後の期日を入力してください");
        	   int enddate = Integer.parseInt(sc.nextLine());
        	   st2.setInt(1,enddate);
        	   st2.setInt(2,seq);
        	   st2.executeUpdate();
        	   break;
    	   case 3:
    		   System.out.println("更新後の優先度を入力してください（高、中、低より選択）");
        	   String priority = sc.nextLine();
        	   st3.setString(1,priority);
        	   st3.setInt(2,seq);
        	   st3.executeUpdate();
        	   break;
           default:
        	   System.out.println("更新対象が正しく選択されていません。");
    	   }
    	   
       } catch(Exception e) {
    	   e.printStackTrace();
       }finally {
         try{
        	if(con!=null) {
        	con.close();
        	}
        	if(sc!=null) {
        		sc.close();
        	}
         } catch(Exception e) {
        	e.printStackTrace();
           }
        }
}

 public static void main(String[] args){
	UpdateTest ut=new UpdateTest();
	System.out.println("---更新前ToDoリスト---");
	ut.selecttodo();
	ut.updatetodo();
	System.out.println("---更新後ToDoリスト---");
	ut.selecttodo();
 }
}