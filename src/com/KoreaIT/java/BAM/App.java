package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.Controller;
import com.KoreaIT.java.BAM.controller.MemberController;
import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class App {
	
	public App() {
	}

	public void run() {
		
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if(cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if(cmd.equals("exit")) {
				System.out.println("프로그램이 종료됩니다.");
				break;
			}
			
			String[] cmdArr = cmd.split(" ");
			
			if(cmdArr.length == 1) {
				System.out.println("명령어를 확인해주세요.");
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
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			controller.doAction(cmd, actionMethodName);
			
		}
		
		System.out.println("== 프로그램 끝 ==");
		sc.close();
	}
	
	
	
	
}
