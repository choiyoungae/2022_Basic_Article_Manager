package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.Scanner;

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
		
		System.out.println("== ���α׷� ���� ==");

		Scanner sc = new Scanner(System.in);
		
		makeTestData();
		int id = articles.size();

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

			if(cmd.startsWith("article list")) {
				if(articles.size() == 0) {					
					System.out.println("�Խñ��� �����ϴ�.");
					continue;
				}
				
				String searchKeyword = cmd.substring("article list".length()).trim();
				
				ArrayList<Article> forPrintArticles = articles;
				
				if(searchKeyword.length() > 0) {
					forPrintArticles = new ArrayList<>();
					
					for(Article article : articles) {
						if(article.title.contains(searchKeyword)) {
							forPrintArticles.add(article);
						}
					}

					System.out.printf("�˻� Ű���� : %s\n", searchKeyword);
					
					if(forPrintArticles.size() == 0) {
						System.out.println("�˻� ����� �����ϴ�.");
						continue;
					}
					
				}
				
				System.out.println("��ȣ |    ����    |    �ۼ���    |  ��ȸ��");
				for(int i=forPrintArticles.size()-1; i>=0; i--) {
					Article thisArticle = forPrintArticles.get(i);
					String[] articleDateTime = thisArticle.regDate.split(" ");
					String articleDate = articleDateTime[0];
					System.out.printf("%2d  | %6s   | %6s | %3d\n", thisArticle.id, thisArticle.title, articleDate, thisArticle.hit);

				}
				
			}
			else if(cmd.equals("article write")) {
				
				String regDate = Util.getNowDateStr();
				
				id++;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, id, regDate);
				articles.add(article);
				
				System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);
				
			}
			else if(cmd.startsWith("article detail ")) {
				String[] cmdArr = cmd.split(" ");
				
				int articleNum = Integer.parseInt(cmdArr[2]);
				
				Article foundArticle = getArticleByArticleNum(articleNum);
				
				if(foundArticle == null) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", articleNum);
					
				} else {
					foundArticle.increaseHit();
					
					System.out.printf("��ȣ : %d\n", foundArticle.id);
					System.out.printf("��¥ : %s\n", foundArticle.regDate);
					System.out.printf("��ȸ�� : %d\n", foundArticle.hit);
					System.out.printf("���� : %s\n", foundArticle.title);
					System.out.printf("���� : %s\n", foundArticle.body);
				}
				
			}
			else if(cmd.startsWith("article delete ")) {
				String[] cmdArr = cmd.split(" ");
				
				int articleNum = Integer.parseInt(cmdArr[2]);
				
				int foundArticle = getArticleIndexByArticleNum(articleNum);
				
				if(foundArticle == -1) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", articleNum);
					
				} else {
					articles.remove(foundArticle);
					System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n", articleNum);
					
				}
			}
			else if(cmd.startsWith("article modify ")) {
				String[] cmdArr = cmd.split(" ");
				
				int articleNum = Integer.parseInt(cmdArr[2]);
				
				Article foundArticle = getArticleByArticleNum(articleNum);
				
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
					
					System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n", articleNum);
										
				}
				
			}
			else if(cmd.equals("member join")) {
				System.out.println("ȸ�������� �����ϰڽ��ϴ�.");
				System.out.printf("���̵� : ");
				String loginId = sc.nextLine();
				System.out.printf("��й�ȣ : ");
				String loginPw = sc.nextLine();
				System.out.printf("��й�ȣ Ȯ�� : ");
				String loginPwConfrim = sc.nextLine();
				
				if(!loginPwConfrim.equals(loginPw)) {
					System.out.println("��й�ȣ Ȯ�� �����Դϴ�. �ٽ� �������ּ���.");
					continue;
				}
				
				System.out.printf("�̸� : ");
				String name = sc.nextLine();
				
				Member member = new Member(loginId, loginPw, name);
				members.add(member);
				System.out.printf("ȸ�������� �Ϸ�Ǿ����ϴ�. %s�� �ݰ����ϴ�.\n", member.name);
				
			}
			else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			}
		}
		
		System.out.println("== ���α׷� �� ==");
		sc.close();
	}
	
	// �׽�Ʈ ������ �����
	private void makeTestData() {
		
		String regDate = Util.getNowDateStr();
				
		articles.add(new Article("test1", "test1", 1, regDate, 11));
		articles.add(new Article("test2", "test2", 2, regDate, 22));
		articles.add(new Article("test3", "test3", 3, regDate, 33));			
	
		System.out.println("�׽�Ʈ�� �����͸� �����߽��ϴ�.");

	}
	
	private int getArticleIndexByArticleNum(int id) {
		
		int i=0;
		
		for(Article article : articles) {
			
			if(article.id == id) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	private Article getArticleByArticleNum(int articleNum) {
		
		int index = getArticleIndexByArticleNum(articleNum);
		
		if(index != -1) {
			return articles.get(index);
		}
		
		return null;
	}
	
}
