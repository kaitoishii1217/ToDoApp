package todoapp;

public class TodoData {
	
	private int id;
	private String todoContent;
	private int priority;
	private int status;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}
	
	public String getTodoContent() {
		return todoContent;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "TodoData [id=" + id + ", todoContent=" + todoContent + ", priority=" + priority + ", status=" + status
				+ "]";
	}
	
	

}
