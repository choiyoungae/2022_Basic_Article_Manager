package com.KoreaIT.java.BAM;

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
				if(articleNumber == 0) {					
					System.out.println("게시글이 없습니다.");
				} else {
					System.out.println("번호 / 제목");
					for(int i=articleNumber-1; i>=0; i--) {
						Article thisArticle = articles.get(i);
						System.out.printf("%s   / %s\n", thisArticle.articleNumber, thisArticle.title);
					}
				}
				
			}
			else if(cmd.equals("article write")) {
				articleNumber++;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, articleNumber);
				articles.add(article);
				System.out.printf("%d번 글이 생성되었습니다.\n", articleNumber);
				
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		
		System.out.println("== 프로그램 끝 ==");
		sc.close();

	}

}
