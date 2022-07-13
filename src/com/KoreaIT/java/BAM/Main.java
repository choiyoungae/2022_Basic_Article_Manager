package com.KoreaIT.java.BAM;

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
				if(articleNumber == 0) {					
					System.out.println("�Խñ��� �����ϴ�.");
				} else {
					System.out.println("��ȣ / ����");
					for(int i=articleNumber-1; i>=0; i--) {
						Article thisArticle = articles.get(i);
						System.out.printf("%s   / %s\n", thisArticle.articleNumber, thisArticle.title);
					}
				}
				
			}
			else if(cmd.equals("article write")) {
				articleNumber++;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, articleNumber);
				articles.add(article);
				System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", articleNumber);
				
			}
			else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			}
		}
		
		System.out.println("== ���α׷� �� ==");
		sc.close();

	}

}
