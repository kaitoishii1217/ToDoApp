import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Inserttest {

    public static void main(String[] args) throws Exception{

        Connection con = null;
        PreparedStatement st = null;
        String inssql = "INSERT INTO todolist VALUES (?,?);";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "root");
            st = con.prepareStatement(inssql);{
            	Scanner sc = new Scanner(System.in);
    			System.out.print("日付を入力してください");
    			int enddate = Integer.parseInt(sc.nextLine());

    			System.out.print("やることを入力してください");
    			String todo = sc.nextLine();

    			st.setInt(1, enddate);
    			st.setString(2, todo);
    			st.executeUpdate();
            }
            con.close();
    }

}