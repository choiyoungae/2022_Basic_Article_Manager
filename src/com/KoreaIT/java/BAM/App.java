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
		
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		makeTestData();

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

			if(cmd.startsWith("article list")) {
				if(articles.size() == 0) {					
					System.out.println("게시글이 없습니다.");
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

					System.out.printf("검색 키워드 : %s\n", searchKeyword);
					
					if(forPrintArticles.size() == 0) {
						System.out.println("검색 결과가 없습니다.");
						continue;
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
			else if(cmd.equals("article write")) {
				
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
			else if(cmd.startsWith("article detail ")) {
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
			else if(cmd.startsWith("article delete ")) {
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
			else if(cmd.startsWith("article modify ")) {
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
			else if(cmd.equals("member join")) {
				System.out.println("회원가입을 진행하겠습니다.");
				
				int id = members.size() + 1;
				String regDate = Util.getNowDateStr();
				
				String loginId = null;

				while(true) {
					System.out.printf("아이디 : ");
					loginId = sc.nextLine();
					
					if(isJoinableLoginId(loginId) == false) {
						System.out.println("이미 사용중인 아이디입니다. 다시 진행해주세요.");
						continue;
					}
					
					break;
				}
				
//				System.out.printf("아이디 : ");
//				String loginId = sc.nextLine();
//				
//				boolean isItAlreadyExist = false;
//
//				for(Member member : members) {
//					
//					if(loginId.equals(member.loginId)) {
//						System.out.println("이미 사용중인 아이디입니다. 다시 진행해주세요.");
//						isItAlreadyExist = true;
//						break;
//					}					
//				}
//				
//				if(isItAlreadyExist == true) {
//					continue;
//				}
				
				String loginPw = null;
				String loginPwConfrim = null;
				
				while(true) {					
					System.out.printf("비밀번호 : ");
					loginPw = sc.nextLine();
					System.out.printf("비밀번호 확인 : ");
					loginPwConfrim = sc.nextLine();
					
					if(!loginPwConfrim.equals(loginPw)) {
						System.out.println("비밀번호 확인 실패입니다. 다시 진행해주세요.");
						continue;
					}
					
					break;
				}
				
				System.out.printf("이름 : ");
				String name = sc.nextLine();
				
				Member member = new Member(loginId, loginPw, name, id, regDate);
				members.add(member);
				System.out.printf("회원가입이 완료되었습니다. %s님 반갑습니다.\n", member.name);
				
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		
		System.out.println("== 프로그램 끝 ==");
		sc.close();
	}
	
	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return true;
		}
		
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		
		for(Member member : members) {
			if(loginId.equals(member.loginId)) {
				return member.id;
			}
		}
		
		return -1;
	}
	
	private void makeTestData() {
		
		String regDate = Util.getNowDateStr();
				
		articles.add(new Article("test1", "test1", 1, regDate, 11));
		articles.add(new Article("test2", "test2", 2, regDate, 22));
		articles.add(new Article("test3", "test3", 3, regDate, 33));			
	
		System.out.println("테스트용 데이터를 생성했습니다.");

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
