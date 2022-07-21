package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private Scanner sc;
	private ArrayList<Member> members;
	private String cmd;
	private String actionMethodName;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		
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
		System.out.println("로그인을 진행하겠습니다.");
		
		while(true) {			
			System.out.printf("아이디 : ");
			String loginedId = sc.nextLine();
			System.out.printf("비밀번호 : ");
			String loginedPw = sc.nextLine();
			
			boolean isLoginSuccess = false;
			
			for(Member member : members) {
				if(member.loginId.equals(loginedId)) {
					if(member.loginPw.equals(loginedPw)) {
						System.out.printf("%s님 반갑습니다.\n", member.name);
						isLoginSuccess = true;
						break;
					}
				}
			}
			
			if(isLoginSuccess == false) {
				System.out.println("아이디 혹은 비밀번호가 맞지 않습니다. 다시 시도하십시오.");
			} else {
				break;
			}
		}
		
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
}
