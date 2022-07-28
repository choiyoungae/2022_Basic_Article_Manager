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
			System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			break;
		}
	}

	private void doJoin() {
		System.out.println("ȸ�������� �����ϰڽ��ϴ�.");
		
		int id = memberService.setNewId();
		String regDate = Util.getNowDateStr();
		
		String loginId = null;

		while(true) {
			System.out.printf("���̵� : ");
			loginId = sc.nextLine();
			
			if(memberService.isJoinableLoginId(loginId) == false) {
				System.out.println("�̹� ������� ���̵��Դϴ�. �ٽ� �������ּ���.");
				continue;
			}
			
			break;
		}
		
		String loginPw = null;
		String loginPwConfrim = null;
		
		while(true) {					
			System.out.printf("��й�ȣ : ");
			loginPw = sc.nextLine();
			System.out.printf("��й�ȣ Ȯ�� : ");
			loginPwConfrim = sc.nextLine();
			
			if(!loginPwConfrim.equals(loginPw)) {
				System.out.println("��й�ȣ Ȯ�� �����Դϴ�. �ٽ� �������ּ���.");
				continue;
			}
			
			break;
		}
		
		System.out.printf("�̸� : ");
		String name = sc.nextLine();
		
		Member member = new Member(loginId, loginPw, name, id, regDate);
		memberService.add(member);
		System.out.printf("ȸ�������� �Ϸ�Ǿ����ϴ�. %s�� �ݰ����ϴ�.\n", member.name);
	}
	
	private void doLogin() {
		
		System.out.println("�α����� �����ϰڽ��ϴ�.");
		
		while(true) {
			String loginId = null;
			String loginPw = null;
			
			while(true) {
				System.out.printf("���̵� : ");
				loginId = sc.nextLine();
				
				if(loginId.trim().length() == 0) {
					System.out.println("���̵� �Էµ��� �ʾҽ��ϴ�.");
					continue;
				}
				break;
			}
			
			while(true) {
				System.out.printf("��й�ȣ : ");
				loginPw = sc.nextLine();
				
				
				if(loginPw.trim().length() == 0) {
					System.out.println("��й�ȣ�� �Էµ��� �ʾҽ��ϴ�.");
					continue;
				}
				break;
			}
			
			Member member = memberService.getMemberByLoginId(loginId);
			
			if(member == null) {
				System.out.println("�Է��Ͻ� ���̵� �������� �ʽ��ϴ�.");
				return;
			}
			
			if(loginPw.equals(member.loginPw)) {
				loginedMember = member;
				System.out.printf("%s��, �ݰ����ϴ�.\n", loginedMember.name);
				break;
				
			} else {
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				return;
			}
			
		}
		
	}
	
	private void doLogout() {
		
		loginedMember = null;
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
	}

	private void showProfile() {
		if(isLogined() == false) {
			System.out.println("�α׾ƿ� �����Դϴ�.");
			return;
		}
		
		System.out.println("== ���� �α��� �� ȸ�� ���� ==");
		System.out.printf("�α��� ���̵� : %s\n", loginedMember.loginId);
		System.out.printf("�̸� : %s\n", loginedMember.name);
		
	}
	
	public void makeTestData() {
		String regDate = Util.getNowDateStr();
		
		memberService.add(new Member("test1", "test1", "������1", memberService.setNewId(), regDate));
		memberService.add(new Member("test2", "test2", "������2", memberService.setNewId(), regDate));
		memberService.add(new Member("test3", "test3", "������3", memberService.setNewId(), regDate));			
	
		System.out.println("�׽�Ʈ�� ȸ�� �����͸� �����߽��ϴ�.");
	}
}
