import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Deletetest{
	
	private static final String dr = "com.mysql.cj.jdbc.Driver";
	private static final String path = "jdbc:mysql://localhost/testdb";
	private static final String user ="root";
	private static final String pass ="root";

	
    Connection con = null;
    PreparedStatement st = null;
    String selsql = "SELECT seq,todo,enddate,priority from todolist;";
    String delsql = "DELETE from todolist where seq = (?);";
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

private void deletetodo(){
       try {
    	   Class.forName(dr);
    	   con = DriverManager.getConnection(path, user, pass);
    	   st = con.prepareStatement(delsql);
        	sc = new Scanner(System.in);
			System.out.println("削除するToDoの番号を指定してください");
			int seq = Integer.parseInt(sc.nextLine());

			st.setInt(1, seq);
			st.executeUpdate();
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
	Deletetest dt=new Deletetest();
	System.out.println("---削除前ToDoリスト---");
	dt.selecttodo();
	dt.deletetodo();
	System.out.println("---削除後ToDoリスト---");
	dt.selecttodo();
 }
}