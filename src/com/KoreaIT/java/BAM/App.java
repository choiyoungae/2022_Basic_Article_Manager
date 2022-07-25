package com.KoreaIT.java.BAM;

import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.Controller;
import com.KoreaIT.java.BAM.controller.MemberController;

public class App {
	
	public App() {
	}

	public void run() {
		
		System.out.println("== ���α׷� ���� ==");

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {
			System.out.printf("��ɾ�) ");
			String cmd = sc.nextLine().trim();
			
			if(cmd.length() == 0) {
				System.out.println("��ɾ �Է����ּ���.");
				continue;
			}
			
			if(cmd.equals("exit")) {
				System.out.println("���α׷��� ����˴ϴ�.");
				break;
			}
			
			String[] cmdArr = cmd.split(" ");
			
			if(cmdArr.length == 1) {
				System.out.println("��ɾ Ȯ�����ּ���.");
				continue;
			}
			
			String controllerName = cmdArr[0];
			String actionMethodName = cmdArr[1];
			
			Controller controller = null;
			
			if(controllerName.equals("article")) {
				controller = articleController;
				
			} else if(controllerName.equals("member")) {
				controller = memberController;
				
			} else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
				continue;
			}
			
			String actionName = controllerName + "/" + actionMethodName;
			switch(actionName) {
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "member/logout":
			case "member/profile":
				if(Controller.isLogined() == false) {
					System.out.println("�α��� �� �̿����ּ���.");
					continue;
				}
				break;
				
			case "member/login":
			case "member/join":
				if(Controller.isLogined() == true) {
					System.out.println("�α׾ƿ� �� �̿����ּ���.");
					continue;
				}
				break;
			}
			
			controller.doAction(cmd, actionMethodName);
			
		}
		
		System.out.println("== ���α׷� �� ==");
		sc.close();
	}
	
	
	
	
}
