package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.MemberController;
import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class App {
	
	private ArrayList<Article> articles;
	private ArrayList<Member> members;
	
	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		makeTestData();
		
		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

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

			if(cmd.startsWith("article list")) {
				articleController.showArticleList(cmd);
				
			}
			else if(cmd.equals("article write")) {
				articleController.writeArticle();
				
			}
			else if(cmd.startsWith("article detail ")) {
				articleController.showArticle(cmd);
				
			}
			else if(cmd.startsWith("article delete ")) {
				articleController.deleteArticle(cmd);
				
			}
			else if(cmd.startsWith("article modify ")) {
				articleController.modifyArticle(cmd);
				
			}
			else if(cmd.equals("member join")) {
				
				memberController.doJoin();
				
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		
		System.out.println("== 프로그램 끝 ==");
		sc.close();
	}
	
	
	
	private void makeTestData() {
		
		String regDate = Util.getNowDateStr();
				
		articles.add(new Article("test1", "test1", 1, regDate, 11));
		articles.add(new Article("test2", "test2", 2, regDate, 22));
		articles.add(new Article("test3", "test3", 3, regDate, 33));			
	
		System.out.println("테스트용 데이터를 생성했습니다.");

	}
	
	
	
}
