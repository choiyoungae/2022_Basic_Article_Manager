package com.KoreaIT.java.BAM;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static ArrayList<Article> articles;
	
	static {
		articles = new ArrayList<>();
	}

	public static void main(String[] args) {
		
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		makeTestData();
		int articleNumber = articles.size();

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
					System.out.println("번호 |    작성일    |    제목    |  조회수");
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
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, articleNumber, regDate);
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
					foundArticle.increaseHit();
					
					System.out.printf("번호 : %d\n", foundArticle.articleNumber);
					System.out.printf("날짜 : %s\n", foundArticle.regDate);
					System.out.printf("조회수 : %d\n", foundArticle.hit);
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
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", articleNum);
					
				} else {
					System.out.println("수정하실 제목과 내용을 작성해주세요.");
					System.out.printf("제목 : ");
					String newTitle = sc.nextLine();
					System.out.printf("내용 : ");
					String newBody = sc.nextLine();
					
					foundArticle.title = newTitle;
					foundArticle.body = newBody;
					
					System.out.printf("%d번 게시글이 수정되었습니다.", articleNum);
					
//					굳이 이렇게 새 객체를 만들어서 변경할 필요 없음! 메모리 낭비가 야기될 수 있음
//					int thisArticleNumber = foundArticle.articleNumber;
//					LocalDateTime thisArticleNow = foundArticle.now;
//					
//					Article newArticle = new Article(newTitle, newBody, thisArticleNumber, thisArticleNow);
//					
//					articles.set(articleIdx, newArticle);
					
				}
				
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		
		System.out.println("== 프로그램 끝 ==");
		sc.close();

	}
	
	// 테스트 데이터 만들기
	private static void makeTestData() {
		
		String regDate = Util.getNowDateStr();
				
		articles.add(new Article("test1", "test1", 1, regDate, 11));
		articles.add(new Article("test2", "test2", 2, regDate, 22));
		articles.add(new Article("test3", "test3", 3, regDate, 33));			
	
		System.out.println("테스트용 데이터를 생성했습니다.");

	}
			

}
