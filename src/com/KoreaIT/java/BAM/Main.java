package com.KoreaIT.java.BAM;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static ArrayList<Article> articles;
	
	static {
		articles = new ArrayList<>();
	}

	public static void main(String[] args) {
		
		System.out.println("== ���α׷� ���� ==");

		Scanner sc = new Scanner(System.in);
		
		makeTestData();
		int articleNumber = articles.size();

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
					System.out.println("��ȣ |    �ۼ���    |    ����    |  ��ȸ��");
					for(int i=articles.size()-1; i>=0; i--) {
						Article thisArticle = articles.get(i);
						String[] articleDateTime = thisArticle.regDate.split(" ");
						String articleDate = articleDateTime[0];
						System.out.printf("%2d  | %6s | %6s   | %3d\n", thisArticle.articleNumber, articleDate, thisArticle.title, thisArticle.hit);
					}
				}
				
			}
			else if(cmd.equals("article write")) {
				
//				LocalDateTime now = LocalDateTime.now();
				String regDate = Util.getNowDateStr();
				
				articleNumber++;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, articleNumber, regDate);
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
					foundArticle.increaseHit();
					
					System.out.printf("��ȣ : %d\n", foundArticle.articleNumber);
					System.out.printf("��¥ : %s\n", foundArticle.regDate);
					System.out.printf("��ȸ�� : %d\n", foundArticle.hit);
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
			else if(cmd.startsWith("article modify ")) {
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
					System.out.println("�����Ͻ� ����� ������ �ۼ����ּ���.");
					System.out.printf("���� : ");
					String newTitle = sc.nextLine();
					System.out.printf("���� : ");
					String newBody = sc.nextLine();
					
					foundArticle.title = newTitle;
					foundArticle.body = newBody;
					
					System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.", articleNum);
					
//					���� �̷��� �� ��ü�� ���� ������ �ʿ� ����! �޸� ���� �߱�� �� ����
//					int thisArticleNumber = foundArticle.articleNumber;
//					LocalDateTime thisArticleNow = foundArticle.now;
//					
//					Article newArticle = new Article(newTitle, newBody, thisArticleNumber, thisArticleNow);
//					
//					articles.set(articleIdx, newArticle);
					
				}
				
			}
			else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			}
		}
		
		System.out.println("== ���α׷� �� ==");
		sc.close();

	}
	
	// �׽�Ʈ ������ �����
	private static void makeTestData() {
		
		String regDate = Util.getNowDateStr();
				
		articles.add(new Article("test1", "test1", 1, regDate, 11));
		articles.add(new Article("test2", "test2", 2, regDate, 22));
		articles.add(new Article("test3", "test3", 3, regDate, 33));			
	
		System.out.println("�׽�Ʈ�� �����͸� �����߽��ϴ�.");

	}
			

}
