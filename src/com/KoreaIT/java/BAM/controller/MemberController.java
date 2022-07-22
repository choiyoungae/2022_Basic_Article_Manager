package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private Scanner sc;
	private ArrayList<Member> members;
	private String cmd;
	private String actionMethodName;
	private Member loginedMember;

	public Member getLoginedMember() {
		return loginedMember;
	}
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		this.loginedMember = null;
		
		members = new ArrayList<Member>();
	}

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch(actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "profile":
			showProfile();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	private void doJoin() {
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
	
	private void doLogin() {
		
		if(isLogined() == true) {
			System.out.println("이미 로그인되어있습니다.");
			return;
		}
		
		System.out.println("로그인을 진행하겠습니다.");
		
		while(true) {			
			System.out.printf("아이디 : ");
			String loginId = sc.nextLine();
			System.out.printf("비밀번호 : ");
			String loginPw = sc.nextLine();
			
			Member member = getMemberByLoginId(loginId);
			
			if(member == null) {
				System.out.println("입력하신 아이디가 존재하지 않습니다.");
				return;
			}
			
			if(loginPw.equals(member.loginPw)) {
				loginedMember = member;
				System.out.printf("%s님, 반갑습니다.\n", loginedMember.name);
				break;
				
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
				return;
			}
			
		}
		
	}
	
	private void doLogout() {
		if(isLogined() == false) {
			System.out.println("이미 로그아웃 상태입니다.");
			return;
		}
		
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

	private void showProfile() {
		if(isLogined() == false) {
			System.out.println("로그아웃 상태입니다.");
			return;
		}
		
		System.out.println("== 현재 로그인 한 회원 정보 ==");
		System.out.printf("로그인 아이디 : %s\n", loginedMember.loginId);
		System.out.printf("이름 : %s\n", loginedMember.name);
		
	}
	
	private boolean isLogined() {
		return loginedMember != null;
	}
	
	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return null;
		}
		
		return members.get(index);
	}
	
	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return true;
		}
		
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		
		int i=0;
		
		for(Member member : members) {
			if(loginId.equals(member.loginId)) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	public void makeTestData() {
		String regDate = Util.getNowDateStr();
		
		members.add(new Member("test1", "test1", "test1", 1, regDate));
		members.add(new Member("test2", "test2", "test2", 2, regDate));
		members.add(new Member("test3", "test3", "test3", 3, regDate));			
	
		System.out.println("테스트용 회원 데이터를 생성했습니다.");
	}
}
