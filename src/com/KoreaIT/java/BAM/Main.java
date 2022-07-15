package com.KoreaIT.java.BAM;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("== ���α׷� ���� ==");

		Scanner sc = new Scanner(System.in);
		ArrayList<Article> articles = new ArrayList<>();
		
		int articleNumber = 0;

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

			if(cmd.equals("article list")) {
				if(articles.size() == 0) {					
					System.out.println("�Խñ��� �����ϴ�.");
				} else {
					System.out.println("��ȣ | ����");
					for(int i=articles.size()-1; i>=0; i--) {
						Article thisArticle = articles.get(i);
						System.out.printf("%d   | %s\n", thisArticle.articleNumber, thisArticle.title);
					}
				}
				
			}
			else if(cmd.equals("article write")) {
				
				LocalDateTime now = LocalDateTime.now();
				
				articleNumber++;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, articleNumber, now);
				articles.add(article);
				
				System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", articleNumber);
				
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
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", articleNum);
					
				} else {
					System.out.printf("��ȣ : %d\n", foundArticle.articleNumber);
					System.out.printf("��¥ : %s\n", foundArticle.formatedNow);
					System.out.printf("���� : %s\n", foundArticle.title);
					System.out.printf("���� : %s\n", foundArticle.body);
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
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", articleNum);
					
				} else {
					articles.remove(foundArticle);
					System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n", articleNum);
					
				}
			}
			else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			}
		}
		
		System.out.println("== ���α׷� �� ==");
		sc.close();

	}

}
