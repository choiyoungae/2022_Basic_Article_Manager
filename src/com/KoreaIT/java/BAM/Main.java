package com.KoreaIT.java.BAM;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		ArrayList<Article> articles = new ArrayList<>();
		
		int articleNumber = 0;

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

			if(cmd.equals("article list")) {
				if(articles.size() == 0) {					
					System.out.println("게시글이 없습니다.");
				} else {
					System.out.println("번호 | 제목");
					for(int i=articles.size()-1; i>=0; i--) {
						Article thisArticle = articles.get(i);
						System.out.printf("%d   | %s\n", thisArticle.articleNumber, thisArticle.title);
					}
				}
				
			}
			else if(cmd.equals("article write")) {
				
				LocalDateTime now = LocalDateTime.now();
				
				articleNumber++;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, articleNumber, now);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", articleNumber);
				
			}
			else if(cmd.startsWith("article detail ")) {
				String[] cmdArr = cmd.split(" ");
				
				int articleNum = Integer.parseInt(cmdArr[2]);
				
				Article foundArticle = null;
				
				for(int i=0; i<articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.articleNumber == articleNum) {
						foundArticle = article;
						break;
					
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", articleNum);
					
				} else {
					System.out.printf("번호 : %d\n", foundArticle.articleNumber);
					System.out.printf("날짜 : %s\n", foundArticle.formatedNow);
					System.out.printf("제목 : %s\n", foundArticle.title);
					System.out.printf("내용 : %s\n", foundArticle.body);
				}
				
			}
			else if(cmd.startsWith("article delete ")) {
				String[] cmdArr = cmd.split(" ");
				
				int articleNum = Integer.parseInt(cmdArr[2]);
				
				Article foundArticle = null;
				
				for(int i=0; i<articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.articleNumber == articleNum) {
						foundArticle = article;
						break;
						
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", articleNum);
					
				} else {
					articles.remove(foundArticle);
					System.out.printf("%d번 게시글이 삭제되었습니다.\n", articleNum);
					
				}
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		
		System.out.println("== 프로그램 끝 ==");
		sc.close();

	}

}
