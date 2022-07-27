package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class ArticleController extends Controller {
	private Scanner sc;
	private String cmd;
	private String actionMethodName;
	public static ArrayList<Article> articles;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		
		articles = Container.articleDao.articles;
	}
	
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch(actionMethodName) {
		case "list":
			showList();
			break;
		case "write":
			doWrite();
			break;
		case "detail":
			showArticle();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			break;
		}
	}

	private void showList() {
		if(articles.size() == 0) {					
			System.out.println("�Խñ��� �����ϴ�.");
			return;
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
				return;
			}
			
		}
		
		System.out.println("��ȣ |    ����    |    �ۼ���    |  ��ȸ��  |  �ۼ���");
		for(int i=forPrintArticles.size()-1; i>=0; i--) {
			Article thisArticle = forPrintArticles.get(i);
			String[] articleDateTime = thisArticle.regDate.split(" ");
			String articleDate = articleDateTime[0];
			ArrayList<Member> members = Container.memberDao.members;
			
			String thisArticleWriter = null;
			for(Member member : members) {
				if (thisArticle.memberId == member.id) {
					thisArticleWriter = member.name;
					break;
				}
			}
			System.out.printf("%2d  | %6s   | %6s | %4d   | %4s\n", thisArticle.id, thisArticle.title, articleDate, thisArticle.hit, thisArticleWriter);

		}
		
	}

	private void doWrite() {
		String regDate = Util.getNowDateStr();
		
		int id = Container.articleDao.setNewId();
		System.out.printf("���� : ");
		String title = sc.nextLine();
		System.out.printf("���� : ");
		String body = sc.nextLine();
		
		Article article = new Article(title, body, id, regDate, loginedMember.id);
		Container.articleDao.add(article);
		
		System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);
	}

	private void showArticle() {
		String[] cmdArr = cmd.split(" ");
		
		if(cmdArr.length == 2) {
			System.out.println("��ɾ Ȯ�����ּ���.");
			return;
		}
		
		int id = Integer.parseInt(cmdArr[2]);
		
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", id);
			
		} else {
			foundArticle.increaseHit();
			ArrayList<Member> members = Container.memberDao.members;
			String thisArticleWriter = null;

			for(Member member : members) {
				if(member.id == foundArticle.memberId) {
					thisArticleWriter = member.name;
				}
			}
			
			System.out.printf("��ȣ : %d\n", foundArticle.id);
			System.out.printf("��¥ : %s\n", foundArticle.regDate);
			System.out.printf("�ۼ��� : %s\n", thisArticleWriter);
			System.out.printf("��ȸ�� : %d\n", foundArticle.hit);
			System.out.printf("���� : %s\n", foundArticle.title);
			System.out.printf("���� : %s\n", foundArticle.body);
		}
	}

	private void doDelete() {
		String[] cmdArr = cmd.split(" ");
		
		if(cmdArr.length == 2) {
			System.out.println("��ɾ Ȯ�����ּ���.");
			return;
		}
		
		int id = Integer.parseInt(cmdArr[2]);
		
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", id);
			return;
		}
		
		if(foundArticle.memberId != loginedMember.id) {				
			System.out.println("������ �ۼ��� �۸� ���� �����մϴ�.");
			return;
		}
		
		articles.remove(foundArticle);
		System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n", id);
			
		
	}

	private void doModify() {
		String[] cmdArr = cmd.split(" ");
		
		if(cmdArr.length == 2) {
			System.out.println("��ɾ Ȯ�����ּ���.");
			return;
		}
		
		int id = Integer.parseInt(cmdArr[2]);
		
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", id);
			return;
			
		} 
			
		if(foundArticle.memberId != loginedMember.id) {				
			System.out.println("������ �ۼ��� �۸� ���� �����մϴ�.");
			return;
			
		}
		
		System.out.println("�����Ͻ� ����� ������ �ۼ����ּ���.");
		System.out.printf("���� : ");
		String newTitle = sc.nextLine();
		System.out.printf("���� : ");
		String newBody = sc.nextLine();
		
		foundArticle.title = newTitle;
		foundArticle.body = newBody;
		
		System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n", id);
		
	}		
	
	
	private int getArticleIndexById(int id) {
		
		int i=0;
		
		for(Article article : articles) {
			
			if(article.id == id) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	private Article getArticleById(int id) {
		
		int index = getArticleIndexById(id);
		
		if(index != -1) {
			return articles.get(index);
		}
		
		return null;
	}

	public void makeTestData() {
		String regDate = Util.getNowDateStr();
		
		Container.articleDao.add(new Article("test1", "test1", Container.articleDao.setNewId(), regDate, 11, 1));
		Container.articleDao.add(new Article("test2", "test2", Container.articleDao.setNewId(), regDate, 22, 2));
		Container.articleDao.add(new Article("test3", "test3", Container.articleDao.setNewId(), regDate, 33, 3));			
	
		System.out.println("�׽�Ʈ�� �Խñ� �����͸� �����߽��ϴ�.");
	}

}
