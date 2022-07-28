package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.service.MemberService;
import com.KoreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private Scanner sc;
	private String cmd;
	private String actionMethodName;
	private MemberService memberService;
	
	public Member getLoginedMember() {
		return loginedMember;
	}
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		
		memberService = Container.memberService;
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
		
		int id = memberService.setNewId();
		String regDate = Util.getNowDateStr();
		
		String loginId = null;

		while(true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine();
			
			if(memberService.isJoinableLoginId(loginId) == false) {
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
		memberService.add(member);
		System.out.printf("회원가입이 완료되었습니다. %s님 반갑습니다.\n", member.name);
	}
	
	private void doLogin() {
		
		System.out.println("로그인을 진행하겠습니다.");
		
		while(true) {
			String loginId = null;
			String loginPw = null;
			
			while(true) {
				System.out.printf("아이디 : ");
				loginId = sc.nextLine();
				
				if(loginId.trim().length() == 0) {
					System.out.println("아이디가 입력되지 않았습니다.");
					continue;
				}
				break;
			}
			
			while(true) {
				System.out.printf("비밀번호 : ");
				loginPw = sc.nextLine();
				
				
				if(loginPw.trim().length() == 0) {
					System.out.println("비밀번호가 입력되지 않았습니다.");
					continue;
				}
				break;
			}
			
			Member member = memberService.getMemberByLoginId(loginId);
			
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
	
	public void makeTestData() {
		String regDate = Util.getNowDateStr();
		
		memberService.add(new Member("test1", "test1", "관리자1", memberService.setNewId(), regDate));
		memberService.add(new Member("test2", "test2", "관리자2", memberService.setNewId(), regDate));
		memberService.add(new Member("test3", "test3", "관리자3", memberService.setNewId(), regDate));			
	
		System.out.println("테스트용 회원 데이터를 생성했습니다.");
	}
}
