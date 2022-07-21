package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.util.Util;

public class ArticleController extends Controller {
	private Scanner sc;
	private ArrayList<Article> articles;
	
	public ArticleController(Scanner sc, ArrayList<Article> articles) {
		this.sc = sc;
		this.articles = articles;
	}
	
	public void doAction(String cmd) {
		
	}

	public void showArticleList(String cmd) {
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
		
		System.out.println("번호 |    제목    |    작성일    |  조회수");
		for(int i=forPrintArticles.size()-1; i>=0; i--) {
			Article thisArticle = forPrintArticles.get(i);
			String[] articleDateTime = thisArticle.regDate.split(" ");
			String articleDate = articleDateTime[0];
			System.out.printf("%2d  | %6s   | %6s | %3d\n", thisArticle.id, thisArticle.title, articleDate, thisArticle.hit);

		}
		
	}

	public void writeArticle() {
		String regDate = Util.getNowDateStr();
		
		int id = articles.size() + 1;
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		Article article = new Article(title, body, id, regDate);
		articles.add(article);
		
		System.out.printf("%d번 글이 생성되었습니다.\n", id);
	}

	public void showArticle(String cmd) {
		String[] cmdArr = cmd.split(" ");
		
		int id = Integer.parseInt(cmdArr[2]);
		
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			
		} else {
			foundArticle.increaseHit();
			
			System.out.printf("번호 : %d\n", foundArticle.id);
			System.out.printf("날짜 : %s\n", foundArticle.regDate);
			System.out.printf("조회수 : %d\n", foundArticle.hit);
			System.out.printf("제목 : %s\n", foundArticle.title);
			System.out.printf("내용 : %s\n", foundArticle.body);
		}
	}

	public void deleteArticle(String cmd) {
		String[] cmdArr = cmd.split(" ");
		
		int id = Integer.parseInt(cmdArr[2]);
		
		int foundArticle = getArticleIndexById(id);
		
		if(foundArticle == -1) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			
		} else {
			articles.remove(foundArticle);
			System.out.printf("%d번 게시글이 삭제되었습니다.\n", id);
			
		}
	}

	public void modifyArticle(String cmd) {
		String[] cmdArr = cmd.split(" ");
		
		int id = Integer.parseInt(cmdArr[2]);
		
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			
		} else {
			System.out.println("수정하실 제목과 내용을 작성해주세요.");
			System.out.printf("제목 : ");
			String newTitle = sc.nextLine();
			System.out.printf("내용 : ");
			String newBody = sc.nextLine();
			
			foundArticle.title = newTitle;
			foundArticle.body = newBody;
			
			System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
								
		}		
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

}
