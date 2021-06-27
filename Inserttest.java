import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Inserttest{
	
	private static final String dr = "com.mysql.cj.jdbc.Driver";
	private static final String path = "jdbc:mysql://localhost/testdb";
	private static final String user ="root";
	private static final String pass ="root";

	public static void main(String[] args){
		
        Connection con = null;
        PreparedStatement st = null;
        String inssql = "INSERT INTO todolist(enddate,todo,priority) VALUES (?,?,?);";
        Scanner sc=null;

           try {
        	   Class.forName(dr);
        	   con = DriverManager.getConnection(path, user, pass);
        	   st = con.prepareStatement(inssql);
            	sc = new Scanner(System.in);
    			System.out.println("日付を入力してください");
    			int enddate = Integer.parseInt(sc.nextLine());

    			System.out.println("やることを入力してください");
    			String todo = sc.nextLine();
    			
    			System.out.println("優先度を入力してください(高、中、低から選択)");
    			String pri = sc.nextLine();

    			st.setInt(1, enddate);
    			st.setString(2, todo);
    			st.setString(3, pri);
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
}


