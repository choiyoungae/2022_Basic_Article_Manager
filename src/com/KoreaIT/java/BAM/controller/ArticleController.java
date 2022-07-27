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
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	private void showList() {
		if(articles.size() == 0) {					
			System.out.println("게시글이 없습니다.");
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

			System.out.printf("검색 키워드 : %s\n", searchKeyword);
			
			if(forPrintArticles.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
				return;
			}
			
		}
		
		System.out.println("번호 |    제목    |    작성일    |  조회수  |  작성자");
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
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		Article article = new Article(title, body, id, regDate, loginedMember.id);
		Container.articleDao.add(article);
		
		System.out.printf("%d번 글이 생성되었습니다.\n", id);
	}

	private void showArticle() {
		String[] cmdArr = cmd.split(" ");
		
		if(cmdArr.length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdArr[2]);
		
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			
		} else {
			foundArticle.increaseHit();
			ArrayList<Member> members = Container.memberDao.members;
			String thisArticleWriter = null;

			for(Member member : members) {
				if(member.id == foundArticle.memberId) {
					thisArticleWriter = member.name;
				}
			}
			
			System.out.printf("번호 : %d\n", foundArticle.id);
			System.out.printf("날짜 : %s\n", foundArticle.regDate);
			System.out.printf("작성자 : %s\n", thisArticleWriter);
			System.out.printf("조회수 : %d\n", foundArticle.hit);
			System.out.printf("제목 : %s\n", foundArticle.title);
			System.out.printf("내용 : %s\n", foundArticle.body);
		}
	}

	private void doDelete() {
		String[] cmdArr = cmd.split(" ");
		
		if(cmdArr.length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdArr[2]);
		
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}
		
		if(foundArticle.memberId != loginedMember.id) {				
			System.out.println("본인이 작성한 글만 변경 가능합니다.");
			return;
		}
		
		articles.remove(foundArticle);
		System.out.printf("%d번 게시글이 삭제되었습니다.\n", id);
			
		
	}

	private void doModify() {
		String[] cmdArr = cmd.split(" ");
		
		if(cmdArr.length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdArr[2]);
		
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
			
		} 
			
		if(foundArticle.memberId != loginedMember.id) {				
			System.out.println("본인이 작성한 글만 변경 가능합니다.");
			return;
			
		}
		
		System.out.println("수정하실 제목과 내용을 작성해주세요.");
		System.out.printf("제목 : ");
		String newTitle = sc.nextLine();
		System.out.printf("내용 : ");
		String newBody = sc.nextLine();
		
		foundArticle.title = newTitle;
		foundArticle.body = newBody;
		
		System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
		
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
	
		System.out.println("테스트용 게시글 데이터를 생성했습니다.");
	}

}
