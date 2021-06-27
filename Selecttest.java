import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Selecttest{
	
	private static final String dr = "com.mysql.cj.jdbc.Driver";
	private static final String path = "jdbc:mysql://localhost/testdb";
	private static final String user ="root";
	private static final String pass ="root";

	public static void main(String[] args){
		
        Connection con = null;
        PreparedStatement st = null;
        String selsql = "SELECT seq,todo,enddate,priority from todolist;";
        ResultSet rs=null;
        
           try {
        	   Class.forName(dr);
        	   con = DriverManager.getConnection(path, user, pass);
        	   st = con.prepareStatement(selsql);
        	   rs=st.executeQuery(selsql);
        	   
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
}
