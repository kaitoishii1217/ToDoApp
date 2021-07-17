package todoapp;

import java.util.List;
import java.util.Scanner;

public class TodoDisplay {
	
	private static TodoDBManager dbManager;
	private Scanner sc = new Scanner(System.in);
	
	public void displayListAll(List<TodoData> todoList) {
		System.out.printf("%-3s","No");
		System.out.print("|");
		System.out.printf("%-3s","優先度");
		System.out.print("|");
		System.out.printf("%-2s","状態");
		System.out.print("|");
		System.out.print("内容");
		System.out.println();
		
		for(int i=0;i<todoList.size();i++) {
			System.out.printf("%-3d",i+1);
			System.out.print("|");
			System.out.printf("%-6d",todoList.get(i).getPriority());
			System.out.print("|");
			switch(todoList.get(i).getStatus()) {
			case 1:
				System.out.printf("%-3s","未");
				break;
			case 2:
				System.out.printf("%-2s","完了");
				break;
			}
			System.out.print("|");
			System.out.print(todoList.get(i).getTodoContent());
			System.out.println();
		}
	}
	
	public void displayInsertScreen() throws Exception {
		dbManager = TodoDBManager.getInstance();
		System.out.println("追加するToDoの内容を入力してください。");
		String newContent = sc.nextLine();
		if(newContent.equals("") ){
			System.out.println("内容は空白にはできません");
			System.out.println();
			throw new Exception();
		} else if(newContent.length()>50){
			System.out.println("内容は50文字以内で入力してください。");
			throw new Exception();
		}
		System.out.println("追加するToDoの優先度を1～10で入力してください。");
		int priority = 0;
		try {
			priority = Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException ne) {
			System.out.println("優先度には1～10の数字を入力してください。");
			System.out.println();
			throw new Exception();
		}
		if(priority<1 || priority>10) {
			System.out.println("優先度は1～10で設定可能です。");
			throw new Exception();
		}
		TodoData todoData = new TodoData();
		todoData.setTodoContent(newContent);
		todoData.setPriority(priority);
		int lineNumber = dbManager.insertData(todoData);
		if(lineNumber==1) {
			System.out.println("優先度：" + todoData.getPriority() + " 「"+ todoData.getTodoContent() + "」をToDoに追加しました。");
		} else {
			System.out.println("ToDoの追加処理に失敗しました。");
		}
	}
	
	public void displayDeleteScreen(int todoListNumber) throws Exception{
		dbManager = TodoDBManager.getInstance();
		System.out.println("削除するToDoのNoを入力してください。");
		int deleteListNumber=0;
		try {
			deleteListNumber = Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException ne) {
			System.out.println("削除する項目を数字で入力してください。");
			System.out.println();
			throw new Exception();
		}
		if(deleteListNumber> todoListNumber || deleteListNumber < 1) {
			System.out.println("入力されたNoのToDoは存在しません。");
			throw new Exception();
		}
		int lineNumber = dbManager.deleteData(deleteListNumber);
		if(lineNumber==1) {
			System.out.println("No："+deleteListNumber+"のレコードを削除しました。");
		} else {
			System.out.println("ToDoの削除処理に失敗しました。");
		}
	}
	
	
	public void displayUpdateScreen(int todoListNumber) throws Exception{
		dbManager = TodoDBManager.getInstance();
		TodoData updateTodoData = new TodoData(); 
		System.out.println("修正するレコードのNoを入力してください。");
		int updateListNumber=0;
		try {
			updateListNumber = Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException ne) {
			System.out.println("修正するレコードを数字で入力してください。");
			System.out.println();
			throw new Exception();
		}
		if(updateListNumber>todoListNumber || updateListNumber < 1) {
			System.out.println("入力されたNoのToDoは存在しません。");
			throw new Exception();
		}
		updateTodoData.setId(updateListNumber);
		System.out.println("修正する項目を選択して数字を入力してください。");
		System.out.println("内容:1　優先度:2　状態:3");
		int updateItem = 0;
		try {
			updateItem = Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException ne) {
			System.out.println("修正する項目を数字で入力してください。");
			System.out.println();
			throw new Exception();
		}
		switch(updateItem) {
		case 1:
			System.out.println("新しいToDoの内容を入力してください。");
			String changedContent = sc.nextLine();
			if(changedContent.equals("") ){
				System.out.println("内容は空白にはできません");
				System.out.println();
				throw new Exception();
			} else if(changedContent.length()>50){
				System.out.println("内容は50文字以内で入力してください。");
				throw new Exception();
			}
			updateTodoData.setTodoContent(changedContent);
			break;
			
		case 2:
			System.out.println("変更後のToDoの優先度を1～10で入力してください。");
			int changedPriority=0;
			try {
			changedPriority = Integer.parseInt(sc.nextLine());
			} catch(NumberFormatException ne) {
				System.out.println("優先度には1～10の数字を入力してください。");
				System.out.println();
				throw new Exception();
			}
			if(changedPriority<1 || changedPriority>10) {
				System.out.println("優先度は1～10で設定可能です。");
				throw new Exception();
			}
			updateTodoData.setPriority(changedPriority);;
			break ;
			
		case 3:
			System.out.println("変更後のToDoの状態を入力してください。");
			System.out.println("未：1　完了：2");
			int changedStatus =0;
			try {
				changedStatus = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException ne) {
				System.out.println("状態は1か2のみ入力可能です。");
				System.out.println();
				throw new Exception();
			}
			if(changedStatus==1 || changedStatus==2) {
				updateTodoData.setStatus(changedStatus);
			} else {
				System.out.println("状態は1か2のみ入力可能です。");
				throw new Exception();
			}
			break;
			
		default:
			System.out.println("修正する項目の番号が正しく入力されていません。");
			throw new Exception();
		}
		int lineNumber = dbManager.updateData(updateTodoData);
		if(lineNumber==1) {
			System.out.println("No："+updateListNumber+"を変更しました。");
		} else {
			System.out.println("ToDoの更新処理に失敗しました");
		}
	}
}
