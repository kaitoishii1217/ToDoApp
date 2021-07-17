package todoapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoDBManager {
	
	private static TodoDBManager dbManager = new TodoDBManager();
	
	ArrayList<TodoData> todoList = new ArrayList<TodoData>();
	
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	int lineNumber;
	
	public static TodoDBManager getInstance() {
		return dbManager;
	}

	
	public ArrayList<TodoData> selectAll(){
		this.todoList =  new ArrayList<TodoData>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/todoapp","root","root");
			st = con.prepareStatement("SELECT * from todotable order by priority desc;");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				TodoData todoLists = new TodoData();
				todoLists.setId(rs.getInt("todo_id"));
				todoLists.setTodoContent(rs.getString("content"));
				todoLists.setPriority(rs.getInt("priority"));
				todoLists.setStatus(rs.getInt("status"));
				todoList.add(todoLists);
			}
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null) {
					con.close();
				}
				if(st!=null) {
					st.close();
				}
				if(rs!=null) {
					rs.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return todoList;
		
	}

	
	public int insertData(TodoData todoList){
		
		String content = todoList.getTodoContent();
		int priority = todoList.getPriority();
		
		String sql = "INSERT into todotable(content,priority) values(?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/todoapp","root","root");
			st = con.prepareStatement(sql);
			st.setString(1,content);
			st.setInt(2,priority);
			lineNumber = st.executeUpdate();
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null) {
					con.close();
				}
				if(st!=null) {
					st.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return lineNumber;
		
	}
	
	public int deleteData(int listNumber){
		
		String sql = "DELETE from todotable where todo_id = ?;";
		int id = todoList.get(listNumber-1).getId();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/todoapp","root","root");
			st = con.prepareStatement(sql);
			st.setInt(1,id);
			lineNumber = st.executeUpdate();
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null) {
					con.close();
				}
				if(st!=null) {
					st.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return lineNumber;
	}
	
	public int updateData(TodoData todoData){
		int id = todoList.get(todoData.getId()-1).getId();
		String content = todoData.getTodoContent();
		int priority = todoData.getPriority();
		int status = todoData.getStatus();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/todoapp","root","root");
			if( content != null) {
				st = con.prepareStatement("UPDATE todotable set content = ? where todo_id = ?;");
				st.setString(1,content);
				st.setInt(2,id);
				lineNumber = st.executeUpdate();
			} else if(priority != 0){
				st = con.prepareStatement("UPDATE todotable set priority = ? where todo_id = ?;");
				st.setInt(1,priority);
				st.setInt(2,id);
				lineNumber = st.executeUpdate();
			} else if(status != 0) {
				st = con.prepareStatement("UPDATE todotable set status = ? where todo_id = ?;");
				st.setInt(1,status);
				st.setInt(2,id);
				lineNumber = st.executeUpdate();
			}
			} catch (SQLException | ClassNotFoundException e){
				e.printStackTrace();
			} finally {
			try {
				if(con!=null) {
					con.close();
				}
				if(st!=null) {
					st.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return lineNumber;
		
	}
}
	
	