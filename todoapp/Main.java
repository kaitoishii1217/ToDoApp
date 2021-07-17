package todoapp;

import java.util.List;
import java.util.Scanner;


public class Main {
	
	private static Scanner sc;
	private static TodoDisplay display;
	private static TodoDBManager dbManager;

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		display = new TodoDisplay();
		dbManager = TodoDBManager.getInstance();
		
			while(true) {
				//一覧の表示
				System.out.println("--------ToDoリスト--------");
				List<TodoData> todoDataList = dbManager.selectAll();
				if(todoDataList.size()==0){
					System.out.println("ToDoはありません。");
				} else {
					display.displayListAll(todoDataList);
				}
				System.out.println("--------------------------");
				
				//次の操作の選択
				operationLoop:
					while(true) {
					System.out.println("実施したい操作を選択してください");
					System.out.println("1:追加　2:修正　3:削除　その他の数字:終了");
					int nextOperation = 0;
					try {
						nextOperation = Integer.parseInt(sc.nextLine());
					} catch(NumberFormatException ne) {
							System.out.println("操作は数字で入力してください。");
							System.out.println();
							break;
					}
					switch(nextOperation) {
					case 1:
						try {
							display.displayInsertScreen();
						} catch(Exception e) {
							break;
						}
						break operationLoop;
						
					case 2:
						if(todoDataList.size()==0){
							System.out.println("修正するToDoはありません。");
							break;
						} else {
							try {
								display.displayUpdateScreen(todoDataList.size());
							} catch (Exception e){
								break;
							}
						}
						break operationLoop;
						
					case 3:
						if(todoDataList.size()==0){
							System.out.println("削除するToDoはありません。");
							break;
						} else {
							try{
								display.displayDeleteScreen(todoDataList.size());
							} catch(Exception e) {
								break;
							}
						break operationLoop;	
						}
						
					default:
						System.out.println("アプリケーションを終了します。");
						return;
					}
			}
		}
	}
}